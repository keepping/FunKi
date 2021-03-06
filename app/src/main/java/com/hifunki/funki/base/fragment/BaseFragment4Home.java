package com.hifunki.funki.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hifunki.funki.util.NetWorkUtil;

import butterknife.ButterKnife;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.base.fragment.BaseFragment4Home.java
 * @link
 * @since 2017-04-12 11:22:22
 */
public abstract class BaseFragment4Home extends Fragment {
    protected Context mContext;
    protected View mRoot;
    protected Bundle mBundle;
    protected LayoutInflater mInflater;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBundle = getArguments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mRoot = inflater.inflate(getLayoutId(), container, false);
        mInflater = inflater;

        // Bind view
        ButterKnife.bind(this, mRoot);

        return mRoot;
    }

    protected abstract int getLayoutId();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initVariable();
        initView(mRoot);
        requestData();//请求数据
    }

    protected void initVariable() {

    }

    protected void initView(View root) {

    }

    private void requestData() {
        if (!NetWorkUtil.isNetConnected()) {//没有网络
            bindData4NoNet();//无网络处理
        } else {
            bindData();//有网络处理
        }
    }

    protected void bindData() {

    }

    protected void bindData4NoNet() {

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }

}
