package com.hifunki.funki.module.photo.gallery.util;

import android.app.Activity;
import android.net.Uri;

import com.hifunki.funki.R;
import com.hifunki.funki.module.photo.ucrop.UCrop;

import java.io.File;

/**
 * UCropUtils
 * Created by Yancy on 2016/11/2.
 */
public class UCropUtils {

    public static void start(Activity mActivity, File sourceFile, File destinationFile, float aspectRatioX, float aspectRatioY, int maxWidth, int maxHeight) {
        UCrop uCrop = UCrop.of(Uri.fromFile(sourceFile), Uri.fromFile(destinationFile))
                .withAspectRatio(aspectRatioX, aspectRatioY)
                .withMaxResultSize(maxWidth, maxHeight);

        UCrop.Options options = new UCrop.Options();
        options.setToolbarColor(mActivity.getResources().getColor(R.color.gallery_blue));
        options.setStatusBarColor(mActivity.getResources().getColor(R.color.gallery_blue));
        uCrop.withOptions(options);


        uCrop.start(mActivity);
    }
}
