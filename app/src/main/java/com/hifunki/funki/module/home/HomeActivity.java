package com.hifunki.funki.module.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.module.home.fragment.HomeFragment;
import com.hifunki.funki.module.home.fragment.MeFragment;
import com.hifunki.funki.module.home.fragment.MsgFragment;
import com.hifunki.funki.module.home.fragment.NavFragment;
import com.hifunki.funki.module.home.fragment.StoreFragment;
import com.hifunki.funki.module.home.inter.OnTabReselectListener;
import com.hifunki.funki.module.home.widget.NavigationButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.home.HomeActivitys.java
 * @link
 * @since 2017-03-07 11:49:49
 */
public class HomeActivity extends BaseActivity implements NavFragment.OnNavigationReselectListener,
        HomeFragment.OnFragmentInteractionListener, NavFragment.OnFragmentInteractionListener,
        MsgFragment.OnFragmentInteractionListener, StoreFragment.OnFragmentInteractionListener,
        MeFragment.OnFragmentInteractionListener {

    @BindView(R.id.main_container)
    FrameLayout mainContainer;
    @BindView(R.id.activity_main_ui)
    LinearLayout activityMainUi;
    private NavFragment mNavBar;
    private List<TurnBackListener> mTurnBackListeners = new ArrayList<>();
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
            mNavBar.setup(this, manager, R.id.main_container, this);
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


    @OnClick({R.id.main_container, R.id.fag_nav, R.id.activity_main_ui})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_container:
                break;
            case R.id.fag_nav:
                break;
            case R.id.activity_main_ui:
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
        for (TurnBackListener l : mTurnBackListeners) {
            if (l.onTurnBack()) return;
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
}


