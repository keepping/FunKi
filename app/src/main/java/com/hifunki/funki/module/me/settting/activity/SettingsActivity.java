package com.hifunki.funki.module.me.settting.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.module.home.activity.HomeActivity;
import com.hifunki.funki.widget.bar.TopBarView;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingsActivity extends BaseActivity {

    @BindView(R.id.tbv_setting)
    TopBarView tbvSetting;
    @BindView(R.id.tv_setting_exit)
    TextView tvSettingExit;
    private Context mContext;


    public static void show(Context context) {
        context.startActivity(new Intent(context, SettingsActivity.class));
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_settings;
    }

    @Override
    protected void initVariable() {
        mContext = this;
    }

    @Override
    protected void initView() {

    }


    @OnClick({R.id.tbv_setting, R.id.tv_setting_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tbv_setting:
                break;
            case R.id.tv_setting_exit:
                Intent intent = new Intent(this, HomeActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

                break;
        }
    }
}
