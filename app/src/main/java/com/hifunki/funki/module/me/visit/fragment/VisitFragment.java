package com.hifunki.funki.module.me.visit.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hifunki.funki.R;
import com.hifunki.funki.base.fragment.BaseFragment;
import com.hifunki.funki.module.me.visit.adapter.VisitCommentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.me.visit.fragment.VisitFragment.java
 * @link
 * @since 2017-04-21 10:25:25
 */
public class VisitFragment extends BaseFragment {

    @BindView(R.id.rl_visit)
    RecyclerView recyclerView;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private List<String> str;

    public VisitFragment() {

    }

    public static VisitFragment newInstance(String param1, String param2) {
        VisitFragment fragment = new VisitFragment();
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
    protected void initVariable() {
        super.initVariable();
        str = new ArrayList<>();
        for(int i=0;i<30;i++){
            str.add("s");
        }
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    protected void initView(View root) {
        super.initView(root);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        VisitCommentAdapter adapter=new VisitCommentAdapter(R.layout.item_visit_comment,str);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initAdapter() {
        super.initAdapter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_visit;
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
