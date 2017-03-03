package com.hifunki.funki.module.photo.gallery.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.photo.gallery.adapter.PhotoPreviewAdapter.java
 * @link
 * @since 2017-03-03 18:32:32
 */
public class PhotoPreviewAdapter extends PagerAdapter {
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
}
