package com.hifunki.funki.module.me.settting.activity;

import android.content.Context;
import android.content.Intent;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;

public class SettingsActivity extends BaseActivity {

    public static void show(Context context) {
        context.startActivity(new Intent(context, SettingsActivity.class));
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_settings;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {

    }

}
