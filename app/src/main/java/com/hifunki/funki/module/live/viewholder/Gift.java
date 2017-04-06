package com.hifunki.funki.module.live.viewholder;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hifunki.funki.R;
import com.hifunki.funki.common.CommonConst;
import com.powyin.scroll.adapter.AdapterDelegate;
import com.powyin.scroll.adapter.PowViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 观众端礼物列表
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.live.viewholder.Gift.java
 * @link
 * @since 2017-04-01 14:50:50
 */
public class Gift extends PowViewHolder<String> {

    @BindView(R.id.iv_audience)
    ImageView ivAudience;

    public Gift(Activity activity, ViewGroup viewGroup) {
        super(activity, viewGroup);
        ButterKnife.bind(this,mItemView);
    }

    @Override
    protected int getItemViewRes() {
        return R.layout.item_live_fan_item;
    }

    @Override
    public void loadData(AdapterDelegate<? super String> multipleAdapter, String data, int postion) {
        Glide.with(mActivity).load(CommonConst.photo).into(ivAudience);
    }

}
