package com.hifunki.funki.base.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hifunki.funki.R;
import com.hifunki.funki.base.application.ApplicationMain;
import com.hifunki.funki.util.StatusBarUtil;

import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/*
 *   ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 *     ┃　　　┃
 *     ┃　　　┃
 *     ┃　　　┗━━━┓
 *     ┃　　　　　　　┣┓
 *     ┃　　　　　　　┏┛
 *     ┗┓┓┏━┳┓┏┛
 *       ┃┫┫　┃┫┫
 *       ┗┻┛　┗┻┛
 *        神兽保佑
 *        代码无BUG!
 */

/**
 * BaseActivity
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.base.activity.BaseActivity.java
 * @link
 * @since 2017-02-23 20:24:24
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ApplicationMain.addActivity(this);

        setContentView(getViewResId());
        //初始化butterKnife
        ButterKnife.bind(this);

        //初始化参数
        initDatas();

        StatusBarUtil.setStatusBarBackground(this, R.drawable.iv_bg_status);

        //初始化titleBar
        initTitleBar();

        //初始化View
        initView();

        //初始化监听
        initListener();
        //初始化适配器
        initAdapter();

        //请求数据
//        loadDatas();
    }

    /**
     * 防止字体随手机系统的字体变大而变大
     */
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    @Override
    protected void onDestroy() {
        ApplicationMain.removeActivity(this);
        super.onDestroy();
    }

    protected abstract int getViewResId();

    protected abstract void initDatas();


    protected abstract void initTitleBar();

    protected abstract void initView();

    protected abstract void initListener();

    protected abstract void initAdapter();

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
