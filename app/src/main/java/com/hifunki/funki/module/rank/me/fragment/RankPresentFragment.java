package com.hifunki.funki.module.rank.me.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hifunki.funki.R;
import com.hifunki.funki.base.fragment.BaseFragment;
import com.hifunki.funki.module.rank.me.adapter.RankAnchorAdapter;
import com.hifunki.funki.module.rank.world.entity.AnchorEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.hifunki.funki.R.id.rl_anchor;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.rank.me.fragment.RankPresentFragment.java
 * @link
 * @since 2017-03-20 14:36:36
 */
public class RankPresentFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private List<AnchorEntity> anchorEntities;
    private String mParam1;
    private String mParam2;

    @BindView(rl_anchor)
    RecyclerView rlAnchor;

    private OnFragmentInteractionListener mListener;

    public RankPresentFragment() {
    }

    public static RankPresentFragment newInstance(String param1, String param2) {
        RankPresentFragment fragment = new RankPresentFragment();
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
        return R.layout.fragment_rank_present;
    }

    @Override
    protected void initView(View root) {
        super.initView(root);
        RankAnchorAdapter mAdapter = new RankAnchorAdapter(getContext(), anchorEntities);
        rlAnchor.setLayoutManager(new LinearLayoutManager(getContext()));
        rlAnchor.setAdapter(mAdapter);

        View headView = LayoutInflater.from(getContext()).inflate(R.layout.item_rank_head_count, (ViewGroup) rlAnchor.getParent(), false);
        mAdapter.addHeaderView(headView, 1);

    }

    @Override
    protected void initVariable() {
        super.initVariable();
        String imagePath = "http://img5.imgtn.bdimg.com/it/u=2946893755,898530310&fm=23&gp=0.jpg";
        AnchorEntity anchorEntity1 = new AnchorEntity(AnchorEntity.FIRST, 0, imagePath, "陪伴是最长情的告白", 1, 45, 7612121);
        AnchorEntity anchorEntity2 = new AnchorEntity(AnchorEntity.SECOND, 0, imagePath, "陪伴是最长情的告白", 1, 45, 761212);
        AnchorEntity anchorEntity3 = new AnchorEntity(AnchorEntity.THIRD, 0, imagePath, "陪伴是最长情的告白", 1, 45, 7625612);
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
