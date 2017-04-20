package com.hifunki.funki.module.dynamic.normal.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.common.BundleConst;
import com.hifunki.funki.common.CommonConst;
import com.hifunki.funki.module.dynamic.normal.adapter.NormalDynamicAdapter;
import com.hifunki.funki.module.dynamic.normal.fragment.NormalDynamicFragment;
import com.hifunki.funki.module.home.widget.ninegrid.NineGridlayout;
import com.hifunki.funki.util.PopWindowUtil;
import com.hifunki.funki.util.StatusBarUtil;
import com.hifunki.funki.widget.FunKiPlayer;
import com.hifunki.funki.widget.bar.TopBarView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.dynamic.normal.activity.NormalDynamicActivity.java
 * @link
 * @since 2017-04-15 14:08:08
 */
public class NormalDynamicActivity extends BaseActivity implements NormalDynamicFragment.OnFragmentInteractionListener {

    @BindView(R.id.tbv_normal_dynamic)
    TopBarView topBarView;
    @BindView(R.id.rl_dynamic_normal_root)
    LinearLayout rlRoot;
    @BindView(R.id.iv_ngrid_layout)
    NineGridlayout nineGridlayout;
    @BindView(R.id.player_follow_picture)
    FunKiPlayer funKiPlayer;
    @BindView(R.id.vp_dynamic_normal)
    ViewPager viewPager;
    @BindView(R.id.tv_dynamic_tranmit)
    TextView tvCommit;
    @BindView(R.id.tv_dynamic_comment)
    TextView tvComment;
    @BindView(R.id.tv_dynamic_star)
    TextView tvStar;
    @BindView(R.id.sv_dynamic_normal)
    NestedScrollView scrollView;
    @BindView(R.id.rl_dynamic_action)
    RelativeLayout rlAction;

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

        viewPager.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
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
    protected void initAdapter() {
        super.initAdapter();
        NormalDynamicAdapter adapter = new NormalDynamicAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    @OnClick({R.id.tv_dynamic_tranmit, R.id.tv_dynamic_comment, R.id.tv_dynamic_star})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_dynamic_tranmit:
                viewPager.setCurrentItem(0);
                break;
            case R.id.tv_dynamic_comment:
                viewPager.setCurrentItem(1);
                break;
            case R.id.tv_dynamic_star:
                viewPager.setCurrentItem(2);
                break;
        }
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

    //监听树
    ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        int reCount = 4;

        @Override
        public void onGlobalLayout() {
            int totalHeight = scrollView.getHeight();
            int dif = rlAction.getHeight();
            ViewGroup.LayoutParams layoutParams = viewPager.getLayoutParams();

            if (layoutParams.height != totalHeight - dif) {
                layoutParams.height = totalHeight - dif;
                viewPager.setLayoutParams(layoutParams);
            }
            reCount--;
            //滑动初始状态
            if (reCount >= 0) {
                scrollView.scrollTo(0, 0);
            }
            if (reCount == 0) {
                viewPager.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        }
    };


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (onGlobalLayoutListener != null) {
            viewPager.getViewTreeObserver().removeOnGlobalLayoutListener(onGlobalLayoutListener);
        }
    }
}
