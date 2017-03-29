package com.hifunki.funki.base.adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 在此写用途
 *
 * @author yinhaoxiang
 * @version V1.0 <描述当前版本功能>
 * @value com.powyin.test.AutoSizeAdapter.java
 * @link
 * @since 2017-03-29 13:48:48
 */
public abstract class AutoSizeAdapter<T> extends PagerAdapter {
    private static final String TAG = "AutoSizeAdapter";
    private static final boolean DEBUG = true;
    private final FragmentManager mFragmentManager;
    private FragmentTransaction mCurTransaction = null;
    private Fragment mCurrentPrimaryItem = null;


    private List<T> mDataList = new ArrayList<>();
    private OnSwipeListener mOnSwipeListener;
    private boolean isLoadingStart = false;
    private boolean isLoadingEnd = false;

    public interface OnSwipeListener {
        void loadMoreStart();
        void loadMoreEnd();
    }

    protected AutoSizeAdapter(Activity activity ) {
        if(activity instanceof AppCompatActivity){
            mFragmentManager = ((AppCompatActivity)activity).getSupportFragmentManager();
        }else {
            throw new RuntimeException(" no support yet ");
        }
    }

    // 业务-------------------------------------------------------------------------
    public void addStart(List<T> start){
        if(start==null || start.size() ==0) return;
        mDataList.addAll(0,start);
        isLoadingStart = false;
        notifyDataSetChanged();
    }

    public void addEnd(List<T> end){
        if(end==null || end.size() ==0) return;
        mDataList.addAll(mDataList.size(),end);
        isLoadingEnd = false;
        notifyDataSetChanged();
    }

    public void loadData(List<T> data){
        if(data==null) {
            data = new ArrayList<>();
        }
        mDataList.clear();
        mDataList.addAll(data);
        notifyDataSetChanged();
    }


    public void setOnSwipeListener(OnSwipeListener listener){
        this.mOnSwipeListener = listener;
    }
    public abstract Fragment getItem(T data);

    // -------------------------------------------------------------------------业务

    @Override
    public void startUpdate(ViewGroup container) {
        if (container.getId() == View.NO_ID) {
            throw new IllegalStateException("ViewPager with adapter " + this + " requires a view id");
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {


        // 控制start 刷新
        if(position<=2 && !isLoadingStart && mOnSwipeListener!=null){
            mOnSwipeListener.loadMoreStart();
            isLoadingStart = true;
        }

        // 控制end 刷新
        if(position>=mDataList.size()-3 && !isLoadingEnd && mOnSwipeListener!=null){
            mOnSwipeListener.loadMoreEnd();
            isLoadingEnd = true;
        }

        if (mCurTransaction == null) {
            mCurTransaction = mFragmentManager.beginTransaction();
        }

        String  itemId = getItemId(position);

        Fragment fragment = mFragmentManager.findFragmentByTag(itemId);
        if (fragment != null) {
            if (DEBUG) Log.v(TAG, "Attaching item #" + itemId + ": f=" + fragment);
            mCurTransaction.attach(fragment);
        } else {
            fragment = getItem(mDataList.get(position));
            if (DEBUG) Log.v(TAG, "Adding item #" + itemId + ": f=" + fragment);
            mCurTransaction.add(container.getId(), fragment, itemId);
        }
        if (fragment != mCurrentPrimaryItem) {
            fragment.setMenuVisibility(false);
            fragment.setUserVisibleHint(false);
        }

        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (mCurTransaction == null) {
            mCurTransaction = mFragmentManager.beginTransaction();
        }

        mCurTransaction.detach((Fragment)object);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        Fragment fragment = (Fragment)object;
        if (fragment != mCurrentPrimaryItem) {
            if (mCurrentPrimaryItem != null) {
                mCurrentPrimaryItem.setMenuVisibility(false);
                mCurrentPrimaryItem.setUserVisibleHint(false);
            }
            if (fragment != null) {
                fragment.setMenuVisibility(true);
                fragment.setUserVisibleHint(true);
            }
            mCurrentPrimaryItem = fragment;
        }
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        if (mCurTransaction != null) {
            mCurTransaction.commitNowAllowingStateLoss();
            mCurTransaction = null;
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return ((Fragment)object).getView() == view;
    }


    private String getItemId(int position) {
        return "AutoSizeAdapter:"+ mDataList.get(position).hashCode();
    }

    @Override
    public int getItemPosition(Object object) {
        Fragment fragment = (Fragment)object;
        String tag = fragment.getTag();
        String id[] = tag.split(":");
        for(int i = 0; i < mDataList.size(); i++){
            if(mDataList.get(i).hashCode() == Integer.valueOf(id[id.length-1])){
                return i;
            }
        }
        return super.getItemPosition(object);
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

}
