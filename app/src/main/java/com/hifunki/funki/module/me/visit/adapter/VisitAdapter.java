package com.hifunki.funki.module.me.visit.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hifunki.funki.module.me.visit.fragment.VisitFragment;

import java.util.List;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.me.visit.adapter.VisitAdapter.java
 * @link
 * @since 2017-04-21 10:22:22
 */
public class VisitAdapter extends FragmentPagerAdapter {

    private List<String> mTitle;

    public VisitAdapter(FragmentManager fm, List<String> mTitle) {
        super(fm);
        this.mTitle = mTitle;
    }

    @Override
    public Fragment getItem(int position) {
        return VisitFragment.newInstance("a", "a");
    }

    @Override
    public int getCount() {
        return 2;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle.get(position);
    }
}
