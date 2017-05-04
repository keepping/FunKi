package com.hifunki.funki.module.msg.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hifunki.funki.module.msg.fragment.ContactsFansFragment;
import com.hifunki.funki.module.msg.fragment.ContactsFriendFragment;

import java.util.List;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.msg.adapter.MsgContactsAdapter.java
 * @link
 * @since 2017-05-04 09:57:57
 */
public class MsgContactsAdapter extends FragmentPagerAdapter {

    private List<String> strings;

    public MsgContactsAdapter(FragmentManager fm, List<String> strings) {
        super(fm);
        this.strings = strings;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ContactsFriendFragment.newInstance("a", "a");
            case 1:
                return ContactsFansFragment.newInstance("a", "a");
        }
        return null;
    }

    @Override
    public int getCount() {
        return strings.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return strings.get(position);
    }
}
