package com.hifunki.funki.module.search.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hifunki.funki.R;
import com.hifunki.funki.module.search.entity.PersonEntity;

import java.util.List;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.search.adapter.HotSearchAdapter.java
 * @link
 * @since 2017-03-10 14:25:25
 */
public class HotSearchAdapter extends BaseQuickAdapter<PersonEntity, BaseViewHolder> {

    private Context mContext;

    public HotSearchAdapter(Context context, int layoutResId, List<PersonEntity> data) {
        super(layoutResId, data);
        this.mContext = context;

    }

    @Override
    protected void convert(BaseViewHolder helper, PersonEntity item) {
        helper.setText(R.id.tv_live_status, item.getName());
        helper.setText(R.id.tv_name, item.getName());
//        Glide.with(mContext).load(item.getImagePath()).into((ImageView) helper.getView(R.id.iv_photo));


    }
}
