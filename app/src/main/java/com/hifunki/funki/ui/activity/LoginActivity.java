package com.hifunki.funki.ui.activity;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.hifunki.funki.R;
import com.hifunki.funki.ui.adapter.AdapterLogin;
import com.hifunki.funki.ui.widget.FunKiTextView;
import com.hifunki.funki.ui.widget.TitleBar;
import com.hifunki.funki.ui.widget.layout.LayoutEmailWithType;
import com.hifunki.funki.ui.widget.layout.LayoutLoginWithType;
import com.hifunki.funki.ui.widget.layout.LayoutTest;
import com.hifunki.funki.ui.widget.scroller.FixedSpeedScroller;

import java.lang.reflect.Field;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.ftvPhone)
    FunKiTextView ftvPhone;
    @BindView(R.id.ftvEmail)
    FunKiTextView ftvEmail;
    private ArrayList<LinearLayout> mTabViews;

    @BindView(R.id.tbLogin)
    TitleBar tbLogin;
    @BindView(R.id.activity_login)
    LinearLayout activityLogin;

    private ViewPager vpPhoneEmail;

    @Override
    protected int getViewResId() {
        return R.layout.activity_login;
    }


    @Override
    protected void init() {
        vpPhoneEmail = (ViewPager) findViewById(R.id.vpPhoneEmail);
        Field mScroller = null;
        try {
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(vpPhoneEmail.getContext());
            mScroller.set(vpPhoneEmail, scroller);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        vpPhoneEmail.setScanScroll(false);
        initViewPager();
//        vpPhoneEmail
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.llTest);
        LayoutTest layoutTest = new LayoutTest(this);
        linearLayout.addView(layoutTest);

    }

    private void initViewPager() {
        mTabViews = new ArrayList<>();
        //获取第一个视图
        LayoutLoginWithType layoutLoginWithType = new LayoutLoginWithType(this, 0);
        LayoutEmailWithType layoutEmailWithType = new LayoutEmailWithType(this, 1);
        mTabViews.add(layoutLoginWithType);
        mTabViews.add(layoutEmailWithType);
        vpPhoneEmail.setAdapter(new AdapterLogin<>(mTabViews));

    }

    @Override
    protected void loadDatas() {

    }

    @OnClick({R.id.ftvPhone, R.id.ftvEmail, R.id.tbLogin, R.id.activity_login})
//    R.id.vpPhoneEmail,
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tbLogin:
                break;
//            case R.id.vpPhoneEmail:
//                break;
            case R.id.activity_login:
                break;
            case R.id.ftvPhone:
                vpPhoneEmail.setCurrentItem(0);
                break;
            case R.id.ftvEmail:
                vpPhoneEmail.setCurrentItem(1);
                break;
        }
    }


}
