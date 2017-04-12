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
        Log.e(TAG, "getCount: ");
        return listFragment.size();
    }


    @Override
    public void startUpdate(ViewGroup container) {
        Log.e(TAG, "startUpdate: ");
        super.startUpdate(container);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Log.e(TAG, "instantiateItem: ");
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Log.e(TAG, "destroyItem: ");
        super.destroyItem(container, position, object);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        Log.e(TAG, "setPrimaryItem: ");
        super.setPrimaryItem(container, position, object);
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        Log.e(TAG, "finishUpdate: ");
        super.finishUpdate(container);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        Log.e(TAG, "isViewFromObject: ");
        return super.isViewFromObject(view, object);
    }

    @Override
    public Parcelable saveState() {
        Log.e(TAG, "saveState: ");
        return super.saveState();
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
        Log.e(TAG, "restoreState: ");
        super.restoreState(state, loader);
    }
}
