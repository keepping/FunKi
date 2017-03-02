package com.hifunki.funki.module.login.adapter.pacific;

import android.view.View;

import java.util.List;

/**
 * PagerAdapter for ViewPager
 */
public final class PagerAdapter2 extends BasePagerAdapter2<SimpleItem, ViewHolder> {

    public PagerAdapter2() {
        super();
    }

    public PagerAdapter2(List<SimpleItem> data) {
        super(data);
    }

    @Override
    protected ViewHolder createViewHolder(View convertView) {
        return new ViewHolder(convertView);
    }
}
