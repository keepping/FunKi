package com.hifunki.funki.module.room.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.module.room.adpater.RoomAdapter;
import com.hifunki.funki.module.room.fragment.RoomDymicFragment;
import com.hifunki.funki.module.room.fragment.RoomLiveFragment;
import com.hifunki.funki.widget.bar.TopBarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

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

    @BindView(R.id.lt_room_info)
    RelativeLayout rlRoomInfo;

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

    private List<String> mTabTitles;

    @Override
    protected int getViewResId() {
        return R.layout.activity_other_room;
    }

    private List<String> mInfoTag;//个人中心信息标签


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

//07001f

    @Override
    protected void initView() {

        tabRoom.addTab(tabRoom.newTab().setText(mTabTitles.get(0)));
        tabRoom.addTab(tabRoom.newTab().setText(mTabTitles.get(1)));
        RoomAdapter roomAdapter = new RoomAdapter(getSupportFragmentManager(), mTabTitles);
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
    public void onFragmentInteraction(Uri uri) {

    }

    public static void show(Context context) {
        context.startActivity(new Intent(context,OtherRoomActivity.class));
    }

//    int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
//    int statusBarHeight = getResources().getDimensionPixelSize(resourceId);
}
