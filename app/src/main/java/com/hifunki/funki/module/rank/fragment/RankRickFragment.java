package com.hifunki.funki.module.rank.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hifunki.funki.R;
import com.hifunki.funki.base.fragment.BaseFragment;
import com.hifunki.funki.module.rank.adapter.RankAnchorAdapter;
import com.hifunki.funki.module.rank.entity.AnchorEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 世界总榜-->土豪总榜Fragment
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.rank.fragment.RankRickFragment.java
 * @link
 * @since 2017-03-14 11:18:18
 */
public class RankRickFragment extends BaseFragment {

    @BindView(R.id.rl_rick)
    RecyclerView rlRick;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private List<AnchorEntity> anchorEntities;

    public RankRickFragment() {
    }


    public static RankRickFragment newInstance(String param1, String param2) {
        RankRickFragment fragment = new RankRickFragment();
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
        return R.layout.fragment_rank_rick;
    }

    @Override
    protected void initData() {
        super.initData();
        String imagePath = "http://img5.imgtn.bdimg.com/it/u=2946893755,898530310&fm=23&gp=0.jpg";
        AnchorEntity anchorEntity1 = new AnchorEntity(AnchorEntity.TOP, 0, imagePath, "陪伴是最长情的告白", 1, 45, 7612121);
        AnchorEntity anchorEntity2 = new AnchorEntity(AnchorEntity.TOP, 0, imagePath, "陪伴是最长情的告白", 1, 45, 761212);
        AnchorEntity anchorEntity3 = new AnchorEntity(AnchorEntity.TOP, 0, imagePath, "陪伴是最长情的告白", 1, 45, 7625612);
        AnchorEntity anchorEntity4 = new AnchorEntity(AnchorEntity.NORMAL, 0, imagePath, "陪伴是最长情的告白", 1, 45, 76421);
        AnchorEntity anchorEntity5 = new AnchorEntity(AnchorEntity.NORMAL, 0, imagePath, "陪伴是最长情的告白", 1, 45, 764124);
        AnchorEntity anchorEntity6 = new AnchorEntity(AnchorEntity.NORMAL, 0, imagePath, "陪伴是最长情的告白", 1, 45, 764121);
        anchorEntities = new ArrayList<>();
        anchorEntities.add(anchorEntity1);
        anchorEntities.add(anchorEntity2);
        anchorEntities.add(anchorEntity3);
        anchorEntities.add(anchorEntity4);
        anchorEntities.add(anchorEntity5);
        anchorEntities.add(anchorEntity6);
    }

    @Override
    protected void initView(View root) {
        super.initView(root);
        RankAnchorAdapter adapter = new RankAnchorAdapter(getContext(), anchorEntities);
        rlRick.setLayoutManager(new LinearLayoutManager(getContext()));
        rlRick.setAdapter(adapter);
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
