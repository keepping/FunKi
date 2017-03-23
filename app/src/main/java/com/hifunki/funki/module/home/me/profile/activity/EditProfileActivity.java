package com.hifunki.funki.module.home.me.profile.activity;

import android.content.Context;
import android.content.Intent;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;

/**
 * 编辑个人资料
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.home.me.fans.EditProfileActivity.java
 * @link
 * @since 2017-03-23 12:01:01
 */
public class EditProfileActivity extends BaseActivity {

    public static void show(Context context) {
        context.startActivity(new Intent(context,EditProfileActivity.class));
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_edit_profile;
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initView() {

    }


}
