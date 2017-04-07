package com.hifunki.funki.module.search.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hifunki.funki.R;
import com.hifunki.funki.base.fragment.BaseFragment;
import com.hifunki.funki.module.search.adapter.SearchUserAdapter;
import com.hifunki.funki.module.search.entity.SearchUser;

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
public class UserListFragment extends BaseFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    public String imagePathss = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489236953984&di=3ba08e016f9b18d0be82354d4c18ff00&imgtype=0&src=http%3A%2F%2Ftupian.enterdesk.com%2F2013%2Fxll%2F011%2F13%2F2%2F7.jpg";
    @BindView(R.id.rv_search_user)
    RecyclerView rvSearchUser;
    private List<SearchUser> searchUserList;

    public UserListFragment() {
        // Required empty public constructor
    }

    public static UserListFragment newInstance(String param1, String param2) {
        UserListFragment fragment = new UserListFragment();
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
        return R.layout.fragment_user_list;
    }

    @Override
    protected void initData() {
        super.initData();
        searchUserList = new ArrayList<>();
        SearchUser searchUser = new SearchUser(imagePathss, "酱油泡饭", "我真的喜欢希斯莱杰演绎的",
                1, 75, true, false);
        searchUserList.add(searchUser);
        for (int i = 0; i < 25; i++) {
            SearchUser searchUser1 = new SearchUser(imagePathss, "酱油泡饭", "我真的喜欢希斯莱杰演绎的",
                    1, 75, false, false);
            searchUserList.add(searchUser1);
        }

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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rvSearchUser.setLayoutManager(linearLayoutManager);
        SearchUserAdapter searchUserAdapter = new SearchUserAdapter(getContext().getApplicationContext(),
                R.layout.item_search_user,searchUserList);
        rvSearchUser.setAdapter(searchUserAdapter);
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
