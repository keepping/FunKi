package com.hifunki.funki.module.home.viewholder;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.hifunki.funki.R;
import com.hifunki.funki.net.back.Post;
import com.hifunki.funki.util.TimeUtil;
import com.powyin.scroll.adapter.AdapterDelegate;
import com.powyin.scroll.adapter.PowViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 在此写用途
 *
 * @author yinhaoxiang
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.home.viewholder.Follow_Movie.java
 * @link
 * @since 2017-03-16 10:04:04
 */
public class Follow_Movie extends PowViewHolder<Post> {

    enum PLAY_STATUS {
        unInit(0),                    //未初始化
        loading(0),                   //载入中

        playing_silence(0),           //无提示播放
        playing_notify(0),            //提示播放

        pause(0),                     //暂时
        replay(0);                    //重播

        PLAY_STATUS(int resId) {
            this.viewId = resId;
        }

        int viewId;
    }


    String uri = "http://flv.bn.netease.com/tvmrepo/2012/7/C/7/E868IGRC7-mobile.mp4";
    //    String uri = "http://14.215.153.200/vcloud.tc.qq.com/o0011hremh8.m301.mp4?sha=219F1A1E310C6D42A945F593618A1F2E2CD9EB63&vkey=871E03FC56BA47391979D7E470837257972B3C6FAEFA0325E4324E8708C386768AA75E834F2039E4DFD9E91A53CA62FF9B8A5869890C0682912D5EA8CC56F86ADD0AC4C223586ABD6E7B8996975A2A6342842A7FD68EFFDD3581706C31C9199C2F4118A674EA0B17DD6C689713358C2A&ocid=1678502922";
    @BindView(R.id.video_view)
    VideoView videoView;

    @BindView(R.id.play_control)
    LinearLayout play_control;

    @BindView(R.id.play_pause_or_start)
    ImageView play_pause_or_start;

    @BindView(R.id.play_full_screen)
    ImageView play_full_screen;

    @BindView(R.id.play_seek)
    SeekBar seekBar;


    @BindView(R.id.play_time_current)
    TextView playTimeCurrent;

    @BindView(R.id.play_time)
    TextView playTime;

    CountDownTimer timer;

    PLAY_STATUS play_status = PLAY_STATUS.unInit;

    public Follow_Movie(Activity activity, ViewGroup viewGroup) {
        super(activity, viewGroup);
        ButterKnife.bind(this, mItemView);
        seekBar.setMax(100);
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        videoView.setOnCompletionListener(onCompletionListener);
    }

    /**
     * 获取布局
     * @return
     */
    @Override
    protected int getItemViewRes() {
        return R.layout.viewholder_post_movie;
    }



