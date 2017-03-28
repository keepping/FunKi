package com.hifunki.funki.module.search.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hifunki.funki.R;
import com.hifunki.funki.util.DisplayUtil;

import java.util.List;

/**
 * 搜索四宫格
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.search.adapter.ActivityImageAdapter.java
 * @link
 * @since 2017-03-25 15:01:01
 */
public class ActivityImageAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private int mImageWidth = 63;

    public ActivityImageAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView ivImage = helper.getView(R.id.iv_activity_search_image);


        if (helper.getAdapterPosition() % 2 == 1) {
            float dp = DisplayUtil.dip2Px(mContext, mImageWidth);
            GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) ivImage.getLayoutParams();
            layoutParams.width = (int) dp;
            layoutParams.height = (int) dp;
            layoutParams.rightMargin = 5;

            ivImage.setLayoutParams(layoutParams);
        } else {
            float dp = DisplayUtil.dip2Px(mContext, mImageWidth);
            GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) ivImage.getLayoutParams();
            layoutParams.width = (int) dp;
            layoutParams.height = (int) dp;
            layoutParams.leftMargin = 5;

            ivImage.setLayoutParams(layoutParams);
        }

        Glide.with(mContext).load(item).into(ivImage);
    }
}
