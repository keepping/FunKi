package com.hifunki.funki.module.home.me.profile.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.widget.TopBarView;

import butterknife.BindView;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.home.me.profile.activity.EditNicknameActivity.java
 * @link
 * @since 2017-03-31 14:16:16
 */
public class EditNicknameActivity extends BaseActivity {

    @BindView(R.id.topView)
    TopBarView topBarView;
    @BindView(R.id.et_nickname)
    EditText etNickname;
    public static int requestCode = 1121;
    private static String nickname;

    @Override
    protected int getViewResId() {
        return R.layout.activity_edit_nick_name;
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initView() {
        TextView menuText = topBarView.getMenuText();
        menuText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editable str = etNickname.getText();
                nickname = str.toString();
                Intent intent = new Intent();
                Bundle bundle=new Bundle();
                bundle.putString("bundle",nickname);
                intent.putExtra("intent",bundle);
                intent.setClass(EditNicknameActivity.this, EditProfileActivity.class);
                setResult(requestCode, intent);
                finish();
            }
        });
    }

    @Override
    protected void initListener() {
        super.initListener();
    }

    public static void show(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, EditNicknameActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }


}
