package com.hifunki.www.funki.ui.activity;

import android.content.Intent;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hifunki.www.funki.R;
import com.hifunki.www.funki.ui.widget.TitleBar;
import com.hifunki.www.funki.util.StatusBarUtil;
import com.hifunki.www.funki.util.TextUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 游客必填信息
 */
public class VisitorFillActivity extends BaseActivity implements TitleBar.OnItemSelectListeners {


    @BindView(R.id.tbVisitor)
    TitleBar tbVisitor;
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
    protected void init() {
//        StatusBarUtil.setStatusBarTrans(this,false);

        StatusBarUtil.setStatusBarBackground(this, R.drawable.iv_bg);

        tbVisitor.setOnItemSelectListeners(this);

//        initState();
        tbVisitor.setLeftImageResource(R.drawable.iv_back);
        tbVisitor.setTitle(getResources().getString(R.string.visitor_title));
        TextView textView = tbVisitor.getmCenterText();

        //点击返回按键
        tbVisitor.getLeftText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("test", "onClick: tv");
            }
        });

        textView.setTextColor(getResources().getColor(R.color.titleText));

        TextUtil.setFzNormalTypeFace(this, textView);

//        http://blog.csdn.net/to_cm/article/details/6002812
        TextPaint paint = textView.getPaint();
        paint.setFakeBoldText(true);
        //设置沉浸式
//        tbVisitor.setImmersive(true);

    }

    @Override
    protected void loadDatas() {

    }


    @OnClick({R.id.tbVisitor, R.id.tvBoy, R.id.tvGirl, R.id.tvThirdSex, R.id.tvLoveSameSex, R.id.tvLoveDifferentSex, R.id.tvNotCareSex, R.id.tvLoveBothSex, R.id.tvSecretSex, R.id.etInputAge, R.id.tvVisitorConfirm, R.id.llSkip, R.id.activity_visitor_fill})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tbVisitor:

                break;
            case R.id.tvBoy:
                changeTvStyle(tvBoy, tvGirl, tvThirdSex);
                sex = 1;
                break;
            case R.id.tvGirl:
                changeTvStyle(tvGirl, tvBoy, tvThirdSex);
                sex = 2;
                break;
            case R.id.tvThirdSex:
                changeTvStyle(tvThirdSex, tvGirl, tvBoy);
                sexOrientation = 1;
                break;
            case R.id.tvLoveSameSex:
                changeTvStyle(tvLoveSameSex, tvLoveDifferentSex, tvNotCareSex, tvLoveBothSex, tvSecretSex);
                sexOrientation = 2;
                break;
            case R.id.tvLoveDifferentSex:
                changeTvStyle(tvLoveDifferentSex, tvLoveSameSex, tvNotCareSex, tvLoveBothSex, tvSecretSex);
                sexOrientation = 3;
                break;
            case R.id.tvNotCareSex:
                changeTvStyle(tvNotCareSex, tvLoveSameSex, tvLoveDifferentSex, tvLoveBothSex, tvSecretSex);
                sexOrientation = 4;
                break;
            case R.id.tvLoveBothSex:
                changeTvStyle(tvLoveBothSex, tvLoveSameSex, tvLoveDifferentSex, tvNotCareSex, tvSecretSex);
                sexOrientation = 5;
                break;
            case R.id.tvSecretSex:
                changeTvStyle(tvSecretSex, tvLoveSameSex, tvLoveDifferentSex, tvNotCareSex, tvLoveBothSex);
                sexOrientation = 6;
                break;
            case R.id.etInputAge:

                break;
            case R.id.tvVisitorConfirm:
                if (sex == 0 && sexOrientation == 0) {
                    return;
                }
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
        }
    }

    /**
     * 改变字体style
     *
     * @param tvs
     * @param textViews
     */
    private void changeTvStyle(TextView tvs, TextView... textViews) {

        tvs.setTextColor(getResources().getColor(R.color.vistorTvClickbg));
        tvs.setBackground(getResources().getDrawable(R.drawable.visitor_tv_click_bg));

        for (TextView tv : textViews) {
            tv.setTextColor(getResources().getColor(R.color.vistorTvbg));
            tv.setBackground(getResources().getDrawable(R.drawable.visitor_tv_bg));
        }

    }


    @Override
    public void onItemSelect() {
//        Log.e("test", "onItemSelect: click");
    }
}
