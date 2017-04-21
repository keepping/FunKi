package com.hifunki.funki.module.room.adpater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hifunki.funki.module.room.fragment.RoomDymicFragment;
import com.hifunki.funki.module.room.fragment.RoomLiveFragment;

import java.util.List;

/**
 * 他人个人空间 viewPage -->apapter
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.room.adpater.RoomVPAdapter.java
 * @link
 * @since 2017-03-24 10:09:09
 */
public class RoomVPAdapter extends FragmentPagerAdapter {

    private List<String> mTabTitles;

    public RoomVPAdapter(FragmentManager fm, List<String> mTabTitles) {
        super(fm);
        this.mTabTitles = mTabTitles;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return RoomDymicFragment.newInstance("a", "a");
        }
        return RoomLiveFragment.newInstance("a", "a");
    }

    @Override
    public int getCount() {
        return mTabTitles.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return mTabTitles.get(position);

    }
}
