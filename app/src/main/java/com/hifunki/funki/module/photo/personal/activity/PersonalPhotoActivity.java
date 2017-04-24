package com.hifunki.funki.module.photo.personal.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.module.photo.personal.adapter.PersonalPhotoVPAdapter;
import com.hifunki.funki.module.photo.personal.fragment.PersonalPhotoFragment;
import com.hifunki.funki.module.photo.personal.fragment.PersonalSercetFragment;
import com.hifunki.funki.module.photo.personal.inter.OnPhotoSelectAllListener;
import com.hifunki.funki.module.photo.personal.inter.OnSercetSelectAllListener;
import com.hifunki.funki.widget.bar.TopBarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PersonalPhotoActivity extends BaseActivity implements PersonalPhotoFragment.OnFragmentInteractionListener, PersonalSercetFragment.OnFragmentInteractionListener, View.OnClickListener {

    @BindView(R.id.tbv_personal_gallery)
    TopBarView topBarView;
    @BindView(R.id.tb_personal)
    TabLayout tbPersonal;
    @BindView(R.id.vp_personal)
    ViewPager vpPersonal;
    private List<String> mTabTitle;
    private List<Fragment> mListFragment;
    private static final String KEY_PERSONAL_GALLERY = "key_personal_gallery";
    public static int VALUE_ROOM_PHOTO_TO_GALLERY = 1;
    public static int VALUE_ME_PHOTO_TO_GALLERY = 2;
    private TextView firstText;
    private TextView menuText;
    private ImageView ivBack;
    private String TAG = getClass().getSimpleName();
    private int currentItem;
    private STATUS status = STATUS.NORMAL;
    private STATUS status1 = STATUS.P_SELECT_NOT_ALL;

    @Override
    protected int getViewResId() {
        return R.layout.activity_personal_gallery;
    }


    private enum STATUS {
        NORMAL,
        EDIT,

        P_SELECT_ALL,
        P_SELECT_NOT_ALL,
        S_SELECT_ALL,
        S_SELECT_NOT_ALL,

    }

    @Override
    protected void initVariable() {
        int type = getIntent().getIntExtra(KEY_PERSONAL_GALLERY, 0);

        mTabTitle = new ArrayList<>();
        mTabTitle.add(getString(R.string.personal_photo));
        mTabTitle.add(getString(R.string.personal_sercet_photo));

        tbPersonal.addTab(tbPersonal.newTab().setText(mTabTitle.get(0)));
        tbPersonal.addTab(tbPersonal.newTab().setText(mTabTitle.get(1)));
        mListFragment = new ArrayList<>();
        mListFragment.add(PersonalPhotoFragment.newInstance(type, "a"));
        mListFragment.add(PersonalSercetFragment.newInstance(type, "a"));
    }

    @Override
    protected void initView() {
        ivBack = topBarView.getFirstImageView();
        firstText = topBarView.getFirstText();
        menuText = topBarView.getMenuText();
        PersonalPhotoVPAdapter personalGalleryAdapter = new PersonalPhotoVPAdapter(getSupportFragmentManager(), mListFragment, mTabTitle);
        vpPersonal.setAdapter(personalGalleryAdapter);
        tbPersonal.setupWithViewPager(vpPersonal);
    }

    @Override
    protected void initAdapter() {
        super.initAdapter();

    }

    @Override
    protected void initListener() {
        super.initListener();
        vpPersonal.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.e(TAG, "onPageSelected: " + position);
                currentItem = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void bindData() {
    }

    @Override
    protected void bindData4NoNet() {

    }

    public static void show(Context context, int type) {
        Intent intent = new Intent(context, PersonalPhotoActivity.class);
        intent.putExtra(KEY_PERSONAL_GALLERY, type);
        context.startActivity(intent);
    }

    @OnClick({R.id.tv_left, R.id.tv_menu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_left:
                status = STATUS.NORMAL;
                refreshUI();
                break;
            case R.id.tv_menu:
                if (status == STATUS.NORMAL) {
                    status = STATUS.EDIT;
                    refreshUI();
                } else if (status == STATUS.EDIT) {
                    if (currentItem == 0) {
                        status = STATUS.P_SELECT_ALL;
                    } else {
                        status = STATUS.S_SELECT_ALL;
                    }
                    refreshUI();
                } else if (status == STATUS.S_SELECT_ALL) {
                    if (currentItem == 0) {
                        status1 = STATUS.S_SELECT_NOT_ALL;
                    } else {
                        status1 = STATUS.S_SELECT_NOT_ALL;
                    }
                    refreshUI();
                } else if (status == STATUS.S_SELECT_NOT_ALL) {
                    status = STATUS.S_SELECT_ALL;
                    refreshUI();
                } else if (status == STATUS.P_SELECT_ALL) {
                    status = STATUS.P_SELECT_NOT_ALL;
                    refreshUI();
                } else if (status == STATUS.P_SELECT_NOT_ALL) {
                    status = STATUS.P_SELECT_ALL;
                    refreshUI();
                }
                break;
        }
    }

    boolean isPhotoSelectAll = false;
    boolean isSercetSelectAll = false;

    private void refreshUI() {
        switch (status) {
            case NORMAL:
                ivBack.setVisibility(View.VISIBLE);
                firstText.setVisibility(View.INVISIBLE);
                menuText.setText("编辑");
                break;
            case EDIT:
                ivBack.setVisibility(View.INVISIBLE);
                firstText.setVisibility(View.VISIBLE);
                menuText.setText("全选");
                break;
            case P_SELECT_ALL:
                isPhotoSelectAll = true;
                onSercetSelectAllListener.selectAll(isSercetSelectAll);
                break;
            case P_SELECT_NOT_ALL:
                isPhotoSelectAll = false;
                onSercetSelectAllListener.selectAll(isSercetSelectAll);
                break;
            case S_SELECT_ALL:
                isSercetSelectAll = true;
                onSelectAllListeners.selectAll(isPhotoSelectAll);
                break;
            case S_SELECT_NOT_ALL:
                isSercetSelectAll = false;
                onSelectAllListeners.selectAll(isPhotoSelectAll);
                break;
        }
    }

    public OnPhotoSelectAllListener onSelectAllListeners;
    public OnSercetSelectAllListener onSercetSelectAllListener;

    public void setOnPhotoSelectAllListener(OnPhotoSelectAllListener selectListener) {
        this.onSelectAllListeners = selectListener;
    }

    public void setOnSercetSelectAllListener(OnSercetSelectAllListener selectListener) {
        this.onSercetSelectAllListener = selectListener;
    }

    @Override
    public void onFragmentInteraction(Uri uri, boolean isSelectAll) {
        this.isPhotoSelectAll = isSelectAll;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
