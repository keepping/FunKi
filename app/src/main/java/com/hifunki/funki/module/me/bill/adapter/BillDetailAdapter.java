package com.hifunki.funki.module.me.bill.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.me.bill.adapter.BillDetailAdapter.java
 * @link
 * @since 2017-04-19 14:47:47
 */
public class BillDetailAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public BillDetailAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
