package com.hifunki.funki.module.home.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hifunki.funki.R;
import com.hifunki.funki.module.home.entity.HomeNewEntity;

import java.util.List;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.home.adapter.HomeNewAdapter.java
 * @link
 * @since 2017-03-17 09:53:53
 */
public class HomeNewAdapter extends BaseQuickAdapter<HomeNewEntity, BaseViewHolder> {

    //里面有mContext属性

    public HomeNewAdapter(int layoutResId, List<HomeNewEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeNewEntity item) {
        Glide.with(mContext).load(item.getImagePath()).into((ImageView) helper.getView(R.id.iv_photo));
        helper.setText(R.id.tv_location, item.getLocation());
        helper.setText(R.id.tv_country, item.getLocation());
        helper.setText(R.id.tv_name, item.getName());
    }
}
