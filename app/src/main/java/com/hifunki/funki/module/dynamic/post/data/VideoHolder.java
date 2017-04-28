package com.hifunki.funki.module.dynamic.post.data;

import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;

import com.hifunki.funki.R;
import com.hifunki.funki.animation.TwoInterpolator;
import com.hifunki.funki.util.FileUtils;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by powyin on 2017/4/27.
 */

public class VideoHolder {

    public interface OnRecodeOver {
        void onStop(VideoHolder holder);
    }

    @BindView(R.id.video_progress)
    View progress;
    @BindView(R.id.video_progress_over)
    View over;
    @BindView(R.id.video_progress_notify)
    View notify;

    private Camera mCamera;
    private final File mVideoRecodeFile;
    private final ViewGroup mItemView;
    private MediaRecorder mMediaRecorder;
    private CountDownTimer countDownTimer;
    private OnRecodeOver mOnRecodeOver;
    private long mVideoTime;

    //---------------------------------------API-------------------------------------------//

    public VideoHolder(Context context, ViewGroup viewGroup, OnRecodeOver lisener) {
        mItemView = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.item_video_recoad, viewGroup, false);
        mVideoRecodeFile = FileUtils.getRandomFilePath(context, ".mp4", false);
        this.mOnRecodeOver = lisener;
        ButterKnife.bind(this, mItemView);
        progress.setVisibility(View.GONE);
        over.setVisibility(View.GONE);
        notify.setVisibility(View.VISIBLE);

        // 加入了一个闪烁
        ObjectAnimator animator = new ObjectAnimator();
        animator.setPropertyName("alpha");
        animator.setDuration(1500);
        animator.setFloatValues(0, 1);
        animator.setInterpolator(new TwoInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        StateListAnimator stateListAnimator = new StateListAnimator();
        stateListAnimator.addState(new int[0], animator);
        notify.setStateListAnimator(stateListAnimator);
        // 加入了一个闪烁
    }


    public void startRecord(Camera camera, Surface surface, final int maxViewWith, final int maxRecodeTime) {
        mCamera = camera;
        progress.setVisibility(View.VISIBLE);
        over.setVisibility(View.GONE);

        // 去除闪烁
        notify.setVisibility(View.VISIBLE);
        notify.setStateListAnimator(null);
        notify.setAlpha(1f);
        // 去除闪烁

        try {
            startMovieRecord(surface);
        } catch (Exception e) {
            e.printStackTrace();
        }

        countDownTimer = new CountDownTimer(maxRecodeTime, 200) {
            @Override
            public void onTick(long millisUntilFinished) {
                mVideoTime = maxRecodeTime - millisUntilFinished;
                float radio = mVideoTime * 1f / maxRecodeTime;
                int left = maxViewWith;
                for (int i = mItemView.getChildCount() - 1; i > 0; i--) {
                    View child = mItemView.getChildAt(i);
                    left -= child.getVisibility() != View.GONE ? 0 : child.getWidth();
                }
                int target = (int) (left * radio);
                ViewGroup.LayoutParams params = mItemView.getChildAt(0).getLayoutParams();
                params.width = target;
                mItemView.getChildAt(0).setLayoutParams(params);
            }

            @Override
            public void onFinish() {
                stopAndReleaseMovieRecord();
                if (mOnRecodeOver != null) {
                    mOnRecodeOver.onStop(VideoHolder.this);
                }
            }
        };
        countDownTimer.start();

    }


    // 得到显示View
    public View getItemView() {
        return mItemView;
    }

    // 停止录制
    public void stopRecord() {
        progress.setVisibility(View.VISIBLE);
        over.setVisibility(View.VISIBLE);
        notify.setVisibility(View.GONE);

        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        stopAndReleaseMovieRecord();
        if (mOnRecodeOver != null) {
            mOnRecodeOver.onStop(VideoHolder.this);
        }

    }

    // 获取视频时间
    public long getVideoPlayTime() {
        return mVideoTime;
    }

    // 得到视频文件
    public File getStoreFile() {
        return mVideoRecodeFile;
    }

    //---------------------------------------API------------------------------------------//

    // 拍摄 初始化
    private boolean startMovieRecord(Surface surface) throws IOException {

        System.out.println("--------------------------------------->>>>>>>>>start");

        if (mCamera == null) return false;


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
        mMediaRecorder.setOutputFile(mVideoRecodeFile.getAbsolutePath());
//        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);

        // Step 5: Set the preview output
        mMediaRecorder.setPreviewDisplay(surface);

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



}
