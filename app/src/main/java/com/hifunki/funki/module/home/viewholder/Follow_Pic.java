package com.hifunki.funki.module.home.viewholder;

import android.app.Activity;
import android.view.ViewGroup;

import com.hifunki.funki.R;
import com.hifunki.funki.module.home.widget.ninegrid.NineGridlayout;
import com.hifunki.funki.net.back.Post;
import com.powyin.scroll.adapter.AdapterDelegate;
import com.powyin.scroll.adapter.PowViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 在此写用途
 *
 * @author yinhaoxiang
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.home.viewholder.Follow_Pic.java
 * @link
 * @since 2017-03-16 10:12:12
 */
public class Follow_Pic extends PowViewHolder<Post>{

    @BindView(R.id.iv_ngrid_layout)
    NineGridlayout nineGridlayout;

    public Follow_Pic(Activity activity, ViewGroup viewGroup) {
        super(activity, viewGroup);
        ButterKnife.bind(this,mItemView);
    }

    @Override
    protected int getItemViewRes() {
        return R.layout.viewholder_post_picture;
    }

    @Override
    protected boolean acceptData(Post data) {
        return data!=null && data.type==2;
    }

    @Override
    public void loadData(AdapterDelegate<? super Post> multipleAdapter, Post data, int postion) {

        nineGridlayout.setImagesData(data.imgageUri);

    }



}
