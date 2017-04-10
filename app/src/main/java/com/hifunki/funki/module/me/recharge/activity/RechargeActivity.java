package com.hifunki.funki.module.me.recharge.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.module.me.recharge.adapter.RechargeAdapter;
import com.hifunki.funki.module.me.recharge.entity.RechargeItem;
import com.hifunki.funki.util.PopWindowUtil;
import com.hifunki.funki.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.hifunki.funki.base.application.ApplicationMain.getContext;


/**
 * 个人中心-->充值
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.me.recharge.activity.RechargeActivity.java
 * @link
 * @since 2017-03-21 13:33:33
 */
public class RechargeActivity extends BaseActivity {

    @BindView(R.id.rl_recharge)
    RecyclerView rlRecharge;
    private List<RechargeItem> rechargeItems;
    private PopWindowUtil sharePopWindow;//分享popWindow
    private View shareView;

    public static void show(Context context) {
        context.startActivity(new Intent(context, RechargeActivity.class));
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_recharge;
    }

    @Override
    protected void initVariable() {
        rechargeItems = new ArrayList<>();
        rechargeItems.add(new RechargeItem(RechargeItem.INPUT, 10, (float) 0.99));
        rechargeItems.add(new RechargeItem(RechargeItem.NORMAL, 10, (float) 0.99));
        rechargeItems.add(new RechargeItem(RechargeItem.NORMAL, 50, (float) 4.99));
        rechargeItems.add(new RechargeItem(RechargeItem.NORMAL, 100, (float) 9.99));
        rechargeItems.add(new RechargeItem(RechargeItem.NORMAL, 200, (float) 19.99));
        rechargeItems.add(new RechargeItem(RechargeItem.NORMAL, 400, (float) 39.99));
        rechargeItems.add(new RechargeItem(RechargeItem.NORMAL, 800, (float) 79.99));
        rechargeItems.add(new RechargeItem(RechargeItem.NORMAL, 1600, (float) 159.99));
        rechargeItems.add(new RechargeItem(RechargeItem.NORMAL, 3200, (float) 319.99));

        rlRecharge.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                super.onItemChildClick(adapter, view, position);
                switch (view.getId()) {
                    case R.id.ll_recharge_input:
                        //创建PopWindow
                        if (sharePopWindow == null) {
                            sharePopWindow = PopWindowUtil.getInstance(getApplicationContext());
                            shareView = LayoutInflater.from(getContext()).inflate(R.layout.pop_recharge_input, null);
//                    sharePopWindow.getPopWindow().setOnDismissListener(onDissmissListener);
                        }
                        sharePopWindow.init(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                        sharePopWindow.showPopWindow(shareView, PopWindowUtil.ATTACH_LOCATION_WINDOW, view, 0, 0);

                        break;
                    case R.id.ll_recharge_normal:

                        ToastUtils.showShortToast("这是普通");
                        break;
                }
            }
        });

    }

    @Override
    protected void initView() {
        RechargeAdapter adapter = new RechargeAdapter(rechargeItems);
        rlRecharge.setLayoutManager(new GridLayoutManager(this, 3));
        rlRecharge.setAdapter(adapter);
    }

    @Override
    protected void bindData() {

    }

    @Override
    protected void bindData4NoNet() {

    }
}
