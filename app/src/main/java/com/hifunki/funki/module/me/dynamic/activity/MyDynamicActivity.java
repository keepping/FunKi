package com.hifunki.funki.module.me.dynamic.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;

import butterknife.BindView;

/**
 * 我的动态界面
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.me.dynamic.activity.MyDynamicActivity1.java
 * @link
 * @since 2017-04-05 14:14:14
 */
public class MyDynamicActivity extends BaseActivity {

    @BindView(R.id.rv_mydymic)
    RecyclerView rvMyDymic;

    public static void show(Context context) {
        context.startActivity(new Intent(context,MyDynamicActivity.class));
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_my_dynamic;
    }

    @Override
    protected void initVariable() {

    }



    @Override
    protected void initView() {

    }

    @Override
    protected void bindData() {

    }

    @Override
    protected void bindData4NoNet() {

    }


}
