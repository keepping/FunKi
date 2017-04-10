package com.hifunki.funki.module.login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.AccountBaseActivity;
import com.hifunki.funki.base.adapter.PagerBaseAdapter;
import com.hifunki.funki.module.login.business.LoginBusiness;
import com.hifunki.funki.module.login.widget.ToolTitleBar;
import com.hifunki.funki.module.login.widget.layout.LayoutEmailWithType;
import com.hifunki.funki.module.login.widget.layout.LayoutPhoneWithType;
import com.hifunki.funki.module.login.widget.scroller.FixedSpeedScroller;
import com.hifunki.funki.module.photo.gallery.activity.PhotoActivity;

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
public class RegisterActivity extends AccountBaseActivity implements View.OnClickListener, ViewTreeObserver.OnGlobalLayoutListener {

    private boolean isPhoneColor;

    @BindView(R.id.fl_title)
    FrameLayout flTitle;
    @BindView(R.id.ll_icon)
    LinearLayout mLlIcon;
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
    private int mLogoHeight;
    private int mLogoWidth;

    private ArrayList<LinearLayout> mTabViews;

    public static void show(Context context) {
        context.startActivity(new Intent(context, RegisterActivity.class));
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_register;
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

        initViewPager();
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

    @Override
    protected void onResume() {
        super.onResume();
        flTitle.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        flTitle.getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }

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
                tvEmail.setTextColor(getResources().getColor(R.color._6B4E9A));
                tvPhone.setTextColor(getResources().getColor(R.color._BBABD4));
                vpPhoneEmail.setCurrentItem(0);
                break;
            case R.id.ivPhoneLine:
                break;
            case R.id.tvEmail://邮箱
                ivPhoneLine.setVisibility(View.INVISIBLE);
                ivEmailLine.setVisibility(View.VISIBLE);
                isPhoneColor = !isPhoneColor;
                tvEmail.setTextColor(getResources().getColor(R.color._BBABD4));
                tvPhone.setTextColor(getResources().getColor(R.color._6B4E9A));
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

    @Override
    public void onGlobalLayout() {
        final LinearLayout llIcon = this.mLlIcon;
        Rect KeypadRect = new Rect();

        flTitle.getWindowVisibleDisplayFrame(KeypadRect);

        int screenHeight = flTitle.getRootView().getHeight();

        int keypadHeight = screenHeight - KeypadRect.bottom;

        //更新键盘激活状态
        if (keypadHeight > 0) {
            updateKeyBoardActiveStatus(true);
        } else {
            updateKeyBoardActiveStatus(false);
        }

        if (keypadHeight > 0 && llIcon.getTag() == null) {
            final int height = llIcon.getHeight();
            final int width = llIcon.getWidth();
            this.mLogoHeight = height;
            this.mLogoWidth = width;
            llIcon.setTag(true);
            LoginBusiness.setTopMarginAnimator(llIcon, height, 0, 1);

            LoginBusiness.setAlphaAnimator(llIcon, 1, 0);
        } else if (keypadHeight == 0 && llIcon.getTag() != null) {
            final int height = mLogoHeight;
            llIcon.setTag(null);
            LoginBusiness.setTopMarginAnimator(llIcon, height, 1, 0);
            LoginBusiness.setAlphaAnimator(llIcon, 0, 1);
        }
    }
}

