package com.hifunki.funki.module.me.bill.activity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
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

    @Override
    protected int getViewResId() {
        return R.layout.activity_biil_filter;
    }

    public static void show(Context context) {
        context.startActivity(new Intent(context, BillFilterActivity.class));
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
    protected void bindData() {

    }

    @Override
    protected void bindData4NoNet() {

    }


}
