package com.hifunki.funki.module.dynamic.normal.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.common.BundleConst;
import com.hifunki.funki.common.CommonConst;
import com.hifunki.funki.module.home.widget.ninegrid.NineGridlayout;
import com.hifunki.funki.util.PopWindowUtil;
import com.hifunki.funki.util.StatusBarUtil;
import com.hifunki.funki.widget.FunKiPlayer;
import com.hifunki.funki.widget.bar.TopBarView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.dynamic.normal.activity.NormalDynamicActivity.java
 * @link
 * @since 2017-04-15 14:08:08
 */
public class NormalDynamicActivity extends BaseActivity {

    @BindView(R.id.tbv_normal_dynamic)
    TopBarView topBarView;
    @BindView(R.id.rl_dynamic_normal_root)
    LinearLayout rlRoot;
    @BindView(R.id.iv_ngrid_layout)
    NineGridlayout nineGridlayout;
    @BindView(R.id.player_follow_picture)
    FunKiPlayer funKiPlayer;

    private PopWindowUtil sharePopoWindow;
    private View shareView;

    private enum STATUS {
        PICTURE,
        MOVIE
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_normal_dynamic;
    }

    @Override
    protected void initVariable() {
        int types = getIntent().getIntExtra("type", 0);
        if (types == BundleConst.FOLLOW_PICTURE_TO_DYNAMIC) {
            refreshUI(STATUS.PICTURE);
        } else if (types == BundleConst.FOLLOW_MOVIE_TO_DYNAMIC) {
            refreshUI(STATUS.MOVIE);
        }
    }

    private void refreshUI(STATUS type) {
        switch (type) {
            case PICTURE:
                funKiPlayer.setVisibility(View.GONE);
                ArrayList<String> strings = new ArrayList<>();
                for (int i = 0; i < 9; i++) {
                    strings.add(CommonConst.photo);
                }
                nineGridlayout.setImagesData(strings);
                break;
            case MOVIE:
                nineGridlayout.setVisibility(View.INVISIBLE);
                funKiPlayer.setVisibility(View.VISIBLE);
                funKiPlayer.play(CommonConst.VIDEO);
                break;

        }
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {
        super.initListener();
        topBarView.getMenuImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //创建PopWindow
                if (sharePopoWindow == null) {
                    sharePopoWindow = PopWindowUtil.getInstance(getApplicationContext());
                    shareView = LayoutInflater.from(NormalDynamicActivity.this).inflate(R.layout.pop_dynamic_normal_share, null);
                    StatusBarUtil.adjustStatusBarHei(shareView);
                    RelativeLayout relativeLayout = (RelativeLayout) shareView.findViewById(R.id.rl_pop_share_dismiss);
                    relativeLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            sharePopoWindow.hidePopWindow();
                        }
                    });
                }
                sharePopoWindow.getPopWindow();
                sharePopoWindow.fitPopupWindowOverStatusBar(true);
                sharePopoWindow.init(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                sharePopoWindow.showPopWindow(shareView, PopWindowUtil.ATTACH_LOCATION_WINDOW, view, 0, 0);
            }
        });
    }

    @Override
    protected void bindData() {

    }

    @Override
    protected void bindData4NoNet() {

    }

    public static void show(Activity mActivity, int type) {
        Intent intent = new Intent(mActivity, NormalDynamicActivity.class);
        intent.putExtra("type", type);
        mActivity.startActivity(intent);
    }
}
