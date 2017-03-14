package com.hifunki.funki.module.login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hifunki.funki.R;
import com.hifunki.funki.library.base.activity.BaseTitleActivity;
import com.hifunki.funki.module.login.widget.PwdGraphView;
import com.hifunki.funki.module.login.widget.ToolTitleBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 图形验证码界面
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.login.PwdGraphActivity.java
 * @link
 * @since 2017-02-24 12:03:03
 */
public class PwdGraphActivity extends BaseTitleActivity implements View.OnClickListener {

    @BindView(R.id.iv_pwd_country)
    ImageView ivPwdCountry;
    @BindView(R.id.tv_pwd_country)
    TextView tvPwdCountry;
    @BindView(R.id.llCounty)
    LinearLayout llCounty;
    @BindView(R.id.etIuputTel)
    EditText etIuputTel;
    @BindView(R.id.iv_pwd_graph)
    ImageView ivPwdGraph;
    @BindView(R.id.etIuputGraph)
    EditText etIuputGraph;
    @BindView(R.id.tvConfirm)
    TextView tvConfirm;
    @BindView(R.id.rlConfirm)
    RelativeLayout rlConfirm;
    @BindView(R.id.activity_pwd_graph)
    LinearLayout activityPwdGraph;
    private PwdGraphView pwdGraphView;

    public static void show(Context context) {
        context.startActivity(new Intent(context, PwdGraphActivity.class));
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_pwd_graph;
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initView() {
        pwdGraphView = PwdGraphView.getInstance();
        pwdGraphView.setContext(this);
        Bitmap bitmap = pwdGraphView.createBitmap();
        ivPwdGraph.setImageBitmap(bitmap);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initAdapter() {

    }

    @Override
    protected void initTitleBar() {
        ToolTitleBar.showLeftButton(this, activityPwdGraph, ToolTitleBar.BTN_TYPE_IMAGE, R.drawable.iv_back, this);
        ToolTitleBar.showCenterButton(this, activityPwdGraph, ToolTitleBar.BTN_TYPE_TEXT, R.string.find_pwd, null);
    }


    @OnClick({R.id.iv_pwd_country, R.id.tv_pwd_country, R.id.llCounty, R.id.iv_pwd_graph, R.id.tvConfirm, R.id.rlConfirm, R.id.activity_pwd_graph})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rlTitleLeft:
                finish();
                break;
            case R.id.iv_pwd_country:
                break;
            case R.id.tv_pwd_country:
                break;
            case R.id.llCounty:
                break;
            case R.id.iv_pwd_graph:
                Bitmap bitmap = pwdGraphView.createBitmap();
                ivPwdGraph.setImageBitmap(bitmap);
                break;
            case R.id.tvConfirm:
                break;
            case R.id.rlConfirm:
                break;
            case R.id.activity_pwd_graph:
                break;
        }
    }
}


