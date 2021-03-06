package com.hifunki.funki.module.me.bill.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.common.BundleConst;
import com.hifunki.funki.widget.flowlayout.FlowLayout;
import com.hifunki.funki.widget.flowlayout.TagAdapter;
import com.hifunki.funki.widget.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.me.bill.activity.BiilFilterActivity1.java
 * @link
 * @since 2017-04-19 10:43:43
 */
public class BillFilterActivity extends BaseActivity {

    @BindView(R.id.bill_tfl)
    TagFlowLayout mFlowLayout;
    private TagAdapter<String> mAdapter;
    private List<String> stringList;

    final static int REQUEST_BILL_TO_BILL_FILTER = 2;

    @Override
    protected int getViewResId() {
        return R.layout.activity_biil_filter;
    }

    public static void show(Activity mActivity, Context context) {
        mActivity.startActivityForResult(new Intent(context, BillFilterActivity.class), BillFilterActivity.REQUEST_BILL_TO_BILL_FILTER);
    }

    @Override
    protected void initVariable() {
        stringList = new ArrayList<>();
        stringList.add("金币");
        stringList.add("砖石");
        stringList.add("余额");
        stringList.add("奖励");
        stringList.add("刷单");

    }

    @Override
    protected void initView() {
        final LayoutInflater mInflater = LayoutInflater.from(this);
        mFlowLayout.setAdapter(mAdapter = new TagAdapter<String>(stringList) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.layout_bill_filter_tag,
                        mFlowLayout, false);
                tv.setText(s);

                return tv;
            }
        });
    }

    @Override
    protected void initListener() {
        super.initListener();
        mFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                Log.e("BillFilterActivity", "onTagClick: " + position);
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("bundle", position);
                intent.putExtra("intent", bundle);
                intent.setClass(BillFilterActivity.this, BillActivity.class);
                setResult(BillFilterActivity.REQUEST_BILL_TO_BILL_FILTER, intent);
                finish();
                return true;
            }
        });
    }

    @Override
    protected void bindData() {

    }

    @Override
    protected void bindData4NoNet() {

    }


    @Override
    public void onBackPressed() {
        Intent backIntent = new Intent(BillFilterActivity.this, BillActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("bundle", 0);
        backIntent.putExtra("intent", bundle);
        BillFilterActivity.this.setResult(1, backIntent);
        BillFilterActivity.this.finish();
        finish();
    }

}
