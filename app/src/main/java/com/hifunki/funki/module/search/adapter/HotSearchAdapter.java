package com.hifunki.funki.module.search.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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

    public HotSearchAdapter(Context context, int layoutResId, List data) {
        super(layoutResId, data);
        this.mContext = context;

    }

    @Override
    protected void convert(BaseViewHolder helper, PersonEntity item) {
        if (item.getLiveStatus() == 1) {
            ((TextView) helper.getView(R.id.tv_live_status)).setText("LIVE");
        }

//        helper.setText(R.id.tv_name, item.getName());
//        helper.setText(R.id.tv_name, item.getName());
//        Log.e("test", "convert: "+item.getImagePath() );
        Glide.with(mContext).load(item.getPhoto()).into((ImageView) helper.getView(R.id.iv_photo));


        Glide.with(mContext).load(item.getImagePath().get(0)).into((ImageView) helper.getView(R.id.iv_imagePath1));
        Glide.with(mContext).load(item.getImagePath().get(1)).into((ImageView) helper.getView(R.id.iv_imagePath2));
        Glide.with(mContext).load(item.getImagePath().get(2)).into((ImageView) helper.getView(R.id.iv_imagePath3));
        Glide.with(mContext).load(item.getImagePath().get(3)).into((ImageView) helper.getView(R.id.iv_imagePath4));


    }
}
