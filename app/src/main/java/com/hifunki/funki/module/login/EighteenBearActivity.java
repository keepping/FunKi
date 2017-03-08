package com.hifunki.funki.module.login;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.login.EighteenBearActivity.java
 * @link
 * @since 2017-03-08 16:23:23
 */
public class EighteenBearActivity extends BaseActivity {

    @BindView(R.id.rl_eighteen_choose)
    RelativeLayout rlEighteenChoose;
    @BindView(R.id.rv_eighteen_choose)
    RecyclerView rvEighteenChoose;
    @BindView(R.id.rl_eighteen)
    RelativeLayout rlEighteen;

    @Override
    protected int getViewResId() {
        return R.layout.activity_eighteen_bear;
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initTitleBar() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initAdapter() {

    }


    @OnClick({R.id.rl_eighteen_choose, R.id.rv_eighteen_choose, R.id.rl_eighteen})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_eighteen_choose:
                break;
            case R.id.rv_eighteen_choose:
                break;
            case R.id.rl_eighteen:
                break;
        }
    }
}
