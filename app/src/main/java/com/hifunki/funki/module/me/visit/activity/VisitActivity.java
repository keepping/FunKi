package com.hifunki.funki.module.me.visit.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.module.me.visit.adapter.VisitAdapter;
import com.hifunki.funki.module.me.visit.fragment.VisitFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.me.visit.activity.BaseActivity.java
 * @link
 * @since 2017-04-21 10:14:14
 */
public class VisitActivity extends BaseActivity implements VisitFragment.OnFragmentInteractionListener{

    @BindView(R.id.tab_visit)
    TabLayout tabLayout;
    @BindView(R.id.vp_visit)
    ViewPager viewPager;
    private List<String> mTitle;

    @Override
    protected int getViewResId() {
        return R.layout.activity_visit;
    }

    @Override
    protected void initVariable() {
        mTitle = new ArrayList<>();
        mTitle.add("来访记录");
        mTitle.add("查看记录");
    }

    @Override
    protected void initView() {
        tabLayout.addTab(tabLayout.newTab().setText(mTitle.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(mTitle.get(1)));
    }

    @Override
    protected void initAdapter() {
        super.initAdapter();
        VisitAdapter adapter=new VisitAdapter(getSupportFragmentManager(),mTitle);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    protected void bindData() {

    }

    @Override
    protected void bindData4NoNet() {

    }

    public static void show(Context context) {
        context.startActivity(new Intent(context,VisitActivity.class));
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
