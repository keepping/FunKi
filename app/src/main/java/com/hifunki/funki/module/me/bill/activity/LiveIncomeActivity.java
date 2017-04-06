package com.hifunki.funki.module.me.bill.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;

import butterknife.BindView;

/**
 * 直播收入详情
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.me.bill.activity.LiveIncomeActivity.java
 * @link
 * @since 2017-03-21 09:55:55
 */
public class LiveIncomeActivity extends BaseActivity {

    @BindView(R.id.rl_live_income)
    RecyclerView rlLiveIncome;

    public static void show(Context context) {
        context.startActivity(new Intent(context, LiveIncomeActivity.class));
    }


    @Override
    protected int getViewResId() {
        return R.layout.activity_live_income;
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initView() {

    }
}
