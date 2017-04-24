package com.hifunki.funki.module.dynamic.normal.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hifunki.funki.R;
import com.hifunki.funki.common.CommonConst;

import java.util.List;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.dynamic.normal.adapter.DynamicCommentAdapter.java
 * @link
 * @since 2017-04-20 15:10:10
 */
public class DynamicCommentAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private int type;

    public DynamicCommentAdapter(int layoutResId, List<String> data, int type) {
        super(layoutResId, data);
        this.type = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView ivPhoto = helper.getView(R.id.civ_dynamic_photo);
        Glide.with(mContext).load(CommonConst.photo).into(ivPhoto);
        if (type == 1) {
            TextView textView = helper.getView(R.id.tv_dynamic_sign);
            textView.setVisibility(View.VISIBLE);
        }
    }
}
