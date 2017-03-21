package com.hifunki.funki.module.rank.world.adapter;

import android.content.Context;
import android.widget.ImageView;

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

    private Context mContext;


    public RankAnchorAdapter(Context context, List data) {
        super(data);
        this.mContext = context;
        addItemType(AnchorEntity.TOP, R.layout.item_rank_anchor_top);
        addItemType(AnchorEntity.NORMAL, R.layout.item_rank_anchor_normal);
    }

    @Override
    protected void convert(BaseViewHolder helper, AnchorEntity item) {
        switch (helper.getItemViewType()) {
            case AnchorEntity.TOP:
                //需要处理排名的问题
                if (helper.getLayoutPosition() == 1) {

                }
                helper.setText(R.id.tv_anthor_position, "NO." + String.valueOf(item.getPosition()));//排名
                Glide.with(mContext).load(item.getImagePath()).into((ImageView) helper.getView(R.id.iv_anchor_photo));

                helper.setText(R.id.tv_anchor_name, item.getUsername());//用户名

                Glide.with(mContext).load(R.drawable.iv_girl).into((ImageView) helper.getView(R.id.iv_anchor_sex));
                helper.setText(R.id.tv_anchor_lv, String.valueOf(item.getLevel()));//等级
                helper.setText(R.id.tv_anchor_gold, String.valueOf(item.getGoldNumber()));//金钱

                break;
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
