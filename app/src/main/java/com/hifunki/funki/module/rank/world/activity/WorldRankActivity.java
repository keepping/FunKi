package com.hifunki.funki.module.rank.world.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.module.rank.world.adapter.RankPageAdapter;
import com.hifunki.funki.module.rank.world.fragment.RankAnchorFragment;

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
public class WorldRankActivity extends BaseActivity implements RankAnchorFragment.OnFragmentInteractionListener{

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
    protected void initVariable() {
        //封装tab数据,数据和tab保持一致
        mTabTitle = new ArrayList<>();
        mTabTitle.add(getString(R.string.world_rank_anchor));
        mTabTitle.add(getString(R.string.world_rank_rich));

        tbRank.addTab(tbRank.newTab().setText(mTabTitle.get(0)));
        tbRank.addTab(tbRank.newTab().setText(mTabTitle.get(1)));
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initAdapter() {
        RankPageAdapter rankPageAdapter = new RankPageAdapter(getSupportFragmentManager(),mTabTitle);
        vpRank.setAdapter(rankPageAdapter);
        tbRank.setupWithViewPager(vpRank);
    }

    @Override
    protected void bindData() {

    }

    @Override
    protected void bindData4NoNet() {

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
