package com.hifunki.funki.module.photo.ucrop.task;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.hifunki.funki.module.photo.ucrop.callback.BitmapCropCallback;
import com.hifunki.funki.module.photo.ucrop.entity.CropParameters;
import com.hifunki.funki.module.photo.ucrop.entity.ExifInfo;
import com.hifunki.funki.module.photo.ucrop.entity.ImageState;
import com.hifunki.funki.module.photo.ucrop.util.BitmapLoadUtils;
import com.hifunki.funki.module.photo.ucrop.util.ImageHeaderParser;
import com.hifunki.funki.util.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.ref.WeakReference;

/**
 * Crops part of image that fills the crop bounds.
 * <p/>
 * First image is downscaled if max size was set and if resulting image is larger that max size.
 * Then image is rotated accordingly.
 * Finally new Bitmap object is created and saved to file.
 */
public class BitmapCropTask extends AsyncTask<Void, Void, Throwable> {


    private final WeakReference<Context> mContext;

    private Bitmap mViewBitmap;

    private final RectF mCropRect;
    private final RectF mCurrentImageRect;

    private float mCurrentScale, mCurrentAngle;
    private final int mMaxResultImageSizeX, mMaxResultImageSizeY;

    private final Bitmap.CompressFormat mCompressFormat;
    private final int mCompressQuality;
    private final String mImageInputPath, mImageOutputPath;
    private final ExifInfo mExifInfo;
    private final BitmapCropCallback mCropCallback;

    private int mCroppedImageWidth, mCroppedImageHeight;

    public BitmapCropTask(@NonNull Context context, @Nullable Bitmap viewBitmap, @NonNull ImageState imageState, @NonNull CropParameters cropParameters,
                          @Nullable BitmapCropCallback cropCallback) {

        mContext = new WeakReference<>(context);

        mViewBitmap = viewBitmap;
        mCropRect = imageState.getCropRect();
        mCurrentImageRect = imageState.getCurrentImageRect();

        mCurrentScale = imageState.getCurrentScale();
        mCurrentAngle = imageState.getCurrentAngle();
        mMaxResultImageSizeX = cropParameters.getMaxResultImageSizeX();
        mMaxResultImageSizeY = cropParameters.getMaxResultImageSizeY();

        mCompressFormat = cropParameters.getCompressFormat();
        mCompressQuality = cropParameters.getCompressQuality();

        mImageInputPath = cropParameters.getImageInputPath();
        mImageOutputPath = cropParameters.getImageOutputPath();
        mExifInfo = cropParameters.getExifInfo();

        mCropCallback = cropCallback;
    }

    @Override
    @Nullable
    protected Throwable doInBackground(Void... params) {
        if (mViewBitmap == null) {
            return new NullPointerException("ViewBitmap is null");
        } else if (mViewBitmap.isRecycled()) {
            return new NullPointerException("ViewBitmap is recycled");
        } else if (mCurrentImageRect.isEmpty()) {
            return new NullPointerException("CurrentImageRect is empty");
        }


        try {
            crop();
            mViewBitmap = null;
        } catch (Throwable throwable) {
            return throwable;
        }

        return null;
    }


    private boolean crop() throws IOException {
        // Downsize if needed
        if (mMaxResultImageSizeX > 0 && mMaxResultImageSizeY > 0) {
            float cropWidth = mCropRect.width() / mCurrentScale;
            float cropHeight = mCropRect.height() / mCurrentScale;

            if (cropWidth > mMaxResultImageSizeX || cropHeight > mMaxResultImageSizeY) {

                float scaleX = mMaxResultImageSizeX / cropWidth;
                float scaleY = mMaxResultImageSizeY / cropHeight;
                float resizeScale = Math.min(scaleX, scaleY);

                Bitmap resizedBitmap = Bitmap.createScaledBitmap(mViewBitmap,
                        Math.round(mViewBitmap.getWidth() * resizeScale),
                        Math.round(mViewBitmap.getHeight() * resizeScale), false);
                if (mViewBitmap != resizedBitmap) {
                    mViewBitmap.recycle();
                }
                mViewBitmap = resizedBitmap;

                mCurrentScale /= resizeScale;
            }
        }

        // Rotate if needed
        if (mCurrentAngle != 0) {
            Matrix tempMatrix = new Matrix();
            tempMatrix.setRotate(mCurrentAngle, mViewBitmap.getWidth() / 2, mViewBitmap.getHeight() / 2);

            Bitmap rotatedBitmap = Bitmap.createBitmap(mViewBitmap, 0, 0, mViewBitmap.getWidth(), mViewBitmap.getHeight(),
                    tempMatrix, true);
            if (mViewBitmap != rotatedBitmap) {
                mViewBitmap.recycle();
            }
            mViewBitmap = rotatedBitmap;
        }

        int top = Math.round((mCropRect.top - mCurrentImageRect.top) / mCurrentScale);
        int left = Math.round((mCropRect.left - mCurrentImageRect.left) / mCurrentScale);
        mCroppedImageWidth = Math.round(mCropRect.width() / mCurrentScale);
        mCroppedImageHeight = Math.round(mCropRect.height() / mCurrentScale);

        boolean shouldCrop = shouldCrop(mCroppedImageWidth, mCroppedImageHeight);

        if (shouldCrop) {
            ExifInterface originalExif = new ExifInterface(mImageInputPath);
            saveImage(Bitmap.createBitmap(mViewBitmap, left, top, mCroppedImageWidth, mCroppedImageHeight));
            if (mCompressFormat.equals(Bitmap.CompressFormat.JPEG)) {
                ImageHeaderParser.copyExif(originalExif, mCroppedImageWidth, mCroppedImageHeight, mImageOutputPath);
            }
            return true;
        } else {
            FileUtils.copyFiles(mImageInputPath, mImageOutputPath);
            return false;
        }
    }

    private void saveImage(@NonNull Bitmap croppedBitmap) throws FileNotFoundException {
        Context context = mContext.get();
        if (context == null) {
            return;
        }

        OutputStream outputStream = null;
        try {
            outputStream = context.getContentResolver().openOutputStream(Uri.fromFile(new File(mImageOutputPath)));
            croppedBitmap.compress(mCompressFormat, mCompressQuality, outputStream);
            croppedBitmap.recycle();
        } finally {
            BitmapLoadUtils.close(outputStream);
        }
    }

    /**
     * Check whether an image should be cropped at all or just file can be copied to the destination path.
     * For each 1000 pixels there is one pixel of error due to matrix calculations etc.
     *
     * @param width  - crop area width
     * @param height - crop area height
     * @return - true if image must be cropped, false - if original image fits requirements
     */
    private boolean shouldCrop(int width, int height) {
        int pixelError = 1;
        pixelError += Math.round(Math.max(width, height) / 1000f);
        return (mMaxResultImageSizeX > 0 && mMaxResultImageSizeY > 0)
                || Math.abs(mCropRect.left - mCurrentImageRect.left) > pixelError
                || Math.abs(mCropRect.top - mCurrentImageRect.top) > pixelError
                || Math.abs(mCropRect.bottom - mCurrentImageRect.bottom) > pixelError
                || Math.abs(mCropRect.right - mCurrentImageRect.right) > pixelError;
    }

    @Override
    protected void onPostExecute(@Nullable Throwable t) {
        if (mCropCallback != null) {
            if (t == null) {
                mCropCallback.onBitmapCropped(Uri.fromFile(new File(mImageOutputPath)), mCroppedImageWidth, mCroppedImageHeight);
            } else {
                mCropCallback.onCropFailure(t);
            }
        }
    }

}
