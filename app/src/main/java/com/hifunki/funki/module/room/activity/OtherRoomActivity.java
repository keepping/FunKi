package com.hifunki.funki.module.room.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.module.room.adpater.RoomVPAdapter;
import com.hifunki.funki.module.room.fragment.RoomDymicFragment;
import com.hifunki.funki.module.room.fragment.RoomLiveFragment;
import com.hifunki.funki.util.DisplayUtil;
import com.hifunki.funki.util.PopWindowUtil;
import com.hifunki.funki.util.StatusBarUtil;
import com.hifunki.funki.widget.bar.TopBarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.hifunki.funki.R.id.tab_room;

/**
 * 他人空间
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.room.activty.OtherRoomActivity.java
 * @link
 * @since 2017-03-23 17:47:47
 */
public class OtherRoomActivity extends BaseActivity implements RoomDymicFragment.OnFragmentInteractionListener, RoomLiveFragment.OnFragmentInteractionListener {

    @BindView(R.id.rl_other_room_live)
    RelativeLayout rlOtherRoomLive;

    @BindView(R.id.me_title)
    RelativeLayout meTitle;

    @BindView(R.id.main_content)
    NestedScrollView mainContent;

    @BindView(R.id.topbarView)
    TopBarView topBarView;

    @BindView(tab_room)
    TabLayout tabRoom;

    @BindView(R.id.vp_room)
    ViewPager vpRoom;

    @BindView(R.id.iv_room_share)
    ImageView ivRoomShare;
    @BindView(R.id.civ_third_photo)
    CircleImageView civThirdPhoto;
    @BindView(R.id.civ_second_photo)
    CircleImageView civSecondPhoto;
    @BindView(R.id.civ_first_photo)
    CircleImageView civFirstPhoto;
    @BindView(R.id.civ_me_photo)
    CircleImageView civMePhoto;
    @BindView(R.id.iv_me_authentication)
    ImageView ivMeAuthentication;
    @BindView(R.id.iv_me_photo)
    RelativeLayout ivMePhoto;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.rl_user_name)
    RelativeLayout rlUserName;
    @BindView(R.id.tv_user_id)
    TextView tvUserId;
    @BindView(R.id.tv_send_diamond)
    TextView tvSendDiamond;
    @BindView(R.id.tv_user_account)
    RelativeLayout tvUserAccount;
    @BindView(R.id.tv_introduce)
    TextView tvIntroduce;
    @BindView(R.id.tv_follow)
    TextView tvFollow;
    @BindView(R.id.tv_follow_num)
    TextView tvFollowNum;
    @BindView(R.id.ll_follow)
    LinearLayout llFollow;
    @BindView(R.id.tv_fans)
    TextView tvFans;
    @BindView(R.id.tv_fans_num)
    TextView tvFansNum;
    @BindView(R.id.ll_fans)
    LinearLayout llFans;
    @BindView(R.id.rl_room_info)
    RelativeLayout rlRoomInfo;
    @BindView(R.id.tv_living)
    TextView tvLiving;
    @BindView(R.id.ll_room_live)
    LinearLayout llRoomLive;
    @BindView(R.id.iv_room_info)
    ImageView ivRoomInfo;


    private PopWindowUtil sharePopWindow;//分享popWindow
    private View shareView;
    private List<String> mTabTitles;

    private List<String> mInfoTag;//个人中心信息标签

    @Override
    protected int getViewResId() {
        return R.layout.activity_other_room;
    }

    @Override
    protected void bindData() {

    }

    @Override
    protected void bindData4NoNet() {

    }

    @Override
    protected void initVariable() {
        mTabTitles = new ArrayList<>();
        mTabTitles.add("动态");
        mTabTitles.add("直播(14)");
    }

    @Override
    protected void initView() {
        StatusBarUtil.adjustStatusBarHei(rlOtherRoomLive);
        tabRoom.addTab(tabRoom.newTab().setText(mTabTitles.get(0)));
        tabRoom.addTab(tabRoom.newTab().setText(mTabTitles.get(1)));
        RoomVPAdapter roomAdapter = new RoomVPAdapter(getSupportFragmentManager(), mTabTitles);
        vpRoom.setAdapter(roomAdapter);
        tabRoom.setupWithViewPager(vpRoom);

        vpRoom.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);

    }

    //监听树
    ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        int reCount = 4;

        @Override
        public void onGlobalLayout() {
            int totalHeight = mainContent.getHeight();
            int topbarHeight = topBarView.getHeight();//titlebar高度
            int dif = tabRoom.getHeight();
            ViewGroup.LayoutParams layoutParams = vpRoom.getLayoutParams();

            if (layoutParams.height != totalHeight - dif - topbarHeight) {
                layoutParams.height = totalHeight - dif - topbarHeight;
                vpRoom.setLayoutParams(layoutParams);
            }

            reCount--;
            //滑动初始状态
            if (reCount >= 0) {
                mainContent.scrollTo(0, 0);
            }

            if (reCount == 0) {
                vpRoom.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        }
    };


    @Override
    protected void initListener() {
        super.initListener();
        mainContent.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if (scrollY < 300) {
                    topBarView.getTitileText().setVisibility(View.INVISIBLE);
                } else {
                    topBarView.getTitileText().setVisibility(View.VISIBLE);
                    topBarView.getTitileText().setText(getResources().getString(R.string.me_name));
                }
                //更新初始状态
                float percent = scrollY * 1f / (576);
                int alpha = (int) (255 * percent);
                int height = rlRoomInfo.getHeight();
                if (scrollY == 0) {
                    topBarView.setBackgroundColor(Color.TRANSPARENT);
                }
                if (scrollY >= 576) {
                    topBarView.setBackgroundColor(getResources().getColor(R.color._07001F));
                } else {
                    int color = Color.alpha(alpha);
                    topBarView.setBackgroundColor(getResources().getColor(R.color._07001F));
                    topBarView.getBackground().setAlpha(color);

                }
                Log.e("test", "onScrollChange: " + "percent=" + percent + "scrollY=" + scrollY + "oldScrollY=" + oldScrollY + "height=" + height);

            }
        });
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public static void show(Context context) {
        context.startActivity(new Intent(context, OtherRoomActivity.class));
    }


    @OnClick(R.id.iv_room_share)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_room_share:
                if (sharePopWindow == null) {
                    sharePopWindow = PopWindowUtil.getInstance(this);
                    shareView = LayoutInflater.from(this).inflate(R.layout.pop_me_share, null);
                }
                sharePopWindow.fitPopupWindowOverStatusBar(true);
                sharePopWindow.init((int) DisplayUtil.dip2Px(this, 198), LinearLayout.LayoutParams.MATCH_PARENT);
                sharePopWindow.showPopWindow(shareView, PopWindowUtil.ATTACH_LOCATION_WINDOW, null, 1, 0);
                break;
        }
    }

}
