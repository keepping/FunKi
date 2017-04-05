package com.hifunki.funki.module.live.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.base.adapter.AutoSizeAdapter;
import com.hifunki.funki.module.live.event.EventPlayContent;
import com.hifunki.funki.module.live.fragment.RoomFragment;
import com.hifunki.funki.module.live.widget.VerticalViewPager;
import com.hifunki.funki.net.back.LiveModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * 观众端主界面
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.live.activity.LiveActivity.java
 * @link
 * @since 2017-03-25 13:37:37
 */
public class LiveActivity extends BaseActivity {

    @BindView(R.id.live)
    SurfaceView live;
    private IjkMediaPlayer ijkMediaPlayer;
    private Random random = new Random();
    AutoSizeAdapter<LiveModel> mPagerAdapter;

    public static void show(Context context) {
        context.startActivity(new Intent(context, LiveActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION  | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_live;
    }

    @Override
    protected void initDatas() {

        ScrollView scrollView ;

        HorizontalScrollView horizontalScrollView;


        RecyclerView recyclerView = null;

        LinearLayoutManager linearLayoutManager = null;

    }

    @Override
    protected void initView() {
        VerticalViewPager mViewPager = (VerticalViewPager) findViewById(R.id.vertical_viewpager);

        mPagerAdapter = new AutoSizeAdapter<LiveModel>(this) {
            @Override
            public Fragment getItem(LiveModel data) {
                return RoomFragment.newInstance(data);
            }
        };
        List<LiveModel> models = new ArrayList<>();
        for(int i=0 ;i < 10;i++){
            models.add(new LiveModel(String.valueOf(random.nextLong())));
        }

        mPagerAdapter.loadData(models);
        mPagerAdapter.setOnSwipeListener(onSwipeListener);

        mViewPager.setAdapter(mPagerAdapter);

//        mViewPager.requestDisallowInterceptTouchEvent();
//
//        View view;

        mViewPager.setCurrentItem(4);
        initPlay();
    }


    // 控制视频播放源
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventPlayContent event) {
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

    AutoSizeAdapter.OnSwipeListener onSwipeListener = new AutoSizeAdapter.OnSwipeListener() {
        @Override
        public void loadMoreStart() {                                                  //头部加载更多
            getWindow().getDecorView().postDelayed(new Runnable() {
                @Override
                public void run() {
                    List<LiveModel> models = new ArrayList<>();
                    for(int i=0 ;i < 10;i++){
                        models.add(new LiveModel(String.valueOf(random.nextLong())));
                    }
                    mPagerAdapter.addStart(models);
                }
            },5000);
        }

        @Override
        public void loadMoreEnd() {                                                    //尾部加载更多
            getWindow().getDecorView().postDelayed(new Runnable() {
                @Override
                public void run() {
                    List<LiveModel> models = new ArrayList<>();
                    for(int i=0 ;i < 10;i++){
                        models.add(new LiveModel(String.valueOf(random.nextLong())));
                    }
                    mPagerAdapter.addEnd(models);
                }
            },5000);
        }
    };


}

