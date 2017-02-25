package com.hifunki.funki.ui.adapter.base;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.analytics.ecommerce.Product;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

/**
 * @className: AdapterBase
 * @description:Adapter基类处理 带有GA的曝光列表
 * @author terryzz0601@gmail.com
 * @date: 2014-7-16 下午7:33:34
 */
public abstract class AdapterBaseWithImpress<T> extends BaseAdapter {
	private Context mContext;
	private List<T> mList;
	private LayoutInflater mLayoutInflater;
	private List<Product> impressList;

	public AdapterBaseWithImpress(Context context, List<T> list) {
		this.mContext = context;
		this.mLayoutInflater = LayoutInflater.from(context);
		this.mList = list;
		this.impressList = new ArrayList<Product>();
	}

	public List<Product> getImpressList() {
		return impressList;
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
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
		this.mList = list;
	}

	public LayoutInflater getLayoutInflater() {
		return mLayoutInflater;
	}

	public void setLayoutInflater(LayoutInflater layoutInflater) {
		this.mLayoutInflater = layoutInflater;
	}

}
