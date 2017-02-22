package com.hifunki.www.funki.ui.activity;

import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hifunki.www.funki.R;
import com.hifunki.www.funki.ui.widget.TitleBar;
import com.hifunki.www.funki.util.StatusBarUtil;
import com.hifunki.www.funki.util.TextUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VisitorFillActivity extends BaseActivity {


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
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.tvVisitorConfirm)
    TextView tvVisitorConfirm;
    @BindView(R.id.llSkip)
    LinearLayout llSkip;
    @BindView(R.id.activity_visitor_fill)
    LinearLayout activityVisitorFill;


    @Override
    protected int getViewResId() {
        return R.layout.activity_visitor_fill;
    }

    @Override
    protected void init() {
//        StatusBarUtil.setStatusBarTrans(this,false);

        StatusBarUtil.setStatusBarBackground(this, R.drawable.iv_bg);

//        initState();
        tbVisitor.setLeftImageResource(R.drawable.iv_back);
        tbVisitor.setTitle(getResources().getString(R.string.visitor_title));
        TextView textView = tbVisitor.getmCenterText();

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tvBoy, R.id.tvGirl, R.id.tvThirdSex, R.id.tvLoveSameSex, R.id.tvLoveDifferentSex, R.id.tvNotCareSex, R.id.tvLoveBothSex, R.id.tvSecretSex, R.id.etInputAge, R.id.textView, R.id.tvVisitorConfirm, R.id.llSkip, R.id.activity_visitor_fill})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvBoy:
                changeTvStyle(tvBoy, tvGirl, tvThirdSex);
                break;
            case R.id.tvGirl:
                changeTvStyle(tvGirl, tvBoy, tvThirdSex);
                break;
            case R.id.tvThirdSex:
                changeTvStyle(tvThirdSex, tvGirl, tvBoy);
                break;
            case R.id.tvLoveSameSex:
                changeTvStyle(tvLoveSameSex, tvLoveDifferentSex, tvNotCareSex, tvLoveBothSex, tvSecretSex);
                break;
            case R.id.tvLoveDifferentSex:
                changeTvStyle(tvLoveDifferentSex, tvLoveSameSex, tvNotCareSex, tvLoveBothSex, tvSecretSex);
                break;
            case R.id.tvNotCareSex:
                changeTvStyle(tvNotCareSex, tvLoveSameSex, tvLoveDifferentSex, tvLoveBothSex, tvSecretSex);
                break;
            case R.id.tvLoveBothSex:
                changeTvStyle(tvLoveBothSex, tvLoveSameSex, tvLoveDifferentSex, tvNotCareSex, tvSecretSex);
                break;
            case R.id.tvSecretSex:
                changeTvStyle(tvSecretSex, tvLoveSameSex, tvLoveDifferentSex, tvNotCareSex, tvLoveBothSex);
                break;
            case R.id.etInputAge:
                break;
            case R.id.textView:
                break;
            case R.id.tvVisitorConfirm:
                break;
            case R.id.llSkip:

                break;
            case R.id.activity_visitor_fill:
                break;
        }
    }

    private void changeTvStyle(TextView tvs, TextView... textViews) {

        tvs.setTextColor(getResources().getColor(R.color.vistorTvClickbg));
        tvs.setBackground(getResources().getDrawable(R.drawable.visitor_tv_click_bg));

        for (TextView tv : textViews) {
            tv.setTextColor(getResources().getColor(R.color.vistorTvbg));
            tv.setBackground(getResources().getDrawable(R.drawable.visitor_tv_bg));
        }

    }
}
