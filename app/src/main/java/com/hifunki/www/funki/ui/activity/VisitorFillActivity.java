package com.hifunki.www.funki.ui.activity;

import android.text.TextPaint;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hifunki.www.funki.R;
import com.hifunki.www.funki.ui.widget.TitleBar;
import com.hifunki.www.funki.util.StatusBarUtil;
import com.hifunki.www.funki.util.TextUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class VisitorFillActivity extends BaseActivity {


    @BindView(R.id.tbVisitor)
    TitleBar tbVisitor;
    @BindView(R.id.activity_visitor_fill)
    LinearLayout activityVisitorFill;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.tvBig)
    TextView tvBig;
    @BindView(R.id.tvNormal)
    TextView tvNormal;

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

        TextUtil.setFzNormalTypeFace(this,textView);

//        http://blog.csdn.net/to_cm/article/details/6002812
        TextPaint paint = textView.getPaint();
        paint.setFakeBoldText(true);
        //设置沉浸式
//        tbVisitor.setImmersive(true);


    }

    @Override
    protected void loadDatas() {

    }


    @OnClick({R.id.tbVisitor, R.id.activity_visitor_fill, R.id.tv, R.id.tvBig, R.id.tvNormal})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv:
                break;
            case R.id.tvBig:

                break;
            case R.id.tvNormal:

                break;
            case R.id.tbVisitor:
                break;
            case R.id.activity_visitor_fill:
                break;
        }
    }

}
