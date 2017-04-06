package com.hifunki.funki.module.dynamic.viewholder;

import android.app.Activity;
import android.view.ViewGroup;

import com.powyin.scroll.adapter.AdapterDelegate;
import com.powyin.scroll.adapter.PowViewHolder;

/**
 * 在此写用途
 *
 * @author yinhaoxiang
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.post.viewholder.PostSelect.java
 * @link
 * @since 2017-03-24 15:18:18
 */
public class PostSelect extends PowViewHolder<Object> {

    public PostSelect(Activity activity, ViewGroup viewGroup) {
        super(activity, viewGroup);
    }

    @Override
    protected int getItemViewRes() {
        return 0;
    }

    @Override
    public void loadData(AdapterDelegate<? super Object> multipleAdapter, Object data, int postion) {

    }
}
