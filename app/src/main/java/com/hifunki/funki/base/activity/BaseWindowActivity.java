package com.hifunki.funki.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;

/**
 * 屏幕常亮activity
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.base.activity.BaseWindowActivity.java
 * @link
 * @since 2017-04-10 17:18:18
 */
public abstract  class BaseWindowActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
}
