package com.hifunki.funki.module.login.adapter.pacific;

import android.view.View;

import java.util.List;

/**
 * AbsAdapter for AdapterView , like ListView,GridView and Spinner
 */
public final class AbsAdapter extends BaseAbsAdapter<SimpleItem, ViewHolder> {
    public AbsAdapter() {
        super();
    }

    public AbsAdapter(int viewTypeCount) {
        super(viewTypeCount);
    }

    public AbsAdapter(List<SimpleItem> data, int viewTypeCount) {
        super(data, viewTypeCount);
    }

    @Override
    protected ViewHolder createViewHolder(View convertView) {
        return new ViewHolder(convertView);
    }
}
