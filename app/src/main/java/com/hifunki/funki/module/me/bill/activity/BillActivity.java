package com.hifunki.funki.module.me.bill.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.common.CommonConst;
import com.hifunki.funki.module.me.bill.adapter.BillAdapter;
import com.hifunki.funki.module.me.bill.entity.BillEntity;
import com.hifunki.funki.widget.bar.TopBarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.hifunki.funki.base.application.ApplicationMain.getContext;

/**
 * 个人中心账单页面
 *
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
        Log.e("BillActivity", "initVariable: ");
        entityList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            BillEntity entity = new BillEntity("今天", "12:36", 121212, "直播收入");
            entityList.add(entity);
        }
    }

    @Override
    protected void initTitleBar() {

    }

    @Override
    protected void initView() {
        adapter = new BillAdapter(R.layout.item_bill, entityList);
        rlBill.setLayoutManager(new LinearLayoutManager(this));

        Log.e("BillActivity", "initView: ");

    }

    @Override
    protected void initListener() {
        Log.e("BillActivity", "initListener: ");
        topBarView.getMenuText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BillFilterActivity.show(getContext());
            }
        });
        rlBill.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                super.onItemChildClick(adapter, view, position);
                switch (view.getId()) {
                    case R.id.tv_more:
                        Log.e("BillActivity", "onItemChildClick: ");
                        BillRankActivity.show(getContext());//跳转到直播收入详情页面
                        break;
                }
            }
        });
    }

    @Override
    protected void initAdapter() {
        Log.e("BillActivity", "initAdapter: ");
        rlBill.setAdapter(adapter);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_biil_head, null);
        //加载头部图片
        ImageView iv = (ImageView) view.findViewById(R.id.iv_bill_bg);
        Glide.with(this).load(CommonConst.IMAGE_VIEW).into(iv);

        adapter.addHeaderView(view);
    }

    @Override
    protected void bindData() {

    }

    @Override
    protected void bindData4NoNet() {

    }
}
