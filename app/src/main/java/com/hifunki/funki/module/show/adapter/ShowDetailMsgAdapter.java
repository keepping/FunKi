package com.hifunki.funki.module.show.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.show.adapter.ShowDetailMsgAdapter.java
 * @link
 * @since 2017-03-21 17:33:33
 */
public class ShowDetailMsgAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public ShowDetailMsgAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
