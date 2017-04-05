package com.hifunki.funki.module.me.user;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.client.User;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 在此写用途
 *
 * @author yinhaoxiang
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.me.user.UserAvatarActivity.java
 * @link
 * @since 2017-03-20 13:57:57
 */
public class UserAvatarActivity extends BaseActivity {


    @BindView(R.id.user_avatar_ver)
    LinearLayout userVerficationStatus;

    public static void show(Context context) {
        context.startActivity(new Intent(context, UserAvatarActivity.class));
    }

    @OnClick({
            R.id.user_avatar_verify,
            R.id.user_head_ver,
    })
    void onClick(View view){
        switch (view.getId()){
            case R.id.user_avatar_verify:

                Intent intent = new Intent(UserAvatarActivity.this, RecodeMovieActivity.class);
                startActivity(intent);


                break;
            case R.id.user_head_ver:



                break;
        }
    }

    @Override
    protected int getViewResId() {
        return R.layout.acitivy_user_avatar;
    }

    @Override
    protected void initDatas() {

    }


    @Override
    protected void initView() {
        switch (User.getAvatarVerficationStatus()){
            case 0:
                userVerficationStatus.setVisibility(View.GONE);
                break;
            case 1:
                userVerficationStatus.setVisibility(View.VISIBLE);
                break;

        }
    }


}
