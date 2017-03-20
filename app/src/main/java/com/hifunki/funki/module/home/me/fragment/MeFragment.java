package com.hifunki.funki.module.home.me.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hifunki.funki.R;
import com.hifunki.funki.base.fragment.BaseFragment;
import com.hifunki.funki.module.home.me.adapter.MeInfoAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 个人中心Fragment
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.home.me.fragment.MeFragment.java
 * @link
 * @since 2017-03-08 10:06:06
 */
public class MeFragment extends BaseFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    @BindView(R.id.rv_me)
    RecyclerView rvMe;

    private String photo = "http://img0.imgtn.bdimg.com/it/u=2329110913,2614087554&fm=214&gp=0.jpg";

    private OnFragmentInteractionListener mListener;
    private List<String> mInfoTag;//个人中心信息标签

    public MeFragment() {
    }

    public static MeFragment newInstance(String param1, String param2) {
        MeFragment fragment = new MeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initView(View root) {
        super.initView(root);

        MeInfoAdapter meInfoAdapter = new MeInfoAdapter(R.layout.item_me_info, mInfoTag);
        rvMe.setNestedScrollingEnabled(false);//防止滑动事件传递到RecycleView
        rvMe.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMe.setAdapter(meInfoAdapter);
    }

    @Override
    protected void initData() {
        super.initData();
        mInfoTag = new ArrayList<>();
        mInfoTag.add(getString(R.string.self_gallery));
        mInfoTag.add(getString(R.string.vistor_record));
        mInfoTag.add(getString(R.string.my_field_control));
        mInfoTag.add(getString(R.string.blacklist));
        mInfoTag.add(getString(R.string.order_management));
        mInfoTag.add(getString(R.string.account_privacy_safety));
        mInfoTag.add(getString(R.string.setting));
        mInfoTag.add(getString(R.string.help_feedback));
        mInfoTag.add(getString(R.string.business_cooperate));
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

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
