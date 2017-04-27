package com.hifunki.funki.module.login.widget.layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hifunki.funki.R;

import butterknife.BindView;

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
    private  EditText etIuputEmail;

    public LayoutEmailWithType(Context context, int type) {
        super(context);
        View vEmail = LayoutInflater.from(context).inflate(R.layout.layout_login_email, this);
        etIuputEmail = (EditText) vEmail.findViewById(R.id.etInputEmail);
        etEmailPwd = (EditText) vEmail.findViewById(R.id.etEmailPwd);
    }


    /**
     * 获取电话et
     * @return
     */
    public EditText getEtEmailTel() {
        if (etIuputEmail != null) {
            return etIuputEmail;
        } else {
            return null;
        }
    }

    /**
     * 获取密码et
     * @return
     */
    public EditText getEtEmailPwd() {
        if (etEmailPwd != null) {
            return etEmailPwd;
        } else {
            return null;
        }
    }

}
