package com.hifunki.funki.module.home.viewholder;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hifunki.funki.R;
import com.hifunki.funki.net.back.Post;
import com.powyin.scroll.adapter.AdapterDelegate;
import com.powyin.scroll.adapter.PowViewHolder;

/**
 * 普通
 *
 * @author yinhaoxiang
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.home.viewholder.FollowLiveNormal.java
 * @link
 * @since 2017-03-16 10:13:13
 */
public class FollowLiveNormal extends PowViewHolder<Post> {
    public FollowLiveNormal(Activity activity, ViewGroup viewGroup) {
        super(activity, viewGroup);
    }

    @Override
    protected int getItemViewRes() {

     //   addItemType(HomeHotEntity.NORMAL_LIVE, R.layout.item_live_vip);//大图
    //    addItemType(HomeHotEntity.TICKET_LIVE, R.layout.item_live_normal);//小图
        return R.layout.item_live_normal;
    }

    @Override
    protected boolean acceptData(Post data) {
        return data!=null && data.type==1;
    }

    @Override
    public void loadData(AdapterDelegate<? super Post> multipleAdapter, Post data, int postion) {
        //普通直播--big
        //等级直播--big
        //普通视频--big
        Glide.with(mActivity).load(data.liveUri).into((ImageView) mItemView.findViewById(R.id.iv_imagepath));
        Glide.with(mActivity).load(data.liveUri).into((ImageView) mItemView.findViewById(R.id.iv_photo));

    }



}
