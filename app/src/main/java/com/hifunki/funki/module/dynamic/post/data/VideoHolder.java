package com.hifunki.funki.module.dynamic.post.data;

import android.app.Activity;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Interpolator;

import com.hifunki.funki.R;
import com.hifunki.funki.util.FileUtils;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.media.RecodeUtil;
import io.media.av.AVRecorder;
import io.media.av.SessionConfig;


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

    private final Activity mActivity;
    private final ViewGroup mItemView;

    private CountDownTimer countDownTimer;
    private OnRecodeOver mOnRecodeOver;
    private long mVideoTime;
    private AVRecorder mAVRecorder;

    private SessionConfig mConfig;

    private boolean mNeedReset = false;                                        // 由于 播放器的 生命控制有 执行  严格要求  所以在这里妥协

    //---------------------------------------API-------------------------------------------//

    public VideoHolder(Activity context) {
        mActivity = context;
        mItemView = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.item_video_recoad, (ViewGroup) context.findViewById(android.R.id.content), false);
        ButterKnife.bind(this, mItemView);
        progress.setVisibility(View.GONE);
        over.setVisibility(View.GONE);
        notify.setVisibility(View.VISIBLE);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1f);
        alphaAnimation.setDuration(1500);
        alphaAnimation.setRepeatCount(Animation.INFINITE);
        alphaAnimation.setInterpolator(new Interpolator() {
            @Override
            public float getInterpolation(float input) {
                return input > 0.5f ? 1 : 0;
            }
        });
        notify.startAnimation(alphaAnimation);
    }




    public void setOnStopLister(OnRecodeOver lisener) {
        mOnRecodeOver = lisener;
    }

    public void setRecorder(AVRecorder avRecorder , boolean needReset) {
        mAVRecorder = avRecorder;
        mNeedReset = needReset;
    }


    public void startRecord(final int maxViewWith, final int maxRecodeTime) {

        if (mAVRecorder == null)
            throw new RuntimeException("for startRecord  you must set mAVRecorder");

        progress.setVisibility(View.VISIBLE);
        over.setVisibility(View.GONE);

        notify.clearAnimation();
        notify.setAlpha(1f);

        try {
            startMovieRecord();
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
    public String getStoreFile() {
        return   mConfig.getOutputFile();
    }

    //---------------------------------------API------------------------------------------//


    void startMovieRecord() {

        try {

            if(mNeedReset){
                mAVRecorder.reset(RecodeUtil.create720pSessionConfig(mActivity));
            }

            mConfig = mAVRecorder.getConfig();

            mAVRecorder.startRecording();
        } catch (IOException e) {
            // Could not create recording at given file path
            Log.e("VideoHolder", "Failed to initOrReset AVRecorder!");
            e.printStackTrace();
        }


    }

    void stopAndReleaseMovieRecord() {
        mAVRecorder.stopRecording();
    }


}










































































