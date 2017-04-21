package com.hifunki.funki.module.room.adpater;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hifunki.funki.R;

import java.util.List;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.room.adpater.RDPhotoAdapter.java
 * @link
 * @since 2017-04-21 14:05:05
 */
public class RDPhotoAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public RDPhotoAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        Glide.with(mContext).load(item).into((ImageView) helper.getView(R.id.iv_room_dynamic_photo));
    }
}
