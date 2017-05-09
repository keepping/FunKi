package com.hifunki.funki.module.photo.gallery.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.module.photo.gallery.entity.PhotoInfo;
import com.hifunki.funki.module.photo.gallery.viewPager.SamplePagerAdapter;
import com.hifunki.funki.widget.HackyViewPager;
import com.hifunki.funki.widget.bar.TopBarView;

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
public class GalleryVpActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.top_pic)
    TopBarView topBarView;
    @BindView(R.id.vp_gallery_photo)
    HackyViewPager vpGalleryPhoto;
    @BindView(R.id.tv_gallery_num)
    TextView tvGalleryNum;
    @BindView(R.id.activity_gallery_vp)
    RelativeLayout activityGalleryVp;
    private int anInt;
    private int mSize;
    private ArrayList<PhotoInfo> photoInfoList;
    static String KEY_GALLERY_PHOTO_NUMBER = "key_gallery_photo_number";
    static String KEY_GALLERY_PHOTO_ALL_NUMBER = "key_gallery_photo_all_number";

    public static void show(Context context, int position, ArrayList<PhotoInfo> photoInfoList) {
        Intent intent = new Intent(context, GalleryVpActivity.class);
        intent.putExtra(GalleryVpActivity.KEY_GALLERY_PHOTO_NUMBER, position);
        intent.putParcelableArrayListExtra(GalleryVpActivity.KEY_GALLERY_PHOTO_ALL_NUMBER, photoInfoList);
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
    protected void initVariable() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        anInt = extras.getInt(GalleryVpActivity.KEY_GALLERY_PHOTO_NUMBER);
        photoInfoList = extras.getParcelableArrayList(GalleryVpActivity.KEY_GALLERY_PHOTO_ALL_NUMBER);
        mSize = photoInfoList.size();
    }

    @Override
    protected void initTitleBar() {
        TextView titileText = topBarView.getTitileText();
        String number = String.format((String) titileText.getText(), anInt, mSize);
        titileText.setText(number);
    }

    @Override
    protected void initView() {
        initViewPager();
    }

    private void initViewPager() {
        Log.e("test", "initViewPager: " + photoInfoList);
        vpGalleryPhoto.setAdapter(new SamplePagerAdapter(GalleryVpActivity.this, photoInfoList));

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initAdapter() {

    }

    @Override
    protected void bindData() {

    }

    @Override
    protected void bindData4NoNet() {

    }

    @OnClick({R.id.activity_gallery_vp})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.vp_gallery_photo:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();
    }
}
