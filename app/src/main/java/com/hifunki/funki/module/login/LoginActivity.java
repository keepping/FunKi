package com.hifunki.funki.module.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.AccountBaseActivity;
import com.hifunki.funki.base.adapter.PagerBaseAdapter;
import com.hifunki.funki.common.Spkey;
import com.hifunki.funki.module.home.activity.HomeActivity;
import com.hifunki.funki.module.login.business.LoginBusiness;
import com.hifunki.funki.module.login.widget.ToolTitleBar;
import com.hifunki.funki.module.login.widget.layout.LayoutEmailWithType;
import com.hifunki.funki.module.login.widget.layout.LayoutPhoneWithType;
import com.hifunki.funki.util.DisplayUtil;
import com.hifunki.funki.util.PopWindowUtil;
import com.hifunki.funki.util.SPUtils;
import com.hifunki.funki.util.ToastUtils;
import com.hifunki.funki.util.ViewUtil;
import com.hifunki.funki.widget.bar.TopBarView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 登录页面信息
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.login.LoginActivity.java
 * @link {@link LayoutPhoneWithType}    {@link LayoutEmailWithType}
 * @since 2017-02-23 20:24:24
 */
public class LoginActivity extends AccountBaseActivity implements View.OnClickListener, ViewTreeObserver.OnGlobalLayoutListener, View.OnFocusChangeListener {

    private boolean isPhoneColor;
    private ArrayList<LinearLayout> mTabViews;
    @BindView(R.id.tbv_login)
    TopBarView topBarView;
    @BindView(R.id.activity_login)
    LinearLayout activityLogin;
    @BindView(R.id.fl_title)
    FrameLayout flTitle;
    @BindView(R.id.ll_icon)
    LinearLayout mLlIcon;
    @BindView(R.id.iv_logo)
    ImageView mIvLogo;
    @BindView(R.id.tv_login)
    TextView tvLogin;
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
    private PopWindowUtil pwdPopWindow;
    private View pwdView;
    private EditText mEtIuputTel;
    private EditText mEtIuputPwd;
    private int mLogoHeight;
    private int mLogoWidth;
    protected InputMethodManager mInputMethodManager;
    private Activity mActivity;

    public static void show(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_login;
    }


    @Override
    protected void initVariable() {
        super.initVariable();
        mActivity = LoginActivity.this;//必须要调用,用来注册本地广播
    }

    @Override
    protected void initView() {
        vpPhoneEmail = (ViewPager) findViewById(R.id.vpPhoneEmail);
        ViewUtil.setVPNotScrool(vpPhoneEmail);

        initViewPager();

    }

    @Override
    protected void initListener() {
        topBarView.getMenuText().setOnClickListener(this);
    }

