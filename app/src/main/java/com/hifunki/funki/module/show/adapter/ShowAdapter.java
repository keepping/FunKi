package com.hifunki.funki.module.show.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hifunki.funki.R;
import com.hifunki.funki.module.show.entity.ShowEntity;

import java.util.List;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.show.adapter.ShowAdapter.java
 * @link
 * @since 2017-03-16 13:31:31
 */
public class ShowAdapter extends BaseQuickAdapter<ShowEntity, BaseViewHolder> {

    private Context mContext;

    public ShowAdapter(Context context, int layoutResId, List<ShowEntity> data) {
        super(layoutResId, data);
        this.mContext = context;

    }

    @Override
    protected void convert(BaseViewHolder helper, ShowEntity item) {

        helper.addOnClickListener(R.id.ll_show_price);//设置砖石购买监听

        helper.setText(R.id.tv_show_tag, item.getTag());
        helper.setText(R.id.tv_show_time, item.getTime());
        helper.setText(R.id.tv_show_comment, item.getContent());
        helper.setText(R.id.tv_show_vip_price, String.valueOf(item.getVipPrice()));
        helper.setText(R.id.tv_vip_percent, String.valueOf(item.getPercent()));
        Glide.with(mContext).load(item.getPhoto()).into((ImageView) helper.getView(R.id.iv_show_photo));

    }


}
