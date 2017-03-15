package com.hifunki.funki.module.search.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hifunki.funki.R;
import com.hifunki.funki.module.search.entity.SearchUser;

import java.util.List;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.search.adapter.SearchUserAdapter.java
 * @link
 * @since 2017-03-10 10:25:25
 */
public class SearchUserAdapter extends BaseQuickAdapter<SearchUser, BaseViewHolder> {

    private Context mContext;

    public SearchUserAdapter(Context context, int layoutResId, List<SearchUser> data) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchUser item) {

        Glide.with(mContext).load(item.getPhoto()).into((ImageView) helper.getView(R.id.iv_photo));

        helper.setText(R.id.tv_nickname, item.getNickname());
        if (item.getSex() == 1) {
//            Glide.with(mContext).load(item.getPhoto()).into((ImageView) helper.getView(R.id.iv_sex));
        }
        if (item.getIsCare()) {
//            Glide.with(mContext).load(item.getPhoto()).into((ImageView) helper.getView(R.id.isCare));
        }


        helper.setText(R.id.tv_grade, String.valueOf("LV."+item.getGrade()));
        helper.setText(R.id.tv_signturex,item.getSignture());
    }
}
