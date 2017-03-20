package com.hifunki.funki.module.home.user;


import android.Manifest;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Process;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


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
    private final long minRecodeTime = 5000;
    long recodeStartTime;
    SurfaceView mSurfaceView;
    ProgressBar mProgressBar;


    SurfaceHolder mSurfaceHolder;
    MediaRecorder mMediaRecorder;
    ValueAnimator valueAnimator;

    Camera mCamera;

    File mRecodeFile;

    boolean surfaceCreated = false;
    CountDownTimer preTimer;
    CountDownTimer recodeTimer;


    private enum STATUS{
        uninit,
        recoding,
        uploadWait,
        uploading,
    }

    private STATUS status = STATUS.uninit;

    @BindView(R.id.count_down)
    TextView count_down;

    @BindView(R.id.recode_start)
    TextView recodeStart;
    @BindView(R.id.recode_cancel)
    TextView recodeCancel;

    @BindView(R.id.recode_upload)
    TextView recodeUplaod;
    @BindView(R.id.recode_recode)
    TextView recodeRecode;

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
    })
    void onClick(View view){
        switch (view.getId()){
            case R.id.recode_start:

                preTimer = new CountDownTimer(3000,500) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        long len = 3 - millisUntilFinished/1000;
                        count_down.setText(String.valueOf(len));
                        updateUI();
                    }

                    @Override
                    public void onFinish() {
                        if(recodeTimer!=null){
                            recodeTimer.cancel();
                            recodeTimer = null;
                        }
                        try {
                            startMovieRecord();

                            recodeTimer = new CountDownTimer(maxRecodeTime,200) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                    float value = millisUntilFinished*1f/maxRecodeTime;
                                    mProgressBar.setSecondaryProgress(100 - (int)(100*value));
                                    mProgressBar.setProgress((int)(100*value));
                                }

                                @Override
                                public void onFinish() {
                                    stopAndReleaseMovieRecord();
                                    mProgressBar.setProgress(100);
                                    mProgressBar.setSecondaryProgress(0);
                                }
                            };



                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };


                break;
            case R.id.recode_cancel:



                break;
            case R.id.recode_upload:



                break;
            case R.id.recode_recode:


                break;
        }



    }


    private void updateUI(){
        switch (status){

            case uninit:
                recodeStart.setVisibility(View.VISIBLE);
                recodeCancel.setVisibility(View.GONE);
                recodeUplaod.setVisibility(View.GONE);
                recodeRecode.setVisibility(View.GONE);

                break;
            case recoding:
                recodeStart.setVisibility(View.GONE);
                recodeCancel.setVisibility(View.VISIBLE);
                recodeUplaod.setVisibility(View.GONE);
                recodeRecode.setVisibility(View.GONE);

                break;
            case uploadWait:

                recodeStart.setVisibility(View.GONE);
                recodeCancel.setVisibility(View.GONE);

                recodeUplaod.setVisibility(View.VISIBLE);
                recodeUplaod.setEnabled(true);
                recodeRecode.setVisibility(View.VISIBLE);
                recodeRecode.setEnabled(true);

                break;
            case uploading:
                recodeStart.setVisibility(View.GONE);
                recodeCancel.setVisibility(View.GONE);

                recodeUplaod.setVisibility(View.VISIBLE);
                recodeUplaod.setEnabled(false);
                recodeRecode.setVisibility(View.VISIBLE);
                recodeRecode.setEnabled(false);

                break;
        }





    }

    @Override
    protected void initView() {
        findView();
        initViewAction();
    }


    // Activity
    public void onResume(){
        super.onResume();
        if (checkCameraAccess() && checkAudioAccess() && surfaceCreated && mCamera==null) {
            openCamera();
        }else {
            if (Build.VERSION.SDK_INT >= 23 && !checkCameraAccess()) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, 1);
            }
            if (Build.VERSION.SDK_INT >= 23 && !checkAudioAccess()) {
                requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO}, 0);
            }
        }

    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        if (checkCameraAccess() &&checkAudioAccess() && surfaceCreated && mCamera==null) {
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
            mCamera.setPreviewDisplay(mSurfaceView.getHolder());
            mCamera.startPreview();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        stopAndReleaseMovieRecord();
        releaseCamera();

    }

    // 检查相机权限
    private boolean checkCameraAccess() {
        return Build.VERSION.SDK_INT < 23 || PackageManager.PERMISSION_GRANTED == checkPermission(Manifest.permission.CAMERA, Process.myPid(), Process.myUid());
    }

    // 检查相机权限
    private boolean checkAudioAccess() {
        return Build.VERSION.SDK_INT < 23 || PackageManager.PERMISSION_GRANTED == checkPermission(Manifest.permission.RECORD_AUDIO, Process.myPid(), Process.myUid());
    }


    private void initViewAction() {

        mProgressBar.setProgress(100);
        mProgressBar.setSecondaryProgress(0);

        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(customCallBack);



    }

    private void findView() {
        mSurfaceView = (SurfaceView) findViewById(R.id.surfaceview);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mProgressBar.setMax(100);


    }



    private SurfaceHolder.Callback customCallBack = new SurfaceHolder.Callback() {

        @Override
        public void surfaceCreated(SurfaceHolder holder) {              // 视频预浏览
            surfaceCreated = true;
            if (checkCameraAccess() && checkAudioAccess() && surfaceCreated && mCamera==null) {
                openCamera();
            }else {
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


    private void tryToFinish() {


        long recodeEndTime = System.currentTimeMillis();

        if (recodeEndTime - recodeStartTime > minRecodeTime) {
            Intent intent = new Intent();
            intent.setData(Uri.fromFile(mRecodeFile));
            this.setResult(RESULT_OK, intent);
            finish();
        } else {
            Toast.makeText(this, "拍摄时间过短哦 不能少于5秒", Toast.LENGTH_SHORT).show();
        }
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
        mMediaRecorder.setPreviewDisplay(mSurfaceHolder.getSurface());

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


}
