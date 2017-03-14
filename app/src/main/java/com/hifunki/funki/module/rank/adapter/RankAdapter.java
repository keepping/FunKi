package com.hifunki.funki.module.rank.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hifunki.funki.module.rank.fragment.RankAnchorFragment;
import com.hifunki.funki.module.rank.fragment.RankRickFragment;

import java.util.List;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.rank.adapter.RankAdapter.java
 * @link
 * @since 2017-03-14 11:02:02
 */
public class RankAdapter extends FragmentPagerAdapter {

    private List<String> mTabTitle;

    public RankAdapter(FragmentManager fm, List<String> mTabTitle) {
        super(fm);
        this.mTabTitle = mTabTitle;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            RankAnchorFragment.newInstance("a", "a");
        } else if (position == 1) {
            RankRickFragment.newInstance("a", "a");
        }
        return null;
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

