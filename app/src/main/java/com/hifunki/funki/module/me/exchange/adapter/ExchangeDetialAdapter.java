package com.hifunki.funki.module.me.exchange.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.me.exchange.adapter.ExchangeDetialAdapter.java
 * @link
 * @since 2017-05-06 16:12:12
 */
public class ExchangeDetialAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public ExchangeDetialAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
