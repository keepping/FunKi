package com.hifunki.funki.module.live.start.activity;

import android.content.Context;
import android.content.Intent;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;

/**
 * 开启直播选择标签
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.live.start.activity.LiveTagActivity.java
 * @link
 * @since 2017-03-29 16:53:53
 */
public class LiveTagActivity extends BaseActivity {


    public static void show(Context context) {
        context.startActivity(new Intent(context, LiveTagActivity.class));
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_live_tag;
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initView() {

    }

}
