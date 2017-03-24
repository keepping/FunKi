package com.hifunki.funki.base.adapter;

import android.widget.BaseAdapter;

/**
 * 在此写用途
 *
 * @author yinhaoxiang
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.base.adapter.EmptyAdapter.java
 * @link
 * @since 2017-03-24 18:49:49
 */
public abstract class EmptyAdapter extends BaseAdapter {

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
