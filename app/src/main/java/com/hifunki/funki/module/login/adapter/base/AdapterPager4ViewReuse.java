//package com.hifunki.funki.module.login.adapter.base;
//
//import android.content.Context;
//import android.support.v4.view.ViewPager;
//import android.view.Gravity;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//
//import com.hifunki.funki.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
///**
// * @className: AdapterPager4ViewReuse.java
// * @desc ViewPager View 视图重用适配器，特点：<br/>
// *       1，view重用机制，减少GC压力 <br/>
// *       2，提供 默认样式、默认当前页数 <br/>
// *       3，自定义 分页样式、首次当前页数 <br/>
// *       4，调用简单，外界只需调用相关{@link #init()}即可 <br/>
// *       5，实现了ViewPager.OnPageChangeListener监听 <br/>
// *
// *       注意：<br/>
// *       1，指定翻页图片后导致页数样式怪异，请重写 {@link #initPageItemHintView()} <br/>
// *       2，销毁时，最好主动调用{@link #clear()}释放内存 <br/>
// * @author 居廉
// * @time 2015-3-12 下午4:06:13
// * @since V 3.2.0
// */
//public abstract class AdapterPager4ViewReuse<DataType> extends AdapterPagerBase<DataType> implements
//		ViewPager.OnPageChangeListener {
//
//	/**
//	 * ViewPager中的View缓存集合
//	 */
//	private ArrayList<View> mViewsCacheMap = null;
//
//	/**
//	 * viewPager控件
//	 */
//	private ViewPager mViewPager;
//
//	/**
//	 * 页数提示容器
//	 */
//	private ViewGroup mItemHintContainer;
//
//	/**
//	 * 页数：选中图片
//	 */
//	private int mSelImgResId = R.drawable.exchange_select;
//
//	/**
//	 * 页数：未选中图片
//	 */
//
//	private int mUnSelImgResId = R.drawable.exchange_normal;
//
//	/**
//	 * 页数布局
//	 */
//	private LinearLayout.LayoutParams itmeHintlayoutParams;
//
//	/**
//	 * 上下文
//	 */
//	protected Context mContext;
//
//	// /////////////////////////////
//	public AdapterPager4ViewReuse(Context context, ViewPager viewPager) {
//		this.mContext = context;
//		this.mViewPager = viewPager;
//		mViewsCacheMap = new ArrayList<View>(1);
//
//		// 设置翻页监听器
//		mViewPager.setOnPageChangeListener(this);
//	}
//
//	/**
//	 * @desc 初始化ViewPager单个View试图
//	 * @param ctx
//	 * @param container
//	 * @param itemEntity
//	 *            指定postion位置的数据实体类
//	 * @param position
//	 *            当前位置
//	 * @return
//	 */
//	public abstract View initVpItemView(Context ctx, ViewGroup container, DataType itemEntity, int position);
//
//	/**
//	 * @desc View被重用，重设相关事件
//	 * @param ctx
//	 * @param container
//	 * @param itemEntity
//	 * @param position
//	 * @param reuseView
//	 * @return
//	 */
//	public abstract View reuseVpItemView(Context ctx, ViewGroup container, DataType itemEntity, int position,
//			View reuseView);
//
//	/**
//	 * @desc 初始化适配器并连接ViewPager <br/>
//	 *       无页数提示 <br/>
//	 * @param dataLists
//	 */
//	public void init(List<DataType> dataLists) {
//		if (dataLists == null) {
//			return;
//		}
//
//		this.mDataLists = dataLists;
//		initViewPager(0);
//	}
//
//	/**
//	 * @desc 初始化适配器并连接ViewPager<br/>
//	 *       有页数提示、默认第一页、页数默认圆圈样式<br/>
//	 * @param dataLists
//	 * @param itemHintContainer
//	 */
//	public void init(ArrayList<DataType> dataLists, ViewGroup itemHintContainer) {
//		init(dataLists, itemHintContainer, 0);
//	}
//
//	/**
//	 * @desc 初始化适配器并连接ViewPager<br/>
//	 *       有页数提示、默认第一页、自定义页数图片样式<br/>
//	 * @param dataLists
//	 * @param itemHintContainer
//	 * @param selImgResId
//	 * @param unSelImgResId
//	 */
//	public void init(ArrayList<DataType> dataLists, ViewGroup itemHintContainer, int selImgResId, int unSelImgResId) {
//		init(dataLists, itemHintContainer, selImgResId, unSelImgResId, 0);
//	}
//
//	/**
//	 * @desc 初始化适配器并连接ViewPager<br/>
//	 *       有页数提示、自定义显示第几页、自定义页数图片样式<br/>
//	 * @param dataLists
//	 * @param itemHintContainer
//	 * @param selImgResId
//	 * @param unSelImgResId
//	 * @param position
//	 */
//	public void init(ArrayList<DataType> dataLists, ViewGroup itemHintContainer, int selImgResId, int unSelImgResId,
//			int position) {
//		this.mSelImgResId = selImgResId;
//		this.mUnSelImgResId = unSelImgResId;
//		init(dataLists, itemHintContainer, position);
//	}
//
//	/**
//	 * @desc 初始化适配器并连接ViewPager<br/>
//	 *       有页数提示、自定义显示第几页、页数默认圆圈样式<br/>
//	 * @param dataLists
//	 * @param itemHintContainer
//	 * @param position
//	 */
//	public void init(ArrayList<DataType> dataLists, ViewGroup itemHintContainer, int position) {
//		if (dataLists == null || itemHintContainer == null) {
//			return;
//		}
//
//		this.mDataLists = dataLists;
//		this.mItemHintContainer = itemHintContainer;
//
//		initPageItemHintView(dataLists, itemHintContainer, position);
//		initViewPager(position);
//	}
//
//	@Override
//	public Object instantiateItem(ViewGroup container, int position) {
//		View view = null;
//		DataType itemEntity = mDataLists.get(position);
//
//		if (mViewsCacheMap == null || mViewsCacheMap.size() == 0) {
//			view = initVpItemView(mContext, container, itemEntity, position);
//		} else {
//			view = mViewsCacheMap.remove(0);
//			reuseVpItemView(mContext, container, itemEntity, position, view);
//		}
//
//		container.addView(view);
//		return view;
//	}
//
//	@Override
//	public void destroyItem(ViewGroup container, int position, Object object) {
//		View view = (View) object;
//		((ViewPager) container).removeView(view);
//
//		if (mViewsCacheMap == null) {
//			mViewsCacheMap = new ArrayList<View>(1);
//		}
//
//		if (mViewsCacheMap.size() == 0) {
//			mViewsCacheMap.add(view);
//		}
//	}
//
//	/**
//	 * @desc 获取当前显示View实体数据
//	 * @return
//	 */
//	public DataType getCurrItemData() {
//		if (mViewPager == null) {
//			return null;
//		}
//
//		return getItemData(mViewPager.getCurrentItem());
//	}
//
//	/**
//	 * @desc 获取指定下标的数据
//	 * @param position
//	 * @return
//	 */
//	public DataType getItemData(int position) {
//		if (mDataLists == null || mDataLists.isEmpty()) {
//			return null;
//		}
//
//		if (position < 0 || (position >= mDataLists.size())) {
//			return null;
//		}
//
//		return mDataLists.get(position);
//	}
//
//	/**
//	 * @desc 初始化 页数视图
//	 * @param datasLists
//	 * @param itemHintContainer
//	 * @param position
//	 */
//	public void initPageItemHintView(ArrayList<DataType> datasLists, ViewGroup itemHintContainer, int position) {
//		int size = datasLists.size();
//		itmeHintlayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
//				LinearLayout.LayoutParams.WRAP_CONTENT);
//		itmeHintlayoutParams.setMargins(20, 0, 0, 0);
//		itmeHintlayoutParams.gravity = Gravity.CENTER;
//
//		for (int i = 0; i < size; i++) {
//			ImageView ivItemHint = new ImageView(mContext);
//			ivItemHint.setLayoutParams(itmeHintlayoutParams);
//
//			if (i == position) {
//				ivItemHint.setImageResource(mSelImgResId);
//			} else {
//				ivItemHint.setImageResource(mUnSelImgResId);
//			}
//
//			itemHintContainer.addView(ivItemHint);
//		}
//	}
//
//	// /// ViewPager.OnPageChangeListener begin////////
//	@Override
//	public final void onPageSelected(int position) {
//		if (getCount() > 1) { // 多于1，才会翻页
//			onPageSelected(position, mDataLists);
//			setItemViewHint(position);
//		}
//	}
//
//	public void onPageSelected(int position, List<DataType> mDataLists) {
//		// 子类扩展
//	}
//
//	@Override
//	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//		// 子类扩展
//	}
//
//	@Override
//	public void onPageScrollStateChanged(int state) {
//		// 子类扩展
//	}
//
//	// /// ViewPager.OnPageChangeListener end////////
//
//	/**
//	 * @desc 释放内存
//	 */
//	public void clear() {
//		if (mViewPager != null) {
//			mViewPager.removeAllViews();
//		}
//
//		itmeHintlayoutParams = null;
//
//		if (mItemHintContainer != null) {
//			mItemHintContainer.removeAllViews();
//			mItemHintContainer = null;
//		}
//
//		if (mViewsCacheMap != null) {
//			mViewsCacheMap.clear();
//			// mViewsCacheMap = null;
//		}
//	}
//
//	/**
//	 * @desc 将是配置设置到ViewPager中，选中View
//	 */
//	private void initViewPager(int position) {
//		mViewPager.setAdapter(this);
//		mViewPager.setCurrentItem(position, false);
//	}
//
//	/**
//	 * @desc 选中翻页
//	 * @param positon
//	 */
//	private void setItemViewHint(int positon) {
//		if (mItemHintContainer == null) {
//			return;
//		}
//
//		int count = mItemHintContainer.getChildCount();
//
//		for (int i = 0; i < count; i++) {
//			ImageView imageView = (ImageView) mItemHintContainer.getChildAt(i);
//
//			if (imageView != null) {
//				int resId = (positon == i) ? mSelImgResId : mUnSelImgResId;
//				imageView.setImageResource(resId);
//			}
//		}
//	}
//
//}