    @OnClick({R.id.play, R.id.play_pause_or_start, R.id.play_full_screen,})
    void onClick(final View view) {
        switch (view.getId()) {
            case R.id.play:
                System.out.println("...................................." + play_status);
                switch (play_status) {
                    case unInit:

                        break;
                    case loading:

                        break;
                    case playing_silence:
                        play_status = PLAY_STATUS.playing_notify;
                        updateUI();
                        mItemView.postDelayed(mRunnable, 3000);
                        break;
                    case playing_notify:
                        videoView.pause();
                        play_status = PLAY_STATUS.pause;
                        break;
                    case pause:
                        startPlay();
                        play_status = PLAY_STATUS.playing_silence;
                        break;
                    case replay:
                        videoView.seekTo(0);
                        startPlay();
                        play_status = PLAY_STATUS.playing_silence;
                        break;
                }
                updateUI();
                break;
            case R.id.play_pause_or_start:
                if (play_status == PLAY_STATUS.playing_silence) {
                    play_status = PLAY_STATUS.playing_notify;
                } else if (play_status == PLAY_STATUS.playing_notify) {
                    play_status = PLAY_STATUS.playing_silence;
                }
                updateUI();

                break;
            case R.id.play_full_screen://切换全屏
                /***
                 * 就算只用户主动切换大小，也是去是activity转向来实现的
                 */
                if (getScreenOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {// 转小屏
                    mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                } else {// 转全屏
                    mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }
                updateFullScreenButton();
                break;
        }
    }

    /**
     * 更新全屏按钮
     */
    private void updateFullScreenButton() {
        if (getScreenOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {// 全屏幕
//            $.id(R.id.view_jky_player_fullscreen).image(
//                    R.drawable.shrink_fullscreen);
//            $.id(R.id.view_jky_player_iv_share).gone();
//            $.id(R.id.view_jky_play_iv_setting).visible();
        } else {
//            $.id(R.id.view_jky_player_fullscreen).image(R.drawable.enlarge_fullscreen);
//            $.id(R.id.view_jky_player_iv_share).visible();
//            $.id(R.id.view_jky_play_iv_setting).gone();
        }
    }




    private void startPlay() {
        videoView.start();
        play_status = PLAY_STATUS.playing_silence;
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        int duration = videoView.getDuration();
        System.out.println("dddddddddddddd   " + duration);
        if (duration > 0) {
            timer = new CountDownTimer(duration, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateUI();
                    System.out.println("tick tick tick ");
                }

                @Override
                public void onFinish() {

                }
            };
            timer.start();
        }
        updateUI();
    }

    private void destroy() {

    }

    private void updateUI() {
        if (mItemView == null) return;
        mItemView.findViewById(R.id.play_init).setVisibility(View.GONE);
        mItemView.findViewById(R.id.play_loading).setVisibility(View.GONE);
        mItemView.findViewById(R.id.play_pause).setVisibility(View.GONE);
        mItemView.findViewById(R.id.play_restart).setVisibility(View.GONE);
        mItemView.findViewById(R.id.play_control).setVisibility(View.GONE);
        switch (play_status) {
            case unInit:
                mItemView.findViewById(R.id.play_init).setVisibility(View.VISIBLE);
                break;
            case loading:
                mItemView.findViewById(R.id.play_loading).setVisibility(View.VISIBLE);
                break;
            case playing_silence:
                mItemView.findViewById(R.id.play_control).setVisibility(View.VISIBLE);
                break;
            case playing_notify:
                mItemView.findViewById(R.id.play_control).setVisibility(View.VISIBLE);
                mItemView.findViewById(R.id.play_pause).setVisibility(View.VISIBLE);
                break;
            case pause:
                mItemView.findViewById(R.id.play_init).setVisibility(View.VISIBLE);
                mItemView.findViewById(R.id.play_control).setVisibility(View.VISIBLE);
                break;
            case replay:
                mItemView.findViewById(R.id.play_restart).setVisibility(View.VISIBLE);
                break;
        }

        int duration = videoView.getDuration();
        int current = videoView.getCurrentPosition();


        float radio = duration == 0 ? 0 : 1f * current / duration;
        seekBar.setProgress((int) (100 * radio));

        int timeLen = TimeUtil.getTimeLenth(duration);


        playTimeCurrent.setText(TimeUtil.getTime(current, timeLen));
        playTime.setText(TimeUtil.getTime(duration, timeLen));

    }



    @Override
    protected boolean acceptData(Post data) {
        return data != null && data.type == 3;
    }

    @Override
    public void loadData(AdapterDelegate<? super Post> multipleAdapter, Post data, int postion) {
        play_status = PLAY_STATUS.unInit;

        videoView.setVideoURI(Uri.parse(uri));
        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                startPlay();

            }
        });
        play_status = PLAY_STATUS.loading;

        updateUI();

    }

    @Override
    protected void onViewDetachedFromWindow() {
        super.onViewDetachedFromWindow();
        System.out.println("onViewDetachedFromWindow");
        play_status = PLAY_STATUS.unInit;
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        videoView.stopPlayback();
        videoView.setVideoURI(null);

    }

    //子线程
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (play_status == PLAY_STATUS.playing_notify) {
                play_status = PLAY_STATUS.playing_silence;
                updateUI();
            }
        }
    };

    //media player 监听
    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            play_status = PLAY_STATUS.replay;
            updateUI();
        }
    };

    //seekbar 监听
    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        boolean isInTouch = false;

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (isInTouch) {
                if (play_status != PLAY_STATUS.unInit && play_status != PLAY_STATUS.loading) {
                    int dur = videoView.getDuration();
                    float raido = 1f * progress / 100;
                    int current = (int) (raido * dur);
                    videoView.seekTo(current);
                }
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            System.out.println("  touch");
            isInTouch = true;
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            System.out.println("  touch down");
            isInTouch = false;
        }
    };


    private FrameLayout getRomotePlayView() {

        //  MultipleRecycleAdapter<Post> multipleRecycleAdapter = (MultipleRecycleAdapter<Post>) multipleAdapter;

        RecyclerView recyclerView = null; // multipleRecycleAdapter.getRecyclerView();
        if (recyclerView == null) return null;

        FrameLayout ret = null;
        FrameLayout frameLayout = (FrameLayout) recyclerView.getParent().getParent();
        if (frameLayout.getChildCount() != 2) {
            ret = new FrameLayout(mActivity);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(600, 300);
            frameLayout.addView(ret, layoutParams);
            return ret;
        } else {
            return (FrameLayout) frameLayout.getChildAt(1);
        }
    }

    private int getScreenOrientation() {
        int rotation = mActivity.getWindowManager().getDefaultDisplay()
                .getRotation();
        DisplayMetrics dm = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        int orientation;
        // if the device's natural orientation is portrait:
        if ((rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_180)
                && height > width
                || (rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270)
                && width > height) {
            switch (rotation) {
                case Surface.ROTATION_0:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                    break;
                case Surface.ROTATION_90:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                    break;
                case Surface.ROTATION_180:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT;
                    break;
                case Surface.ROTATION_270:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
                    break;
                default:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                    break;
            }
        }
        else {
            switch (rotation) {
                case Surface.ROTATION_0:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                    break;
                case Surface.ROTATION_90:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                    break;
                case Surface.ROTATION_180:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
                    break;
                case Surface.ROTATION_270:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT;
                    break;
                default:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                    break;
            }
        }

        return orientation;
    }

}

















