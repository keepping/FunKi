package com.hifunki.funki.module.home.me.recharge.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.module.home.me.recharge.adapter.RechargeAdapter;
import com.hifunki.funki.module.home.me.recharge.entity.RechargeItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 个人中心-->充值
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.home.me.recharge.activity.RechargeActivity.java
 * @link
 * @since 2017-03-21 13:33:33
 */
public class RechargeActivity extends BaseActivity {

    @BindView(R.id.rl_recharge)
    RecyclerView rlRecharge;
    private List<RechargeItem> rechargeItems;

    public static void show(Context context) {
        context.startActivity(new Intent(context, RechargeActivity.class));
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_recharge;
    }

    @Override
    protected void initDatas() {
        rechargeItems = new ArrayList<>();
        rechargeItems.add(new RechargeItem(10, (float) 0.99));
        rechargeItems.add(new RechargeItem(50, (float) 4.99));
        rechargeItems.add(new RechargeItem(100, (float) 9.99));
        rechargeItems.add(new RechargeItem(200, (float) 19.99));
        rechargeItems.add(new RechargeItem(400, (float) 39.99));
        rechargeItems.add(new RechargeItem(800, (float) 79.99));
        rechargeItems.add(new RechargeItem(1600, (float) 159.99));
        rechargeItems.add(new RechargeItem(3200, (float) 319.99));
    }

    @Override
    protected void initView() {
        RechargeAdapter adapter = new RechargeAdapter(rechargeItems);
        rlRecharge.setLayoutManager(new GridLayoutManager(this, 3));
        rlRecharge.setAdapter(adapter);
    }
}
