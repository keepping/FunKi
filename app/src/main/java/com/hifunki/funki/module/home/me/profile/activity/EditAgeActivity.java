package com.hifunki.funki.module.home.me.profile.activity;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.home.me.profile.activity.EditAgeActivity.java
 * @link
 * @since 2017-03-31 15:31:31
 */
public class EditAgeActivity extends BaseActivity {

    @Override
    protected int getViewResId() {
        return R.layout.activity_edit_age;
    }

    @Override
    protected void initDatas() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        String txtTime = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH) + "";

    }

    @Override
    protected void initView() {

    }

    public static void show(EditProfileActivity editProfileActivity) {


    }
}
