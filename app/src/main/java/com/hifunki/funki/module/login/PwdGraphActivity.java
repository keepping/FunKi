package com.hifunki.funki.module.login;

import android.content.Context;
import android.content.Intent;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;

/**
 *
 * 图形验证码界面
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.login.PwdGraphActivity.java
 * @link
 * @since 2017-02-24 12:03:03
 */
public class PwdGraphActivity extends BaseActivity {

    public static void show(Context context) {
        context.startActivity(new Intent(context, PwdGraphActivity.class));
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_pwd_graph;
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initAdapter() {

    }

    @Override
    protected void initTitleBar() {

    }


}


