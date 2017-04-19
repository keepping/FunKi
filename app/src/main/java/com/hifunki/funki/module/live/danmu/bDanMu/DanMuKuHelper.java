package com.hifunki.funki.module.live.danmu.bDanMu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.hifunki.funki.util.ImageUtils;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.Random;

import master.flame.danmaku.controller.IDanmakuView;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.AndroidDisplayer;
import master.flame.danmaku.danmaku.model.android.BaseCacheStuffer;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.ui.widget.DanmakuView;

/**
 * 在此写用途
 *
 * @author yinhaoxiang
 * @version V1.0 <描述当前版本功能>                                         // bilibi 弹幕简化使用
 * @value com.hifunki.funki.module.live.danmu.BDanMu.DanMuKuHelper.java
 * @link
 * @since 2017-04-11 14:26:26
 */
public class DanMuKuHelper {

    public DanMuKuHelper(Context context, DanmakuView danmakuView) {
        this.context = context;
        this.mDanmakuView = danmakuView;
    }

    private Context context;
    private DanmakuView mDanmakuView;

    private DanmakuContext mContext;

    private BaseDanmakuParser mParser = new BaseDanmakuParser() {
        @Override
        protected IDanmakus parse() {
            return new Danmakus();
        }
    };

    private BaseCacheStuffer.Proxy mCacheStufferAdapter = new BaseCacheStuffer.Proxy() {
        @Override
        public void prepareDrawing(final BaseDanmaku danmaku, boolean fromWorkerThread) {
            DanMuData danMuData = (DanMuData) danmaku.tag;
            String targetUri = danMuData == null ? null : danMuData.avatar;
            if (TextUtils.isEmpty(targetUri)) return;
            Glide.with(context).load(targetUri)
                    .asBitmap().into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    DanMuData danMuData = (DanMuData) danmaku.tag;
                    resource = ImageUtils.toCircle(resource,200);
                    danMuData.chachBitmap = new WeakReference<Bitmap>(resource);
                    mDanmakuView.invalidateDanmaku(danmaku, false);
                }
            });
        }

        @Override
        public void releaseResource(BaseDanmaku danmaku) {

        }



    };


    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        // 弹幕冲击；
//        // 设置最大显示行数
//        HashMap<Integer, Integer> maxLinesPair = new HashMap<Integer, Integer>();
//        maxLinesPair.put(BaseDanmaku.TYPE_SCROLL_RL, 7); // 滚动弹幕最大显示5行
//        // 设置是否禁止重叠
//        HashMap<Integer, Boolean> overlappingEnablePair = new HashMap<Integer, Boolean>();
//        overlappingEnablePair.put(BaseDanmaku.TYPE_SCROLL_RL, true);
//        overlappingEnablePair.put(BaseDanmaku.TYPE_FIX_TOP, true);

        mContext = DanmakuContext.create();


        mContext.setDanmakuStyle(IDisplayer.DANMAKU_STYLE_STROKEN, 3).setDuplicateMergingEnabled(false).setScrollSpeedFactor(1.2f)                                //.setScaleTextSize(1.2f)
                .setCacheStuffer(new FunKiStuffer(), mCacheStufferAdapter) // 图文混排使用SpannedCacheStuffer
//                .setCacheStuffer(new BackgroundCacheStuffer())  // 绘制背景使用BackgroundCacheStuffer
//                .setMaximumLines(maxLinesPair)
                .preventOverlapping(null).setDanmakuMargin(10);


        mDanmakuView.setCallback(new master.flame.danmaku.controller.DrawHandler.Callback() {
            @Override
            public void updateTimer(DanmakuTimer timer) {
            }

            @Override
            public void drawingFinished() {

            }

            @Override
            public void danmakuShown(BaseDanmaku danmaku) {

            }

            @Override
            public void prepared() {
                mDanmakuView.start();
            }
        });

        mDanmakuView.setOnDanmakuClickListener(new IDanmakuView.OnDanmakuClickListener() {

            @Override
            public boolean onDanmakuClick(IDanmakus danmakus) {
                Log.d("DFM", "onDanmakuClick: danmakus size:" + danmakus.size());
                BaseDanmaku latest = danmakus.last();
                if (null != latest) {
                    Log.d("DFM", "onDanmakuClick: text of latest danmaku:" + latest.text);
                    return true;
                }
                return false;
            }

            @Override
            public boolean onViewClick(IDanmakuView view) {
                //    mMediaController.setVisibility(View.VISIBLE);
                return false;
            }
        });

        mDanmakuView.prepare(mParser, mContext);
        //     mDanmakuView.showFPS(true);
        mDanmakuView.enableDanmakuDrawingCache(true);


    }


    public void onResume() {
        if (mDanmakuView != null && mDanmakuView.isPrepared() && mDanmakuView.isPaused()) {
            mDanmakuView.resume();
        }
    }


    public void onPause() {
        if (mDanmakuView != null && mDanmakuView.isPrepared()) {
            mDanmakuView.pause();
        }
    }


    public void onDestroyView() {
        if (mDanmakuView != null) {
            mDanmakuView.release();
            mDanmakuView = null;
        }
    }

    private static Random random = new Random(100);

    public void addDanMu(DanMuData danMuData) {
        if(mDanmakuView==null) return;


        BaseDanmaku danmaku = mContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);

        // for(int i=0;i<100;i++){
        // }
        danmaku.text = String.valueOf(random.nextInt(10000000));
        danmaku.tag = danMuData;

        //  danmaku.priority = 0;  // 可能会被各种过滤器过滤并隐藏显示
        danmaku.isLive = false;
        danmaku.setTime(mDanmakuView.getCurrentTime() + random.nextInt(15000));
        danmaku.textSize = 21f * (mParser.getDisplayer().getDensity() - 0.6f);
        danmaku.textColor = Color.WHITE;
        // danmaku.textShadowColor = Color.WHITE;
        // danmaku.underlineColor = Color.GREEN;

        //  danmaku.borderColor = Color.GREEN;


        mDanmakuView.addDanmaku(danmaku);
    }

}
