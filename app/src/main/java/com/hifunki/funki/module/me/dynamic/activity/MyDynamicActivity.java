package com.hifunki.funki.module.me.dynamic.activity;

import android.content.Context;
import android.content.Intent;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;

/**
 * 我的动态界面
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.me.dynamic.activity.MyDynamicActivity1.java
 * @link
 * @since 2017-04-05 14:14:14
 */
public class MyDynamicActivity extends BaseActivity {

    public static void show(Context context) {
        context.startActivity(new Intent(context,MyDynamicActivity.class));
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_my_dynamic;
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initView() {

    }


}
