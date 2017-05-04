package com.hifunki.funki.module.home.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.hifunki.funki.R;
import com.hifunki.funki.base.fragment.BaseFragment;
import com.hifunki.funki.module.home.activity.HomeActivity;
import com.hifunki.funki.module.home.adapter.HomePagerAdapter;
import com.hifunki.funki.module.rank.world.activity.WorldRankActivity;
import com.hifunki.funki.module.search.activity.SearchActivity;
import com.hifunki.funki.module.show.activity.ShowActivity;
import com.hifunki.funki.util.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.hifunki.funki.R.id.rb_home_follow;

/**
 * 首页Fragment
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.home.fragment.HomeFragment.java
 * @link
 * @since 2017-03-08 10:06:06
 */
public class HomeFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.iv_home_search)
    ImageView ivHomeSearch;
    @BindView(R.id.iv_home_funki)
    ImageView ivHomeFunki;
    @BindView(R.id.iv_home_show)
    ImageView ivHomeShow;
    @BindView(R.id.iv_home_rank)
    ImageView ivHomeRank;
    @BindView(R.id.iv_home_indicate)
    ImageView ivHomeIndicate;
    @BindView(rb_home_follow)
    RadioButton rbHomeFollow;
    @BindView(R.id.rb_home_hot)
    RadioButton rbHomeHot;
    @BindView(R.id.rb_home_new)
    RadioButton rbHomeNew;
    @BindView(R.id.rg_home_title)
    RadioGroup rgHomeTitle;
    @BindView(R.id.vp_home)
    ViewPager vpHome;

    private String mParam1;
    private String mParam2;

    private FragmentManager mFragmentManager;
    private OnFragmentInteractionListener mListener;
    private HomeActivity mActivity;
    public static String TAG = HomeActivity.TAG;
    private HomePagerAdapter adapter;
    private List<Fragment> listFragment;

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
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "HomeFragment=onCreate: ");
        mFragmentManager = getFragmentManager();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initVariable() {
        super.initVariable();
        mActivity = (HomeActivity) getActivity();
        listFragment = new ArrayList<>();
        listFragment.add(HomeFollowFragment.newInstance("aa", "xx"));
        listFragment.add(HomeHotFragment.newInstance("aa", "xx"));
        listFragment.add(HomeNewFragment.newInstance("aa", "xx"));
//        EventBus.getDefault().register(this);
    }

    @Override
    protected void initView(View root) {
        super.initView(root);

        StatusBarUtil.adjustStatusBarHei(root.findViewById(R.id.toolbar_layout));

        vpHome = (ViewPager) root.findViewById(R.id.vp_home);

        adapter = new HomePagerAdapter(getChildFragmentManager(), listFragment);//the fragment manager belong to dynamic fragmentmanger

    }

    @Override
    protected void initListener() {
        super.initListener();
        rgHomeTitle.setOnCheckedChangeListener(this);
        vpHome.addOnPageChangeListener(this);

        //默认点中热门-->需要在viewpager设置了监听之后写
        vpHome.setAdapter(adapter);
        vpHome.setCurrentItem(1);
    }


    @Override
    protected void bindData() {

    }

    @Override
    protected void bindData4NoNet() {

    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @OnClick({R.id.iv_home_search, R.id.iv_home_funki, R.id.iv_home_show, R.id.iv_home_rank, R.id.iv_home_indicate, rb_home_follow, R.id.rb_home_hot, R.id.rb_home_new, R.id.rg_home_title, R.id.vp_home})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_home_search:
                //判断是否登录
//                SPUtils spUtils = new SPUtils(Spkey.FILE_LOGIN);
//                if (spUtils.getInt(Spkey.KEY_LOGIN_SUCCESS) != 1) {
//                    LoginActivity.show(homeActivity);
//                } else {  }
                SearchActivity.show(mActivity);
                break;
            case R.id.iv_home_show:
                ShowActivity.show(mActivity);
                break;
            case R.id.iv_home_rank:
                WorldRankActivity.show(mActivity);
                break;
            case R.id.iv_home_funki:
                break;
            case R.id.iv_home_indicate:

                break;
            case rb_home_follow:
                break;
            case R.id.rb_home_hot:
                break;
            case R.id.rb_home_new:
                break;
            case R.id.rg_home_title:
                break;
            case R.id.vp_home:
                break;

        }
    }

    /**
     * RadioGroup的监听
     *
     * @param group
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.rb_home_follow:
                vpHome.setCurrentItem(0);
                ivHomeIndicate.setVisibility(View.INVISIBLE);
                break;
            case R.id.rb_home_hot:
                vpHome.setCurrentItem(1);
                ivHomeIndicate.setVisibility(View.VISIBLE);
                break;
            case R.id.rb_home_new:
                vpHome.setCurrentItem(2);
                ivHomeIndicate.setVisibility(View.INVISIBLE);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                rbHomeFollow.setChecked(true);
                rbHomeFollow.setTextColor(getResources().getColor(R.color._FFD71B));
                rbHomeHot.setTextColor(getResources().getColor(R.color._BBABD4));
                rbHomeNew.setTextColor(getResources().getColor(R.color._BBABD4));
                ivHomeIndicate.setVisibility(View.INVISIBLE);
                break;
            case 1:
                rbHomeHot.setChecked(true);
                rbHomeHot.setTextColor(getResources().getColor(R.color._FFD71B));
                rbHomeFollow.setTextColor(getResources().getColor(R.color._BBABD4));
                rbHomeNew.setTextColor(getResources().getColor(R.color._BBABD4));
                ivHomeIndicate.setVisibility(View.VISIBLE);

                //暂停followMovie

                break;
            case 2:
                rbHomeNew.setChecked(true);
                rbHomeNew.setTextColor(getResources().getColor(R.color._FFD71B));
                rbHomeHot.setTextColor(getResources().getColor(R.color._BBABD4));
                rbHomeFollow.setTextColor(getResources().getColor(R.color._BBABD4));
                ivHomeIndicate.setVisibility(View.INVISIBLE);

                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
