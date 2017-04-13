package com.hifunki.funki.module.photo.personal.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.module.photo.personal.adapter.PersonalGalleryAdapter;
import com.hifunki.funki.module.photo.personal.fragment.PersonalPhotoFragment;
import com.hifunki.funki.module.photo.personal.fragment.PersonalSercetFragment;
import com.hifunki.funki.widget.bar.TopBarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PersonalGalleryActivity extends BaseActivity implements PersonalPhotoFragment.OnFragmentInteractionListener, PersonalSercetFragment.OnFragmentInteractionListener {

    @BindView(R.id.topView)
    TopBarView topView;
    @BindView(R.id.tb_personal)
    TabLayout tbPersonal;
    @BindView(R.id.vp_personal)
    ViewPager vpPersonal;
    private List<String> mTabTitle;
    private List<Fragment> mListFragment;

    @Override
    protected int getViewResId() {
        return R.layout.activity_personal_gallery;
    }

    @Override
    protected void initVariable() {
        mTabTitle = new ArrayList<>();
        mTabTitle.add(getString(R.string.personal_photo));
        mTabTitle.add(getString(R.string.personal_sercet_photo));

        tbPersonal.addTab(tbPersonal.newTab().setText(mTabTitle.get(0)));
        tbPersonal.addTab(tbPersonal.newTab().setText(mTabTitle.get(1)));
        mListFragment = new ArrayList<>();
        mListFragment.add(PersonalPhotoFragment.newInstance("a", "a"));
        mListFragment.add(PersonalPhotoFragment.newInstance("a", "a"));

    }

    @Override
    protected void initView() {
        PersonalGalleryAdapter personalGalleryAdapter = new PersonalGalleryAdapter(getSupportFragmentManager(), mListFragment, mTabTitle);
        vpPersonal.setAdapter(personalGalleryAdapter);
        tbPersonal.setupWithViewPager(vpPersonal);
    }

    @Override
    protected void bindData() {

    }

    @Override
    protected void bindData4NoNet() {

    }

    public static void show(Context context) {
        context.startActivity(new Intent(context, PersonalGalleryActivity.class));
    }


    @OnClick({})
    public void onClick(View view) {
        switch (view.getId()) {


        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
