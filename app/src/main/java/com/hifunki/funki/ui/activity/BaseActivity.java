package com.hifunki.funki.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hifunki.funki.ui.application.ApplicationMain;

import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * BaseActivity
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.ui.activity.BaseActivity.java
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

        //初始化titleBar
        initTitleBar();

        //初始化View
        initView();

        //初始化监听
        initListener();
        //初始化适配器
        initAdapter();
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
