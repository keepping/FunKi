package com.hifunki.funki.module.me.blacklist.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.me.blacklist.adapter.BlackListAdapter.java
 * @link
 * @since 2017-04-24 11:40:40
 */
public class BlackListAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public BlackListAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
