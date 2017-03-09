package com.hifunki.funki.module.home.fragment;

import android.os.Bundle;

import com.hifunki.funki.R;
import com.hifunki.funki.module.home.BaseFragment;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.home.fragment.HomeSearchFragment.java
 * @link
 * @since 2017-03-09 14:22:22
 */
public class HomeSearchFragment extends BaseFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static HomeSearchFragment newInstance(String param1, String param2) {
        HomeSearchFragment fragment = new HomeSearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_search;
    }
}
