package com.hifunki.funki.ui.activity;

import android.widget.RelativeLayout;

import com.hifunki.funki.R;
import com.hifunki.funki.ui.widget.ToolEditTitleBar;
import com.hifunki.funki.ui.widget.ToolTitleBar;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.ui.activity.CountryResultActivity.java
 * @link
 * @since 2017-02-27 14:55:55
 */
public class CountryResultActivity extends BaseActivity {


    @Override
    protected int getViewResId() {
        return R.layout.activity_country_result;
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initTitleBar() {


        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.activity_country_result);

        ToolEditTitleBar.showLeftButton(this, relativeLayout, ToolTitleBar.BTN_TYPE_IMAGE, R.drawable.iv_back, null);
        ToolEditTitleBar.showCenterEditText(this, relativeLayout, null, null);
        ToolEditTitleBar.showRightButton(this, relativeLayout, R.layout.activity_login_search, ToolTitleBar.BTN_TYPE_IMAGE, R.drawable.iv_close, null);
        //右边的搜索
        ToolEditTitleBar.showRightButton(this, relativeLayout, R.layout.activity_login_search, ToolTitleBar.BTN_TYPE_TEXT, R.string.search, null);
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
}
