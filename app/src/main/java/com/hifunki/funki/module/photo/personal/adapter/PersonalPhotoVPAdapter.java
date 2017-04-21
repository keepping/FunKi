package com.hifunki.funki.module.photo.personal.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.photo.personal.adapter.PersonalPhotoVPAdapter.java
 * @link
 * @since 2017-04-13 10:56:56
 */
public class PersonalPhotoVPAdapter extends FragmentPagerAdapter {

    private List<Fragment> mListFragmnet;
    private List<String> mTabTitle;

    public PersonalPhotoVPAdapter(FragmentManager fm, List<Fragment> mListFragment, List<String> mTabTitle) {
        super(fm);
        this.mListFragmnet = mListFragment;
        this.mTabTitle = mTabTitle;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return mListFragmnet.get(0);
            case 1:
                return mListFragmnet.get(1);
        }
        return null;
    }

    @Override
    public int getCount() {
        return mListFragmnet.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabTitle.get(position);
    }
}
