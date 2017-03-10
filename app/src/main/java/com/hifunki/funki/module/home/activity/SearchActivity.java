package com.hifunki.funki.module.home.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseTitleActivity;
import com.hifunki.funki.module.home.adapter.HomeSearchAdapter;
import com.hifunki.funki.module.home.fragment.UserListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.home.activity.SearchActivity.java
 * @link
 * @since 2017-03-10 13:23:23
 */
public class SearchActivity extends BaseTitleActivity implements UserListFragment.OnFragmentInteractionListener{

    @BindView(R.id.iv_Title_left)
    ImageView ivTitleLeft;
    @BindView(R.id.tv_et_search)
    TextView tvEtSearch;
    @BindView(R.id.iv_et_close)
    ImageView ivEtClose;
    @BindView(R.id.etTitleCenter)
    EditText etTitleCenter;
    @BindView(R.id.rlEtTitle)
    RelativeLayout rlEtTitle;
    @BindView(R.id.tb_home_search)
    TabLayout tbHomeSearch;
    @BindView(R.id.vp_search)
    ViewPager vpSearch;
    private List<String> mTabTitle;

    public static void show(Context context) {
        context.startActivity(new Intent(context, SearchActivity.class));
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initDatas() {
        mTabTitle = new ArrayList<>();
        mTabTitle.add("用户");
        mTabTitle.add("直播");
        mTabTitle.add("视频");
        mTabTitle.add("动态");
    }

    @Override
    protected void initTitleBar() {

    }

    @Override
    protected void initView() {
        tbHomeSearch.addTab(tbHomeSearch.newTab().setText(mTabTitle.get(0)));
        tbHomeSearch.addTab(tbHomeSearch.newTab().setText(mTabTitle.get(1)));
        tbHomeSearch.addTab(tbHomeSearch.newTab().setText(mTabTitle.get(2)));
        tbHomeSearch.addTab(tbHomeSearch.newTab().setText(mTabTitle.get(3)));

        HomeSearchAdapter homeSearchAdapter = new HomeSearchAdapter(this.getSupportFragmentManager(), mTabTitle);

        vpSearch.setAdapter(homeSearchAdapter);

        tbHomeSearch.setupWithViewPager(vpSearch);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initAdapter() {

    }



    @OnClick({R.id.iv_Title_left, R.id.tv_et_search, R.id.iv_et_close, R.id.etTitleCenter, R.id.rlEtTitle, R.id.tb_home_search, R.id.vp_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_Title_left:
                break;
            case R.id.tv_et_search:
                break;
            case R.id.iv_et_close:
                break;
            case R.id.etTitleCenter:
                break;
            case R.id.rlEtTitle:
                break;
            case R.id.tb_home_search:
                break;
            case R.id.vp_search:
                break;
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
