package com.hifunki.funki.module.home.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hifunki.funki.R;
import com.hifunki.funki.base.fragment.BaseFragment;
import com.hifunki.funki.module.home.activity.HomeActivity;
import com.hifunki.funki.module.rank.activity.WorldRankActivity;
import com.hifunki.funki.module.search.activity.SearchActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.hifunki.funki.R.id.vp_home;

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
    @BindView(R.id.iv_home_ticket)
    ImageView ivHomeTicket;
    @BindView(R.id.iv_home_rank)
    ImageView ivHomeRank;
    @BindView(R.id.tv_home_follow)
    TextView tvHomeFollow;
    @BindView(R.id.tv_home_latest)
    TextView tvHomeLatest;
    @BindView(R.id.tv_home_hot)
    TextView tvHomeHot;
    @BindView(vp_home)
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

        List<String> list = new ArrayList<>();
        list.add("ss");
        list.add("aa");
        list.add("bb");
        List<Fragment> list1 = new ArrayList<>();

        list1.add(HomeHotFragment.newInstance("aa", "xx"));
        list1.add(HomeHotFragment.newInstance("aa", "xx"));
        list1.add(HomeHotFragment.newInstance("aa", "xx"));


        final List<String> datas = new ArrayList();
        for (int i = 0; i < 2; i++) {
            datas.add("数据源" + i);
        }
        vpHome = (ViewPager) root.findViewById(R.id.vp_home);

//        HomePagerAdapter adapter = new HomePagerAdapter( datas);
//        vpHome.setAdapter(adapter);


        vpHome.setCurrentItem(1);

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
    @OnClick({R.id.iv_home_search, R.id.iv_home_ticket, R.id.iv_home_rank, R.id.tv_home_follow, R.id.tv_home_latest, R.id.tv_home_hot})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_home_search:


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
            case R.id.tv_home_follow://follow
                vpHome.setCurrentItem(0);

                break;
            case R.id.tv_home_latest:
                vpHome.setCurrentItem(1);

                break;
            case R.id.tv_home_hot:
                vpHome.setCurrentItem(2);

                break;

        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
