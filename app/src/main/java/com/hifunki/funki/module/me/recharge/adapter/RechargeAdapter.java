package com.hifunki.funki.module.me.recharge.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hifunki.funki.R;
import com.hifunki.funki.module.me.recharge.entity.RechargeItem;

import java.util.List;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.me.recharge.adapter.RechargeAdapter.java
 * @link
 * @since 2017-03-21 14:29:29
 */
public class RechargeAdapter extends BaseMultiItemQuickAdapter<RechargeItem,BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public RechargeAdapter(List<RechargeItem> data) {
        super(data);

        addItemType(RechargeItem.INPUT, R.layout.item_recharge_input);
        addItemType(RechargeItem.NORMAL, R.layout.item_recharge_normal);//大图
    }

    @Override
    protected void convert(BaseViewHolder helper, RechargeItem item) {
        switch (item.getItemType()){
            case RechargeItem.INPUT:
//                LinearLayout llInput=helper.getView(R.id.ll_recharge_input);
                helper.addOnClickListener(R.id.ll_recharge_input);
                break;
            case RechargeItem.NORMAL:
                helper.addOnClickListener(R.id.ll_recharge_normal);
                break;
        }
    }
}
