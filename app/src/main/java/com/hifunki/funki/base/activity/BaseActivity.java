package com.hifunki.funki.base.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.hifunki.funki.base.application.ApplicationMain;
import com.hifunki.funki.util.NetWorkUtil;

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
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.base.activity.BaseActivity.java
 * @link
 * @since 2017-03-09 10:27:27
 */
public abstract class BaseActivity extends AppCompatActivity{

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("BaseActivity", "onCreate: " + System.currentTimeMillis() + this.getClass());
        ApplicationMain.addActivity(this);

        setContentView(getViewResId());
        //初始化butterKnife
        ButterKnife.bind(this);

        //初始化参数
        initVariable();

        //初始化titleBar
        initTitleBar();

        //初始化View
        initView();

        //初始化监听
        initListener();
        //初始化适配器
        initAdapter();

        requestData();
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

    protected abstract void initVariable();


    protected  void initTitleBar(){

    }

    protected abstract void initView();

    protected void initListener(){

    }

    protected void initAdapter(){

    }

    private void requestData() {
        if (!NetWorkUtil.isNetConnected()) {//没有网络
            bindData4NoNet();//无网络处理
        } else {
            bindData();//有网络处理
        }
    }

    protected  void bindData(){

    }

    protected  void bindData4NoNet(){

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
