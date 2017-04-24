package com.hifunki.funki.module.splash.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.module.home.activity.HomeActivity;

/**
 * 闪屏页
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.splash.activity.SplashActivit1y.java
 * @link
 * @since 2017-03-29 11:04:04
 */
public class SplashActivity extends BaseActivity {


    private Context mContext;
    private Activity mActivity;

    @Override
    protected int getViewResId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initVariable() {
        mContext = getApplicationContext();
        mActivity = SplashActivity.this;//必须要调用,用来注册本地广播
    }


    @Override
    protected void initView() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                HomeActivity.show(SplashActivity.this, mActivity);
            }
        }, 100);

    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
    }

    @Override
    protected void initListener() {
        super.initListener();
    }

    @Override
    protected void initAdapter() {
        super.initAdapter();
    }

    @Override
    protected void bindData() {

    }

    @Override
    protected void bindData4NoNet() {

    }
}
