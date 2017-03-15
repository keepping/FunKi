package com.hifunki.funki.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.List;


/**
 * Adapter基类
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.base.adapter.AdapterBase.java
 * @link
 * @since 2017-03-07 13:41:41
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
