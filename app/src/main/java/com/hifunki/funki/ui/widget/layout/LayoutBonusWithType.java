//package com.hifunki.funki.ui.widget.layout;
//
//import java.util.ArrayList;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
//import com.handmark.pulltorefresh.library.PullToRefreshListView;
//import com.jollycorp.jollychic.R;
//import com.jollycorp.jollychic.business.BusinessCommon;
//import com.jollycorp.jollychic.common.CommonConst;
//import com.jollycorp.jollychic.data.entity.BonusEntity;
//import com.jollycorp.jollychic.tools.ToolList;
//import com.jollycorp.jollychic.tools.ToolView;
//import com.jollycorp.jollychic.ui.adapter.AdapterBonusList;
//
///**
// *
// * @className LayoutBonusWithType.java
// * @date 2015-2-26 上午9:55:26
// * @author 匡衡
// * @since V 3.5
// * @discription 红包layout
// */
//public class LayoutBonusWithType extends LinearLayout {
//
//	private View thisView;
//	private Context context;
//	private int type;
//
//	private PullToRefreshListView lvBonus;
//	private View emptyView;
//	private View vLoadedErrorView;
//	private TextView tvEmptyBonus;
//
//	private AdapterBonusList adapterBonusList;
//
//	private ImageView ivImage;
//
//	public LayoutBonusWithType(Context context, int type, OnRefreshListener2<ListView> onRefreshListener2, OnClickListener onClickListener) {
//		super(context);
//		this.context = context;
//		this.type = type;
//		thisView = LayoutInflater.from(context).inflate(R.layout.layout_bonus, this);
//		vLoadedErrorView = ToolView.createLoadedFailedView(context);
//		initView(onRefreshListener2, onClickListener);
//	}
//
//	@SuppressLint("InflateParams")
//	private void initView(OnRefreshListener2<ListView> onRefreshListener2, OnClickListener onClickListener) {
//		lvBonus = (PullToRefreshListView) thisView.findViewById(R.id.lvBonus);
//		lvBonus.setOnRefreshListener(onRefreshListener2);
//		lvBonus.setOnViewClickListener(onClickListener);
//		emptyView = LayoutInflater.from(context).inflate(R.layout.emptyview_bonus, null);
//		tvEmptyBonus = (TextView) emptyView.findViewById(R.id.tvEmptyBonus);
//		ivImage = (ImageView) emptyView.findViewById(R.id.ivEmptyBonus);
//		vLoadedErrorView.findViewById(R.id.ivLoadingFailed).setOnClickListener(onClickListener);
//	}
//
//	/**
//	 * 更新视图
//	 *
//	 * @param listBonus
//	 */
//	public void updateAdapterView(ArrayList<BonusEntity> listBonus) {
//		refreshComplete();
//		if (ToolList.isEmpty(listBonus)) {
//			setEmptyView();
//			return;
//		}
//		if (adapterBonusList == null) {
//			adapterBonusList = new AdapterBonusList(context, listBonus);
//			lvBonus.setAdapter(adapterBonusList);
//		} else {
//			adapterBonusList.setList(listBonus);
//			adapterBonusList.notifyDataSetChanged();
//		}
//	}
//
//	/**
//	 * 请求完成后设置参数
//	 *
//	 * @param requestSuccess
//	 */
//	public void setRequestSuccess(boolean requestSuccess) {
//		if (lvBonus != null) {
//			lvBonus.setRequetSuccess(requestSuccess);
//		}
//	}
//
//	/**
//	 * 显示首次加载失败视图
//	 *
//	 * @param loadedFailedView
//	 */
//	public void showLoadedFailedView() {
//		if (vLoadedErrorView == null) {
//			return;
//		}
//		if (vLoadedErrorView.getVisibility() == View.GONE) {
//			vLoadedErrorView.setVisibility(View.VISIBLE);
//		}
//		lvBonus.setEmptyView(vLoadedErrorView);
//	}
//
//	/**
//	 * 隐藏加载失败视图
//	 *
//	 * @param loadedFailedView
//	 */
//	public void hideLoadedFailedView() {
//		if (vLoadedErrorView == null) {
//			return;
//		}
//		if (vLoadedErrorView.getVisibility() == View.VISIBLE) {
//			vLoadedErrorView.setVisibility(View.GONE);
//		}
//	}
//
//	/**
//	 * 刷新完成
//	 */
//	public void refreshComplete() {
//		ToolView.refreshCompleted(lvBonus);
//	}
//
//	/**
//	 * 刷新失败时视图
//	 */
//	public void setNetWorkError() {
//		if (lvBonus != null) {
//			lvBonus.setNetWorkError();
//		}
//	}
//
//	public void setRefresh(boolean doScroll) {
//		if (lvBonus != null) {
//			lvBonus.setRefreshing(doScroll);
//		}
//	}
//
//	private void setEmptyView() {
//		switch (type) {
//		case CommonConst.BONUS_ALL:
//			tvEmptyBonus.setText(context.getString(R.string.text_tip_empty_bonus, new Object[] { CommonConst.USED_STATE }));
//			break;
//		case CommonConst.BONUS_UNUSED:
//			tvEmptyBonus.setText(context.getString(R.string.mybonus_no_available));
//			ivImage.setImageDrawable(context.getResources().getDrawable(R.drawable.iv_bonus_blank));
//			break;
//		case CommonConst.BONUS_LAPSED:
//		case CommonConst.BONUS_USED:
//			tvEmptyBonus.setText(context.getString(R.string.mybonus_no_history));
//			ivImage.setImageDrawable(context.getResources().getDrawable(R.drawable.iv_bonus_no_history));
//			break;
//		}
//		lvBonus.setEmptyView(emptyView);
//	}
//
//
//
//}
