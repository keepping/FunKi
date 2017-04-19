package com.hifunki.funki.module.home.viewholder;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hifunki.funki.R;
import com.hifunki.funki.common.CommonConst;
import com.hifunki.funki.module.home.widget.ninegrid.NineGridlayout;
import com.hifunki.funki.net.back.Post;
import com.powyin.scroll.adapter.AdapterDelegate;
import com.powyin.scroll.adapter.PowViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 九宫格图片
 *
 * @author yinhaoxiang
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.home.viewholder.FollowPic.java
 * @link
 * @since 2017-03-16 10:12:12
 */
public class FollowPic extends PowViewHolder<Post>{

    @BindView(R.id.iv_ngrid_layout)
    NineGridlayout nineGridlayout;
    @BindView(R.id.iv_post_photo)
    ImageView ivPhoto;

    public FollowPic(Activity activity, ViewGroup viewGroup) {
        super(activity, viewGroup);
        ButterKnife.bind(this,mItemView);
    }

    @Override
    protected int getItemViewRes() {
        return R.layout.viewholder_follow_picture;
    }

    @Override
    protected boolean acceptData(Post data) {
        return data!=null && data.type==4;
    }

    @Override
    public void loadData(AdapterDelegate<? super Post> multipleAdapter, Post data, int postion) {

        Glide.with(mActivity).load(CommonConst.photo).into(ivPhoto);//头像
        nineGridlayout.setImagesData(data.imgageUri);
    }

}
