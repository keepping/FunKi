package com.hifunki.funki.module.live.anchor.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 结束直播间界面
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.live.start.activity.LiveFinishActivity.java
 * @link
 * @since 2017-03-29 16:53:53
 */
public class LiveFinishActivity extends BaseActivity {


    @BindView(R.id.tv_live_finish)
    TextView tvLiveFinish;
    @BindView(R.id.rl_live_finish_gold)
    RelativeLayout rlLiveFinishGold;
    @BindView(R.id.tv_finish_gold_extra)
    TextView tvFinishGoldExtra;
    @BindView(R.id.tv_finish_share)
    TextView tvFinishShare;
    @BindView(R.id.ll_live_finish_share)
    LinearLayout llLiveFinishShare;
    @BindView(R.id.tv_back_home)
    TextView tvBackHome;

    @Override
    protected int getViewResId() {
        return R.layout.activity_live_finish;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void bindData() {

    }

    @Override
    protected void bindData4NoNet() {

    }

    public static void show(Activity activity, Context context) {
        Intent intent = new Intent(context, LiveFinishActivity.class);
        context.startActivity(intent);
        activity.finish();//follow 映客
        activity.overridePendingTransition(0, 0);
    }


    @OnClick({R.id.tv_live_finish, R.id.rl_live_finish_gold, R.id.tv_finish_gold_extra, R.id.tv_finish_share, R.id.ll_live_finish_share, R.id.tv_back_home})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_live_finish:
                break;
            case R.id.rl_live_finish_gold:
                break;
            case R.id.tv_finish_gold_extra:
                break;
            case R.id.tv_finish_share:
                break;
            case R.id.ll_live_finish_share:
                break;
            case R.id.tv_back_home:
                finish();
                break;
        }
    }
}
