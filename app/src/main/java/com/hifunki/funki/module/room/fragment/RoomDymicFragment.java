package com.hifunki.funki.module.room.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hifunki.funki.R;
import com.hifunki.funki.base.fragment.BaseFragment;
import com.hifunki.funki.common.CommonConst;
import com.hifunki.funki.module.home.entity.HomeHotEntity;
import com.hifunki.funki.module.room.adpater.RoomDynamicAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 他人个人空间 --动态
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.room.fragment.RoomDymicFragment.java
 * @link
 * @since 2017-03-24 10:11:11
 */
public class RoomDymicFragment extends BaseFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    @BindView(R.id.rl_dymic)
    RecyclerView rlDymic;

    private List<String> mInfoTag;//个人中心信息标签

    private List<HomeHotEntity> hotEntities;
    private OnFragmentInteractionListener mListener;

    public RoomDymicFragment() {

    }

    public static RoomDymicFragment newInstance(String param1, String param2) {
        RoomDymicFragment fragment = new RoomDymicFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_room_dymic;
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
    protected void initVariable() {
        super.initVariable();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        hotEntities = new ArrayList<>();
        //普通直播--big
        HomeHotEntity homeHotEntity1 = new HomeHotEntity(HomeHotEntity.NORMAL_LIVE, true, CommonConst.photo, "嘻哈朋克", "新西兰", "Japanese", 45212, "欢迎来到我的直播间", "电音狂魔", "双性变", CommonConst.photo, false, false, 0, false, false, 0, 0, 0, false);//pgc
        //等级直播--big
        HomeHotEntity homeHotEntity3 = new HomeHotEntity(HomeHotEntity.LEVEL_LIVE, true, CommonConst.photo, "嘻哈朋克", "新西兰", "Japanese", 45212, "欢迎来到我的直播间", "电音狂魔", "双性变", CommonConst.photo, false, true, 75, false, false, 0, 0, 0, false);//pgc
        //普通视频--big
        HomeHotEntity homeHotEntity5 = new HomeHotEntity(HomeHotEntity.NORMAL_VIDEO, true, CommonConst.photo, "嘻哈朋克", "新西兰", "Japanese", 45212, "欢迎来到我的直播间", "电音狂魔", "双性变", CommonConst.photo, true, false, 0, false, false, 0, 0, 0, false);//only pgc

        //门票直播--small view
        HomeHotEntity homeHotEntity6 = new HomeHotEntity(HomeHotEntity.LEVEL_LIVE, true, CommonConst.photo, "嘻哈朋克", "新西兰", "Japanese", 45212, "欢迎来到我的直播间", "电音狂魔", "双性变", CommonConst.photo, false, false, 0, false, true, 50, 25, 42, false);// pgc
        HomeHotEntity homeHotEntity7 = new HomeHotEntity(HomeHotEntity.LEVEL_LIVE, true, CommonConst.photo, "嘻哈朋克", "新西兰", "Japanese", 45212, "欢迎来到我的直播间", "电音狂魔", "双性变", CommonConst.photo, false, false, 0, false, true, 100, 25, 84, false);//ugc
        //门票视频--small
        HomeHotEntity homeHotEntity8 = new HomeHotEntity(HomeHotEntity.LEVEL_LIVE, true, CommonConst.photo, "嘻哈朋克", "新西兰", "Japanese", 45212, "欢迎来到我的直播间", "电音狂魔", "双性变", CommonConst.photo, true, false, 0, false, false, 0, 0, 0, true);//only pgc

        hotEntities.add(homeHotEntity1);
        hotEntities.add(homeHotEntity3);
        hotEntities.add(homeHotEntity5);
        hotEntities.add(homeHotEntity6);
        hotEntities.add(homeHotEntity7);
        hotEntities.add(homeHotEntity8);
    }

    @Override
    protected void bindData() {

    }

    @Override
    protected void bindData4NoNet() {

    }

    @Override
    protected void initView(View root) {
        super.initView(root);
        RoomDynamicAdapter adapter = new RoomDynamicAdapter(hotEntities);
        rlDymic.setLayoutManager(new LinearLayoutManager(getContext()));
        rlDymic.setAdapter(adapter);
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
