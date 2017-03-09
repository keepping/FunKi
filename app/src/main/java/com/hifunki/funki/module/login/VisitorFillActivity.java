package com.hifunki.funki.module.login;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseTitleActivity;
import com.hifunki.funki.base.application.ApplicationMain;
import com.hifunki.funki.module.login.business.VisitorFillBusiness;
import com.hifunki.funki.module.login.widget.ToolTitleBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 游客必填信息
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.login.VisitorFillActivity.java
 * @link
 * @since 2017-02-23 20:24:24
 */
public class VisitorFillActivity extends BaseTitleActivity implements View.OnClickListener {


    @BindView(R.id.tvBoy)
    TextView tvBoy;
    @BindView(R.id.tvGirl)
    TextView tvGirl;
    @BindView(R.id.tvThirdSex)
    TextView tvThirdSex;
    @BindView(R.id.tvLoveSameSex)
    TextView tvLoveSameSex;
    @BindView(R.id.tvLoveDifferentSex)
    TextView tvLoveDifferentSex;
    @BindView(R.id.tvNotCareSex)
    TextView tvNotCareSex;
    @BindView(R.id.tvLoveBothSex)
    TextView tvLoveBothSex;
    @BindView(R.id.tvSecretSex)
    TextView tvSecretSex;
    @BindView(R.id.etInputAge)
    EditText etInputAge;
    @BindView(R.id.tvVisitorConfirm)
    TextView tvVisitorConfirm;
    @BindView(R.id.llSkip)
    LinearLayout llSkip;
    @BindView(R.id.activity_visitor_fill)
    LinearLayout activityVisitorFill;

    private int sex;
    private boolean isDifferentSex;
    private boolean isSameSex;
    private boolean isNotCareSex;
    private boolean isBothSex;
    private boolean isSecretSex;
    private String TAG = "VisitorFillActivity";

    public static void show(Context context) {
        context.startActivity(new Intent(context, VisitorFillActivity.class));
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_visitor_fill;
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initTitleBar() {
        ToolTitleBar.showLeftButton(this, activityVisitorFill, ToolTitleBar.BTN_TYPE_IMAGE, R.drawable.iv_back, this);
        ToolTitleBar.showCenterButton(this, activityVisitorFill, ToolTitleBar.BTN_TYPE_TEXT, R.string.visitor_title, null);
    }


    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {
        etInputAge.addTextChangedListener(etAgeTextWatcher);
    }

    @Override
    protected void initAdapter() {

    }

    @OnClick({R.id.tvBoy, R.id.tvGirl, R.id.tvThirdSex, R.id.tvLoveSameSex, R.id.tvLoveDifferentSex, R.id.tvNotCareSex, R.id.tvLoveBothSex, R.id.tvSecretSex, R.id.tvVisitorConfirm, R.id.llSkip, R.id.activity_visitor_fill})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvBoy://男
                VisitorFillBusiness.changeTvStyle(this, tvBoy, tvGirl, tvThirdSex);
                sex = 1;
                VisitorFillBusiness.isJump(this, tvVisitorConfirm, sex, isDifferentSex, isBothSex, isNotCareSex, isSameSex, isSecretSex);
                break;
            case R.id.tvGirl://女
                VisitorFillBusiness.changeTvStyle(this, tvGirl, tvBoy, tvThirdSex);
                sex = 2;
                VisitorFillBusiness.isJump(this, tvVisitorConfirm, sex, isDifferentSex, isBothSex, isNotCareSex, isSameSex, isSecretSex);
                break;
            case R.id.tvThirdSex://第三性
                VisitorFillBusiness.changeTvStyle(this, tvThirdSex, tvGirl, tvBoy);
                sex = 3;
                VisitorFillBusiness.isJump(this, tvVisitorConfirm, sex, isDifferentSex, isBothSex, isNotCareSex, isSameSex, isSecretSex);
                break;
            case R.id.tvLoveSameSex://我爱同性
                isSameSex = !isSameSex;
                VisitorFillBusiness.changeTvStyle(this, isSameSex, tvLoveSameSex);
                VisitorFillBusiness.isJump(this, tvVisitorConfirm, sex, isDifferentSex, isBothSex, isNotCareSex, isSameSex, isSecretSex);
                break;
            case R.id.tvLoveDifferentSex://我爱异性
                isDifferentSex = !isDifferentSex;
                VisitorFillBusiness.changeTvStyle(this, isDifferentSex, tvLoveDifferentSex);
                VisitorFillBusiness.isJump(this, tvVisitorConfirm, sex, isDifferentSex, isBothSex, isNotCareSex, isSameSex, isSecretSex);
                break;
            case R.id.tvNotCareSex://我都不介意
                isNotCareSex = !isNotCareSex;
                VisitorFillBusiness.changeTvStyle(this, isNotCareSex, tvNotCareSex);
                VisitorFillBusiness.isJump(this, tvVisitorConfirm, sex, isDifferentSex, isBothSex, isNotCareSex, isSameSex, isSecretSex);
                break;
            case R.id.tvLoveBothSex://男女都爱
                isBothSex = !isBothSex;
                VisitorFillBusiness.changeTvStyle(this, isBothSex, tvLoveBothSex);
                VisitorFillBusiness.isJump(this, tvVisitorConfirm, sex, isDifferentSex, isBothSex, isNotCareSex, isSameSex, isSecretSex);
                break;
            case R.id.tvSecretSex://保密
                isSecretSex = !isSecretSex;
                VisitorFillBusiness.changeTvStyle(this, isSecretSex, tvSecretSex);
                VisitorFillBusiness.isJump(this, tvVisitorConfirm, sex, isDifferentSex, isBothSex, isNotCareSex, isSameSex, isSecretSex);
                break;
            case R.id.tvVisitorConfirm:
                //跳转规则
                boolean jump = VisitorFillBusiness.isJump(sex, isDifferentSex, isBothSex, isNotCareSex, isSameSex, isSecretSex);
                if (jump) {
                    //获取年龄
                    String age = etInputAge.getText().toString();
                    LoginActivity.show(this);
                }
                break;
            case R.id.llSkip:
                LoginActivity.show(this);
                break;
            case R.id.activity_visitor_fill://根布局
                break;
            case R.id.rlTitleLeft:
                ApplicationMain.finishAllActivity();
                break;
        }
    }


    /**
     * 年龄监听
     */
    private TextWatcher etAgeTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            etInputAge.setCursorVisible(true);

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            int length = s.length();
            etInputAge.setSelection(length);
            if (length == 0) {
                etInputAge.setCursorVisible(false);
            }

        }
    };


}
