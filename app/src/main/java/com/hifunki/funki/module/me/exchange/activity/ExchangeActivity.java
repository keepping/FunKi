package com.hifunki.funki.module.me.exchange.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 兑换
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.me.exchange.ExchangeActivity.java
 * @link
 * @since 2017-03-31 16:01:01
 */
public class ExchangeActivity extends BaseActivity {

    @BindView(R.id.tv_exchange_confirm)
    TextView tvExchangeConfirm;
    @BindView(R.id.rl_exchange_before)
    RelativeLayout rlExchangeBefore;//兑换前界面
    @BindView(R.id.iv_exchange_point)
    ImageView ivExchangePoint;
    @BindView(R.id.tv_exchange_before)
    TextView tvExchangeBefore;
    @BindView(R.id.layout_wait)
    LinearLayout layoutWait;//兑换中界面

    public static void show(Context context) {
        context.startActivity(new Intent(context, ExchangeActivity.class));
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_exchange;
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initView() {
        layoutWait.setVisibility(View.INVISIBLE);//默认不显示提现过程中
    }

    @OnClick({R.id.tv_exchange_confirm, R.id.rl_exchange_before,  R.id.iv_exchange_point, R.id.tv_exchange_before})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_exchange_confirm://点击兑换按钮
                rlExchangeBefore.setVisibility(View.INVISIBLE);
                layoutWait.setVisibility(View.VISIBLE);
                break;
            case R.id.rl_exchange_before:
                break;
            case R.id.iv_exchange_point:
                break;
            case R.id.tv_exchange_before:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
