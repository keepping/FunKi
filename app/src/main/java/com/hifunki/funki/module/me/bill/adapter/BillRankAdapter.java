package com.hifunki.funki.module.me.bill.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hifunki.funki.R;
import com.hifunki.funki.module.home.entity.HomeHotEntity;

import java.util.List;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.me.bill.adapter.BillRankAdapter.java
 * @link
 * @since 2017-04-19 11:33:33
 */
public class BillRankAdapter extends BaseMultiItemQuickAdapter<HomeHotEntity, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public BillRankAdapter(List<HomeHotEntity> data) {
        super(data);
        addItemType(HomeHotEntity.NORMAL_LIVE, R.layout.item_bill_rank);

    }

    @Override
    protected void convert(BaseViewHolder helper, HomeHotEntity item) {

    }
}
