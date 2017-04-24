package com.hifunki.funki.module.home.viewholder;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.hifunki.funki.R;
import com.hifunki.funki.common.BundleConst;
import com.hifunki.funki.common.CommonConst;
import com.hifunki.funki.module.dynamic.normal.activity.NormalDynamicActivity;
import com.hifunki.funki.module.home.widget.ninegrid.NineGridlayout;
import com.hifunki.funki.net.back.Post;
import com.powyin.scroll.adapter.AdapterDelegate;
import com.powyin.scroll.adapter.PowViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 九宫格图片
 *
 * @author yinhaoxiang
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.home.viewholder.FollowPicture.java
 * @link
 * @since 2017-03-16 10:12:12
 */
public class FollowPicture extends PowViewHolder<Post> {

    @BindView(R.id.iv_ngrid_layout)
    NineGridlayout nineGridlayout;
    @BindView(R.id.iv_follow_photo)
    ImageView ivPhoto;
    @BindView(R.id.layout_picture_star)
    LinearLayout llPicture;

    public FollowPicture(Activity activity, ViewGroup viewGroup) {
        super(activity, viewGroup);
        ButterKnife.bind(this, mItemView);
    }

    @Override
    protected int getItemViewRes() {
        return R.layout.viewholder_follow_picture;
    }

    @Override
    protected boolean acceptData(Post data) {
        return data != null && data.type == 4;
    }

    @Override
    public void loadData(AdapterDelegate<? super Post> multipleAdapter, Post data, int postion) {

        Glide.with(mActivity).load(CommonConst.photo).into(ivPhoto);//头像
        nineGridlayout.setImagesData(data.imgageUri);
        llPicture.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.tv_follow_picture_comment})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_follow_picture_comment://点击弹出一个popupWindow
                NormalDynamicActivity.show(mActivity, BundleConst.FOLLOW_PICTURE_TO_DYNAMIC);
                break;
        }
    }

}