    @OnClick({R.id.activity_login, R.id.ivPhoneLine, R.id.ivEmailLine, R.id.tvPhone, R.id.tvEmail, R.id.vpPhoneEmail, R.id.tv_login, R.id.tvForgetPwd, R.id.tvHelpCenter,R.id.tv_menu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_login:
                hideKeyBoard(getCurrentFocus().getWindowToken());
                break;
            case R.id.ll_login_register:
                RegisterActivity.show(this);
                break;
            case R.id.rlTitleLeft:
                VisitorFillActivity.show(this);
                finish();
                break;
            case R.id.tv_menu:
                RegisterActivity.show(this);
            case R.id.tvPhone:
                ivPhoneLine.setVisibility(View.VISIBLE);
                ivEmailLine.setVisibility(View.INVISIBLE);
                isPhoneColor = !isPhoneColor;
                //设置行动电话的字体颜色
                tvEmail.setTextColor(getResources().getColor(R.color._6B4E9A));
                tvPhone.setTextColor(getResources().getColor(R.color._BBABD4));
                vpPhoneEmail.setCurrentItem(0);
                break;
            case R.id.tvEmail:
                ivPhoneLine.setVisibility(View.INVISIBLE);
                ivEmailLine.setVisibility(View.VISIBLE);
                isPhoneColor = !isPhoneColor;
                tvEmail.setTextColor(getResources().getColor(R.color._BBABD4));
                tvPhone.setTextColor(getResources().getColor(R.color._6B4E9A));
                vpPhoneEmail.setCurrentItem(1);
                break;
            case R.id.ivPhoneLine:
                break;
            case R.id.ivEmailLine:
                break;
            case R.id.vpPhoneEmail:
                break;
            case R.id.tv_login:
                if (mEtIuputTel.getText().toString().equals("0") && mEtIuputPwd.getText().toString().equals("0")) {
                    //保存到sp中
                    SPUtils spUtils = new SPUtils(Spkey.FILE_LOGIN);
                    spUtils.put(Spkey.KEY_LOGIN_SUCCESS, 1);

                    ToastUtils.showShortToastSafe("login success");
                    HomeActivity.show(this, mActivity);
                }
                ToastUtils.showShortToastSafe("username=0,password=0");
                break;
            case R.id.tvForgetPwd:
                //创建PopWindow
                if (pwdPopWindow == null) {
                    pwdPopWindow = PopWindowUtil.getInstance(this);
                    pwdView = LayoutInflater.from(this).inflate(R.layout.pop_login_forget_pwd, null);
                    pwdPopWindow.getPopWindow().setOnDismissListener(onDissmissListener);
                }
                pwdPopWindow.init((int) DisplayUtil.dip2Px(this, 173), LinearLayout.LayoutParams.MATCH_PARENT);
                pwdPopWindow.showPopWindow(pwdView, PopWindowUtil.ATTACH_LOCATION_WINDOW, null, 0, 0);
                TextView tvPhonePwd = (TextView) pwdView.findViewById(R.id.tv_phone_pwd);
                TextView tvEmailPwd = (TextView) pwdView.findViewById(R.id.tv_email_pwd);
                ImageView iv_close = (ImageView) pwdView.findViewById(R.id.iv_close);
                tvPhonePwd.setOnClickListener(this);
                tvEmailPwd.setOnClickListener(this);
                iv_close.setOnClickListener(this);
                break;
            case R.id.tvHelpCenter:

                break;
            case R.id.tv_phone_pwd://通过手机号找回密码
                PwdGraphActivity.show(this);
                break;
            case R.id.tv_email_pwd:
                break;
            case R.id.iv_close:
                pwdPopWindow.hidePopWindow();
                break;
        }
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


    private void initViewPager() {
        mTabViews = new ArrayList<>();
        //获取第一个视图
        LayoutPhoneWithType layoutPhoneWithType = new LayoutPhoneWithType(etItemListener, onClickListener, this, 0);

        LayoutEmailWithType layoutEmailWithType = new LayoutEmailWithType(this, 1);
        mTabViews.add(layoutPhoneWithType);
        mTabViews.add(layoutEmailWithType);

        mEtIuputTel = layoutPhoneWithType.getEtIuputTel();
        mEtIuputPwd = layoutPhoneWithType.getEtIuputPwd();

        mEtIuputTel.setOnFocusChangeListener(this);
        mEtIuputPwd.setOnFocusChangeListener(this);

        vpPhoneEmail.setAdapter(new PagerBaseAdapter<>(mTabViews));

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
    protected void onResume() {
        super.onResume();
        flTitle.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        flTitle.getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }

    //设置editText的点击监听
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        int id = v.getId();
        switch (id) {
            case R.id.etIuputTel:
                if (hasFocus) {
                    mEtIuputTel.setActivated(true);
                    mEtIuputPwd.setActivated(false);
                }
                break;
            case R.id.etIuputPwd:
                if (hasFocus) {
                    mEtIuputPwd.setActivated(true);
                    mEtIuputTel.setActivated(false);
                }
                break;
            default:
                break;
        }
    }

    //注册监听视图树的观察者
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
