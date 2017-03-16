package com.hifunki.funki.base.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.hifunki.funki.util.DisplayUtil;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.base.activity.BaseCoordinatorActivity.java
 * @link
 * @since 2017-03-16 18:30:30
 */
public abstract class BaseCoordinatorActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int statusBarHeight = DisplayUtil.getStatusBarHeight(this);
        int x = getResources().getDimensionPixelSize(statusBarHeight);
        System.out.print("s"+x);
        Log.e("test", "onCreate: "+x );
    }
}
