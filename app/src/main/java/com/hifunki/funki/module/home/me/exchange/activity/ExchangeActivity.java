package com.hifunki.funki.module.home.me.exchange.activity;

import android.content.Context;
import android.content.Intent;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;

/**
 * 兑换
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.home.me.exchange.ExchangeActivity.java
 * @link
 * @since 2017-03-31 16:01:01
 */
public class ExchangeActivity extends BaseActivity {

    @Override
    protected int getViewResId() {
        return R.layout.activity_exchange;
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initView() {

    }

    public static void show(Context context) {
        context.startActivity(new Intent(context, ExchangeActivity.class));
    }
}
