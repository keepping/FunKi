package com.hifunki.funki.module.home.user;


import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.media.MediaMetadataRetriever;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Process;
import android.support.annotation.NonNull;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.util.FileUtils;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by MT3020 on 2016/3/8.
 */
public class RecodeMovieActivity extends BaseActivity {

    // 最长录制时间
    private final long maxRecodeTime = 12000;
    // 最短录制时间
//    private final long minRecodeTime = 5000;
    long recodeStartTime;

    MediaRecorder mMediaRecorder;

    Camera mCamera;

    File mRecodeFile;

    boolean surfaceCreated = false;
    CountDownTimer preTimer;
    CountDownTimer recodeTimer;


    private enum STATUS {
        unInit,
        recoding,
        uploadWait,
        uploading,
    }

    private STATUS status = STATUS.unInit;

    SurfaceHolder mSurfaceRecode;


    // preView
    @BindView(R.id.preview_play)
    ImageView preview_play;
    @BindView(R.id.surface_play_image)
    ImageView preview_content;
    @BindView(R.id.upload_cancel)
    TextView uploadCancel;
    @BindView(R.id.upload_progress)
    TextView uploadProgress;

    // recode
    @BindView(R.id.surface_preview)
    SurfaceView mSurfacePreview;
    @BindView(R.id.count_down)
    TextView count_down;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    //common

    @BindView(R.id.recode_start)
    TextView recodeStart;
    @BindView(R.id.recode_cancel)
    TextView recodeCancel;

    @BindView(R.id.recode_upload)
    TextView recodeUpload;
    @BindView(R.id.recode_recode)
    TextView recodeRecode;

