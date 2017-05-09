package com.hifunki.funki.module.msg.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.msg.adapter.ContactsFriendAdapter.java
 * @link
 * @since 2017-05-04 10:21:21
 */
public class ContactsFriendAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public ContactsFriendAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
