package com.hifunki.funki.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hifunki.funki.R;
import com.hifunki.funki.business.VisitorFillBusiness;
import com.hifunki.funki.ui.application.ApplicationMain;
import com.hifunki.funki.ui.widget.ToolTitleBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 游客必填信息
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.ui.activity.VisitorFillActivity.java
 * @link
 * @since 2017-02-23 20:24:24
 */
public class VisitorFillActivity extends BaseActivity implements View.OnClickListener {


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

//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
//            View decorView = getWindow().getDecorView();
//            decorView.setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
//        }
//    }


    @Override
    protected void initTitleBar() {
        ToolTitleBar.showLeftButton(this, activityVisitorFill, ToolTitleBar.BTN_TYPE_IMAGE, R.drawable.iv_back, this);
        ToolTitleBar.showCenterButton(this, activityVisitorFill, ToolTitleBar.BTN_TYPE_TEXT, R.string.visitor_title, null);
    }


    @Override
    protected void initView() {

//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        //透明导航栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        tbVisitor.setHeight(DisplayUtil.px2dip(this, 84));//设置高度

//        tbVisitor.setImmersive(true);
//        tbVisitor.setLeftImageResource(R.drawable.iv_back);
//        tbVisitor.setTitle(getResources().getString(R.string.visitor_title));
//        TextView textView = tbVisitor.getmCenterText();
//        //点击返回按键
//        tbVisitor.getLeftText().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("test", "onClick: tv");
//            }
//        });
//        textView.setTextColor(getResources().getColor(R.color.titleText));
//        TextUtil.setFzNormalTypeFace(this, textView);
//        http://blog.csdn.net/to_cm/article/details/6002812
//        TextPaint paint = textView.getPaint();
//        paint.setFakeBoldText(true);
        //设置沉浸式
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initAdapter() {

    }


    @OnClick({R.id.tvBoy, R.id.tvGirl, R.id.tvThirdSex, R.id.tvLoveSameSex, R.id.tvLoveDifferentSex, R.id.tvNotCareSex, R.id.tvLoveBothSex, R.id.tvSecretSex, R.id.etInputAge, R.id.tvVisitorConfirm, R.id.llSkip, R.id.activity_visitor_fill})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvBoy:
                VisitorFillBusiness.changeTvStyle(this, tvBoy, tvGirl, tvThirdSex);
                sex = 1;
                break;
            case R.id.tvGirl:
                VisitorFillBusiness.changeTvStyle(this, tvGirl, tvBoy, tvThirdSex);
                sex = 2;
                break;
            case R.id.tvThirdSex:
                VisitorFillBusiness.changeTvStyle(this, tvThirdSex, tvGirl, tvBoy);
                sex = 3;
                break;
            case R.id.tvLoveDifferentSex:
                isDifferentSex = !isDifferentSex;
                VisitorFillBusiness.changeTvStyle(this, isDifferentSex, tvLoveDifferentSex);
                break;
            case R.id.tvLoveSameSex:
                isSameSex = !isSameSex;
                VisitorFillBusiness.changeTvStyle(this, isSameSex, tvLoveSameSex);
                break;
            case R.id.tvNotCareSex:
                isNotCareSex = !isNotCareSex;
                VisitorFillBusiness.changeTvStyle(this, isNotCareSex, tvNotCareSex);
                break;
            case R.id.tvLoveBothSex:
                isBothSex = !isBothSex;
                VisitorFillBusiness.changeTvStyle(this, isBothSex, tvLoveBothSex);
                break;
            case R.id.tvSecretSex:
                isSecretSex = !isSecretSex;
                VisitorFillBusiness.changeTvStyle(this, isSecretSex, tvSecretSex);
                break;
            case R.id.etInputAge:

                break;
            case R.id.tvVisitorConfirm:
//                Log.e("test", "onClick: " + sex + "isDifferentSex=" + isDifferentSex + "isBothSex=" + isBothSex + "isNotCareSex=" + isNotCareSex + "isSameSex=" + isSameSex + "isSecretSex=" + isSecretSex);
//                if (sex == 0 || !isDifferentSex || !isBothSex || !isNotCareSex || !isSameSex || !isSecretSex) {
//                    return;
//                }
                //跳转规则
                boolean isJump = ((sex != 0) || isDifferentSex || isBothSex || isNotCareSex || isSameSex || isSecretSex);
                if (!isJump) {
                    return;
                }
                //设置颜色
                tvVisitorConfirm.setTextColor(getResources().getColor(R.color.vistorTvbgNormal));
                tvVisitorConfirm.setBackground(getResources().getDrawable(R.drawable.visitor_confirm_bg_yello));
                //获取年龄
                String age = etInputAge.getText().toString();

                LoginActivity.show(this);
                break;
            case R.id.llSkip:
                LoginActivity.show(this);
                break;
            case R.id.activity_visitor_fill://根布局
                break;
            case R.id.rlTitleLeft:
//                ToastUtil.showToast(this, "back");
                ApplicationMain.finishAllActivity();
                break;
        }
    }


}
