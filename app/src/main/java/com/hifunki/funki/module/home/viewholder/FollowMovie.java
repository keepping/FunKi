package com.hifunki.funki.module.home.viewholder;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hifunki.funki.R;
import com.hifunki.funki.common.BundleConst;
import com.hifunki.funki.common.CommonConst;
import com.hifunki.funki.module.dynamic.normal.activity.NormalDynamicActivity;
import com.hifunki.funki.net.back.Post;
import com.hifunki.funki.widget.FunKiPlayer;
import com.powyin.scroll.adapter.AdapterDelegate;
import com.powyin.scroll.adapter.PowViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 在此写用途
 *
 * @author yinhaoxiang
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.home.viewholder.FollowMovie.java
 * @link
 * @since 2017-03-16 10:04:04
 */
public class FollowMovie extends PowViewHolder<Post> {

    @BindView(R.id.fun_player)
    FunKiPlayer fun_player;
    @BindView(R.id.tv_follow_movie_comment)
    TextView tvComment;

    public FollowMovie(Activity activity, ViewGroup viewGroup) {
        super(activity, viewGroup);
        ButterKnife.bind(this, mItemView);
    }

    @Override
    protected int getItemViewRes() {
        return R.layout.viewholder_follow_movie;
    }

    @Override
    protected boolean acceptData(Post data) {
        return data != null && data.type == 3;
    }

    @Override
    public void loadData(AdapterDelegate<? super Post> multipleAdapter, Post data, int postion) {
        fun_player.play(CommonConst.VIDEO);

    }

    @OnClick({R.id.tv_follow_movie_comment})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_follow_movie_comment://点击弹出一个popupWindow
                NormalDynamicActivity.show(mActivity, BundleConst.FOLLOW_MOVIE_TO_DYNAMIC);
                break;
        }
    }

}

















