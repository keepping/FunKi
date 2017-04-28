package com.hifunki.funki.module.splash.activity;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.util.Log;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.module.home.activity.HomeActivity;
import com.hifunki.funki.util.PackageUtil;

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
    private String TAG=getClass().getSimpleName();

    @Override
    protected int getViewResId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initVariable() {
        mContext = getApplicationContext();
        mActivity = SplashActivity.this;//必须要调用,用来注册本地广播
        String country="";
        String language="";
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            country = getResources().getConfiguration().locale.getCountry();
            language = getResources().getConfiguration().locale.getLanguage();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            country = getResources().getConfiguration().getLocales().get(0).getCountry();
            language = getResources().getConfiguration().getLocales().get(0).getLanguage();
        }
//        Log.d(TAG, "initVariable() called-country="+country+"language="+language);
//        initVariable() called-country=CNlanguage=zh

        try {
            String packageName = PackageUtil.getPackageName(this);//for user
            Log.e(TAG, "initVariable: "+packageName );
            int packageCode = PackageUtil.getPackageCode(this);// for update

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
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
