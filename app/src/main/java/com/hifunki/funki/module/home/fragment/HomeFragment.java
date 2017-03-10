package com.hifunki.funki.module.home.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hifunki.funki.R;
import com.hifunki.funki.common.Spkey;
import com.hifunki.funki.module.home.BaseFragment;
import com.hifunki.funki.module.home.activity.HomeActivity;
import com.hifunki.funki.module.search.activity.SearchActivity;
import com.hifunki.funki.module.login.LoginActivity;
import com.hifunki.funki.util.SPUtils;

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
    @BindView(R.id.iv_home_ticket)
    ImageView ivHomeTicket;
    @BindView(R.id.iv_home_list)
    ImageView ivHomeList;
    @BindView(R.id.tv_home_focus)
    TextView tvHomeFocus;
    @BindView(R.id.tv_home_latest)
    TextView tvHomeLatest;
    @BindView(R.id.tv_home_hot)
    TextView tvHomeHot;
    @BindView(R.id.vp_home)
    ViewPager vpHome;

    private String mParam1;
    private String mParam2;

    private FragmentManager mFragmentManager;
    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
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
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @SuppressWarnings("RestrictedApi")
    @OnClick({R.id.iv_home_search, R.id.iv_home_ticket, R.id.iv_home_list, R.id.tv_home_focus, R.id.tv_home_latest, R.id.tv_home_hot, R.id.vp_home})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_home_search:
                HomeActivity homeActivity = (HomeActivity) getActivity();

                SPUtils spUtils = new SPUtils(Spkey.FILE_LOGIN);
                if (spUtils.getInt(Spkey.KEY_LOGIN_SUCCESS) != 1) {
                    LoginActivity.show(homeActivity);
                } else {
                    SearchActivity.show(homeActivity);
                }

                break;
            case R.id.iv_home_ticket:
                break;
            case R.id.iv_home_list:
                break;
            case R.id.tv_home_focus:
                break;
            case R.id.tv_home_latest:
                break;
            case R.id.tv_home_hot:
                break;
            case R.id.vp_home:
                break;
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
