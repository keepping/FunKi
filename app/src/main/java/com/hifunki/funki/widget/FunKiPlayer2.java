package com.hifunki.funki.widget;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.hifunki.funki.util.TimeUtil;

import java.io.IOException;

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
public class FunKiPlayer2 extends FrameLayout {

    private final String TAG = getClass().getSimpleName();

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
    @BindView(R.id.iv_play_status)
    ImageView ivPlayStatus;//播放状态
    @BindView(R.id.fl_play)
    FrameLayout flPlay;//播放控制区域
    @BindView(R.id.iv_play_bg)
    ImageView ivPlayBg;//播放的背景图
    @BindView(R.id.iv_net_play_restart)
    TextView tvNetPlayRestart;//无网络重新播放
    String uri = CommonConst.VIDEO;
    String imageView;
    private boolean isAttatch = false;
    private SurfaceHolder mHolder;

    PLAY_STATUS status = PLAY_STATUS.UNINIT;

    enum PLAY_STATUS {
        UNINIT,                    //未初始化
        LOADING,                   //载入中
        PLAYING_SILENCE,           //无提示播放
        PLAYING_NOTIFY,            //提示播放
        STOP_SILENCE,              //暂停无提示
        STOP_NOTIFY,               //暂停提示
        PAUSE,                     //暂停
        REPLAY,                    //重播
        NONET,                     //无网络
    }


    private boolean isPlaying = false;

    public FunKiPlayer2(@NonNull Context context) {
        this(context, null);
    }

