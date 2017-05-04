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
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.module.login.widget.PwdGraphView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 图形验证码界面
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.login.FindPwdGraphActivity.java
 * @link
 * @since 2017-02-24 12:03:03
 */
public class FindPwdGraphActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.iv_pwd_country)
    ImageView ivPwdCountry;
    @BindView(R.id.tv_pwd_country)
    TextView tvPwdCountry;
    @BindView(R.id.llCounty)
    LinearLayout llCounty;
    @BindView(R.id.etFindPhone)
    EditText etFindPhone;
    @BindView(R.id.etFindEmail)
    EditText etFindEmail;
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
    @BindView(R.id.rlFindPhone)
    RelativeLayout rlFindPhone;
    private PwdGraphView pwdGraphView;
    private static final String KEY_FIND_PWD_TYPE = "key_find_pwd_type";
    static final int KEY_FIND_PWD_PHONE = 1;
    static final int KEY_FIND_PWD_EMAIL = 2;


    public static void show(Context context, int type) {
        Intent intent = new Intent(context, FindPwdGraphActivity.class);
        intent.putExtra(FindPwdGraphActivity.KEY_FIND_PWD_TYPE, type);
        context.startActivity(intent);
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_pwd_graph;
    }

    @Override
    protected void initVariable() {
        int type = getIntent().getIntExtra(FindPwdGraphActivity.KEY_FIND_PWD_TYPE, 0);
        if (type == KEY_FIND_PWD_PHONE) {
            rlFindPhone.setVisibility(View.VISIBLE);
            etFindEmail.setVisibility(View.GONE);
        } else if (type == KEY_FIND_PWD_EMAIL) {
            rlFindPhone.setVisibility(View.GONE);
            etFindEmail.setVisibility(View.VISIBLE);
        }
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
    protected void bindData() {

    }

    @Override
    protected void bindData4NoNet() {

    }

    @Override
    protected void initTitleBar() {

    }


    @OnClick({R.id.iv_pwd_country, R.id.tv_pwd_country, R.id.llCounty, R.id.iv_pwd_graph, R.id.tvConfirm, R.id.rlConfirm, R.id.activity_pwd_graph})
    public void onClick(View view) {
        switch (view.getId()) {

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


