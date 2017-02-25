package com.hifunki.funki.ui.adapter.base;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

/**
 * @className: AdapterPagerBase.java
 * @desc PagerAdapter基类
 * @author 居廉
 * @time 2015-2-4 上午11:50:19
 * @since V 3.1.0
 */
public class AdapterPagerBase<DataType> extends PagerAdapter {
	/**
	 * 数据源
	 */
	protected List<DataType> mDataLists;
	
	
	@Override
	public int getCount() {
		if (mDataLists == null) {
			return 0;
		}
		
		return mDataLists.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

	@Override
	public void setPrimaryItem(ViewGroup container, int position, Object object) {
		super.setPrimaryItem(container, position, object);
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		((ViewPager) container).removeView((View)object);
	}
	
}
