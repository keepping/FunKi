package com.hifunki.funki.module.photo.gallery.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;

import com.hifunki.funki.R;
import com.hifunki.funki.library.base.activity.BaseTitleActivity;
import com.hifunki.funki.library.common.BundleConst;
import com.hifunki.funki.library.base.adapter.PagerBaseAdapter;
import com.hifunki.funki.module.login.widget.ToolTitleBar;
import com.hifunki.funki.module.photo.gallery.entity.PhotoInfo;
import com.hifunki.funki.module.photo.gallery.widget.LayoutGalleryPhoto;

import java.util.ArrayList;

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
public class GalleryVpActivity extends BaseTitleActivity implements View.OnClickListener {

    @BindView(R.id.vp_gallery_photo)
    ViewPager vpGalleryPhoto;
    @BindView(R.id.activity_gallery_vp)
    RelativeLayout activityGalleryVp;
    private int anInt;
    private int mSize;
    private ArrayList<RelativeLayout> mTabViews;
    private ArrayList<PhotoInfo> photoInfoList;

    public static void show(Context context, int position, ArrayList<PhotoInfo> photoInfoList) {
        Intent intent = new Intent(context, GalleryVpActivity.class);
        intent.putExtra(BundleConst.KEY_GALLERY_PHOTO_NUMBER, position);
        intent.putParcelableArrayListExtra(BundleConst.KEY_GALLERY_PHOTO_ALL_NUMBER, photoInfoList);
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
        photoInfoList = extras.getParcelableArrayList(BundleConst.KEY_GALLERY_PHOTO_ALL_NUMBER);
        mSize = photoInfoList.size();
    }

    @Override
    protected void initTitleBar() {
        ToolTitleBar.showLeftButton(this, activityGalleryVp, ToolTitleBar.BTN_TYPE_IMAGE, R.drawable.iv_back, this);

        String number = String.format(getString(R.string.gallery_photo), anInt, mSize);

        ToolTitleBar.showCenterButton(this, activityGalleryVp, ToolTitleBar.BTN_TYPE_TEXT, number, null);

        ToolTitleBar.showRightButtonMsg(this, activityGalleryVp, R.string.confirm, this);

    }

    @Override
    protected void initView() {
        initViewPager();
    }

    private void initViewPager() {
        mTabViews = new ArrayList<>();
        if (!photoInfoList.isEmpty()) {
            for (int i = 0; i < photoInfoList.size(); i++) {
                LayoutGalleryPhoto layoutGalleryPhoto = new LayoutGalleryPhoto(this, photoInfoList, i);
                mTabViews.add(layoutGalleryPhoto);
            }
        }
        //获取第一个视图

        vpGalleryPhoto.setAdapter(new PagerBaseAdapter<>(mTabViews));
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initAdapter() {

    }

    @OnClick({R.id.activity_gallery_vp})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.vp_gallery_photo:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();
    }
}
