package com.hifunki.funki.module.bill.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.module.bill.adapter.BillAdapter;
import com.hifunki.funki.module.bill.entity.BillEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 个人中心账单页面
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.bill.activity.BillActivity.java
 * @link
 * @since 2017-03-20 17:05:05
 */
public class BillActivity extends BaseActivity {

    @BindView(R.id.rl_bill)
    RecyclerView rlBill;
    /**
     * 跳转界面
     *
     * @param context
     */
    public static void show(Context context) {
        context.startActivity(new Intent(context, BillActivity.class));
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_bill;
    }

    @Override
    protected void initDatas() {
        List<BillEntity> entityList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            BillEntity entity = new BillEntity("今天", "12:36", 121212, "直播收入");
            entityList.add(entity);
        }
        BillAdapter adapter=new BillAdapter(R.layout.item_bill,entityList);
        rlBill.setLayoutManager(new LinearLayoutManager(this));
        rlBill.setAdapter(adapter);
    }

    @Override
    protected void initTitleBar() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initAdapter() {

    }
}
