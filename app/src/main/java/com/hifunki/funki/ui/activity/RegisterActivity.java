package com.hifunki.funki.ui.activity;

import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hifunki.funki.R;
import com.hifunki.funki.ui.adapter.PagerBaseAdapter;
import com.hifunki.funki.ui.widget.TitleBar;
import com.hifunki.funki.ui.widget.layout.LayoutEmailWithType;
import com.hifunki.funki.ui.widget.layout.LayoutPhoneWithType;
import com.hifunki.funki.ui.widget.scroller.FixedSpeedScroller;
import com.hifunki.funki.util.StatusBarUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.ui.activity.RegisterActivity.java
 * @link
 * @since 2017-02-24 10:36:36
 */
public class RegisterActivity extends BaseActivity {

    @BindView(R.id.tbRegister)
    TitleBar tbRegister;
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

    @Override
    protected int getViewResId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initTitleBar() {
        tbRegister.setLeftImageResource(R.drawable.iv_back);
        tbRegister.setTitle(getString(R.string.register));
        tbRegister.getLeftText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("test", "onClick: back tv");
            }
        });
    }

    @Override
    protected void initView() {
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
        LayoutPhoneWithType layoutLoginWithType = new LayoutPhoneWithType(etItemListener, onClickListener, this, 0);
        LayoutEmailWithType layoutEmailWithType = new LayoutEmailWithType(this, 1);
        mTabViews.add(layoutLoginWithType);
        mTabViews.add(layoutEmailWithType);
        vpPhoneEmail.setAdapter(new PagerBaseAdapter<>(mTabViews));

    }

    @Override
    protected void loadDatas() {

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
                case R.id.ivTelShow:
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
            case R.id.tvPhone://行动电话
                break;
            case R.id.ivPhoneLine:
                break;
            case R.id.tvEmail://邮箱
                break;
            case R.id.ivEmailLine:
                break;
            case R.id.vpPhoneEmail://中间ViewPager
                break;
            case R.id.llRegNext://注册下一步
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

