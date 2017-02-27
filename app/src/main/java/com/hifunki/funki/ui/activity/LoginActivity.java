package com.hifunki.funki.ui.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hifunki.funki.R;
import com.hifunki.funki.ui.adapter.PagerBaseAdapter;
import com.hifunki.funki.ui.widget.ToolTitleBar;
import com.hifunki.funki.ui.widget.layout.LayoutEmailWithType;
import com.hifunki.funki.ui.widget.layout.LayoutPhoneWithType;
import com.hifunki.funki.ui.widget.scroller.FixedSpeedScroller;
import com.hifunki.funki.util.DisplayUtil;
import com.hifunki.funki.util.PopWindowUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录页面信息
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.ui.activity.LoginActivity.java
 * @link {@link LayoutPhoneWithType}    {@link LayoutEmailWithType}
 * @since 2017-02-23 20:24:24
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private boolean isPhoneColor;
    private ArrayList<LinearLayout> mTabViews;

    @BindView(R.id.activity_login)
    LinearLayout activityLogin;
    @BindView(R.id.llLogin)
    LinearLayout llLogin;
    @BindView(R.id.tvForgetPwd)
    TextView tvForgetPwd;
    @BindView(R.id.tvHelpCenter)
    TextView tvHelpCenter;
    @BindView(R.id.ivPhoneLine)
    ImageView ivPhoneLine;
    @BindView(R.id.ivEmailLine)
    ImageView ivEmailLine;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.tvEmail)
    TextView tvEmail;

    @BindView(R.id.vpPhoneEmail)
    ViewPager vpPhoneEmail;
    private PopWindowUtil popWindowPwd;
    private View popViewPwd;

    @Override
    protected int getViewResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initDatas() {

    }


    @Override
    protected void initTitleBar() {
        ToolTitleBar.showLeftButton(this,activityLogin, ToolTitleBar.BTN_TYPE_IMAGE, R.drawable.iv_back, null);

        ToolTitleBar.showCenterButton(this,activityLogin, ToolTitleBar.BTN_TYPE_TEXT, R.string.login, null);
        ToolTitleBar.showRightButton(this, activityLogin, R.layout.activity_login_x, ToolTitleBar.BTN_TYPE_TEXT, R.string.register, null);

//        tbLogin.setLeftImageResource(R.drawable.iv_back);
//        tbLogin.setTitle(getString(R.string.login));
//        tbLogin.getLeftText().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("test", "onClick: back tv");
//            }
//        });
//        tbLogin.set

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


    /**
     * phone and email listener
     */
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.llCounty:
                    //TODO
                    startActivity(new Intent(LoginActivity.this, CountyListActivity.class));
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

    private void initViewPager() {
        mTabViews = new ArrayList<>();
        //获取第一个视图
        LayoutPhoneWithType layoutLoginWithType = new LayoutPhoneWithType(etItemListener, onClickListener, this, 0);
        LayoutEmailWithType layoutEmailWithType = new LayoutEmailWithType(this, 1);
        mTabViews.add(layoutLoginWithType);
        mTabViews.add(layoutEmailWithType);
        vpPhoneEmail.setAdapter(new PagerBaseAdapter<>(mTabViews));

    }


    @OnClick({R.id.activity_login, R.id.ivPhoneLine, R.id.ivEmailLine, R.id.tvPhone, R.id.tvEmail, R.id.vpPhoneEmail, R.id.llLogin, R.id.tvForgetPwd, R.id.tvHelpCenter})
//    R.id.vpPhoneEmail,
    public void onClick(View view) {
        switch (view.getId()) {

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
            case R.id.llLogin:
                break;
            case R.id.tvForgetPwd:
                //创建PopWindow
                if (popWindowPwd != null) {
                    popWindowPwd = PopWindowUtil.getInstance(this);
                    popViewPwd = LayoutInflater.from(this).inflate(R.layout.pop_forget_pwd, null);
                    popWindowPwd.init((int) DisplayUtil.dip2Px(this, 173), LinearLayout.LayoutParams.MATCH_PARENT);
                    popWindowPwd.showPopWindow(popViewPwd, PopWindowUtil.ATTACH_LOCATION_WINDOW, null, 0, 0);
                }
                TextView tvPhonePwd = (TextView) popViewPwd.findViewById(R.id.tvPhonePwd);
                TextView tvEmailPwd = (TextView) popViewPwd.findViewById(R.id.tvEmailPwd);
                ImageView iv_close = (ImageView) popViewPwd.findViewById(R.id.iv_close);
                tvPhonePwd.setOnClickListener(this);
                tvEmailPwd.setOnClickListener(this);
                iv_close.setOnClickListener(this);
                break;
            case R.id.tvHelpCenter:
                //TODO
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.tvPhonePwd://通过手机号找回密码
                startActivity(new Intent(LoginActivity.this, PwdGraphActivity.class));
                break;
            case R.id.tvEmailPwd:
                break;
            case R.id.iv_close:
                popWindowPwd.dismissPopWindow();
                break;
        }
    }


}
