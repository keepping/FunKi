package com.hifunki.funki.module.fans.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.common.CommonConst;
import com.hifunki.funki.module.fans.adapter.FansAdapter;
import com.hifunki.funki.module.fans.entity.FansEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 个人中心-->我的粉丝
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.fans.activity.MyFans.java
 * @link
 * @since 2017-03-21 12:01:01
 */
public class MyFansActivity extends BaseActivity {

    @BindView(R.id.rl_fans)
    RecyclerView rlFans;
    private List<FansEntity> entities;

    public static void show(Context context) {
        context.startActivity(new Intent(context, MyFansActivity.class));
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_my_fans;
    }

    @Override
    protected void initDatas() {
        entities = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            FansEntity entity = new FansEntity("蜡笔小新", CommonConst.photo, 1, 56, "我是来自火星的", false);
            entities.add(entity);
        }
    }

    @Override
    protected void initView() {
        FansAdapter adapter = new FansAdapter(R.layout.item_fans,entities);
        rlFans.setLayoutManager(new LinearLayoutManager(this));
        rlFans.setAdapter(adapter);
    }

}
