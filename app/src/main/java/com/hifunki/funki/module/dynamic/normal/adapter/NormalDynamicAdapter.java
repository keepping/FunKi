package com.hifunki.funki.module.dynamic.normal.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hifunki.funki.module.dynamic.normal.fragment.NormalDynamicFragment;

import java.util.List;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.dynamic.normal.adapter.NormalDynamicAdapter.java
 * @link
 * @since 2017-04-20 14:56:56
 */
public class NormalDynamicAdapter extends FragmentPagerAdapter {

    private List<String> mStrings;

    public NormalDynamicAdapter(FragmentManager fm, List<String> mTabTitle) {
        super(fm);
        this.mStrings = mTabTitle;
    }

    @Override
    public Fragment getItem(int position) {
        return NormalDynamicFragment.newInstance("f", "f");
    }

    @Override
    public int getCount() {
        return mStrings.size();
    }
}
