package com.hifunki.funki.ui.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hifunki.funki.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.ui.activity.SelectImageActivity.java
 * @link
 * @since 2017-02-28 09:42:42
 */
public class SelectImageActivity extends BaseActivity {


    @BindView(R.id.iv_selectimage)
    ImageView ivSelectimage;
    @BindView(R.id.et_nickname)
    EditText etNickname;
    @BindView(R.id.tv_complete)
    TextView tvComplete;
    @BindView(R.id.activity_select_image)
    RelativeLayout activitySelectImage;

    @Override
    protected int getViewResId() {
        return R.layout.activity_select_image;
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initTitleBar() {

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

    @OnClick({R.id.iv_selectimage, R.id.et_nickname, R.id.tv_complete, R.id.activity_select_image})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_selectimage:
                break;
            case R.id.et_nickname:
                break;
            case R.id.tv_complete:
                break;
            case R.id.activity_select_image:
                break;
        }
    }
}
