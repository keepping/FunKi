//package com.hifunki.funki.module.login.adapter.base;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import android.content.Context;
//import android.support.v4.view.ViewPager;
//import android.util.SparseArray;
//import android.view.Gravity;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//
//import com.jollycorp.jollychic.R;
//
///**
// * @className: AdapterPager4Cycle.java
// * @desc ViewPager无限循环适配器，特点：<br/>
// *       1，view重用机制，减少GC压力 <br/>
// *       2，提供 默认样式、默认当前页数 <br/>
// *       3，自定义 分页样式、首次当前页数 <br/>
// *       4，调用简单，外界只需调用相关{@link #init()}即可 <br/>
// *       5，实现了ViewPager.OnPageChangeListener监听 <br/>
// *
// *       注意：<br/>
// *       1，构造函数中的ViewPager对象，通过它的getCurrentItem()获取选中的位置是不正确的，请调用
// *       {@link #getCurrPosition()} <br/>
// *       2，指定翻页图片后导致页数样式怪异，请重写 {@link #initPageItemHintView()} <br/>
// *       3，销毁时，最好主动调用{@link #clear()}释放内存 <br/>
// * @author 居廉
// * @time 2015-2-3 下午7:39:10
// * @since V 3.1.0
// */
//public abstract class AdapterPager4Cycle<DataType> extends AdapterPagerBase<DataType> implements
//		ViewPager.OnPageChangeListener {
//
//	/**
//	 * ViewPager中的View缓存集合
//	 */
//	private SparseArray<View> mViewsCacheMap = null;
//
//	/**
//	 * viewPager控件
//	 */
//	private ViewPager mViewPager;
//
//	/**
//	 * 注意：<br/>
//	 * ViewPager的getCurrentItem()返回的位置是不准确的<br/>
//	 * 如果获取当前ViewPager选中的位置，请调用{@link #getCurrPosition()}
//	 */
//	private int currPosition;
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
//	public AdapterPager4Cycle(Context context, ViewPager viewPager) {
//		this.mContext = context;
//		this.mViewPager = viewPager;
//		mViewsCacheMap = new SparseArray<View>(7);
//
//		// 设置翻页监听器
//		mViewPager.setOnPageChangeListener(this);
//	}
//
//	/**
//	 * @desc 获取ViewPager单个View试图
//	 * @param ctx
//	 * @param container
//	 * @param itemEntity 指定postion位置的数据实体类
//	 * @param position 当前位置
//	 * @return
//	 */
//	public abstract View getVpItemView(Context ctx, ViewGroup container, DataType itemEntity, int position);
//
//	/**
//	 * @desc 初始化适配器并连接ViewPager <br/>
//	 *       无页数提示 <br/>
//	 * @param dataLists
//	 */
//	public void init(ArrayList<DataType> dataLists) {
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
//	/**
//	 * @desc 获取当前选中位置(试图下标)
//	 * @return
//	 */
//	public int getCurrPosition() {
//		return currPosition;
//	}
//
//	@Override
//	public Object instantiateItem(ViewGroup container, int position) {
//		View view = mViewsCacheMap.get(position);
//		int size = getCount();
//
//		if (view == null) {
//			// ToolLog.v("MyShopBag", "===== (view==null), pos:" + position);
//			view = initItemViewFacade(container, position, size);
//			mViewsCacheMap.put(position, view);
//		}
//
//		container.addView(view);
//		return view;
//	}
//
//	@Override
//	public int getCount() {
//		int count = super.getCount();
//		// ToolLog.v("MyShopBag", "=======getCount() -> count:" + count);
//
//		if (count <= 0) {
//			return 0;
//		}
//
//		if (count == 1) {
//			return count;
//		}
//
//		return (count + 2);
//	}
//
//	/**
//	 * @desc 向ViewPager中添加View
//	 * @param container
//	 * @param position
//	 * @param size
//	 * @return
//	 */
//	private final View initItemViewFacade(ViewGroup container, int position, int size) {
//		View view = null;
//
//		if (size == 1) {
//			position = 0;
//			view = getVpItemView(mContext, container, mDataLists.get(position), position);
//		} else if ((position > 0) && (position <= (size - 2))) {
//			position = position - 1;
//			DataType itemEntity = mDataLists.get(position);
//			view = getVpItemView(mContext, container, itemEntity, position);
//		} else {// 当前下标是最左边或最后边，不处理
//			view = new View(mContext);
//		}
//
//		return view;
//	}
//
//	/**
//	 * @desc 获取指定位置的View
//	 * @param position
//	 * @return
//	 */
//	public View getItemView(int position) {
//		int size = mViewsCacheMap.size();
//		if (size == 0) {
//			return null;
//		}
//
//		if (size == 1) {
//			return mViewsCacheMap.get(0);
//		}
//
//		position = position + 1;
//		if (position >= size) {
//			return null;
//		}
//
//		return mViewsCacheMap.get(position);
//	}
//
//	/**
//	 * @desc 获取指定下标的数据
//	 * @param position
//	 * @return
//	 */
//	public DataType getItemData(int position){
//		if(mDataLists==null || mDataLists.isEmpty()){
//			return null;
//		}
//
//		if(position<0 || (position >= mDataLists.size())){
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
//		itmeHintlayoutParams.setMargins(10, 0, 0, 0);//间距
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
//		//ToolLog.v("MyShopBag", "=======onPageSelected() -> position:" + position + ", mViewsCacheMapSize:"
//		//		+ mViewsCacheMap.size());
//		if (mViewsCacheMap != null && mViewsCacheMap.size() > 1) { // 多于1，才会循环跳转
//			if (position < 1) { // 首位之前，跳转到末尾
//				position = mDataLists.size();
//				mViewPager.setCurrentItem(position, false);
//				return;
//			} else if (position > mDataLists.size()) { // 末位之后，跳转到首位
//				position = 1;
//				mViewPager.setCurrentItem(position, false);
//				return;
//			}
//
//			currPosition = position - 1;
//			//ToolLog.v("MyShopBag",
//			//		"====onPageSelected() -> currPosition:" + currPosition + ", size:" + mDataLists.size());
//			onPageSelected(currPosition, mDataLists);
//			setItemViewHint(currPosition);
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
//		position = position + 1;
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
