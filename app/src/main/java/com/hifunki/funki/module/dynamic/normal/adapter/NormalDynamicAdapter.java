package com.hifunki.funki.module.dynamic.normal.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hifunki.funki.module.dynamic.normal.fragment.NormalDynamicFragment;

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


    public NormalDynamicAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return NormalDynamicFragment.newInstance(0, "f");
            case 1:
                return NormalDynamicFragment.newInstance(1, "f");
            case 2:
                return NormalDynamicFragment.newInstance(2, "f");
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
