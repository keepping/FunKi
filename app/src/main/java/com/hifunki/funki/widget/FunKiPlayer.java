package com.hifunki.funki.widget;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.hifunki.funki.R;
import com.hifunki.funki.util.TimeUtil;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * 在此写用途
 *
 * @author yinhaoxiang
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.widget.FunKiPlayer.java
 * @link
 * @since 2017-03-23 09:41:41
 */
public class FunKiPlayer extends FrameLayout {

    private final String tag = "FunKiPlayer";

    Activity context;


    IjkMediaPlayer ijkMediaPlayer;
    SurfaceView surfaceView;
    LinearLayout play_control;
    ImageView play_pause_or_start;
    ImageView play_full_screen;
    SeekBar seekBar;
    TextView playTimeCurrent;
    TextView playTime;

    String uri;
    CountDownTimer timer;

    PLAY_STATUS play_status = PLAY_STATUS.unInit;

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


    public FunKiPlayer(@NonNull Context context) {
        this(context, null);
    }

    public FunKiPlayer(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FunKiPlayer(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);

    }

    public void play(String uri) {

     //   System.out.println("......................................................................");
        ensurePlayer();

        this.uri = uri;
        play_status = PLAY_STATUS.unInit;

        try {
            ijkMediaPlayer.reset();
            ijkMediaPlayer.setDataSource(getContext(),Uri.parse(uri));
//            ijkMediaPlayer.prepareAsync();
//            ijkMediaPlayer.setDisplay(live.getHolder());
//            ijkMediaPlayer.setDataSource(this, Uri.parse(event.uri));
            ijkMediaPlayer.prepareAsync();
            ijkMediaPlayer.start();
        }catch (Exception e){
            e.printStackTrace();
        }


        play_status = PLAY_STATUS.loading;

        updateUI();
    }


    private void initView(Context context) {
        this.context = (Activity) context;
        View.inflate(context, R.layout.player_content, this);
        surfaceView = (SurfaceView) findViewById(R.id.video_view);
        play_control = (LinearLayout) findViewById(R.id.play_control);
        play_pause_or_start = (ImageView) findViewById(R.id.play_pause_or_start);
        play_full_screen = (ImageView) findViewById(R.id.play_full_screen);
        seekBar = (SeekBar) findViewById(R.id.play_seek);
        playTimeCurrent = (TextView) findViewById(R.id.play_time_current);
        playTime = (TextView) findViewById(R.id.play_time);

        seekBar.setMax(100);
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);