    private SurfaceHolder.Callback recodeCallBack = new SurfaceHolder.Callback() {

        @Override
        public void surfaceCreated(SurfaceHolder holder) {              // 视频预浏览
            surfaceCreated = true;
            if (checkCameraAccess() && checkAudioAccess() && surfaceCreated && mCamera == null) {
                openCamera();
            } else {
                if (Build.VERSION.SDK_INT >= 23 && !checkCameraAccess()) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, 1);
                }
                if (Build.VERSION.SDK_INT >= 23 && !checkAudioAccess()) {
                    requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO}, 0);
                }
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            releaseCamera();
            surfaceCreated = false;
        }

    };

    @Override
    protected int getViewResId() {
        return R.layout.activity_movie_recorder_view;
    }

    @Override
    protected void initDatas() {
        mRecodeFile = new File(FileUtils.getRandomVideoPath(this));
    }

    @OnClick({
            R.id.recode_start,
            R.id.recode_cancel,
            R.id.recode_upload,
            R.id.recode_recode,
            R.id.upload_cancel,
    })
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.recode_start:

                if(status!=STATUS.unInit) {
                    break;
                }

                if (preTimer != null) {
                    preTimer.cancel();
                    preTimer = null;
                }

                preTimer = new CountDownTimer(3500, 500) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        long len =  millisUntilFinished / 1000;
                        updateUI();

                        count_down.setVisibility(View.VISIBLE);
                        count_down.setText(String.valueOf(len));
                    }

                    @Override
                    public void onFinish() {

                        if (recodeTimer != null) {
                            recodeTimer.cancel();
                            recodeTimer = null;
                        }

                        try {

                            startMovieRecord();
                            status = STATUS.recoding;
                            updateUI();

                            recodeTimer = new CountDownTimer(maxRecodeTime, 100) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                    float value = millisUntilFinished * 1f / maxRecodeTime;

                                    mProgressBar.setSecondaryProgress((100 - (int) (100 * value)) / 2);
                                    mProgressBar.setProgress((int) (100 * value)/2 + 50);
                                }

                                @Override
                                public void onFinish() {
                                    stopAndReleaseMovieRecord();
//                                    mProgressBar.setProgress(100);
//                                    mProgressBar.setSecondaryProgress(0);
                                    Bitmap bitmap =  getVideoThumbnail(mRecodeFile.getAbsolutePath());

                                    System.out.println("...............:::: "+ (bitmap==null));

                                    preview_content.setImageBitmap(bitmap);
                                    releaseCamera();

                                    status = STATUS.uploadWait;
                                    updateUI();
                                }
                            };
                            recodeTimer.start();


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };

                preTimer.start();


                break;
            case R.id.recode_cancel:

                stopAndReleaseMovieRecord();

                if (recodeTimer != null) {
                    recodeTimer.cancel();
                    recodeTimer = null;
                }
                if (preTimer != null) {
                    preTimer.cancel();
                    preTimer = null;
                }

                status = STATUS.unInit;
                updateUI();

                break;
            case R.id.recode_upload:
                status = STATUS.uploading;
                updateUI();

                break;
            case R.id.recode_recode:

                if (checkCameraAccess() && checkAudioAccess() && surfaceCreated && mCamera == null) {
                    openCamera();
                }
                stopAndReleaseMovieRecord();
                status = STATUS.unInit;
                updateUI();

                break;
            case R.id.upload_cancel:
                status = STATUS.uploadWait;
                updateUI();
                break;


        }


    }

    @Override
    protected void initView() {
        mProgressBar.setMax(100);
        mProgressBar.setProgress(100);
        mProgressBar.setSecondaryProgress(0);
        mSurfaceRecode = mSurfacePreview.getHolder();
        mSurfaceRecode.addCallback(recodeCallBack);
        updateUI();
    }

    public void onResume() {
        super.onResume();
        if (checkCameraAccess() && checkAudioAccess() && surfaceCreated && mCamera == null) {
            openCamera();
        } else {
            if (Build.VERSION.SDK_INT >= 23 && !checkCameraAccess()) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, 1);
            }
            if (Build.VERSION.SDK_INT >= 23 && !checkAudioAccess()) {
                requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO}, 0);
            }
        }

    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (checkCameraAccess() && checkAudioAccess() && surfaceCreated && mCamera == null) {
            openCamera();
        }
    }

    private void openCamera() {
        try {
            mCamera = Camera.open();
            Camera.Parameters params = mCamera.getParameters();
            params.set("orientation", "portrait");
            mCamera.setParameters(params);
            mCamera.setDisplayOrientation(90);
            mCamera.setPreviewDisplay(mSurfacePreview.getHolder());
            mCamera.startPreview();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopAndReleaseMovieRecord();
        releaseCamera();

    }

    private void updateUI() {
        preview_content.setVisibility(View.GONE);                        //浏览图
        preview_play.setVisibility(View.GONE);
        count_down.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.GONE);
        recodeStart.setVisibility(View.GONE);
        recodeCancel.setVisibility(View.GONE);
        recodeUpload.setVisibility(View.GONE);
        recodeRecode.setVisibility(View.GONE);
        ((View)uploadCancel.getParent()).setVisibility(View.GONE);

        switch (status) {
            case unInit:
                recodeStart.setVisibility(View.VISIBLE);
                break;
            case recoding:
                mProgressBar.setVisibility(View.VISIBLE);
                recodeCancel.setVisibility(View.VISIBLE);
                break;
            case uploadWait:
                preview_content.setVisibility(View.VISIBLE);
                preview_play.setVisibility(View.VISIBLE);
                recodeUpload.setVisibility(View.VISIBLE);
                recodeUpload.setEnabled(true);
                recodeRecode.setVisibility(View.VISIBLE);
                recodeRecode.setEnabled(true);

                break;
            case uploading:
                preview_content.setVisibility(View.VISIBLE);
                recodeUpload.setVisibility(View.VISIBLE);
                recodeUpload.setEnabled(false);
                recodeRecode.setVisibility(View.VISIBLE);
                recodeRecode.setEnabled(false);
                ((View)uploadCancel.getParent()).setVisibility(View.VISIBLE);
                break;
        }


    }



    // 检查相机权限
    private boolean checkCameraAccess() {
        return Build.VERSION.SDK_INT < 23 || PackageManager.PERMISSION_GRANTED == checkPermission(Manifest.permission.CAMERA, Process.myPid(), Process.myUid());
    }

    // 检查相机权限
    private boolean checkAudioAccess() {
        return Build.VERSION.SDK_INT < 23 || PackageManager.PERMISSION_GRANTED == checkPermission(Manifest.permission.RECORD_AUDIO, Process.myPid(), Process.myUid());
    }

    // 拍摄 初始化
    private boolean startMovieRecord() throws IOException {

        if (mCamera == null) return false;

        recodeStartTime = System.currentTimeMillis();

        if (mMediaRecorder != null) {
            stopAndReleaseMovieRecord();
        }

        mMediaRecorder = new MediaRecorder();

        // Step 1: Unlock and set camera to MediaRecorder
        mCamera.unlock();
        mMediaRecorder.setCamera(mCamera);

        // Step 2: Set sources

//         mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
        mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        mMediaRecorder.setOrientationHint(90);
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);


        // Step 3: Set a CamcorderProfile (requires API Level 8 or higher)

//         CamcorderProfile profile = CamcorderProfile.get(0,CamcorderProfile.QUALITY_CIF);
//         mMediaRecorder.setProfile(profile);
//         mMediaRecorder.setOrientationHint(90);

        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mMediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);


        // Step 4: Set output file
        mMediaRecorder.setOutputFile(mRecodeFile.getAbsolutePath());
//        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);

        // Step 5: Set the preview output
        mMediaRecorder.setPreviewDisplay(mSurfaceRecode.getSurface());

        mMediaRecorder.setOnInfoListener(new MediaRecorder.OnInfoListener() {
            @Override
            public void onInfo(MediaRecorder mr, int what, int extra) {
                System.out.println("setOnInfoListener");
            }
        });

        try {
            mMediaRecorder.prepare();
            mMediaRecorder.start();
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return false;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // 拍摄 终止
    private void stopAndReleaseMovieRecord() {

        if (mMediaRecorder != null) {
            try {
                mMediaRecorder.stop();
                mMediaRecorder.reset();
                mMediaRecorder.release();

                mCamera.lock();
                mMediaRecorder = null;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void releaseCamera() {
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.setPreviewCallbackWithBuffer(null);
            mCamera.release();
            mCamera = null;
        }
    }


    private Bitmap getVideoThumbnail(String filePath) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            retriever.setDataSource(filePath);
            bitmap = retriever.getFrameAtTime();
        }
        catch(IllegalArgumentException e) {
            e.printStackTrace();
        }
        catch (RuntimeException e) {
            e.printStackTrace();
        }
        finally {
            try {
                retriever.release();
            }
            catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }



}
