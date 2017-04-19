package com.hifunki.funki.module.me.bill.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hifunki.funki.R;
import com.hifunki.funki.module.me.bill.entity.BillEntity;

import java.util.List;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.me.bill.adapter.BillAdapter.java
 * @link
 * @since 2017-03-20 17:52:52
 */
public class BillAdapter extends BaseQuickAdapter<BillEntity,BaseViewHolder> {

    public BillAdapter(int layoutResId, List<BillEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BillEntity item) {
        helper.addOnClickListener(R.id.tv_more);
    }
}
