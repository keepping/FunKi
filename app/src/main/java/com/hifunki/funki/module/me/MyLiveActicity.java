package com.hifunki.funki.module.me;

import android.content.Context;
import android.content.Intent;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.me.MyLiveActicity1.java
 * @link
 * @since 2017-04-17 12:36:36
 */
public class MyLiveActicity extends BaseActivity {

    @Override
    protected int getViewResId() {
        return R.layout.activity_my_live;
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

    public static void show(Context context) {
        context.startActivity(new Intent(context,MyLiveActicity.class));
    }
}
