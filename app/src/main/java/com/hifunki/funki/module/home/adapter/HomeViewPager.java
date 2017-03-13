package com.hifunki.funki.module.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 首页ViewPager
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.home.adapter.HomeViewPager.java
 * @link
 * @since 2017-03-13 16:39:39
 */
public class HomeViewPager extends FragmentPagerAdapter {

    private List<Fragment> list;

    public HomeViewPager(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            list.get(0);
//            HomeFollowFragment.newInstance("t", "a");
        } else if (position == 1) {
            list.get(1);
//            HomeHotFragment.newInstance("t", "a");
        } else if (position == 2) {
            list.get(2);
//            HomeNewFragment.newInstance("t", "a");
        }
        return null;
    }


//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//        return super.instantiateItem(container, position);
//
//    }
//
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
//
//    }

    @Override
    public int getCount() {
        return list
                .size();
    }
}
