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
 * @value com.hifunki.funki.module.home.viewholder.Follow_Movie.java
 * @link
 * @since 2017-03-16 10:04:04
 */
public class Follow_Movie extends PowViewHolder<Post> {
    public Follow_Movie(Activity activity, ViewGroup viewGroup) {
        super(activity, viewGroup);
    }

    @Override
    protected int getItemViewRes() {
        return R.layout.viewholder_post_movie;
    }

    @Override
    protected boolean acceptData(Post data) {
        return data!=null && data.type==1;
    }

    @Override
    public void loadData(AdapterDelegate<? super Post> multipleAdapter, Post data, int postion) {

    }
}
