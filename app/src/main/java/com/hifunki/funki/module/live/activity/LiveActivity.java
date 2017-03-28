package com.hifunki.funki.module.live.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.module.live.adapter.LiveVerticalAdapter;
import com.hifunki.funki.module.live.event.EventPlayContent;
import com.hifunki.funki.module.live.fragment.RoomFragment;
import com.hifunki.funki.module.live.widget.VerticalViewPager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * 直播主界面
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.live.activity.LiveActivity.java
 * @link
 * @since 2017-03-25 13:37:37
 */
public class LiveActivity extends BaseActivity {




    //
    private IjkMediaPlayer ijkMediaPlayer;

    private ArrayList<String> mVideoUrls = new ArrayList<>();

    private int mCurrentItem;//当前position
    private int mRoomId = -1;
    private RelativeLayout mRoomContainer;
    private boolean mInit = false;
    private FragmentManager mFragmentManager;

    private RoomFragment mRoomFragment = RoomFragment.newInstance();

    @BindView(R.id.live)
    SurfaceView live;

    public static void show(Context context) {
        context.startActivity(new Intent(context, LiveActivity.class));
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_live;
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initView() {

        VerticalViewPager mViewPager = (VerticalViewPager) findViewById(R.id.vertical_viewpager);
        PagerAdapter mPagerAdapter = new LiveVerticalAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        initPlay();
    }


    // 控制视频播放源
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventPlayContent event) {

        System.out.println(".......................................event"+event.uri);

        try {
            ijkMediaPlayer.reset();
            ijkMediaPlayer.setDisplay(live.getHolder());

            ijkMediaPlayer.setDataSource(this, Uri.parse(event.uri));
            ijkMediaPlayer.prepareAsync();
            ijkMediaPlayer.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    private void initPlay() {
        ijkMediaPlayer = new IjkMediaPlayer();
        try {
            live.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder holder) {
                    ijkMediaPlayer.setDisplay(live.getHolder());
                }
                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                }
                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {
                    ijkMediaPlayer.setDisplay(null);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startPlay(){


    }

//    private void stopPlay(){
//        if(isPlaying){
//            System.out.println("=============================================================== playing................. false");
//            ijkMediaPlayer.setDisplay(null);
//            isPlaying = false;
//            ijkMediaPlayer.stop();
//        }
//
//    }






}

