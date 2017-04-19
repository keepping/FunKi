package com.hifunki.funki.module.me.bill.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.module.me.bill.adapter.BillDetailAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class BillDetailActivity extends BaseActivity {

    @BindView(R.id.rl_bill_detail)
    RecyclerView rlBillDetail;
    private List mList;
    private int mType;

    public static void show(Context context,int i) {
        Intent intent = new Intent(context, BillDetailActivity.class);
        intent.putExtra("intent", i);
        context.startActivity(intent);
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_bill_detail;
    }

    @Override
    protected void initVariable() {
        mList = new ArrayList();
        for (int i = 0; i < 10; i++) {
            mList.add("aa");
        }
        mType = getIntent().getIntExtra("intent", 0);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {
        super.initListener();
    }

    @Override
    protected void initAdapter() {
        super.initAdapter();
        if(mType==2) {
            BillDetailAdapter adapter = new BillDetailAdapter(R.layout.item_bill_detail_redpocket, mList);
            rlBillDetail.setLayoutManager(new LinearLayoutManager(this));
            rlBillDetail.setAdapter(adapter);
        }else{
            BillDetailAdapter adapter = new BillDetailAdapter(R.layout.item_bill_detail_livecost, mList);
            rlBillDetail.setLayoutManager(new LinearLayoutManager(this));
            rlBillDetail.setAdapter(adapter);
        }
    }

    @Override
    protected void bindData() {

    }

    @Override
    protected void bindData4NoNet() {

    }

}
