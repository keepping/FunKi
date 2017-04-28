package com.hifunki.funki.module.login;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.base.adapter.PagerBaseAdapter;
import com.hifunki.funki.module.login.widget.layout.LayoutEmailWithType;
import com.hifunki.funki.module.login.widget.layout.LayoutPhoneWithType;
import com.hifunki.funki.module.photo.gallery.activity.PhotoActivity;
import com.hifunki.funki.util.ViewUtil;
import com.hifunki.funki.util.keyBoard.KeyboardUtil;
import com.hifunki.funki.widget.bar.TopBarView;

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
public class RegisterActivity extends BaseActivity implements View.OnClickListener, View.OnFocusChangeListener {

    private boolean isPhoneColor;

    @BindView(R.id.tbv_register)
    TopBarView tbRegister;
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
    RelativeLayout activityLogin;
    private int mLogoHeight;
    private int mLogoWidth;

    @BindView(R.id.ll_edit)
    LinearLayout llEdit;
    @BindView(R.id.view_empty)
    View viewEmpty;
    private ArrayList<LinearLayout> mTabViews;
    private EditText etIuputTel;
    private EditText etIuputPwd;
    private EditText etEmailTel;
    private EditText etEmailPwd;
    ViewTreeObserver.OnGlobalLayoutListener layoutListener;

    public static void show(Context context) {
        context.startActivity(new Intent(context, RegisterActivity.class));
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initVariable() {

    }


    @Override
    protected void initTitleBar() {

    }

    @Override
    protected void initView() {
        vpPhoneEmail = (ViewPager) findViewById(R.id.vpPhoneEmail);
        ViewUtil.setVPNotScrool(vpPhoneEmail);

        initViewPager();
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

    private void initViewPager() {
        mTabViews = new ArrayList<>();
        //获取第一个视图
        LayoutPhoneWithType layoutPhoneWithType = new LayoutPhoneWithType(etItemListener, onClickListener, this, 0);
        LayoutEmailWithType layoutEmailWithType = new LayoutEmailWithType(this, 1);
        etIuputTel = layoutPhoneWithType.getEtIuputTel();
        etIuputPwd = layoutPhoneWithType.getEtIuputPwd();

        etEmailTel = layoutEmailWithType.getEtEmailTel();
        etEmailPwd = layoutEmailWithType.getEtEmailPwd();

        etIuputTel.setOnFocusChangeListener(this);
        etIuputPwd.setOnFocusChangeListener(this);
        etEmailTel.setOnFocusChangeListener(this);
        etEmailPwd.setOnFocusChangeListener(this);


        mTabViews.add(layoutPhoneWithType);
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

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.etIuputTel:
                if (hasFocus) {
                    KeyboardUtil.showKeyboard(tvPhone);
                    etIuputTel.setActivated(true);
                    etIuputPwd.setActivated(false);
                }
                break;
            case R.id.etIuputPwd:
                if (hasFocus) {
                    KeyboardUtil.showKeyboard(tvPhone);
                    etIuputTel.setActivated(false);
                    etIuputPwd.setActivated(true);
                }
                break;
            case R.id.etInputEmail:
                if (hasFocus) {
                    KeyboardUtil.showKeyboard(tvPhone);
                    etEmailTel.setActivated(true);
                    etEmailPwd.setActivated(false);
                }
                break;
            case R.id.etEmailPwd:
                if (hasFocus) {
                    KeyboardUtil.showKeyboard(tvPhone);
                    etEmailPwd.setActivated(true);
                    etEmailTel.setActivated(false);
                }
                break;
            default:
                break;
        }
    }

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
        layoutListener = KeyboardUtil.attach(this, new KeyboardUtil.IPanelHeightTarget() {
            @Override
            public void refreshHeight(int panelHeight) {
                ViewGroup.LayoutParams layoutParams = viewEmpty.getLayoutParams();
                if (layoutParams.height != panelHeight) {
                    layoutParams.height = panelHeight;
                    viewEmpty.setLayoutParams(layoutParams);
                }
            }

            @Override
            public int getHeight() {
                ViewGroup.LayoutParams layoutParams = viewEmpty.getLayoutParams();
                return layoutParams.height;
            }

            @Override
            public void onKeyboardShowing(boolean showing) {
                if (showing) {
                    mLlIcon.setVisibility(View.GONE);
                    llEdit.setVisibility(View.VISIBLE);
                } else {
                    mLlIcon.setVisibility(View.VISIBLE);
                    llEdit.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        KeyboardUtil.detach(this, layoutListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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


}

