package com.hifunki.funki.module.room.adpater;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hifunki.funki.R;
import com.hifunki.funki.common.CommonConst;
import com.hifunki.funki.module.home.entity.HomeHotEntity;
import com.hifunki.funki.module.home.widget.ninegrid.NineGridlayout;
import com.hifunki.funki.widget.FunKiPlayer;

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

    public RoomDynamicAdapter(List<HomeHotEntity> data) {
        super(data);
        addItemType(HomeHotEntity.NORMAL_LIVE, R.layout.item_room_dynamic_gallery);
        addItemType(HomeHotEntity.LEVEL_LIVE, R.layout.viewholder_follow_picture);
        addItemType(HomeHotEntity.NORMAL_VIDEO, R.layout.viewholder_follow_movie);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeHotEntity item) {
        switch (helper.getItemViewType()) {
            case HomeHotEntity.NORMAL_LIVE:
                RecyclerView recyclerView = helper.getView(R.id.rl_room_dynamic_gallery);
                recyclerView.setLayoutManager(new GridLayoutManager(mContext, 5));
                RDPhotoAdapter adapter = new RDPhotoAdapter(R.layout.item_room_dynamic_photo, CommonConst.NINE_PHOTO);
                recyclerView.setAdapter(adapter);

                helper.addOnClickListener(R.id.rl_room_dynamic_photo_more);
                break;
            case HomeHotEntity.LEVEL_LIVE:
                NineGridlayout nineGridlayout = helper.getView(R.id.iv_ngrid_layout);
                nineGridlayout.setImagesData(CommonConst.NINE_PHOTO);
                break;
            case HomeHotEntity.NORMAL_VIDEO:
                FunKiPlayer player = helper.getView(R.id.fun_player);
                player.play(CommonConst.VIDEO);
                break;
        }
    }
}
