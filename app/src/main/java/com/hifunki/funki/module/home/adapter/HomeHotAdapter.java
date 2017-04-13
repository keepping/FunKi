package com.hifunki.funki.module.home.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hifunki.funki.R;
import com.hifunki.funki.module.home.entity.HomeHotEntity;

import java.util.List;

/**
 * 首页热门的adapter
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.home.adapter.HomeHotAdapter.java
 * @link
 * @since 2017-03-15 14:14:14
 */
public class HomeHotAdapter extends BaseMultiItemQuickAdapter<HomeHotEntity, BaseViewHolder> {

    private Context mContext;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public HomeHotAdapter(Context context, List<HomeHotEntity> data) {
        super(data);
        this.mContext = context;
        addItemType(HomeHotEntity.NORMAL_LIVE, R.layout.item_live_vip);//大图
        addItemType(HomeHotEntity.TICKET_LIVE, R.layout.item_live_normal);//小图
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeHotEntity item) {
        switch (helper.getItemViewType()) {
            case HomeHotEntity.NORMAL_LIVE://big
                //普通直播--big
                //等级直播--big
                //普通视频--big
                ImageView ivPhoto=helper.getView(R.id.iv_photo);
                Glide.with(mContext).load(item.getImagePath()).into((ImageView) helper.getView(R.id.iv_imagepath));
                Glide.with(mContext).load(item.getPhoto()).into(ivPhoto);
                helper.setText(R.id.tv_name, item.getUsername());
                helper.setText(R.id.tv_location, item.getLocation());
                helper.setText(R.id.tv_language, item.getLanguage());
                helper.setText(R.id.tv_count_person, String.valueOf(item.getWatchCount()));

                helper.addOnClickListener(R.id.iv_photo);
                helper.addOnClickListener(R.id.iv_imagepath);
                break;
            case HomeHotEntity.TICKET_LIVE://small
                //门票直播--small view
                //门票视频--small
                Glide.with(mContext).load(item.getImagePath()).into((ImageView) helper.getView(R.id.iv_imagepath));
                Glide.with(mContext).load(item.getPhoto()).into((ImageView) helper.getView(R.id.iv_photo));
                helper.setText(R.id.tv_name, item.getUsername());
                helper.setText(R.id.tv_location, item.getLocation());
                helper.setText(R.id.tv_language, item.getLanguage());
                helper.setText(R.id.tv_count_person, String.valueOf(item.getWatchCount()));
                break;
        }
    }
}
