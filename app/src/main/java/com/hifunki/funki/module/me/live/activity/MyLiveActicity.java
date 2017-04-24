package com.hifunki.funki.module.me.live.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.module.me.live.adapter.MyLiveAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.me.MyLiveActicity1.java
 * @link
 * @since 2017-04-17 12:36:36
 */
public class MyLiveActicity extends BaseActivity {

    @BindView(R.id.rl_my_live)
    RecyclerView rlMyLive;
    private List<String> str;

    @Override
    protected int getViewResId() {
        return R.layout.activity_my_live;
    }

    @Override
    protected void initVariable() {
        str = new ArrayList<>();
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
        rlMyLive.setLayoutManager(new LinearLayoutManager(this));
        MyLiveAdapter adapter=new MyLiveAdapter(R.layout.item_mylive,str);
        rlMyLive.setAdapter(adapter);
    }

    @Override
    protected void bindData() {

    }

    @Override
    protected void bindData4NoNet() {

    }

    public static void show(Context context) {
        context.startActivity(new Intent(context,MyLiveActicity.class));
    }
}
