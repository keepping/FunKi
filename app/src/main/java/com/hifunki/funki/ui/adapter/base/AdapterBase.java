package com.hifunki.funki.ui.adapter.base;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;


/**
 * @className: AdapterBase
 * @description:Adapter基类处理
 * @author terryzz0601@gmail.com
 * @date: 2014-7-16 下午7:33:34
 */
public abstract class AdapterBase<T> extends BaseAdapter {

	private Context mContext;
	private List<T> mList;
	private LayoutInflater mLayoutInflater;

	public AdapterBase(Context context, List<T> list) {
		this.mContext = context;
		this.mList = list;
		this.mLayoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		if (mList == null) {
			return 0;
		}
		
		return mList.size();
	}

	@Override
	public T getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public Context getContext() {
		return mContext;
	}

	public void setContext(Context mContext) {
		this.mContext = mContext;
	}

	public List<T> getList() {
		return mList;
	}

	public void setList(List<T> list) {
//		if (mList != null) {
//			mList.clear();
//		}
		this.mList = list;
	}
	
	public void addAll(List<T> list) {
		if(mList!=null){
			mList.addAll(list);
		}
	}

	public void clear(){
		if(mList!=null){
			mList.clear();
		}
	}
	
	public LayoutInflater getLayoutInflater() {
		return mLayoutInflater;
	}

	public void setLayoutInflater(LayoutInflater layoutInflater) {
		this.mLayoutInflater = layoutInflater;
	}
}
