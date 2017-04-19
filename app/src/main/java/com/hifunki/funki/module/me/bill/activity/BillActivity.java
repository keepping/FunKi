package com.hifunki.funki.module.me.bill.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.common.BundleConst;
import com.hifunki.funki.common.CommonConst;
import com.hifunki.funki.module.me.bill.adapter.BillAdapter;
import com.hifunki.funki.module.me.bill.entity.BillEntity;
import com.hifunki.funki.widget.bar.TopBarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.hifunki.funki.base.application.ApplicationMain.getContext;

/**
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.me.bill.activity.BillActivity.java
 * @link
 * @since 2017-03-20 17:05:05
 */
public class BillActivity extends BaseActivity {

    @BindView(R.id.rl_bill)
    RecyclerView rlBill;
    @BindView(R.id.tbv_bill)
    TopBarView topBarView;
    private BillAdapter adapter;
    private List<BillEntity> entityList;
    private Activity mActivity;

    private String TAG = "BillActivity";

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
    protected void initVariable() {
        mActivity = this;
        entityList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {

        }
        BillEntity entity = new BillEntity(BillEntity.RECHARGE, "今天", "12:36", 121212, "直播收入");
        BillEntity entity0 = new BillEntity(BillEntity.RED_PACKET_OUT, "今天", "12:36", 121212, "直播收入");
        BillEntity entity1 = new BillEntity(BillEntity.RED_PACKET_IN, "今天", "12:36", 121212, "直播收入");
        BillEntity entity2 = new BillEntity(BillEntity.LIVE_COST, "今天", "12:36", 121212, "直播消费");
        BillEntity entity3 = new BillEntity(BillEntity.ACTIVE, "今天", "12:36", 121212, "直播收入");
        BillEntity entity4 = new BillEntity(BillEntity.LIVE_INCOME, "今天", "12:36", 121212, "直播收入");
        BillEntity entity5 = new BillEntity(BillEntity.PAYPAL, "今天", "12:36", 121212, "直播收入");
        entityList.add(entity);
        entityList.add(entity0);
        entityList.add(entity1);
        entityList.add(entity2);
        entityList.add(entity3);
        entityList.add(entity4);
        entityList.add(entity5);
    }

    @Override
    protected void initTitleBar() {

    }

    @Override
    protected void initView() {
        adapter = new BillAdapter(entityList);
        rlBill.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    protected void initListener() {
        topBarView.getMenuText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BillFilterActivity.show(mActivity, getContext());

            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Log.e(TAG, "onItemClick: " + position);
                if (position == 2) {
                    BillDetailActivity.show(getContext(), position);
                } else if (position == 3) {
                    BillDetailActivity.show(getContext(), position);
                }
            }
        });

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Log.e(TAG, "onItemChildClick: " + position);
                if (position == 5) {
                    BillRankActivity.show(getContext());//跳转到直播收入详情页面
                } else if (position == 2) {
//                    BillDetailActivity.show(getContext());
                }
            }
        });
    }

    @Override
    protected void initAdapter() {
        rlBill.setAdapter(adapter);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_biil_head, null);
        //加载头部图片
        ImageView iv = (ImageView) view.findViewById(R.id.iv_bill_bg);
        Glide.with(this).load(CommonConst.IMAGE_VIEW).into(iv);

        adapter.addHeaderView(view);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case BundleConst.REQUEST_BILL_TO_BILL_FILTER:
                Bundle extras = data.getBundleExtra("intent");
                int bundle = extras.getInt("bundle");
                break;
        }
    }

    @Override
    protected void bindData() {

    }

    @Override
    protected void bindData4NoNet() {

    }
}
