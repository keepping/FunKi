package com.hifunki.funki.module.live.viewholder;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hifunki.funki.R;
import com.hifunki.funki.client.User;
import com.powyin.scroll.adapter.AdapterDelegate;
import com.powyin.scroll.adapter.PowViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 在此写用途
 *
 * @author yinhaoxiang
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.live.viewholder.ChatFan.java
 * @link
 * @since 2017-03-31 18:17:17
 */
public class ChatFan extends PowViewHolder<User>  {

    public ChatFan(Activity activity, ViewGroup viewGroup) {
        super(activity, viewGroup);
        ButterKnife.bind(this,mItemView);
    }

    @BindView(R.id.fan_avatar)
    ImageView fanAvatar;

    @Override
    protected int getItemViewRes() {
        return R.layout.item_live_fan_item;
    }

    @Override
    public void loadData(AdapterDelegate<? super User> multipleAdapter, User data, int postion) {


        Glide.with(mActivity).load("http://img2.100bt.com/upload/ttq/20131103/1383470553132_middle.jpg").into(fanAvatar);


    }
}
