package com.hifunki.funki.module.home.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hifunki.funki.R;
import com.hifunki.funki.module.home.entity.HomeNewEntity;
import com.hifunki.funki.util.DisplayUtil;

import java.util.List;

/**
 * 首页最新adapter
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
        //动态设置布局宽高
        RelativeLayout rlNewLive = helper.getView(R.id.rl_new_live);
        //设置imageView的属性
        ImageView ivNewImage = helper.getView(R.id.iv_new_image);

        if (helper.getAdapterPosition() % 2 == 1) {
            //设置主布局的宽高
            GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) rlNewLive.getLayoutParams();
            layoutParams.height = DisplayUtil.getScreenWidth(mContext) / 2;
            layoutParams.width = DisplayUtil.getScreenWidth(mContext) / 2 - (int) DisplayUtil.dip2Px(mContext, 1);
            layoutParams.rightMargin = 1;
            rlNewLive.setLayoutParams(layoutParams);
            //设置ImageView的宽高
            ViewGroup.LayoutParams ivParams = ivNewImage.getLayoutParams();
            ivParams.height = DisplayUtil.getScreenWidth(mContext) / 2;
            ivParams.width = DisplayUtil.getScreenWidth(mContext) / 2 - (int) DisplayUtil.dip2Px(mContext, 1);
            layoutParams.rightMargin = 1;
            ivNewImage.setLayoutParams(ivParams);

        } else {
            GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) rlNewLive.getLayoutParams();
            layoutParams.height = DisplayUtil.getScreenWidth(mContext) / 2;
            layoutParams.width = DisplayUtil.getScreenWidth(mContext) / 2 - (int) DisplayUtil.dip2Px(mContext, 1);
            layoutParams.leftMargin = 1;
            rlNewLive.setLayoutParams(layoutParams);

            ViewGroup.LayoutParams ivParams = ivNewImage.getLayoutParams();
            ivParams.height = DisplayUtil.getScreenWidth(mContext) / 2;
            ivParams.width = DisplayUtil.getScreenWidth(mContext) / 2 - (int) DisplayUtil.dip2Px(mContext, 1);
            layoutParams.leftMargin = 1;
            ivNewImage.setLayoutParams(ivParams);
        }
        //加载图片
        Glide.with(mContext).load(item.getImagePath()).into(ivNewImage);
        helper.setText(R.id.tv_location, item.getLocation());
        helper.setText(R.id.tv_country, item.getLocation());
        helper.setText(R.id.tv_name, item.getName());
        helper.addOnClickListener(R.id.iv_new_image);
    }
}
