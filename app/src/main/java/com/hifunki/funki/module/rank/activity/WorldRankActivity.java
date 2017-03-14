package com.hifunki.funki.module.rank.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseTitleActivity;
import com.hifunki.funki.module.rank.adapter.RankAdapter;
import com.hifunki.funki.module.rank.fragment.RankAnchorFragment;
import com.hifunki.funki.module.rank.fragment.RankRickFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 世界总榜界面
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.home.activity.WorldRank.java
 * @link
 * @since 2017-03-13 15:45:45
 */
public class WorldRankActivity extends BaseTitleActivity implements RankAnchorFragment.OnFragmentInteractionListener, RankRickFragment.OnFragmentInteractionListener{

    @BindView(R.id.tvTitleLeft)
    TextView tvTitleLeft;
    @BindView(R.id.rlTitleLeft)
    RelativeLayout rlTitleLeft;
    @BindView(R.id.tvTitleCenter)
    TextView tvTitleCenter;
    @BindView(R.id.rlTitleCenter)
    RelativeLayout rlTitleCenter;
    @BindView(R.id.ll_title_right)
    LinearLayout llTitleRight;
    @BindView(R.id.flTitleContainer)
    FrameLayout flTitleContainer;
    @BindView(R.id.tb_rank)
    TabLayout tbRank;
    @BindView(R.id.vp_rank)
    ViewPager vpRank;
    private List<String> mTabTitle;

    /**
     * 跳转当前界面
     *
     * @param context
     */
    public static void show(Context context) {
        context.startActivity(new Intent(context, WorldRankActivity.class));
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_world_rank;
    }

    @Override
    protected void initDatas() {
        //封装tab数据,数据和tab保持一致
        mTabTitle = new ArrayList<>();
        mTabTitle.add(getString(R.string.world_rank_anchor));
        mTabTitle.add(getString(R.string.world_rank_rich));

        tbRank.addTab(tbRank.newTab().setText(mTabTitle.get(0)));
        tbRank.addTab(tbRank.newTab().setText(mTabTitle.get(1)));
    }

    @Override
    protected void initTitleBar() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initAdapter() {

        RankAdapter rankAdapter = new RankAdapter(getSupportFragmentManager(),mTabTitle);
        vpRank.setAdapter(rankAdapter);
        tbRank.setupWithViewPager(vpRank);

        View view ;

    }

    @OnClick({R.id.tb_rank, R.id.vp_rank})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tb_rank:
                break;
            case R.id.vp_rank:
                break;
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
