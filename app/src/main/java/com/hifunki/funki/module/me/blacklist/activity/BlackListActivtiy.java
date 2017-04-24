package com.hifunki.funki.module.me.blacklist.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.module.me.blacklist.adapter.BlackListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class BlackListActivtiy extends BaseActivity{

    @BindView(R.id.rl_blacklist)
    RecyclerView rlBlackList;
    private List<String> str;

    public static void show(Context context) {
        context.startActivity(new Intent(context,BlackListActivtiy.class));
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_back_list;
    }

    @Override
    protected void initVariable() {
        str = new ArrayList<>();
        str.add("a");
        str.add("a");
        str.add("a");
        str.add("a");
        str.add("a");
        str.add("a");
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initAdapter() {
        super.initAdapter();
        BlackListAdapter adapter=new BlackListAdapter(R.layout.item_blacklist,str);
        rlBlackList.setLayoutManager(new LinearLayoutManager(this));
        rlBlackList.setAdapter(adapter);
    }

    @Override
    protected void bindData() {

    }

    @Override
    protected void bindData4NoNet() {

    }

}
