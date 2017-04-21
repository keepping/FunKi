package com.hifunki.funki.module.room.adpater;

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
 * @value com.hifunki.funki.module.room.adpater.RoomDynamicAdapter.java
 * @link
 * @since 2017-04-21 11:11:11
 */
public class RoomDynamicAdapter extends BaseMultiItemQuickAdapter<HomeHotEntity, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public RoomDynamicAdapter(List<HomeHotEntity> data) {
        super(data);
        addItemType(HomeHotEntity.NORMAL_LIVE, R.layout.item_room_dynamic_gallery);
        addItemType(HomeHotEntity.LEVEL_LIVE, R.layout.viewholder_follow_picture);
        addItemType(HomeHotEntity.NORMAL_VIDEO, R.layout.viewholder_follow_movie);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeHotEntity item) {
        switch (item.getItemType()) {
            case HomeHotEntity.NORMAL_LIVE:

                break;
            case HomeHotEntity.LEVEL_LIVE:

                break;
            case HomeHotEntity.NORMAL_VIDEO:

                break;
        }
    }
}
