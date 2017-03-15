package com.hifunki.funki.module.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hifunki.funki.module.home.fragment.HomeFollowFragment;
import com.hifunki.funki.module.home.fragment.HomeHotFragment;
import com.hifunki.funki.module.home.fragment.HomeNewFragment;

/**
 * 首页ViewPager
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.home.adapter.HomePagerAdapter.java
 * @link
 * @since 2017-03-13 16:39:39
 */
public class HomePagerAdapter extends FragmentPagerAdapter {

    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return HomeFollowFragment.newInstance("t", "a");
        } else if (position == 1) {
            return HomeHotFragment.newInstance("t", "a");
        }
        return HomeNewFragment.newInstance("t", "a");

    }


    @Override
    public int getCount() {
        return 3;
    }
}
