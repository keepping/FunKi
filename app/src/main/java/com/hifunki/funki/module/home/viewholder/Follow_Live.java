package com.hifunki.funki.module.home.viewholder;

import android.app.Activity;
import android.view.ViewGroup;

import com.hifunki.funki.R;
import com.hifunki.funki.net.back.Post;
import com.powyin.scroll.adapter.AdapterDelegate;
import com.powyin.scroll.adapter.PowViewHolder;

/**
 * 在此写用途
 *
 * @author yinhaoxiang
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.home.viewholder.Follow_Live.java
 * @link
 * @since 2017-03-16 10:13:13
 */
public class Follow_Live extends PowViewHolder<Post> {
    public Follow_Live(Activity activity, ViewGroup viewGroup) {
        super(activity, viewGroup);
    }

    @Override
    protected int getItemViewRes() {
        return R.layout.viewholder_post_picture;
    }

    @Override
    protected boolean acceptData(Post data) {
        return data!=null && data.type==3;
    }

    @Override
    public void loadData(AdapterDelegate<? super Post> multipleAdapter, Post data, int postion) {

    }
}
