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
import com.hifunki.funki.widget.bar.TopBarView;

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
public class ExchangeActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tbv_exchange)
    TopBarView tbvExchange;
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
    private STATUS status = STATUS.INIT;
    private Context mContext;

    private enum STATUS {
        INIT,
        WAITING,
        SUCCESS,
        FAILED,
    }

    public static void show(Context context) {
        context.startActivity(new Intent(context, ExchangeActivity.class));
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_exchange;
    }

    @Override
    protected void initVariable() {
        mContext = this;
    }

    @Override
    protected void initView() {
        tbvExchange.getMenuText().setOnClickListener(this);
        updateUI();
        layoutWait.setVisibility(View.INVISIBLE);//默认不显示提现过程中
    }

    private void updateUI() {
        switch (status){
            case INIT:
                rlExchangeBefore.setVisibility(View.VISIBLE);
                layoutWait.setVisibility(View.GONE);
                break;
            case WAITING:
                rlExchangeBefore.setVisibility(View.GONE);
                layoutWait.setVisibility(View.VISIBLE);
                break;
            case SUCCESS:
                break;
            case FAILED:
                break;

        }
    }

    @Override
    protected void bindData() {

    }

    @Override
    protected void bindData4NoNet() {

    }

    @OnClick({R.id.tv_exchange_confirm, R.id.rl_exchange_before,  R.id.iv_exchange_point, R.id.tv_exchange_before,R.id.tv_menu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_exchange_confirm://点击兑换按钮
                status=STATUS.WAITING;
                updateUI();
                break;
            case R.id.rl_exchange_before:
                break;
            case R.id.iv_exchange_point:
                break;
            case R.id.tv_exchange_before:
                break;
            case R.id.tv_menu:
                ExChangeDetailActivity.show(mContext);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
