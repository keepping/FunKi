package com.hifunki.funki.module.home.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.SystemClock;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import com.hifunki.funki.module.dynamic.post.activity.PostDynamicActivity;
import com.hifunki.funki.module.home.fragment.HomeFragment;
import com.hifunki.funki.module.home.fragment.HomeHotFragment;
import com.hifunki.funki.module.home.fragment.HomeNewFragment;
import com.hifunki.funki.module.home.fragment.MsgFragment;
import com.hifunki.funki.module.home.fragment.StoreFragment;
import com.hifunki.funki.module.home.widget.NavigationButton;
import com.hifunki.funki.module.live.anchor.activity.AnchorActivity;
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
public class HomeActivity extends BaseCoordinatorActivity implements HomeFragment.OnFragmentInteractionListener,
        MsgFragment.OnFragmentInteractionListener, StoreFragment.OnFragmentInteractionListener,
        MeFragment.OnFragmentInteractionListener, HomeHotFragment.OnFragmentInteractionListener,
        HomeNewFragment.OnFragmentInteractionListener, View.OnClickListener {

    @BindView(R.id.main_container)
    FrameLayout mainContainer;
    @BindView(R.id.activity_main_ui)
    LinearLayout activityMainUi;
    @BindView(R.id.nav_item)
    ImageView ivNavItem;
    @BindView(R.id.nav_item_home)
    NavigationButton navHome;
    @BindView(R.id.nav_item_msg)
    NavigationButton navMsg;
    @BindView(R.id.nav_item_store)
    NavigationButton navStore;
    @BindView(R.id.nav_item_me)
    NavigationButton navMe;

    private PopWindowUtil pwdPopWindow;
    private View pwdView;

    private long mBackPressedTime;
    public static String TAG = "HomeActivity";
    private HomeFragment homeFragment;
    private MsgFragment msgFragment;
    private StoreFragment storeFragment;
    private MeFragment meFragment;
    private FragmentManager fragmentManager;

    public static void show(Context context, Activity activity) {
        context.startActivity(new Intent(context, HomeActivity.class));
        activity.finish();
    }

    public interface TurnBackListener {
        boolean onTurnBack();
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initTitleBar() {

    }

    @Override
    protected void initView() {
        navHome.init(R.drawable.tab_icon_home,
                R.string.app_name,
                FragmentConst.HomeFragment);

        navMsg.init(R.drawable.tab_icon_msg,
                R.string.app_name,
                FragmentConst.MsgFragment);

        navStore.init(R.drawable.tab_icon_store,
                R.string.app_name,
                FragmentConst.StoreFragment);

        navMe.init(R.drawable.tab_icon_me,
                R.string.app_name,
                FragmentConst.MeFragment);

        fragmentManager = getSupportFragmentManager();
        homeFragment = HomeFragment.newInstance("a", "a");
        msgFragment = MsgFragment.newInstance("a", "a");
        storeFragment = StoreFragment.newInstance("a", "a");
        meFragment = MeFragment.newInstance("a", "a");

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.main_container, homeFragment);
        transaction.add(R.id.main_container, msgFragment);
        transaction.add(R.id.main_container, storeFragment);
        transaction.add(R.id.main_container, meFragment);
        transaction.commit();

        navHome.performClick();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initAdapter() {

    }

    @Override
    protected void bindData() {

    }

    @Override
    protected void bindData4NoNet() {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
//        doNewIntent(intent, false);
    }

    void showOrHideFragment() {

    }

    @OnClick({R.id.nav_item_home, R.id.nav_item_msg, R.id.nav_item_store, R.id.nav_item_me, R.id.main_container, R.id.activity_main_ui, R.id.nav_item})
    public void onClick(View view) {
        FragmentTransaction transaction1;
        transaction1 = fragmentManager.beginTransaction();
        switch (view.getId()) {
            case R.id.nav_item_home:
                setSelected(navHome, navMsg, navStore, navMe);
                transaction1.show(homeFragment);
                transaction1.hide(msgFragment);
                transaction1.hide(storeFragment);
                transaction1.hide(meFragment);
                transaction1.commit();
                break;
            case R.id.nav_item_msg:
                setSelected(navMsg, navHome, navStore, navMe);
                transaction1.hide(homeFragment);
                transaction1.show(msgFragment);
                transaction1.hide(storeFragment);
                transaction1.hide(meFragment);
                transaction1.commit();
                break;
            case R.id.nav_item_store:
                setSelected(navStore, navMsg, navHome, navMe);
                transaction1.hide(homeFragment);
                transaction1.hide(msgFragment);
                transaction1.show(storeFragment);
                transaction1.hide(meFragment);
                transaction1.commit();
                break;
            case R.id.nav_item_me:
                setSelected(navMe, navMsg, navStore, navHome);
                transaction1.hide(msgFragment);
                transaction1.hide(homeFragment);
                transaction1.hide(storeFragment);
                transaction1.show(meFragment);
                transaction1.commit();
                break;
            case R.id.main_container:
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
                AnchorActivity.show(this);
//                StartLiveActivity.show(this);
                break;
        }
    }

    private void setSelected(NavigationButton... button) {
        button[0].setSelected(true);
        button[1].setSelected(false);
        button[2].setSelected(false);
        button[3].setSelected(false);
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

    @Override
    public void onFragmentInteraction(Uri uri) {

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


