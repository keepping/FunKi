package com.hifunki.funki.module.rank.me.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hifunki.funki.module.rank.me.fragment.RankPresentFragment;

import java.util.List;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.rank.me.adapter.RankAdapter.java
 * @link
 * @since 2017-03-20 14:32:32
 */
public class RankAdapter extends FragmentPagerAdapter {

    private List<String> mTabTitle;

    public RankAdapter(FragmentManager fm, List<String> mTabTitle) {
        super(fm);
        this.mTabTitle = mTabTitle;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return RankPresentFragment.newInstance("t", "a");
            case 1:
                return RankPresentFragment.newInstance("t", "a");
            default:
                return RankPresentFragment.newInstance("t", "a");
        }
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
