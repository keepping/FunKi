package com.hifunki.funki.module.live.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.hifunki.funki.module.live.fragment.RoomFragment;

/**
 * 在此写用途
 *
 * @author yinhaoxiang
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.live.adapter.LiveVerticalAdapter.java
 * @link
 * @since 2017-03-28 13:40:40
 */
public class LiveVerticalAdapter extends FragmentPagerAdapter{


    public LiveVerticalAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new RoomFragment();
    }

    @Override
    public int getCount() {
        return 9;
    }
}
