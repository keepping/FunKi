package com.hifunki.funki.module.home.viewholder;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.hifunki.funki.R;
import com.hifunki.funki.net.back.Post;
import com.hifunki.funki.util.ConstUtils;
import com.hifunki.funki.util.TimeUtil;
import com.powyin.scroll.adapter.AdapterDelegate;
import com.powyin.scroll.adapter.MultipleRecycleAdapter;
import com.powyin.scroll.adapter.PowViewHolder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

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
    public Follow_Movie(Activity activity, ViewGroup viewGroup) {
        super(activity, viewGroup);
        ButterKnife.bind(this, mItemView);
        seekBar.setMax(100);
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        videoView.setOnCompletionListener(onCompletionListener);
    }



    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (play_status == PLAY_STATUS.playing_notify) {
                play_status = PLAY_STATUS.playing_silence;
                updateUI();
            }
        }
    };

    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        boolean isInTouch = false;
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (isInTouch) {
                if (play_status != PLAY_STATUS.unInit && play_status!= PLAY_STATUS.loading) {
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
    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            play_status = PLAY_STATUS.replay;
            updateUI();
        }
    };

    String uri = "http://14.215.154.145/vcloud.tc.qq.com/u0012xltd8y.m301.mp4?sha=97251AF3C063DD4071F751E1D5819A1E62B5ED59&vkey=950FDC6B1E9BE9B5353A5A3E469D768368C49A1A3B9A7E9F2616AD28E8232F6DEB4773E6EB3995EAF22F12EF39B34F02C4D43571E9E3485100D1B4E43C3A97658BD6791507545DB37E6E52431113BBAE10ABC126542CF80AF41C32DEC4DF2E9E87482F6B70D1FAF87D9504019815D0FB&ocid=696312330";

    @BindView(R.id.video_view)
    VideoView videoView;

    @BindView(R.id.play_control)
    RelativeLayout play_control;

    @BindView(R.id.play_pause_or_start)
    ImageView play_pause_or_start;

    @BindView(R.id.play_full_screen)
    ImageView play_full_screen;

    @BindView(R.id.play_seek)
    SeekBar seekBar;

    @BindView(R.id.play_time)
    TextView playTime;


    enum PLAY_STATUS {
        unInit(0),                    //未初始化
        loading(0),                   //载入中

        playing_silence(0),           //无提示播放
        playing_notify(0),            //提示播放

        pause(0),                     //暂时
        replay(0);                    //

        PLAY_STATUS(int resId) {
            this.viewId = resId;
        }

        int viewId;
    }

    PLAY_STATUS play_status = PLAY_STATUS.unInit;


    @OnClick({
            R.id.play,
            R.id.play_pause_or_start,
            R.id.play_full_screen,
    })
    void onClick(final View view) {
        switch (view.getId()) {
            case R.id.play:
                System.out.println("...................................." + play_status);
                switch (play_status) {
                    case unInit:
                        videoView.setVideoURI(Uri.parse(uri));
                        videoView.requestFocus();
                        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                System.out.println("------------------------->>>>>> pr");
                                startPlay();

                            }
                        });
                        play_status = PLAY_STATUS.loading;
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
            case R.id.play_full_screen:

                break;
        }
    }

    CountDownTimer timer;

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

    private void destroy(){

    }

    private void updateUI() {
        if (mItemView == null) return;
        mItemView.findViewById(R.id.play_init).setVisibility(View.INVISIBLE);
        mItemView.findViewById(R.id.play_loading).setVisibility(View.INVISIBLE);
        mItemView.findViewById(R.id.play_pause).setVisibility(View.INVISIBLE);
        mItemView.findViewById(R.id.play_restart).setVisibility(View.INVISIBLE);
        mItemView.findViewById(R.id.play_control).setVisibility(View.INVISIBLE);
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
        String radioString = TimeUtil.getTime(current, timeLen) + "/" +
                TimeUtil.getTime(duration, timeLen);

        playTime.setText(radioString);

    }

    @Override
    protected int getItemViewRes() {
        return R.layout.viewholder_post_movie;
    }

    @Override
    protected boolean acceptData(Post data) {
        return data != null && data.type == 3;
    }

    @Override
    public void loadData(AdapterDelegate<? super Post> multipleAdapter, Post data, int postion) {
        play_status = PLAY_STATUS.unInit;
        updateUI();

    }




    @Override
    protected void onViewDetachedFromWindow() {
        super.onViewDetachedFromWindow();
        System.out.println("onViewDetachedFromWindow");
        play_status = PLAY_STATUS.unInit;
        if(timer!=null){
            timer.cancel();
            timer = null;
        }
        videoView.stopPlayback();
        videoView.setVideoURI(null);

    }

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


}

