    public FunKiPlayer2(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FunKiPlayer2(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
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
        ivPlayStatus.setOnClickListener(clickListener);
        status = PLAY_STATUS.UNINIT;
        updateUI();
    }

    public void play() {
        mHolder = surfaceView.getHolder();
        ijkMediaPlayer = new IjkMediaPlayer();
        initPlayer();
        startPlay();
    }

    private void initPlayer() {
        ijkMediaPlayer.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(IMediaPlayer iMediaPlayer) {
                ijkMediaPlayer.start();
                isPlaying = true;
                status = PLAY_STATUS.PLAYING_NOTIFY;
                updateUI();
            }
        });
        ijkMediaPlayer.setOnErrorListener(new IMediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
                status = PLAY_STATUS.NONET;
                updateUI();
                return true;
            }
        });
        ijkMediaPlayer.setOnCompletionListener(new IMediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(IMediaPlayer iMediaPlayer) {
                status = PLAY_STATUS.REPLAY;
                updateUI();
            }
        });
        //当使用seekTo()设置播放位置的时候回调。
        ijkMediaPlayer.setOnSeekCompleteListener(new IMediaPlayer.OnSeekCompleteListener() {
            @Override
            public void onSeekComplete(IMediaPlayer iMediaPlayer) {
                status=PLAY_STATUS.PLAYING_NOTIFY;
                updateUI();
            }
        });
    }

    private void startPlay() {
        ijkMediaPlayer.reset();
        try {
            ijkMediaPlayer.setDataSource(getContext(), Uri.parse(uri));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ijkMediaPlayer.setDisplay(mHolder);
        mHolder.setKeepScreenOn(true);
        mHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                ijkMediaPlayer.setDisplay(holder);
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
        ijkMediaPlayer.prepareAsync();
        ijkMediaPlayer.setKeepInBackground(false);

    }
    //                        case PAUSE:
//                            ijkMediaPlayer.start();
//                            status = PLAY_STATUS.PLAYING_NOTIFY;
//                            updateUI();
//                            break;
//                        case REPLAY:
//                            ijkMediaPlayer.seekTo(0);
////                            startPlay();
//                            status = PLAY_STATUS.PLAYING_SILENCE;
//                            break;
//                        case NONET:
//                            break;

    private OnClickListener clickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.fl_play://全屏开始播放
                    switch (status) {
                        case UNINIT:
                            status = PLAY_STATUS.LOADING;
                            updateUI();
                            play();
                            break;
                        case LOADING:
                            break;
                        case PLAYING_NOTIFY:
                            if (isPlaying) {
                                status = PLAY_STATUS.PLAYING_SILENCE;
                                updateUI();
                            }
                            break;
                        case PLAYING_SILENCE:
                            if (isPlaying) {
                                status = PLAY_STATUS.PLAYING_NOTIFY;
                                updateUI();
                            }
                            break;
                        case STOP_NOTIFY:
                            if (!isPlaying) {
                                status = PLAY_STATUS.STOP_SILENCE;
                                updateUI();
                            }
                            break;
                        case STOP_SILENCE:
                            if (!isPlaying) {
                                status = PLAY_STATUS.STOP_NOTIFY;
                                updateUI();
                            }
                            break;
                        default:
                            break;
                    }
                    break;
                case R.id.iv_play_status:
                    switch (status) {
                        case UNINIT:
                            status = PLAY_STATUS.LOADING;
                            updateUI();
                            play();
                            break;
                        case PLAYING_NOTIFY:
                            if (isPlaying) {
                                ijkMediaPlayer.pause();
                                isPlaying = false;
                                status = PLAY_STATUS.STOP_NOTIFY;
                                updateUI();
                            }
                            break;
                        case STOP_SILENCE:
                            if (!isPlaying) {
                                status = PLAY_STATUS.STOP_NOTIFY;
                                updateUI();
                            }
                            break;
                        case STOP_NOTIFY:
                            if (!isPlaying) {
                                ijkMediaPlayer.start();
                                isPlaying = true;
                                status = PLAY_STATUS.PLAYING_NOTIFY;
                                updateUI();
                            }
                            break;
                        case REPLAY:
                            ijkMediaPlayer.seekTo(0);
                            startPlay();
                            break;
                        default:
                            break;
                    }
                    break;
                case R.id.iv_play_pause_start:
//                    if (status == PLAY_STATUS.PLAYING_SILENCE) {
//                        status = PLAY_STATUS.PLAYING_NOTIFY;
//                    } else if (status == PLAY_STATUS.PLAYING_NOTIFY) {
//                        status = PLAY_STATUS.PLAYING_SILENCE;
//                    }
//                    updateUI();
                    break;
                case R.id.iv_play_fullscreen:
                    Configuration mConfiguration = getResources().getConfiguration();
                    if (mConfiguration.orientation == Configuration.ORIENTATION_PORTRAIT) {     // 竖屏状态
                        FrameLayout frameLayout = getRemotePlayView();
                        frameLayout.removeAllViews();
                        FunKiPlayer2 player = new FunKiPlayer2(activity);
                        frameLayout.addView(player, new LayoutParams(-1, -1));
                        player.play();
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
        ivPlayStatus.setVisibility(View.GONE);//开始播放
        pbLoading.setVisibility(View.GONE);//loading
        llPlayControl.setVisibility(View.GONE);//底部控制栏
        ivPlayBg.setVisibility(View.GONE);//播放背景图片
        switch (status) {
            case UNINIT:
                ivPlayStatus.setVisibility(View.VISIBLE);
                ivPlayBg.setVisibility(View.VISIBLE);
                Glide.with(activity).load(CommonConst.IMAGE_VIEW).into(ivPlayBg);
                break;
            case LOADING:
                pbLoading.setVisibility(View.VISIBLE);
                break;
            case PLAYING_SILENCE:
                ivPlayStatus.setVisibility(View.GONE);
                llPlayControl.setVisibility(View.GONE);
                break;
            case PLAYING_NOTIFY:
                removeCallbacks(runnable1);
                ivPlayStatus.setVisibility(View.VISIBLE);
                ivPlayStatus.setImageResource(R.drawable.iv_play_pause);
                llPlayControl.setVisibility(View.VISIBLE);
                postDelayed(runnable,2000);
                break;
            case STOP_NOTIFY:
                removeCallbacks(runnable);
                ivPlayStatus.setVisibility(View.VISIBLE);
                ivPlayStatus.setImageResource(R.drawable.iv_play_start_1);
                llPlayControl.setVisibility(View.VISIBLE);
                postDelayed(runnable1,2000);
                break;
            case STOP_SILENCE:
                ivPlayStatus.setVisibility(View.GONE);
                llPlayControl.setVisibility(View.GONE);
                break;
            case REPLAY:
                removeCallbacks(runnable);
                removeCallbacks(runnable1);
                ivPlayStatus.setVisibility(View.VISIBLE);
                ivPlayStatus.setImageResource(R.drawable.iv_refresh);
                break;
            case NONET:
                tvNetPlayRestart.setVisibility(View.VISIBLE);
                break;
        }
        if (ijkMediaPlayer != null) {
            long duration = ijkMediaPlayer.getDuration();
            long current = ijkMediaPlayer.getCurrentPosition();

            float radio = duration == 0 ? 0 : 1f * current / duration;
            seekBar.setProgress((int) (100 * radio));

            int timeLen = TimeUtil.getTimeLenth(duration);

            playTimeCurrent.setText(TimeUtil.getTime(current, timeLen));
            ivPlayAllTime.setText(TimeUtil.getTime(duration, timeLen));
        }
    }

    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        boolean isInTouch = false;

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (isInTouch) {
                if (status != PLAY_STATUS.UNINIT && status != PLAY_STATUS.LOADING) {
                    long dur = ijkMediaPlayer.getDuration();
                    float raido = 1f * progress / 100;
                    int current = (int) (raido * dur);
                    ijkMediaPlayer.seekTo(current);
                    status=PLAY_STATUS.LOADING;
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

    /**
     * hide playing video control visiablity
     */
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            status = PLAY_STATUS.PLAYING_SILENCE;
            updateUI();
        }
    };

    Runnable runnable1=new Runnable() {
        @Override
        public void run() {
            status = PLAY_STATUS.STOP_SILENCE;
            updateUI();
        }
    };

//    @Override
//    protected void onAttachedToWindow() {
//        isAttatch = true;
//        super.onAttachedToWindow();
//        System.out.println("home-->onAttachedToWindow");
////        ensurePlayer();
//    }
//
//    @Override
//    protected void onDetachedFromWindow() {
//        isAttatch = false;
//        super.onDetachedFromWindow();
//        System.out.println("home-->onDetachedFromWindow");
//        if (ijkMediaPlayer != null) {
//            ijkMediaPlayer.pause();
//            ijkMediaPlayer = null;
//        }
//        status = PLAY_STATUS.PAUSE;
//    }
//
//    @Override
//    public void onStartTemporaryDetach() {
//        isAttatch = false;
//        super.onStartTemporaryDetach();
//        if (ijkMediaPlayer != null) {
//            ijkMediaPlayer.pause();
//            ijkMediaPlayer = null;
//        }
//        status = PLAY_STATUS.PAUSE;
//    }
//
//    @Override
//    public void onFinishTemporaryDetach() {
//        isAttatch = true;
//        super.onFinishTemporaryDetach();
//    }

    // 从 DecorView 取出一个代理展示 视频 的 FrameLayout  如果没有则加入；
    private FrameLayout getRemotePlayView() {
        ViewGroup viewGroup = (ViewGroup) activity.getWindow().getDecorView();
        FrameLayout ret = null;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            if (TAG.equals(viewGroup.getChildAt(i).getTag())) {
                ret = (FrameLayout) viewGroup.getChildAt(i);
                break;
            }
        }
        if (ret == null) {
            ret = new FrameLayout(activity);
            ret.setTag(TAG);
            LayoutParams layoutParams = new LayoutParams(-1, -1);
            viewGroup.addView(ret, layoutParams);
        }
        return ret;
    }


    public void loadDatas(String video, String imageView) {
        this.uri = video;
        this.imageView = imageView;
    }

//    public interface OnLoadDatasListener {
//        void setOnLoadDatas(boolean isLoadDatas);
//    }
//
//    public OnLoadDatasListener onLoadDatasListener;
//
//    public void setOnLoadDatasListener(OnLoadDatasListener listener) {
//        this.onLoadDatasListener = listener;
//    }
//    onLoadDatasListener.setOnLoadDatas(false);


}
