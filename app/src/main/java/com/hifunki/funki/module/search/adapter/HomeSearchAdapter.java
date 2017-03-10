package com.hifunki.funki.module.search.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hifunki.funki.module.home.fragment.UserListFragment;

import java.util.List;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.search.adapter.HomeSearchAdapter.java
 * @link
 * @since 2017-03-10 10:25:25
 */
public class HomeSearchAdapter extends FragmentPagerAdapter {
    private final List<String> mTabTitle;

    public HomeSearchAdapter(FragmentManager fm, List<String> mTabTitle) {
        super(fm);
        this.mTabTitle = mTabTitle;
    }

    @Override
    public Fragment getItem(int position) {
        return UserListFragment.newInstance("t", "a");
    }

    @Override
    public int getCount() {
        return mTabTitle.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabTitle.get(position);

    }
}
