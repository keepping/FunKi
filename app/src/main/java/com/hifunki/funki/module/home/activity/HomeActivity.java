package com.hifunki.funki.module.home.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseCoordinatorActivity;
import com.hifunki.funki.common.FragmentConst;
import com.hifunki.funki.module.dynamic.activity.PostDynamicActivity;
import com.hifunki.funki.module.home.fragment.HomeFragment;
import com.hifunki.funki.module.home.fragment.HomeHotFragment;
import com.hifunki.funki.module.home.fragment.HomeNewFragment;
import com.hifunki.funki.module.home.fragment.MsgFragment;
import com.hifunki.funki.module.home.fragment.NavFragment;
import com.hifunki.funki.module.home.fragment.StoreFragment;
import com.hifunki.funki.module.home.inter.OnTabReselectListener;
import com.hifunki.funki.module.home.widget.NavigationButton;
import com.hifunki.funki.module.live.start.activity.StartLiveActivity;
import com.hifunki.funki.module.me.MeFragment;
import com.hifunki.funki.util.DisplayUtil;
import com.hifunki.funki.util.PopWindowUtil;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 主页Activity
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.home.HomeActivitys.java
 * @link
 * @since 2017-03-07 11:49:49
 */
public class HomeActivity extends BaseCoordinatorActivity implements NavFragment.OnNavigationReselectListener,
        HomeFragment.OnFragmentInteractionListener, NavFragment.OnFragmentInteractionListener,
        MsgFragment.OnFragmentInteractionListener, StoreFragment.OnFragmentInteractionListener,
        MeFragment.OnFragmentInteractionListener, HomeHotFragment.OnFragmentInteractionListener,
        HomeNewFragment.OnFragmentInteractionListener, View.OnClickListener {

    @BindView(R.id.main_container)
    FrameLayout mainContainer;
    @BindView(R.id.activity_main_ui)
    LinearLayout activityMainUi;
    @BindView(R.id.nav_item)
    ImageView ivNavItem;

    private NavFragment mNavBar;
    private PopWindowUtil pwdPopWindow;
    private View pwdView;

    private long mBackPressedTime;

    public static void show(Context context, Activity activity) {
        context.startActivity(new Intent(context, HomeActivity.class));
        activity.finish();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    public interface TurnBackListener {
        boolean onTurnBack();
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initTitleBar() {

    }

    @Override
    protected void initView() {
        FragmentManager manager = getSupportFragmentManager();
        mNavBar = ((NavFragment) manager.findFragmentById(R.id.fag_nav));

        if (mNavBar != null) {
            mNavBar.setup(this, manager, R.id.main_container, this, FragmentConst.NavFragment);
        } else {
            Log.e("test", "initView: " + "null");
        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initAdapter() {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
//        doNewIntent(intent, false);
    }


    @OnClick({R.id.main_container, R.id.fag_nav, R.id.activity_main_ui, R.id.nav_item})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_container:
                break;
            case R.id.fag_nav:
                break;
            case R.id.activity_main_ui:
                break;
            case R.id.nav_item://点击跳出动态、视频
                if (pwdPopWindow == null) {
                    pwdPopWindow = PopWindowUtil.getInstance(this);
                    pwdView = LayoutInflater.from(this).inflate(R.layout.pop_home_dymic_live, null);
                    pwdPopWindow.getPopWindow().setOnDismissListener(onDissmissListener);
                }
                pwdPopWindow.init((int) DisplayUtil.dip2Px(this, 192), LinearLayout.LayoutParams.MATCH_PARENT);
                pwdPopWindow.showPopWindow(pwdView, PopWindowUtil.ATTACH_LOCATION_WINDOW, view, 0, 0);
                //动态
                LinearLayout llHomeDymic = (LinearLayout) pwdView.findViewById(R.id.ll_home_dymic);
                //直播
                LinearLayout llHomeLive = (LinearLayout) pwdView.findViewById(R.id.ll_home_live);
                llHomeDymic.setOnClickListener(this);
                llHomeLive.setOnClickListener(this);
                break;
            case R.id.ll_home_dymic://跳转到发动态
                pwdPopWindow.hidePopWindow();
                SystemClock.sleep(500);
                PostDynamicActivity.show(this);
                break;
            case R.id.ll_home_live://跳转到开启直播界面
                pwdPopWindow.hidePopWindow();
                SystemClock.sleep(500);
                StartLiveActivity.show(this);
                break;
        }
    }

    @Override
    public void onReselect(NavigationButton navigationButton) {
        Fragment fragment = navigationButton.getFragment();
        if (fragment != null
                && fragment instanceof OnTabReselectListener) {
            OnTabReselectListener listener = (OnTabReselectListener) fragment;
            listener.onTabReselect();
        }
    }

    @Override
    public void onBackPressed() {

        Configuration mConfiguration = this.getResources().getConfiguration();
        if (mConfiguration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            return;
        }


        boolean isDoubleClick = true;
        if (isDoubleClick) {
            long curTime = SystemClock.uptimeMillis();
            if ((curTime - mBackPressedTime) < (3 * 1000)) {
                finish();
            } else {
                mBackPressedTime = curTime;
                Toast.makeText(this, R.string.tip_double_click_exit, Toast.LENGTH_LONG).show();
            }
        } else {
            finish();
        }
    }

    /**
     * popupWindow dimiss
     */
    PopupWindow.OnDismissListener onDissmissListener = new PopupWindow.OnDismissListener() {
        @Override
        public void onDismiss() {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pwdPopWindow.hidePopWindow();
    }
}


