package com.hifunki.funki.module.dynamic.normal.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hifunki.funki.R;
import com.hifunki.funki.base.fragment.BaseFragment;
import com.hifunki.funki.module.dynamic.normal.adapter.DynamicCommentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.dynamic.normal.fragment.NormalDynamicFragment.java
 * @link
 * @since 2017-04-20 14:56:56
 */
public class NormalDynamicFragment extends BaseFragment {

    @BindView(R.id.rl_comment)
    RecyclerView rlComment;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private int mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private List<String> stringList;

    public NormalDynamicFragment() {
    }

    public static NormalDynamicFragment newInstance(int param1, String param2) {
        NormalDynamicFragment fragment = new NormalDynamicFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
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
        return R.layout.fragment_normal_dynamic;
    }

    @Override
    protected void initVariable() {
        super.initVariable();
        stringList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            stringList.add("");
        }

        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    protected void initView(View root) {
        super.initView(root);

    }

    @Override
    protected void initAdapter() {
        super.initAdapter();
        rlComment.setLayoutManager(new LinearLayoutManager(getContext()));
        DynamicCommentAdapter adapter = new DynamicCommentAdapter(R.layout.item_dynamic_comment, stringList,mParam1);
        rlComment.setAdapter(adapter);
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
