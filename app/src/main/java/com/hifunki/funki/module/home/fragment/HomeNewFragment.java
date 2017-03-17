package com.hifunki.funki.module.home.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hifunki.funki.R;
import com.hifunki.funki.base.fragment.BaseFragment;
import com.hifunki.funki.module.home.adapter.HomeNewAdapter;
import com.hifunki.funki.module.home.entity.HomeNewEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 首页最新Fragment
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.home.fragment.HomeNewFragment.java
 * @link
 * @since 2017-03-13 16:48:48
 */
public class HomeNewFragment extends BaseFragment {

    @BindView(R.id.rv_new)
    RecyclerView rvNew;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private List<HomeNewEntity> entities;

    public HomeNewFragment() {
    }


    public static HomeNewFragment newInstance(String param1, String param2) {
        HomeNewFragment fragment = new HomeNewFragment();
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
        return R.layout.fragment_home_new;
    }

    @Override
    protected void initData() {
        super.initData();
        String imagePath="http://pic.58pic.com/58pic/11/84/12/13S58PICuRf.jpg";
        entities = new ArrayList<>();
        //添加数据
        for(int i=0;i<10;i++){
            HomeNewEntity homeNewEntity=new HomeNewEntity(imagePath,"日本","日本语","爱笑的河豚",true);
            HomeNewEntity homeNewEntity1=new HomeNewEntity(imagePath,"日本","日本语","爱笑的河豚",false);
            entities.add(homeNewEntity);
            entities.add(homeNewEntity1);
        }
        HomeNewAdapter adapter=new HomeNewAdapter(R.layout.item_live_new,entities);

        rvNew.setLayoutManager(new GridLayoutManager(getContext(),2));

        rvNew.setAdapter(adapter);

    }

    @Override
    protected void initView(View root) {
        super.initView(root);
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
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
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
