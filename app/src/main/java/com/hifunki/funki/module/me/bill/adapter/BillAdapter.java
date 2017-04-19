package com.hifunki.funki.module.me.bill.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hifunki.funki.R;
import com.hifunki.funki.common.CommonConst;
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
public class BillAdapter extends BaseMultiItemQuickAdapter<BillEntity, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public BillAdapter(List<BillEntity> data) {
        super(data);
        addItemType(BillEntity.RECHARGE, R.layout.item_bill);
        addItemType(BillEntity.RED_PACKET_IN, R.layout.item_bill_photo);
        addItemType(BillEntity.RED_PACKET_OUT, R.layout.item_bill_photo);
        addItemType(BillEntity.LIVE_COST, R.layout.item_bill_photo);
        addItemType(BillEntity.ACTIVE, R.layout.item_bill);
        addItemType(BillEntity.LIVE_INCOME, R.layout.item_bill);
        addItemType(BillEntity.PAYPAL, R.layout.item_bill_pay);
    }

    @Override
    protected void convert(BaseViewHolder helper, BillEntity item) {
        switch (item.getItemType()) {
            case BillEntity.RECHARGE:
                break;
            case BillEntity.RED_PACKET_IN:
                TextView textView = helper.getView(R.id.tv_bill_type);
                textView.setText("红包");
                Glide.with(mContext).load(CommonConst.photo).into((ImageView) helper.getView(R.id.civ_bill_photo));
                helper.addOnClickListener(R.id.ll_more);
                break;
            case BillEntity.RED_PACKET_OUT:
                break;
            case BillEntity.LIVE_COST:
                TextView textView1=helper.getView(R.id.tv_bill_type);
                textView1.setText("直播消费");
                TextView textViewCost=helper.getView(R.id.tv_bill_photo_cost);
                textViewCost.setText("-20000");

                break;
            case BillEntity.ACTIVE:
                break;
            case BillEntity.LIVE_INCOME:
                helper.addOnClickListener(R.id.tv_more).linkify(R.id.tv_more);
                break;
            case BillEntity.PAYPAL:
                break;
            default:
                break;
        }

    }
}
