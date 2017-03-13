package com.hifunki.funki.module.search.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hifunki.funki.R;
import com.hifunki.funki.module.search.entity.LiveEntity;

import java.util.List;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.search.adapter.SearchLiveAdapter.java
 * @link
 * @since 2017-03-13 10:29:29
 */
public class SearchLiveAdapter extends BaseMultiItemQuickAdapter<LiveEntity, BaseViewHolder> {

    private Context mContext;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public SearchLiveAdapter(Context context, List data) {
        super(data);
        this.mContext = context;
        addItemType(LiveEntity.VIP_LIVE, R.layout.list_live_vip);
        addItemType(LiveEntity.LEVEL_LIVE, R.layout.list_live_normal);
    }

    @Override
    protected void convert(BaseViewHolder helper, LiveEntity item) {
        switch (helper.getItemViewType()) {
            case LiveEntity.VIP_LIVE:
                Glide.with(mContext).load(item.getImagePath()).into((ImageView) helper.getView(R.id.iv_imagepath));
                Glide.with(mContext).load(item.getPhoto()).into((ImageView) helper.getView(R.id.iv_photo));
                helper.setText(R.id.tv_name, item.getUsername());
                helper.setText(R.id.tv_location, item.getLocation());
                helper.setText(R.id.tv_language, item.getLanguage());
                helper.setText(R.id.tv_count_person, String.valueOf(item.getCount()));
                break;
            case LiveEntity.LEVEL_LIVE:
                Glide.with(mContext).load(item.getImagePath()).into((ImageView) helper.getView(R.id.iv_imagepath));
                Glide.with(mContext).load(item.getPhoto()).into((ImageView) helper.getView(R.id.iv_photo));
                helper.setText(R.id.tv_name, item.getUsername());
                helper.setText(R.id.tv_location, item.getLocation());
                helper.setText(R.id.tv_language, item.getLanguage());
                helper.setText(R.id.tv_count_person, String.valueOf(item.getCount()));
                break;
        }
    }
}
