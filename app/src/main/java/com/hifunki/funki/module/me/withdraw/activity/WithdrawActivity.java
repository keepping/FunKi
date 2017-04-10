package com.hifunki.funki.module.me.withdraw.activity;

import android.content.Context;
import android.content.Intent;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;

/**
 * 提现界面
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.me.withdraw.activity.WithdrawActivity.java
 * @link
 * @since 2017-03-31 16:51:51
 */
public class WithdrawActivity extends BaseActivity {

    public static void show(Context context) {
        context.startActivity(new Intent(context, WithdrawActivity.class));
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_withdraw;
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


}
