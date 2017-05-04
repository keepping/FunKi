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
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hifunki.funki.R;
import com.hifunki.funki.common.CommonConst;
import com.hifunki.funki.util.NetWorkUtil;
import com.hifunki.funki.util.TimeUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
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

    Activity activity;
    IjkMediaPlayer ijkMediaPlayer;
    @BindView(R.id.sv_video)
    SurfaceView surfaceView;
    @BindView(R.id.ll_play_control)
    LinearLayout llPlayControl;//底部控制栏
    @BindView(R.id.iv_play_pause_start)
    ImageView ivPlayPauseStart;//暂停或者是播放
    @BindView(R.id.iv_play_fullscreen)
    ImageView ivPlayFullScreen;//全屏播放
    @BindView(R.id.iv_play_seek)
    SeekBar seekBar;//手动拖动栏
    @BindView(R.id.iv_play_current_time)
    TextView playTimeCurrent;//当前已经播放时间
    @BindView(R.id.iv_play_all_time)
    TextView ivPlayAllTime;//视频所有时间
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;//等待加载
    @BindView(R.id.iv_play_pause)
    ImageView ivPlayPause;//暂停播放
    @BindView(R.id.iv_play_restart)
    ImageView ivPlayRestart;//重新开始播放
    @BindView(R.id.iv_play_init)
    ImageView ivPlayInit;//初始化话开始播放
    @BindView(R.id.fl_play)
    FrameLayout flPlay;//播放控制区域
    @BindView(R.id.iv_play_bg)
    ImageView ivPlayBg;//播放的背景图
    @BindView(R.id.iv_net_play_restart)
    TextView tvNetPlayRestart;//无网络重新播放
    String uri;
    CountDownTimer timer;

    PLAY_STATUS status = PLAY_STATUS.UNINIT;
    private boolean isAttatch = false;

    enum PLAY_STATUS {
        UNINIT,                    //未初始化
        LOADING,                   //载入中
        PLAYING_SILENCE,           //无提示播放
        PLAYING_NOTIFY,            //提示播放
        PAUSE,                     //暂停
        REPLAY,                    //重播
        NONET,                     //无网络
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

    private void initView(Context context) {
        activity = (Activity) context;
        View rootView = View.inflate(context, R.layout.item_funki_player, this);
        ButterKnife.bind(this, rootView);

        seekBar.setMax(100);
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);

        flPlay.setOnClickListener(clickListener);
        ivPlayPauseStart.setOnClickListener(clickListener);
        ivPlayFullScreen.setOnClickListener(clickListener);
        tvNetPlayRestart.setOnClickListener(clickListener);
        boolean isNetConnected = NetWorkUtil.isNetConnected();
        if (!isNetConnected) {
            updateUI();
            return;
        }

        initSurfaceHolder();

    }

    private void initSurfaceHolder() {
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                ensurePlayer();
                if (status == PLAY_STATUS.UNINIT && !TextUtils.isEmpty(uri)) {
                    play(uri);
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                ijkMediaPlayer.setDisplay(holder);
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                if (ijkMediaPlayer != null) {
                    ijkMediaPlayer.stop();
                }
                status = PLAY_STATUS.UNINIT;
                updateUI();
            }
        });
    }

    public void play(final String uri) {
        ensurePlayer();
        this.uri = uri;
        status = PLAY_STATUS.UNINIT;
        Glide.with(activity).load(CommonConst.IMAGE_VIEW).into(ivPlayBg);
        try {
            ijkMediaPlayer.reset();
            ijkMediaPlayer.setDataSource(getContext(), Uri.parse(uri));
            ijkMediaPlayer.prepareAsync();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        status = PLAY_STATUS.LOADING;
        updateUI();
    }

    /**
     * 确保播放器
     */
    void ensurePlayer() {
        if (ijkMediaPlayer == null) {
            ijkMediaPlayer = new IjkMediaPlayer();
            ijkMediaPlayer.setOnPreparedListener(new IjkMediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(IMediaPlayer iMediaPlayer) {
                    if (isAttatch) {
                        startPlay();
                    }
                }
            });

            ijkMediaPlayer.setOnCompletionListener(new IjkMediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(IMediaPlayer iMediaPlayer) {
                    status = PLAY_STATUS.REPLAY;
                    updateUI();
                }
            });
        }
    }

    private void startPlay() {
        ijkMediaPlayer.start();
        status = PLAY_STATUS.PLAYING_SILENCE;
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        long duration = ijkMediaPlayer.getDuration();
        System.out.println("funkiplayer=" + duration);
        if (duration > 0) {
            timer = new CountDownTimer(duration, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    System.out.println("funkiplayer=millisUntilFinished" + millisUntilFinished);
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

    private OnClickListener clickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.fl_play://全屏开始播放
                    switch (status) {
                        case UNINIT:
                            status = PLAY_STATUS.LOADING;
                            System.out.println("status="+status+"uninit");
                            break;
                        case LOADING:
                            status = PLAY_STATUS.PLAYING_SILENCE;
                            System.out.println("status=="+status+"loading");
                            break;
                        case PLAYING_SILENCE:
                            status = PLAY_STATUS.PLAYING_NOTIFY;
//                            postDelayed(mRunnable, 3000);
                            break;
                        case PLAYING_NOTIFY:
                            ijkMediaPlayer.pause();
                            status = PLAY_STATUS.PAUSE;
                            break;
                        case PAUSE:
                            startPlay();
                            status = PLAY_STATUS.PLAYING_SILENCE;
                            break;
                        case REPLAY:
                            ijkMediaPlayer.seekTo(0);
                            startPlay();
                            status = PLAY_STATUS.PLAYING_SILENCE;
                            break;
                    }
                    updateUI();
                    break;
                case R.id.iv_play_pause_start:
                    if (status == PLAY_STATUS.PLAYING_SILENCE) {
                        status = PLAY_STATUS.PLAYING_NOTIFY;
                    } else if (status == PLAY_STATUS.PLAYING_NOTIFY) {
                        status = PLAY_STATUS.PLAYING_SILENCE;
                    }
                    updateUI();
                    break;
                case R.id.iv_play_fullscreen:
                    Configuration mConfiguration = getResources().getConfiguration();
                    if (mConfiguration.orientation == Configuration.ORIENTATION_PORTRAIT) {     // 竖屏状态
                        FrameLayout frameLayout = getRemotePlayView();
                        frameLayout.removeAllViews();
                        FunKiPlayer player = new FunKiPlayer(activity);
                        frameLayout.addView(player, new FrameLayout.LayoutParams(-1, -1));
                        player.play(uri);
                        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

                    } else {
                        getRemotePlayView().removeAllViews();
                        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    }
                    break;
                case R.id.iv_net_play_restart:

                    break;
            }
        }
    };

    private void updateUI() {
        ivPlayInit.setVisibility(View.GONE);//开始播放
        pbLoading.setVisibility(View.GONE);//loading
        ivPlayPause.setVisibility(View.GONE);//暂停播放
        ivPlayRestart.setVisibility(View.GONE);//重新开始播放
        llPlayControl.setVisibility(View.GONE);//底部控制栏
        ivPlayBg.setVisibility(View.GONE);
        switch (status) {
            case UNINIT:
                ivPlayInit.setVisibility(View.VISIBLE);
                ivPlayBg.setVisibility(View.VISIBLE);
                break;
            case LOADING:
                pbLoading.setVisibility(View.VISIBLE);
                break;
            case PLAYING_SILENCE:
                llPlayControl.setVisibility(View.VISIBLE);
                break;
            case PLAYING_NOTIFY:
                llPlayControl.setVisibility(View.VISIBLE);
                ivPlayPause.setVisibility(View.VISIBLE);
                break;
            case PAUSE:
                ivPlayInit.setVisibility(View.VISIBLE);
                llPlayControl.setVisibility(View.VISIBLE);
                break;
            case REPLAY:
                ivPlayRestart.setVisibility(View.VISIBLE);
                break;
            case NONET:
                tvNetPlayRestart.setVisibility(View.VISIBLE);
                break;
        }
        ensurePlayer();
        long duration = ijkMediaPlayer.getDuration();
        long current = ijkMediaPlayer.getCurrentPosition();

        float radio = duration == 0 ? 0 : 1f * current / duration;
        seekBar.setProgress((int) (100 * radio));

        int timeLen = TimeUtil.getTimeLenth(duration);

        playTimeCurrent.setText(TimeUtil.getTime(current, timeLen));
        ivPlayAllTime.setText(TimeUtil.getTime(duration, timeLen));
    }

    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        boolean isInTouch = false;

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            ensurePlayer();
            if (isInTouch) {
                if (status != PLAY_STATUS.UNINIT && status != PLAY_STATUS.LOADING) {
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


    @Override
    protected void onAttachedToWindow() {
        isAttatch = true;
        super.onAttachedToWindow();
//        ensurePlayer();
    }

    @Override
    protected void onDetachedFromWindow() {
        isAttatch = false;
        super.onDetachedFromWindow();
        if (ijkMediaPlayer != null) {
            ijkMediaPlayer.pause();
            ijkMediaPlayer = null;
        }
        status = PLAY_STATUS.PAUSE;
    }

    @Override
    public void onStartTemporaryDetach() {
        isAttatch = false;
        super.onStartTemporaryDetach();
        if (ijkMediaPlayer != null) {
            ijkMediaPlayer.pause();
            ijkMediaPlayer = null;
        }
        status = PLAY_STATUS.PAUSE;
    }

    @Override
    public void onFinishTemporaryDetach() {
        isAttatch = true;
        super.onFinishTemporaryDetach();
        ensurePlayer();
    }

    // 从 DecorView 取出一个代理展示 视频 的 FrameLayout  如果没有则加入；
    private FrameLayout getRemotePlayView() {
        ViewGroup viewGroup = (ViewGroup) activity.getWindow().getDecorView();
        FrameLayout ret = null;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            if (tag.equals(viewGroup.getChildAt(i).getTag())) {
                ret = (FrameLayout) viewGroup.getChildAt(i);
                break;
            }
        }
        if (ret == null) {
            ret = new FrameLayout(activity);
            ret.setTag(tag);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            viewGroup.addView(ret, layoutParams);
        }
        return ret;
    }

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (status == PLAY_STATUS.PLAYING_NOTIFY) {
                status = PLAY_STATUS.PLAYING_SILENCE;
                updateUI();
            }
        }
    };


}
