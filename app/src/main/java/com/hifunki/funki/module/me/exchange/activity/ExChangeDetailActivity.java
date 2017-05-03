package com.hifunki.funki.module.me.exchange.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;

import butterknife.BindView;

/**
 * 兑换记录
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.me.exchange.ExchangeActivity.java
 * @link
 * @since 2017-03-31 16:01:01
 */
public class ExChangeDetailActivity extends BaseActivity {

    @BindView(R.id.rv_exchange_record)
    RecyclerView recyclerView;
    @Override
    protected int getViewResId() {
        return R.layout.activity_exchange_detail;
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

    public static void show(Context mContext) {
        mContext.startActivity(new Intent(mContext,ExChangeDetailActivity.class));
    }
}
