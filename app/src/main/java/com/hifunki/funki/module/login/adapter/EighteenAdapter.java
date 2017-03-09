package com.hifunki.funki.module.login.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hifunki.funki.R;
import com.hifunki.funki.module.login.entity.EighteenEntity;

import java.util.List;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.login.adapter.EighteenAdapter.java
 * @link
 * @since 2017-03-09 09:38:38
 */
public class EighteenAdapter extends BaseQuickAdapter<EighteenEntity, BaseViewHolder> {
    public EighteenAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EighteenEntity item) {
        helper.setText(R.id.tv_eighteen,item.getText());
    }
}
