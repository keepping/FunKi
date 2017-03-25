package com.hifunki.funki.module.live.activity;

import android.content.Context;
import android.content.Intent;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;

/**
 * 直播主界面
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.live.activity.LiveActivity.java
 * @link
 * @since 2017-03-25 13:37:37
 */
public class LiveActivity extends BaseActivity {

    public static void show(Context context) {
        context.startActivity(new Intent(context,LiveActivity.class));
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_live;
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initView() {

    }

}
