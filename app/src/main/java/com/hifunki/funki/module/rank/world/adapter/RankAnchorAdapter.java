package com.hifunki.funki.module.rank.world.adapter;

import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hifunki.funki.R;
import com.hifunki.funki.module.rank.world.entity.AnchorEntity;

import java.util.List;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.rank.world.adapter.RankAnchorAdapter.java
 * @link
 * @since 2017-03-15 15:55:55
 */
public class RankAnchorAdapter extends BaseMultiItemQuickAdapter<AnchorEntity, BaseViewHolder> {

    public RankAnchorAdapter( List data) {
        super(data);
        addItemType(AnchorEntity.FIRST, R.layout.item_rank_anchor_first);
        addItemType(AnchorEntity.SECOND, R.layout.item_rank_anchor_second);
        addItemType(AnchorEntity.THIRD, R.layout.item_rank_anchor_third);
        addItemType(AnchorEntity.NORMAL, R.layout.item_rank_anchor_normal);
    }

    @Override
    protected void convert(BaseViewHolder helper, AnchorEntity item) {
        switch (helper.getItemViewType()) {
            case AnchorEntity.FIRST:
                //根布局
                RelativeLayout rlRanking = helper.getView(R.id.rl_ranking);
                //排行
                ImageView ivRanking = helper.getView(R.id.iv_anchor_ranking);
                //头像
                ImageView ivPhoto = helper.getView(R.id.iv_anchor_photo);
                //设置排名
                helper.setText(R.id.tv_anthor_position, "NO." + String.valueOf(item.getPosition()));

                Glide.with(mContext).load(item.getImagePath()).into(ivPhoto);

                helper.setText(R.id.tv_anchor_name, item.getUsername());//用户名
                //设置性别
                ImageView ivSex = helper.getView(R.id.iv_anchor_sex);

                helper.setText(R.id.tv_anchor_lv, String.valueOf(item.getLevel()));//等级
                helper.setText(R.id.tv_anchor_gold, String.valueOf(item.getGoldNumber()));//金钱

                break;
            case AnchorEntity.SECOND:

            case AnchorEntity.THIRD:

            case AnchorEntity.NORMAL:

                helper.setText(R.id.tv_anthor_position, "NO." + String.valueOf(item.getPosition()));//排名
                Glide.with(mContext).load(item.getImagePath()).into((ImageView) helper.getView(R.id.iv_anchor_photo));

                helper.setText(R.id.tv_anchor_name, item.getUsername());

                //这里代码很可能有问题
//                Glide.with(mContext).load(R.drawable.iv_girl).into((ImageView) helper.getView(R.id.iv_anchor_sex));

                helper.setText(R.id.tv_anchor_lv, String.valueOf(item.getLevel()));//等级
                helper.setText(R.id.tv_anchor_gold, String.valueOf(item.getGoldNumber()));//金钱

                break;
        }
    }
}
