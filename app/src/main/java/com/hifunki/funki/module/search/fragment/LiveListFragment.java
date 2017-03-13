package com.hifunki.funki.module.search.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hifunki.funki.R;
import com.hifunki.funki.module.home.BaseFragment;
import com.hifunki.funki.module.search.adapter.SearchLiveAdapter;
import com.hifunki.funki.module.search.entity.LiveEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.search.fragment.LiveFragment.java
 * @link
 * @since 2017-03-13 09:46:46
 */
public class LiveListFragment extends BaseFragment {


    @BindView(R.id.rl_live)
    RecyclerView rlLive;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private String imagePath = "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2043558769,3881627522&fm=23&gp=0.jpg";
    private List<LiveEntity> liveEntities;

    public LiveListFragment() {
    }

    public static LiveListFragment newInstance(String param1, String param2) {
        LiveListFragment fragment = new LiveListFragment();
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
        return R.layout.fragment_live_list;
    }

    @Override
    protected void initData() {
        super.initData();
        liveEntities = new ArrayList<>();
        LiveEntity vipLive = new LiveEntity(LiveEntity.VIP_LIVE, imagePath, "垃圾朋克", "波士顿", "English", 46523, imagePath, "欢迎大家来看我的调酒表演", "柠檬红茶", "电音控", true, 50, 25, 42, 0, false, false);
        LiveEntity levelLive = new LiveEntity(LiveEntity.LEVEL_LIVE, imagePath, "垃圾朋克", "波士顿", "English", 46523, imagePath, "欢迎大家来看我的调酒表演", "柠檬红茶", "电音控", false, 0, 0, 0, 48, false, false);
//        LiveEntity normalLive = new LiveEntity(LiveEntity.NORMAL_LIVE, imagePath, "垃圾朋克", "波士顿", "English", 46523, imagePath, "欢迎大家来看我的调酒表演", "柠檬红茶", "电音控", false, 0, 0, 0, 0, true, false);
//        LiveEntity inviteLive = new LiveEntity(LiveEntity.INVITE_LIVE, imagePath, "垃圾朋克", "波士顿", "English", 46523, imagePath, "欢迎大家来看我的调酒表演", "柠檬红茶", "电音控", false, 0, 0, 0, 0, false, true);
        liveEntities.add(vipLive);
        liveEntities.add(levelLive);
//        liveEntities.add(normalLive);
//        liveEntities.add(inviteLive);
    }

    @Override
    protected void initView(View root) {
        super.initView(root);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext().getApplicationContext());

        SearchLiveAdapter searchLiveAdapter = new SearchLiveAdapter(getContext().getApplicationContext(), liveEntities);
        rlLive.setLayoutManager(linearLayoutManager);

        rlLive.setAdapter(searchLiveAdapter);

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
