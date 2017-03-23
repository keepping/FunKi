package com.hifunki.funki.module.room.activity;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.module.home.me.adapter.MeInfoAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 他人空间
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.room.activty.OtherRoomActivity.java
 * @link
 * @since 2017-03-23 17:47:47
 */
public class OtherRoomActivity extends BaseActivity {

    @Override
    protected int getViewResId() {
        return R.layout.activity_other_room;
    }

    private List<String> mInfoTag;//个人中心信息标签



    @Override
    protected void initDatas() {
        mInfoTag = new ArrayList<>();
        mInfoTag.add(getString(R.string.self_gallery));
        mInfoTag.add(getString(R.string.vistor_record));
        mInfoTag.add(getString(R.string.my_field_control));
        mInfoTag.add(getString(R.string.blacklist));
        mInfoTag.add(getString(R.string.order_management));
        mInfoTag.add(getString(R.string.account_privacy_safety));
        mInfoTag.add(getString(R.string.setting));
        mInfoTag.add(getString(R.string.help_feedback));
        mInfoTag.add(getString(R.string.business_cooperate));


        mInfoTag.add(getString(R.string.blacklist));
        mInfoTag.add(getString(R.string.order_management));
        mInfoTag.add(getString(R.string.account_privacy_safety));
        mInfoTag.add(getString(R.string.setting));
        mInfoTag.add(getString(R.string.help_feedback));
        mInfoTag.add(getString(R.string.business_cooperate));

        mInfoTag.add(getString(R.string.blacklist));
        mInfoTag.add(getString(R.string.order_management));
        mInfoTag.add(getString(R.string.account_privacy_safety));
        mInfoTag.add(getString(R.string.setting));
        mInfoTag.add(getString(R.string.help_feedback));
        mInfoTag.add(getString(R.string.business_cooperate));
    }


    @BindView(R.id.me_title)
    RelativeLayout meTitle;

    @BindView(R.id.rv_content)
    RecyclerView rvContent;

    @BindView(R.id.main_content)
    NestedScrollView mainContent;


    ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        int reCount = 4;
        @Override
        public void onGlobalLayout() {
            int total = mainContent.getHeight();
            int dif = meTitle.getHeight();
            ViewGroup.LayoutParams layoutParams = rvContent.getLayoutParams();

            int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
            int statusBarHeight = getResources().getDimensionPixelSize(resourceId);

            if(layoutParams.height!=total-dif-statusBarHeight){
                layoutParams.height = total-dif-statusBarHeight;
                rvContent.setLayoutParams(layoutParams);

                System.out.println("-----------------------");
            }

            reCount --;
            if(reCount>=0){
                mainContent.scrollTo(0,0);
            }
            if(reCount==0){
                rvContent.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        }
    };

    @Override
    protected void initView() {
        rvContent.setLayoutManager(new LinearLayoutManager(this));

        MeInfoAdapter meInfoAdapter = new MeInfoAdapter(R.layout.item_me_info, mInfoTag);
        rvContent.setAdapter(meInfoAdapter);

        rvContent.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);

    }
}
