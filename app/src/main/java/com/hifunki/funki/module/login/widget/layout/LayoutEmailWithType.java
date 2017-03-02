package com.hifunki.funki.module.login.widget.layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hifunki.funki.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by dell on 2017/2/23.
 */

public class LayoutEmailWithType extends LinearLayout {

    @BindView(R.id.etInputEmail)
    EditText etInputEmail;
    @BindView(R.id.etEmailPwd)
    EditText etEmailPwd;
    @BindView(R.id.ivEmailShow)
    ImageView ivEmailShow;

    public LayoutEmailWithType(Context context, int type) {
        super(context);
        View vEmail = LayoutInflater.from(context).inflate(R.layout.layout_login_email, this);
    }

    @OnClick({R.id.etInputEmail, R.id.etEmailPwd, R.id.ivEmailShow})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.etInputEmail:
                break;
            case R.id.etEmailPwd:
                break;
            case R.id.ivEmailShow:
                break;
        }
    }
}
