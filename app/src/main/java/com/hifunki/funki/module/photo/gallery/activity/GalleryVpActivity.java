package com.hifunki.funki.module.photo.gallery.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.common.BundleConst;
import com.hifunki.funki.module.login.widget.ToolTitleBar;
import com.hifunki.funki.util.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.photo.gallery.activity.GalleryVpActivity.java
 * @link
 * @since 2017-03-03 17:36:36
 */
public class GalleryVpActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.iv_gallery_photo)
    ImageView ivGalleryPhoto;
    @BindView(R.id.vp_gallery_photo)
    ViewPager vpGalleryPhoto;
    @BindView(R.id.activity_gallery_vp)
    RelativeLayout activityGalleryVp;
    private int anInt;
    private int size;

    public static void show(Context context, int position, int size) {
        Intent intent = new Intent(context, GalleryVpActivity.class);
        intent.putExtra(BundleConst.KEY_GALLERY_PHOTO_NUMBER, position);
        intent.putExtra(BundleConst.KEY_GALLERY_PHOTO_ALL_NUMBER, size);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_gallery_vp;
    }

    @Override
    protected void initDatas() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        anInt = extras.getInt(BundleConst.KEY_GALLERY_PHOTO_NUMBER);
        size = extras.getInt(BundleConst.KEY_GALLERY_PHOTO_ALL_NUMBER);
    }

    @Override
    protected void initTitleBar() {
        StatusBarUtil.setStatusBarBackground(this, R.drawable.iv_bg_status);
        ToolTitleBar.showLeftButton(this, activityGalleryVp, ToolTitleBar.BTN_TYPE_IMAGE, R.drawable.iv_back, this);

        String number = String.format(getString(R.string.gallery_photo), anInt, size);

        ToolTitleBar.showCenterButton(this, activityGalleryVp, ToolTitleBar.BTN_TYPE_TEXT, number, null);
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

    @OnClick({R.id.iv_gallery_photo, R.id.activity_gallery_vp})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_gallery_photo:
                break;
            case R.id.vp_gallery_photo:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
