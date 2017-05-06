package com.hifunki.funki.module.home.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hifunki.funki.R;
import com.hifunki.funki.base.fragment.BaseFragment;
import com.hifunki.funki.common.CommonConst;
import com.hifunki.funki.module.home.adapter.HomeFollowAdapter;
import com.hifunki.funki.module.rank.world.entity.AnchorEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 首页关注Fragment
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.home.fragment.HomeFollowFragment2.java
 * @link
 * @since 2017-05-4 16:46:46
 */
public class HomeFollowFragment2 extends BaseFragment {

    @BindView(R.id.rv_follow)
    RecyclerView rvFollow;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private List<AnchorEntity> anchorEntities;

    public HomeFollowFragment2() {
    }

    public static HomeFollowFragment2 newInstance(String param1, String param2) {
        HomeFollowFragment2 fragment = new HomeFollowFragment2();
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
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_follow2;
    }

    @Override
    protected void initVariable() {
        super.initVariable();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        AnchorEntity anchorEntity1 = new AnchorEntity(AnchorEntity.FIRST, 0, CommonConst.photo, "陪伴是最长情的告白", 1, 45, 7612121);
        AnchorEntity anchorEntity2 = new AnchorEntity(AnchorEntity.SECOND, 0, CommonConst.photo, "陪伴是最长情的告白", 1, 45, 761212);
        AnchorEntity anchorEntity3 = new AnchorEntity(AnchorEntity.THIRD, 0, CommonConst.photo, "陪伴是最长情的告白", 1, 45, 7625612);
        AnchorEntity anchorEntity4 = new AnchorEntity(AnchorEntity.NORMAL, 0, CommonConst.photo, "陪伴是最长情的告白", 1, 45, 76421);
        AnchorEntity anchorEntity6 = new AnchorEntity(AnchorEntity.SPECIAL, 0, CommonConst.photo, "陪伴是最长情的告白", 1, 45, 764121);
        anchorEntities = new ArrayList<>();
        anchorEntities.add(anchorEntity1);
        anchorEntities.add(anchorEntity2);
        anchorEntities.add(anchorEntity3);
        anchorEntities.add(anchorEntity4);
        anchorEntities.add(anchorEntity6);
    }

    @Override
    protected void initView(View root) {
        super.initView(root);

        HomeFollowAdapter adapter = new HomeFollowAdapter(anchorEntities);
        rvFollow.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFollow.setAdapter(adapter);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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
