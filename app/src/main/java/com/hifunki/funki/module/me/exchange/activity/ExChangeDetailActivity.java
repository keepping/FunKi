package com.hifunki.funki.module.me.exchange.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.module.me.exchange.adapter.ExchangeDetialAdapter;
import com.hifunki.funki.widget.bar.TopBarView;

import java.util.ArrayList;
import java.util.List;

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

    @BindView(R.id.tbv_exchange)
    TopBarView tbvExchange;
    @BindView(R.id.rv_exchange_record)
    RecyclerView recyclerView;
    private List<String> mList;

    @Override
    protected int getViewResId() {
        return R.layout.activity_exchange_detail;
    }

    @Override
    protected void initVariable() {
        mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mList.add("sjj");
        }
    }

    @Override
    protected void initView() {
        ExchangeDetialAdapter adapter = new ExchangeDetialAdapter(R.layout.item_exchange_detail, mList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void bindData() {

    }

    @Override
    protected void bindData4NoNet() {

    }

    public static void show(Context mContext) {
        mContext.startActivity(new Intent(mContext, ExChangeDetailActivity.class));
    }
}
