package com.hifunki.funki.ui.activity;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hifunki.funki.R;
import com.hifunki.funki.ui.adapter.AdapterLogin;
import com.hifunki.funki.ui.widget.TitleBar;
import com.hifunki.funki.ui.widget.layout.LayoutEmailWithType;
import com.hifunki.funki.ui.widget.layout.LayoutPhoneWithType;
import com.hifunki.funki.ui.widget.scroller.FixedSpeedScroller;
import com.hifunki.funki.util.StatusBarUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;


public class LoginActivity extends BaseActivity {

    private boolean isPhoneColor;
    private ArrayList<LinearLayout> mTabViews;

    @BindView(R.id.ivPhoneLine)
    ImageView ivPhoneLine;
    @BindView(R.id.ivEmailLine)
    ImageView ivEmailLine;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.tvEmail)
    TextView tvEmail;
    @BindView(R.id.tbLogin)
    TitleBar tbLogin;
    @BindView(R.id.activity_login)
    LinearLayout activityLogin;
    @BindView(R.id.vpPhoneEmail)
    ViewPager vpPhoneEmail;

    @Override
    protected int getViewResId() {
        return R.layout.activity_login;
    }


    @Override
    protected void init() {
        StatusBarUtil.setStatusBarBackground(this, R.drawable.iv_bg);

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

    }

    private void initViewPager() {
        mTabViews = new ArrayList<>();
        //获取第一个视图
        LayoutPhoneWithType layoutLoginWithType = new LayoutPhoneWithType(this, 0);
        LayoutEmailWithType layoutEmailWithType = new LayoutEmailWithType(this, 1);
        mTabViews.add(layoutLoginWithType);
        mTabViews.add(layoutEmailWithType);
        vpPhoneEmail.setAdapter(new AdapterLogin<>(mTabViews));

    }

    @Override
    protected void loadDatas() {

    }

    @OnClick({R.id.tbLogin, R.id.activity_login, R.id.ivPhoneLine, R.id.ivEmailLine, R.id.tvPhone, R.id.tvEmail, R.id.vpPhoneEmail})
//    R.id.vpPhoneEmail,
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tbLogin:
                break;
            case R.id.activity_login:
                break;
            case R.id.tvPhone:
                ivPhoneLine.setVisibility(View.VISIBLE);
                ivEmailLine.setVisibility(View.INVISIBLE);
                isPhoneColor = !isPhoneColor;
                //设置行动电话的字体颜色
                tvEmail.setTextColor(getResources().getColor(R.color.loginTvUnClick));
                tvPhone.setTextColor(getResources().getColor(R.color.vistorTvTitle));
                vpPhoneEmail.setCurrentItem(0);
                break;
            case R.id.tvEmail:
                ivPhoneLine.setVisibility(View.INVISIBLE);
                ivEmailLine.setVisibility(View.VISIBLE);
                isPhoneColor = !isPhoneColor;
                tvEmail.setTextColor(getResources().getColor(R.color.vistorTvTitle));
                tvPhone.setTextColor(getResources().getColor(R.color.loginTvUnClick));
                vpPhoneEmail.setCurrentItem(1);
                break;
            case R.id.ivPhoneLine:
                break;
            case R.id.ivEmailLine:
                break;
            case R.id.vpPhoneEmail:
                break;

        }
    }


}
