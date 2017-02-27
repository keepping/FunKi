package com.hifunki.funki.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hifunki.funki.R;
import com.hifunki.funki.business.VisitorFillBusiness;
import com.hifunki.funki.ui.widget.ToolTitleBar;
import com.hifunki.funki.util.LogUtils;

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
    private int sexOrientation;

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
                sexOrientation = 1;
                break;
            case R.id.tvLoveSameSex:
                VisitorFillBusiness.changeTvStyle(this, tvLoveSameSex, tvLoveDifferentSex, tvNotCareSex, tvLoveBothSex, tvSecretSex);
                sexOrientation = 2;
                break;
            case R.id.tvLoveDifferentSex:
                VisitorFillBusiness.changeTvStyle(this, tvLoveDifferentSex, tvLoveSameSex, tvNotCareSex, tvLoveBothSex, tvSecretSex);
                sexOrientation = 3;
                break;
            case R.id.tvNotCareSex:
                VisitorFillBusiness.changeTvStyle(this, tvNotCareSex, tvLoveSameSex, tvLoveDifferentSex, tvLoveBothSex, tvSecretSex);
                sexOrientation = 4;
                break;
            case R.id.tvLoveBothSex:
                VisitorFillBusiness.changeTvStyle(this, tvLoveBothSex, tvLoveSameSex, tvLoveDifferentSex, tvNotCareSex, tvSecretSex);
                sexOrientation = 5;
                break;
            case R.id.tvSecretSex:
                VisitorFillBusiness.changeTvStyle(this, tvSecretSex, tvLoveSameSex, tvLoveDifferentSex, tvNotCareSex, tvLoveBothSex);
                sexOrientation = 6;
                break;
            case R.id.etInputAge:

                break;
            case R.id.tvVisitorConfirm:
                if (sex == 0 && sexOrientation == 0) {
                    return;
                }
                //设置颜色
                tvVisitorConfirm.setTextColor(getResources().getColor(R.color.vistorTvbgNormal));
                tvVisitorConfirm.setBackground(getResources().getDrawable(R.drawable.visitor_confirm_bg_yello));
                Intent intent = new Intent();
                intent.setClass(VisitorFillActivity.this, LoginActivity.class);

                //获取年龄
                String age = etInputAge.getText().toString();


                startActivity(intent);
                break;
            case R.id.llSkip:

                startActivity(new Intent(VisitorFillActivity.this, LoginActivity.class));
                break;
            case R.id.activity_visitor_fill://根布局
                break;
            case R.id.rlTitleLeft:
//                ToastUtil.showToast(this, "back");
                LogUtils.e("back");
                break;
        }
    }


}
