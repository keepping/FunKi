package com.hifunki.funki.module.home.me.fans.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hifunki.funki.module.home.me.fans.entity.FansEntity;

import java.util.List;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.home.me.fans.adapter.FansAdapter.java
 * @link
 * @since 2017-03-21 12:05:05
 */
public class FansAdapter extends BaseQuickAdapter<FansEntity,BaseViewHolder> {

    public FansAdapter(int layoutResId, List<FansEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FansEntity item) {

    }
}
