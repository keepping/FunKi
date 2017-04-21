package com.hifunki.funki.module.login.widget.layout;

import android.content.Context;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hifunki.funki.R;


/**
 * Created by dell on 2017/2/23.
 */

public class LayoutPhoneWithType extends LinearLayout {

    LinearLayout llCounty;
    EditText etIuputTel;
    LinearLayout llChooseCountry;
    EditText etIuputPwd;
    ImageView ivTelShow;
    private Context mContext;


    //声明接口，这个接口由客户端实现，客户重写其中的事件处理方法
//    public inter OnDownLoadCompletedListener {
//        void onDownLoadCompleted(Bitmap bitmap);
//    }
//
//    public void setOnDownLoadCompletedListener(OnDownLoadCompletedListener listener) {
//        this.listener = listener;
//    }

    public LayoutPhoneWithType(TextWatcher textWatcher, OnClickListener onClickListener, Context context, int type) {
        super(context);
//        ButterKnife.bind(this);
        this.mContext = context;
        View vPhone = LayoutInflater.from(mContext).inflate(R.layout.layout_login_phone, this);
//        请输入行动电话号
        llCounty = (LinearLayout) vPhone.findViewById(R.id.llCounty);
        etIuputTel = (EditText) vPhone.findViewById(R.id.etIuputTel);
        etIuputPwd = (EditText) vPhone.findViewById(R.id.etIuputPwd);
        ivTelShow = (ImageView) vPhone.findViewById(R.id.iv_tel_show);
        //传入监听
        llCounty.setOnClickListener(onClickListener);
        ivTelShow.setOnClickListener(onClickListener);
        etIuputTel.addTextChangedListener(textWatcher);
        etIuputPwd.addTextChangedListener(textWatcher);
    }

    /**
     * 获取电话et
     * @return
     */
    public EditText getEtIuputTel() {
        if (etIuputTel != null) {
            return etIuputTel;
        } else {
            return null;
        }
    }

    /**
     * 获取密码et
     * @return
     */
    public EditText getEtIuputPwd() {
        if (etIuputPwd != null) {
            return etIuputPwd;
        } else {
            return null;
        }
    }
}
