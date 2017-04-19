package com.hifunki.funki.module.me.bill.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.common.CommonConst;
import com.hifunki.funki.module.home.entity.HomeHotEntity;
import com.hifunki.funki.module.me.bill.adapter.BillRankAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class BillRankActivity extends BaseActivity {

    @BindView(R.id.rl_bill_rank)
    RecyclerView rlBillRank;
    private List<HomeHotEntity> mList;

    @Override
    protected int getViewResId() {
        return R.layout.activity_bill_rank;
    }

    @Override
    protected void initVariable() {
        mList = new ArrayList<>();
        HomeHotEntity homeHotEntity1 = new HomeHotEntity(HomeHotEntity.NORMAL_LIVE, true, CommonConst.photo, "嘻哈朋克", "新西兰", "Japanese", 45212, "欢迎来到我的直播间", "电音狂魔", "双性变", CommonConst.photo, false, false, 0, false, false, 0, 0, 0, false);//pgc
        for (int i = 0; i < 30; i++) {
            mList.add(homeHotEntity1);
        }
    }

    @Override
    protected void initView() {
        rlBillRank.setLayoutManager(new LinearLayoutManager(this));
        rlBillRank.setAdapter(new BillRankAdapter(mList));
    }

    @Override
    protected void bindData() {

    }

    @Override
    protected void bindData4NoNet() {

    }

    public static void show(Context context) {
        context.startActivity(new Intent(context, BillRankActivity.class));
    }
}
