package com.hifunki.funki.module.me.follow.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.me.follow.adapter.MyFollowAdapter.java
 * @link
 * @since 2017-04-24 12:57:57
 */
public class MyFollowAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public MyFollowAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
