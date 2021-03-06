package com.hifunki.funki.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hifunki.funki.module.home.fragment.HomeFragment;
import com.hifunki.funki.util.NetWorkUtil;

import butterknife.ButterKnife;


/**
 * Fragment基类
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.base.fragment.BaseFragment.java
 * @link
 * @since 2017-03-15 10:27:27
 */
@SuppressWarnings("WeakerAccess")
public abstract class BaseFragment extends Fragment {
    protected Context mContext;
    protected View mRoot;
    protected Bundle mBundle;
    protected LayoutInflater mInflater;
    private String TAG= HomeFragment.TAG;

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
        Log.e("BaseFragment", "BaseFragment=onActivityCreated: ");
        initVariable();
        initView(mRoot);
        initListener();
        initAdapter();
        requestData();//请求数据
    }

    protected void initVariable() {

    }

    protected void initView(View root) {

    }

    protected  void initListener() {

    }

    protected  void initAdapter() {

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
