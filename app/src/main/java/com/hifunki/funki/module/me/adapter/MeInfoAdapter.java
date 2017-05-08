package com.hifunki.funki.module.me.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hifunki.funki.R;
import com.hifunki.funki.module.me.entity.MeBottomEntity;

import java.util.List;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.me.adapter.MeInfoAdapter.java
 * @link
 * @since 2017-03-18 11:47:47
 */
public class MeInfoAdapter extends BaseQuickAdapter<MeBottomEntity, BaseViewHolder> {
    public MeInfoAdapter(int layoutResId, List<MeBottomEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MeBottomEntity item) {
        helper.setText(R.id.tv_tag, item.getName());
        helper.addOnClickListener(R.id.rl_item_info);
        ImageView imageView=helper.getView(R.id.iv_me_image);
        imageView.setImageDrawable(item.getRes());

    }
}
