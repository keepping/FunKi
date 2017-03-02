package com.hifunki.funki.module.login;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.module.photo.PhotoActivity;
import com.hifunki.funki.module.login.adapter.PagerBaseAdapter;
import com.hifunki.funki.module.login.widget.ToolTitleBar;
import com.hifunki.funki.module.login.widget.layout.LayoutEmailWithType;
import com.hifunki.funki.module.login.widget.layout.LayoutPhoneWithType;
import com.hifunki.funki.module.login.widget.scroller.FixedSpeedScroller;

import java.lang.reflect.Field;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 注册界面
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.login.RegisterActivity.java
 * @link
 * @since 2017-02-24 10:36:36
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {


    private boolean isPhoneColor;

    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.ivPhoneLine)
    ImageView ivPhoneLine;
    @BindView(R.id.tvEmail)
    TextView tvEmail;
    @BindView(R.id.ivEmailLine)
    ImageView ivEmailLine;
    @BindView(R.id.vpPhoneEmail)
    ViewPager vpPhoneEmail;
    @BindView(R.id.llRegNext)
    LinearLayout llRegNext;
    @BindView(R.id.ivAgree)
    ImageView ivAgree;
    @BindView(R.id.tvHelpCenter)
    TextView tvHelpCenter;
    @BindView(R.id.activity_login)
    LinearLayout activityLogin;

    private ArrayList<LinearLayout> mTabViews;

    public static void show(Context context) {
        context.startActivity(new Intent(context, RegisterActivity.class));
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initTitleBar() {
        ToolTitleBar.showLeftButton(this, activityLogin, ToolTitleBar.BTN_TYPE_IMAGE, R.drawable.iv_back, this);

        ToolTitleBar.showCenterButton(this, activityLogin, ToolTitleBar.BTN_TYPE_TEXT, R.string.register, null);
    }

    @Override
    protected void initView() {

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

    @Override
    protected void initListener() {

    }

    @Override
    protected void initAdapter() {

    }

    private void initViewPager() {
        mTabViews = new ArrayList<>();
        //获取第一个视图
        LayoutPhoneWithType layoutLoginWithType = new LayoutPhoneWithType(etItemListener, onClickListener, this, 0);
        LayoutEmailWithType layoutEmailWithType = new LayoutEmailWithType(this, 1);
        mTabViews.add(layoutLoginWithType);
        mTabViews.add(layoutEmailWithType);
        vpPhoneEmail.setAdapter(new PagerBaseAdapter<>(mTabViews));

    }


    /**
     * phone and email listener
     */
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.llCounty:
                    Log.e("test", "onClick: llcounty");
                    break;
                case R.id.etIuputTel:
                    Log.e("test", "onClick: etIuputTel");
                    break;
                case R.id.etIuputPwd:
                    Log.e("test", "onClick: etIuputPwd");
                    break;
                case R.id.iv_tel_show:
                    Log.e("test", "onClick: ivTelShow");
                    break;
            }

        }
    };

    private TextWatcher etItemListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            Log.e("test", "etItemListener: beforeTextChanged");
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Log.e("test", "etItemListener: onTextChanged");
        }

        @Override
        public void afterTextChanged(Editable s) {
            Log.e("test", "etItemListener: afterTextChanged");
        }
    };

    @OnClick({R.id.tvPhone, R.id.ivPhoneLine, R.id.tvEmail, R.id.ivEmailLine, R.id.vpPhoneEmail, R.id.llRegNext, R.id.ivAgree, R.id.tvHelpCenter, R.id.activity_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rlTitleLeft:
                //跳到登录界面
                LoginActivity.show(this);
                break;
            case R.id.tvPhone://行动电话
                ivPhoneLine.setVisibility(View.VISIBLE);
                ivEmailLine.setVisibility(View.INVISIBLE);
                isPhoneColor = !isPhoneColor;
                //设置行动电话的字体颜色
                tvEmail.setTextColor(getResources().getColor(R.color.loginTvUnClick));
                tvPhone.setTextColor(getResources().getColor(R.color.vistorTvTitle));
                vpPhoneEmail.setCurrentItem(0);
                break;
            case R.id.ivPhoneLine:
                break;
            case R.id.tvEmail://邮箱
                ivPhoneLine.setVisibility(View.INVISIBLE);
                ivEmailLine.setVisibility(View.VISIBLE);
                isPhoneColor = !isPhoneColor;
                tvEmail.setTextColor(getResources().getColor(R.color.vistorTvTitle));
                tvPhone.setTextColor(getResources().getColor(R.color.loginTvUnClick));
                vpPhoneEmail.setCurrentItem(1);
                break;
            case R.id.ivEmailLine:
                break;
            case R.id.vpPhoneEmail://中间ViewPager
                break;
            case R.id.llRegNext://注册下一步
                PhotoActivity.show(this);
                break;
            case R.id.ivAgree://同意协议
                break;
            case R.id.tvHelpCenter://协议
                break;
            case R.id.activity_login:
                break;
        }
    }
}

