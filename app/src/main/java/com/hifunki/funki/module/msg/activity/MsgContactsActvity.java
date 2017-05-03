package com.hifunki.funki.module.msg.activity;

import android.content.Context;
import android.content.Intent;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;

public class MsgContactsActvity extends BaseActivity {

    @Override
    protected int getViewResId() {
        return R.layout.activity_msg_contacts;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {

    }

    public static void show(Context context) {
        context.startActivity(new Intent(context,MsgContactsActvity.class));
    }
}
