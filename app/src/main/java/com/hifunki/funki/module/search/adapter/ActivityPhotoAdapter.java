package com.hifunki.funki.module.search.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hifunki.funki.R;
import com.hifunki.funki.module.search.entity.Join;

import java.util.List;

/**
 * Created by WangTest on 2017/3/11.
 */

public class ActivityPhotoAdapter extends BaseQuickAdapter<Join, BaseViewHolder> {

    private Context mContext;

    public ActivityPhotoAdapter(Context context, int layoutResId, List<Join> data) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Join item) {
        Glide.with(mContext).load(item.getJoinPhotot()).into((ImageView) helper.getView(R.id.iv_activity_photo));
        helper.setText(R.id.tv_activity_name, item.getJoinName());
    }
}