        findViewById(R.id.play).setOnClickListener(clickListener);
        findViewById(R.id.play_pause_or_start).setOnClickListener(clickListener);
        findViewById(R.id.play_full_screen).setOnClickListener(clickListener);

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                ensurePlayer();
                if(play_status == PLAY_STATUS.unInit && !TextUtils.isEmpty(uri)){
                    play(uri);
                }
                ijkMediaPlayer.setDisplay(holder);
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                if(ijkMediaPlayer!=null){
                    ijkMediaPlayer.stop();
                }
                play_status = PLAY_STATUS.unInit;
                updateUI();

            }
        });

    }

    void ensurePlayer(){
        if(ijkMediaPlayer==null){
            ijkMediaPlayer = new IjkMediaPlayer();
            ijkMediaPlayer.setOnPreparedListener(new IjkMediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(IMediaPlayer iMediaPlayer) {
                    startPlay();
                }
            });

            ijkMediaPlayer.setOnCompletionListener(new IjkMediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(IMediaPlayer iMediaPlayer) {
                    play_status = PLAY_STATUS.replay;
                    updateUI();
                }
            });
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ensurePlayer();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(ijkMediaPlayer!=null){
            ijkMediaPlayer.release();
            ijkMediaPlayer = null;
        }
        play_status = PLAY_STATUS.unInit;
    }

    @Override
    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        if(ijkMediaPlayer!=null){
            ijkMediaPlayer.release();
            ijkMediaPlayer = null;
        }
        play_status = PLAY_STATUS.unInit;

    }

    @Override
    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
        ensurePlayer();
    }

    private OnClickListener clickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.play:
                    switch (play_status) {
                        case unInit:

                            break;
                        case loading:

                            break;
                        case playing_silence:
                            play_status = PLAY_STATUS.playing_notify;
                            updateUI();
                            postDelayed(mRunnable, 3000);
                            break;
                        case playing_notify:
                            ijkMediaPlayer.pause();
                            play_status = PLAY_STATUS.pause;
                            break;
                        case pause:
                            startPlay();
                            play_status = PLAY_STATUS.playing_silence;
                            break;
                        case replay:
                            ijkMediaPlayer.seekTo(0);
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
                    Configuration mConfiguration = getResources().getConfiguration();
                    if (mConfiguration.orientation == Configuration.ORIENTATION_PORTRAIT) {     // 竖屏状态
                        FrameLayout frameLayout = getRemotePlayView();
                        frameLayout.removeAllViews();
                        FunKiPlayer player = new FunKiPlayer(context);
                        frameLayout.addView(player, new FrameLayout.LayoutParams(-1, -1));
                        player.play(uri);
                        context.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

                    } else {
                        getRemotePlayView().removeAllViews();
                        context.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    }

                    break;
            }
        }
    };

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
            ensurePlayer();
            if (isInTouch) {
                if (play_status != PLAY_STATUS.unInit && play_status != PLAY_STATUS.loading) {
                    long dur = ijkMediaPlayer.getDuration();
                    float raido = 1f * progress / 100;
                    int current = (int) (raido * dur);
                    ijkMediaPlayer.seekTo(current);
                }
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            isInTouch = true;
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            isInTouch = false;
        }
    };

    /**
     * 监听全屏跟非全屏
     *
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged(final Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Configuration mConfiguration = this.getResources().getConfiguration();
        if (mConfiguration.orientation == Configuration.ORIENTATION_PORTRAIT) {                                // 竖屏状态
            getRemotePlayView().removeAllViews();
            surfaceView.setVisibility(VISIBLE);
        } else {
            View remote = getRemotePlayView().getChildCount() > 0 ? getRemotePlayView().getChildAt(0) : null;
            if (remote == this) {
                surfaceView.setVisibility(VISIBLE);
            } else {
                surfaceView.setVisibility(GONE);
            }
        }

    }

    private void startPlay() {
        ijkMediaPlayer.start();
        play_status = PLAY_STATUS.playing_silence;
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        long duration = ijkMediaPlayer.getDuration();

        if (duration > 0) {
            timer = new CountDownTimer(duration, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateUI();
                }

                @Override
                public void onFinish() {

                }
            };
            timer.start();
        }
        updateUI();
    }

    private void updateUI() {

        findViewById(R.id.play_init).setVisibility(View.GONE);
        findViewById(R.id.play_loading).setVisibility(View.GONE);
        findViewById(R.id.play_pause).setVisibility(View.GONE);
        findViewById(R.id.play_restart).setVisibility(View.GONE);
        findViewById(R.id.play_control).setVisibility(View.GONE);
        switch (play_status) {
            case unInit:
                findViewById(R.id.play_init).setVisibility(View.VISIBLE);
                break;
            case loading:
                findViewById(R.id.play_loading).setVisibility(View.VISIBLE);
                break;
            case playing_silence:
                findViewById(R.id.play_control).setVisibility(View.VISIBLE);
                break;
            case playing_notify:
                findViewById(R.id.play_control).setVisibility(View.VISIBLE);
                findViewById(R.id.play_pause).setVisibility(View.VISIBLE);
                break;
            case pause:
                findViewById(R.id.play_init).setVisibility(View.VISIBLE);
                findViewById(R.id.play_control).setVisibility(View.VISIBLE);
                break;
            case replay:
                findViewById(R.id.play_restart).setVisibility(View.VISIBLE);
                break;
        }

        ensurePlayer();
        long duration = ijkMediaPlayer.getDuration();
        long current = ijkMediaPlayer.getCurrentPosition();

        float radio = duration == 0 ? 0 : 1f * current / duration;
        seekBar.setProgress((int) (100 * radio));

        int timeLen = TimeUtil.getTimeLenth(duration);

        playTimeCurrent.setText(TimeUtil.getTime(current, timeLen));
        playTime.setText(TimeUtil.getTime(duration, timeLen));
    }


    // 从 DecorView 取出一个代理展示 视频 的 FrameLayout  如果没有则加入；
    private FrameLayout getRemotePlayView() {
        ViewGroup viewGroup = (ViewGroup) context.getWindow().getDecorView();
        FrameLayout ret = null;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            if (tag.equals(viewGroup.getChildAt(i).getTag())) {
                ret = (FrameLayout) viewGroup.getChildAt(i);
                break;
            }
        }
        if (ret == null) {
            ret = new FrameLayout(context);
            ret.setTag(tag);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            viewGroup.addView(ret, layoutParams);
        }
        return ret;
    }

}
