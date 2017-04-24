package com.hifunki.funki.module.home.adapter;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 首页ViewPager
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.home.adapter.HomePagerAdapter.java
 * @link
 * @since 2017-03-13 16:39:39
 */
public class HomePagerAdapter extends FragmentStatePagerAdapter {

    private String TAG = "HomePagerAdapter";
    private List<Fragment> listFragment;

    public HomePagerAdapter(FragmentManager fm, List<Fragment> listFragment) {
        super(fm);
        Log.e(TAG, "HomePagerAdapter: ");
        this.listFragment = listFragment;
    }

    @Override
    public Fragment getItem(int position) {
        Log.e(TAG, "getItem: ");
        if (position == 0) {
            Log.e(TAG, "getItem0: ");
            return listFragment.get(0);
        } else if (position == 1) {
            Log.e(TAG, "getItem1: ");
            return listFragment.get(1);
        } else if (position == 2) {
            Log.e(TAG, "getItem2: ");
            return listFragment.get(2);
        }
        return null;
    }

    @Override
    public int getCount() {

        return listFragment.size();
    }



}
