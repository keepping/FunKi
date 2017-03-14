package com.hifunki.funki.base.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.hifunki.funki.library.util.ListUtil;

import java.util.ArrayList;

/**
 * Created by dell on 2017/2/23.
 */

public class PagerBaseAdapter<T extends View> extends PagerAdapter {
    private ArrayList<T> viewList;

    public PagerBaseAdapter(ArrayList<T> viewList) {
        this.viewList = viewList;
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView(viewList.get(position));
    }

    @Override
    public Object instantiateItem(View container, int position) {
        ((ViewPager) container).addView(viewList.get(position));
        return viewList.get(position);
    }

    public View getCurrentView(int index) {
        if (ListUtil.isNotEmpty(viewList) && (viewList.size() > index)) {
            return viewList.get(index);
        }
        return null;
    }
}
