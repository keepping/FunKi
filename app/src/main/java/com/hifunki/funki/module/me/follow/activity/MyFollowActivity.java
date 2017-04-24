package com.hifunki.funki.module.me.follow.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.module.me.follow.adapter.MyFollowAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MyFollowActivity extends BaseActivity {

    @BindView(R.id.rl_my_follow)
    RecyclerView rlMyFollow;
    private List<String> str;

    public static void show(Context context) {
        context.startActivity(new Intent(context, MyFollowActivity.class));
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_my_follow;
    }

    @Override
    protected void initVariable() {
        str = new ArrayList<>();
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
        rlMyFollow.setLayoutManager(new LinearLayoutManager(this));
        MyFollowAdapter adapter = new MyFollowAdapter(R.layout.item_my_follow, str);
        rlMyFollow.setAdapter(adapter);
    }

    @Override
    protected void bindData() {

    }

    @Override
    protected void bindData4NoNet() {

    }

}
