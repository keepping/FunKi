package com.hifunki.funki.module.search.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hifunki.funki.R;
import com.hifunki.funki.module.search.entity.ActivityEntity;

import java.util.List;

/**
 * Created by WangTest on 2017/3/11.
 */

public class ActivitySearchAdapter extends BaseQuickAdapter<ActivityEntity, BaseViewHolder> {
    private Context mContext;
    private int contextView;

    public ActivitySearchAdapter(Context context, int layoutResId, List<ActivityEntity> data) {
        super(layoutResId, data);
        this.mContext = context;

    }

    @Override
    protected void convert(BaseViewHolder helper, ActivityEntity item) {
        Glide.with(mContext).load(item.getActivityImage()).into((ImageView) helper.getView(R.id.iv_activity_image));

        RecyclerView recycleView = helper.getView(R.id.rv_activity_join);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        ActivityPhotoAdapter activityPhotoAdapter = new ActivityPhotoAdapter(mContext, R.layout.list_search_activity_photo, item.getJoinList());
        recycleView.setLayoutManager(linearLayoutManager);
        recycleView.setAdapter(activityPhotoAdapter);


    }

    public void setContextView(int contextView) {
        this.contextView = contextView;
    }
}
