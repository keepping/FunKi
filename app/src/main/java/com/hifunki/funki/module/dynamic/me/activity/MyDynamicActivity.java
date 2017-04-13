package com.hifunki.funki.module.dynamic.me.activity;

import android.content.Context;
import android.content.Intent;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;


public class MyDynamicActivity extends BaseActivity {

    public static void show(Context context) {
        context.startActivity(new Intent(context, MyDynamicActivity.class));
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_my_dynamic;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void bindData() {

    }

    @Override
    protected void bindData4NoNet() {

    }

}
