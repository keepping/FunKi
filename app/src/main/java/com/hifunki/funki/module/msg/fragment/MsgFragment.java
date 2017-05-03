package com.hifunki.funki.module.msg.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hifunki.funki.R;
import com.hifunki.funki.base.fragment.BaseFragment;
import com.hifunki.funki.module.msg.adapter.ExpandableItemAdapter;
import com.hifunki.funki.module.msg.adapter.MsgRecommendAdapter;
import com.hifunki.funki.module.msg.entity.Level0Item;
import com.hifunki.funki.module.msg.entity.Level1Item;
import com.hifunki.funki.widget.bar.TopBarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 消息Fragment
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.msg.fragment.MsgFragment.java
 * @link
 * @since 2017-03-08 10:06:06
 */
public class MsgFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.tbv_msg)
    TopBarView tbvMsg;
    @BindView(R.id.rv_msg)
    RecyclerView rvMsg;
    @BindView(R.id.rv_msg1)
    RecyclerView rvMsg1;
    @BindView(R.id.rv_msg_recommend)
    RecyclerView rvMsgRecommend;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private List<String> mList;
    private ArrayList<MultiItemEntity> res;
    private String TAG = getClass().getSimpleName();

    public MsgFragment() {
    }

    public static MsgFragment newInstance(String param1, String param2) {
        MsgFragment fragment = new MsgFragment();
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
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mList = new ArrayList<>();
        mList.add("nihel");
        mList.add("nihel");
        mList.add("nihel");
        mList.add("nihel");

        int lv0Count = 1;
        int lv1Count = 14;


        res = new ArrayList<>();
        for (int i = 0; i < lv0Count; i++) {
            Level0Item lv0 = new Level0Item("This is " + i + "th item in Level 0", "subtitle of " + i);
            for (int j = 0; j < lv1Count; j++) {
                Level1Item lv1 = new Level1Item("Level 1 item: " + j, "(no animation)");
                lv0.addSubItem(lv1);
            }
            res.add(lv0);
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_msg;
    }

    @Override
    protected void initView(View root) {
        super.initView(root);
        tbvMsg.getMenuImage().setOnClickListener(this);
        ExpandableItemAdapter adapter = new ExpandableItemAdapter(res);
        rvMsg.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMsg.setAdapter(adapter);


        rvMsgRecommend.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        MsgRecommendAdapter msgRecommendAdapter = new MsgRecommendAdapter(R.layout.item_msg_recommend, mList);
        rvMsgRecommend.setAdapter(msgRecommendAdapter);

        rvMsg1.setLayoutManager(new LinearLayoutManager(getContext()));
        MsgRecommendAdapter msgRecommendAdapter1 = new MsgRecommendAdapter(R.layout.item_msg_recommend, mList);
        rvMsg1.setAdapter(msgRecommendAdapter1);

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
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

}
