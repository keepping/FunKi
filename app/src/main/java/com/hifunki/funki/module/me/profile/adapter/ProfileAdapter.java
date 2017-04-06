package com.hifunki.funki.module.me.profile.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hifunki.funki.module.me.profile.entity.ProfileEntity;

import java.util.List;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.me.profile.adapter.ProfileAdapter.java
 * @link
 * @since 2017-03-24 13:36:36
 */
public class ProfileAdapter extends BaseMultiItemQuickAdapter<ProfileEntity,BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ProfileAdapter(List<ProfileEntity> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProfileEntity item) {

    }
}
