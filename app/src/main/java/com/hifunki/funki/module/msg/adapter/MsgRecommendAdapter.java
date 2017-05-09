package com.hifunki.funki.module.msg.adapter;

import android.widget.ImageView;

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
 * @value com.hifunki.funki.module.msg.adapter.MsgRecommendAdapter.java
 * @link
 * @since 2017-05-03 13:40:40
 */
public class MsgRecommendAdapter  extends BaseQuickAdapter<String,BaseViewHolder>{

    public MsgRecommendAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView imageView=helper.getView(R.id.civ_msg_photo);
        Glide.with(mContext).load(CommonConst.photo).into(imageView);
    }
}
