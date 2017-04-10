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
 * 没有标题栏的activity
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
        Log.e("BaseActivity", "attachBaseContext: "+"" );
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("BaseActivity", "onCreate: "+"" );
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

//    public void setTitleBar(int resId){
//
//        ImageView titleView = (ImageView)findViewById(R.id.base_tittle);
//        titleView.setImageResource(resId);
//
//    }

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

    protected abstract void bindData();

    protected abstract void bindData4NoNet();

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    //    private final ViewTreeObserver.OnGlobalLayoutListener mOnGlobalLayout = new ViewTreeObserver.OnGlobalLayoutListener() {
//        int barHei = 0;
//        @Override
//        public void onGlobalLayout() {
//
//            if(ennableTitleBar()){
//                barHei = DisplayUtil.getStatusBarHeight(BaseActivity.this);
//                FrameLayout frameLayout = (FrameLayout)findViewById(R.id.base_content);
//                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams)frameLayout.getLayoutParams();
//                layoutParams.topMargin = barHei;
//                frameLayout.setLayoutParams(layoutParams);
//
//                ImageView titleView = (ImageView)findViewById(R.id.base_tittle);
//                FrameLayout.LayoutParams imageLayout = (FrameLayout.LayoutParams)titleView.getLayoutParams();
//                imageLayout.height = barHei;
//                titleView.setLayoutParams(imageLayout);
//            }else {
//
//                ImageView titleView = (ImageView)findViewById(R.id.base_tittle);
//                titleView.setVisibility(View.GONE);
//            }
//
//
//
//            getWindow().getDecorView().getViewTreeObserver().removeOnGlobalLayoutListener(mOnGlobalLayout);
//
//        }
//    };





//    @Override
//    public void setContentView(@LayoutRes int layoutResID) {
//        super.setContentView(R.layout.activity_base_coordinator);
//
//        FrameLayout frameLayout = (FrameLayout)findViewById(R.id.base_content);
//        getLayoutInflater().inflate(layoutResID,frameLayout,true);
//
//        getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(mOnGlobalLayout);
//
//    }

//    protected boolean ennableTitleBar(){
//        return false;
//    }

}
