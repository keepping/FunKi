package com.hifunki.funki.module.live.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.module.live.widget.VerticalViewPager;

import java.util.ArrayList;

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

//    @BindView(R.id.sv_live)
//    SurfaceView svLive;

//    private SurfaceHolder holder;
//
//    private IjkMediaPlayer ijkMediaPlayer;

    private ArrayList<String> mVideoUrls = new ArrayList<>();

    private int mCurrentItem;//当前position

    public static void show(Context context) {
        context.startActivity(new Intent(context, LiveActivity.class));
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_live;
    }

    @Override
    protected void initDatas() {
        mVideoUrls.add("rtmp://live.hkstv.hk.lxdns.com/live/hks");
        mVideoUrls.add("rtmp://live.hkstv.hk.lxdns.com/live/hks");
        mVideoUrls.add("rtmp://live.hkstv.hk.lxdns.com/live/hks");
        mVideoUrls.add("rtmp://live.hkstv.hk.lxdns.com/live/hks");
    }

    @Override
    protected void initView() {

        VerticalViewPager mViewPager = (VerticalViewPager) findViewById(R.id.vertical_viewpager);
        PagerAdapter mPagerAdapter = new PagerAdapter();
        mViewPager.setAdapter(mPagerAdapter);

//        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });

        mViewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {


            }
        });

//        holder = svLive.getHolder();
//        ijkMediaPlayer = new IjkMediaPlayer();
//
//        try {
//            String uri = "rtmp://live.hkstv.hk.lxdns.com/live/hks";
//            ijkMediaPlayer.setDataSource(this, Uri.parse(uri));
//            ijkMediaPlayer.setDisplay(holder);
//            holder.addCallback(new SurfaceHolder.Callback() {
//
//                @Override
//                public void surfaceCreated(SurfaceHolder holder) {
//
//                }
//
//
//                @Override
//                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//
//                    ijkMediaPlayer.setDisplay(holder);
//                }
//
//
//                @Override
//                public void surfaceDestroyed(SurfaceHolder holder) {
//
//                }
//            });
//            ijkMediaPlayer.prepareAsync();
//            ijkMediaPlayer.start();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        ijkMediaPlayer.setKeepInBackground(false);


    }


    class PagerAdapter extends android.support.v4.view.PagerAdapter {

        @Override
        public int getCount() {
            return mVideoUrls.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(container.getContext()).inflate(R.layout.view_room_item, null);

            ViewPager viewPager= (ViewPager) view.findViewById(R.id.vp_room);
            viewPager.setAdapter(new PagerAdapter1());

            view.setId(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(container.findViewById(position));
        }
    }

    class PagerAdapter1 extends android.support.v4.view.PagerAdapter {

        @Override
        public int getCount() {
            return mVideoUrls.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(container.getContext()).inflate(R.layout.view_room_item1, null);

            view.setId(position);
            container.addView(view);

            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(container.findViewById(position));
        }
    }


}
