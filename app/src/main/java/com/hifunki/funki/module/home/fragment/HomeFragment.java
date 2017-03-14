package com.hifunki.funki.module.home.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.hifunki.funki.R;
import com.hifunki.funki.library.base.fragment.BaseFragment;
import com.hifunki.funki.module.home.activity.HomeActivity;
import com.hifunki.funki.module.home.adapter.HomeViewPager;
import com.hifunki.funki.module.rank.activity.WorldRankActivity;
import com.hifunki.funki.module.search.activity.SearchActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.home.fragment.HomeFragment.java
 * @link
 * @since 2017-03-08 10:06:06
 */
public class HomeFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.iv_home_search)
    ImageView ivHomeSearch;
    @BindView(R.id.iv_home_funki)
    ImageView ivHomeFunki;
    @BindView(R.id.iv_home_ticket)
    ImageView ivHomeTicket;
    @BindView(R.id.iv_home_rank)
    ImageView ivHomeRank;
    @BindView(R.id.iv_home_indicate)
    ImageView ivHomeIndicate;
    @BindView(R.id.rb_home_follow)
    RadioButton rbHomeFollow;
    @BindView(R.id.rb_home_hot)
    RadioButton rbHomeHot;
    @BindView(R.id.rb_home_new)
    RadioButton rbHomeNew;
    @BindView(R.id.rb_home_title)
    RadioGroup rbHomeTitle;
    @BindView(R.id.vp_home)
    ViewPager vpHome;

    private String mParam1;
    private String mParam2;

    private FragmentManager mFragmentManager;
    private OnFragmentInteractionListener mListener;
    private HomeActivity mActivity;

    public HomeFragment() {
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentManager = getFragmentManager();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        super.initData();
        mActivity = (HomeActivity) getActivity();
    }

    @Override
    protected void initView(View root) {
        super.initView(root);


        List<Fragment> listFragment = new ArrayList<>();

        listFragment.add(HomeHotFragment.newInstance("aa", "xx"));
        listFragment.add(HomeFollowFragment.newInstance("aa", "xx"));
        listFragment.add(HomeNewFragment.newInstance("aa", "xx"));

        vpHome = (ViewPager) root.findViewById(R.id.vp_home);

        HomeViewPager adapter = new HomeViewPager(getFragmentManager(), listFragment);
        vpHome.setAdapter(adapter);

//        vpHome.setCurrentItem(1);

    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @SuppressWarnings("RestrictedApi")
    @OnClick({R.id.iv_home_search, R.id.iv_home_funki, R.id.iv_home_ticket, R.id.iv_home_rank, R.id.iv_home_indicate, R.id.rb_home_follow, R.id.rb_home_hot, R.id.rb_home_new, R.id.rb_home_title, R.id.vp_home})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_home_search:

                //是否登录
//                SPUtils spUtils = new SPUtils(Spkey.FILE_LOGIN);
//                if (spUtils.getInt(Spkey.KEY_LOGIN_SUCCESS) != 1) {
//                    LoginActivity.show(homeActivity);
//                } else {  }
                SearchActivity.show(mActivity);
                break;
            case R.id.iv_home_ticket:
                break;
            case R.id.iv_home_rank:
                WorldRankActivity.show(mActivity);
                break;
            case R.id.iv_home_funki:
                break;
            case R.id.iv_home_indicate:
                break;
            case R.id.rb_home_follow:
                break;
            case R.id.rb_home_hot:
                break;
            case R.id.rb_home_new:
                break;
            case R.id.rb_home_title:
                break;
            case R.id.vp_home:
                break;

        }
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
