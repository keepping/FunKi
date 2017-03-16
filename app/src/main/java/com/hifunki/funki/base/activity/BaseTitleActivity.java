package com.hifunki.funki.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hifunki.funki.R;
import com.hifunki.funki.util.StatusBarUtil;

/**
 * BaseTitleActivity
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.base.activity.BaseTitleActivity.java
 * @link
 * @since 2017-02-23 20:24:24
 */
public abstract class BaseTitleActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


      //  StatusBarUtil.setStatusBarBackground(this, R.drawable.iv_login_top_status_bg);

    }

}
