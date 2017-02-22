package com.hifunki.www.funki.ui.activity;

import android.graphics.Typeface;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hifunki.www.funki.R;
import com.hifunki.www.funki.ui.widget.TitleBar;
import com.hifunki.www.funki.util.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class VisitorFillActivity extends BaseActivity {


    @BindView(R.id.tbVisitor)
    TitleBar tbVisitor;
    @BindView(R.id.activity_visitor_fill)
    LinearLayout activityVisitorFill;

    @Override
    protected int getViewResId() {
        return R.layout.activity_visitor_fill;
    }

    @Override
    protected void init() {
//        StatusBarUtil.setStatusBarTrans(this,false);

        StatusBarUtil.setStatusBarBackground(this,R.drawable.iv_bg);

//        initState();
        tbVisitor.setLeftImageResource(R.drawable.iv_back);
        tbVisitor.setTitle(getResources().getString(R.string.visitor_title));
        TextView textView = tbVisitor.getmCenterText();
        Typeface fromAsset = Typeface.createFromAsset(getAssets(), "font/Fan_Normal.ttf");
        textView.setTypeface(fromAsset, Typeface.BOLD);
        //设置沉浸式
        tbVisitor.setImmersive(true);
    }

    @Override
    protected void loadDatas() {

    }


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        // TODO: add setContentView(...) invocation
//        ButterKnife.bind(this);
//    }

    @OnClick({R.id.tbVisitor, R.id.activity_visitor_fill})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tbVisitor:
                break;
            case R.id.activity_visitor_fill:
                break;
        }
    }

    /**
     * 沉浸式状态栏
     */
    private void initState() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

}
