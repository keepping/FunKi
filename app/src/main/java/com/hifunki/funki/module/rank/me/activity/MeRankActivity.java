package com.hifunki.funki.module.rank.me.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.module.rank.me.adapter.RankAdapter;
import com.hifunki.funki.module.rank.me.fragment.RankPresentFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.rank.me.activity.java
 * @link
 * @since 2017-03-20 14:18:18
 */
public class MeRankActivity extends BaseActivity implements RankPresentFragment.OnFragmentInteractionListener
        {


    @BindView(R.id.tb_rank)
    TabLayout tbRank;
    @BindView(R.id.vp_rank)
    ViewPager vpRank;
    private List<String> mTabTitle;


    public static void show(Context context) {
        context.startActivity(new Intent(context, MeRankActivity.class));
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_me_rank;
    }

    @Override
    protected void initDatas() {
        //封装tab数据,数据和tab保持一致
        mTabTitle = new ArrayList<>();
        mTabTitle.add(getString(R.string.present));
        mTabTitle.add(getString(R.string.duration));
        mTabTitle.add(getString(R.string.share));

        tbRank.addTab(tbRank.newTab().setText(mTabTitle.get(0)));
        tbRank.addTab(tbRank.newTab().setText(mTabTitle.get(1)));
        tbRank.addTab(tbRank.newTab().setText(mTabTitle.get(2)));
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
        RankAdapter rankAdapter = new RankAdapter(getSupportFragmentManager(), mTabTitle);
        vpRank.setAdapter(rankAdapter);
        tbRank.setupWithViewPager(vpRank);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}