package com.hifunki.funki.module.dynamic.normal.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.dynamic.normal.adapter.DynamicCommentAdapter.java
 * @link
 * @since 2017-04-20 15:10:10
 */
public class DynamicCommentAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public DynamicCommentAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
