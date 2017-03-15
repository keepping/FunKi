package com.hifunki.funki.module.search.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hifunki.funki.R;
import com.hifunki.funki.module.search.entity.JoinEntity;

import java.util.List;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.search.adapter.ActivityPhotoAdapter.java
 * @link
 * @since 2017-03-10 10:25:25
 */

public class ActivityPhotoAdapter extends BaseQuickAdapter<JoinEntity, BaseViewHolder> {

    private Context mContext;

    public ActivityPhotoAdapter(Context context, int layoutResId, List<JoinEntity> data) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, JoinEntity item) {
        Glide.with(mContext).load(item.getJoinPhotot()).into((ImageView) helper.getView(R.id.iv_activity_photo));
        helper.setText(R.id.tv_activity_name, item.getJoinName());
    }
}
