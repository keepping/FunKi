package com.hifunki.funki.module.search.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.module.search.adapter.ActivitySearchAdapter;
import com.hifunki.funki.module.search.adapter.HomeSearchAdapter;
import com.hifunki.funki.module.search.adapter.HotSearchAdapter;
import com.hifunki.funki.module.search.entity.ActivityEntity;
import com.hifunki.funki.module.search.entity.JoinEntity;
import com.hifunki.funki.module.search.entity.PersonEntity;
import com.hifunki.funki.module.search.fragment.LiveListFragment;
import com.hifunki.funki.module.search.fragment.UserListFragment;
import com.hifunki.funki.module.search.fragment.VideoListFragment;
import com.hifunki.funki.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.search.activity.SearchActivity.java
 * @link
 * @since 2017-03-10 13:23:23
 */
public class SearchActivity extends BaseActivity implements UserListFragment.OnFragmentInteractionListener,
        LiveListFragment.OnFragmentInteractionListener, VideoListFragment.OnFragmentInteractionListener {

    @BindView(R.id.iv_Title_left)
    ImageView ivTitleLeft;
    @BindView(R.id.tv_et_cancel)
    TextView tvEtCancel;
    @BindView(R.id.iv_et_close)
    ImageView ivEtClose;
    @BindView(R.id.etTitleCenter)
    EditText etTitleCenter;
    @BindView(R.id.rlEtTitle)
    RelativeLayout rlEtTitle;

    @BindView(R.id.ll_result)
    LinearLayout llResult;
    @BindView(R.id.tb_home_search)
    TabLayout tbHomeSearch;
    @BindView(R.id.vp_search)
    ViewPager vpSearch;

    @BindView(R.id.ll_activity_search)
    FrameLayout llActivitySearch;
    @BindView(R.id.rv_activity_recommend)
    RecyclerView rvActivityRecommend;
    @BindView(R.id.sv_search)
    NestedScrollView svSearch;

    public String imagePathss = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489236953984&di=3ba08e016f9b18d0be82354d4c18ff00&imgtype=0&src=http%3A%2F%2Ftupian.enterdesk.com%2F2013%2Fxll%2F011%2F13%2F2%2F7.jpg";
    private List<String> mTabTitle;
    private boolean isSearch;
    private List<PersonEntity> personEntities;
    private List<ActivityEntity> activityList;
    private TextView tvSticky;

    public static void show(Context context) {
        context.startActivity(new Intent(context, SearchActivity.class));
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_search;
    }



    @Override
    protected void initVariable() {
        mTabTitle = new ArrayList<>();
        mTabTitle.add("用户");
        mTabTitle.add("直播");
        mTabTitle.add("视频");
        mTabTitle.add("动态");
        List<String> imagePath = new ArrayList<>();
        imagePath.add("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1489226909&di=838a1cbd7a5ac44cd14a75defdbb3e15&src=http://pic1.5442.com:82/2015/0409/01/15.jpg!960.jpg");
        imagePath.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489236953985&di=5972d42ed1127338cf6e55fee281c2d7&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F17%2F14%2F25%2F43Y58PICfJB_1024.jpg");
        imagePath.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489236953984&di=14bea02cb3b79970900533f7150d4cc5&imgtype=0&src=http%3A%2F%2Fwww.bz55.com%2Fuploads%2Fallimg%2F150604%2F139-150604162F5.jpg");
        imagePath.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489236953984&di=14bea02cb3b79970900533f7150d4cc5&imgtype=0&src=http%3A%2F%2Fwww.bz55.com%2Fuploads%2Fallimg%2F150604%2F139-150604162F5.jpg");

        PersonEntity personEntity = new PersonEntity(
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489236953984&di=3ba08e016f9b18d0be82354d4c18ff00&imgtype=0&src=http%3A%2F%2Ftupian.enterdesk.com%2F2013%2Fxll%2F011%2F13%2F2%2F7.jpg",
                1, "水源席子", 1, 31, "我爱北京天安门", imagePath
        );

        PersonEntity personEntity1 = new PersonEntity(
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489236953984&di=3ba08e016f9b18d0be82354d4c18ff00&imgtype=0&src=http%3A%2F%2Ftupian.enterdesk.com%2F2013%2Fxll%2F011%2F13%2F2%2F7.jpg",
                1, "水源席子", 1, 31, "我爱北京天安门", imagePath
        );

        PersonEntity personEntity2 = new PersonEntity(
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489236953984&di=3ba08e016f9b18d0be82354d4c18ff00&imgtype=0&src=http%3A%2F%2Ftupian.enterdesk.com%2F2013%2Fxll%2F011%2F13%2F2%2F7.jpg",
                1, "水源席子", 1, 31, "我爱北京天安门", imagePath
        );

        personEntities = new ArrayList<>();
        personEntities.add(personEntity);
        personEntities.add(personEntity1);
        personEntities.add(personEntity2);

        List<JoinEntity> joinEntityList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            JoinEntity joinEntity = new JoinEntity("酱油泡芙", imagePathss);
            joinEntityList.add(joinEntity);
        }

        ActivityEntity activityEntity = new ActivityEntity("酱油泡芙 ", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489236953984&di=3ba08e016f9b18d0be82354d4c18ff00&imgtype=0&src=http%3A%2F%2Ftupian.enterdesk.com%2F2013%2Fxll%2F011%2F13%2F2%2F7.jpg",
                joinEntityList);
        ActivityEntity activityEntity2 = new ActivityEntity("酱油泡芙", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489236953984&di=3ba08e016f9b18d0be82354d4c18ff00&imgtype=0&src=http%3A%2F%2Ftupian.enterdesk.com%2F2013%2Fxll%2F011%2F13%2F2%2F7.jpg",
                joinEntityList);
        ActivityEntity activityEntity3 = new ActivityEntity("酱油泡芙", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489236953984&di=3ba08e016f9b18d0be82354d4c18ff00&imgtype=0&src=http%3A%2F%2Ftupian.enterdesk.com%2F2013%2Fxll%2F011%2F13%2F2%2F7.jpg",
                joinEntityList);
        ActivityEntity activityEntity4 = new ActivityEntity("酱油泡芙", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489236953984&di=3ba08e016f9b18d0be82354d4c18ff00&imgtype=0&src=http%3A%2F%2Ftupian.enterdesk.com%2F2013%2Fxll%2F011%2F13%2F2%2F7.jpg",
                joinEntityList);
        activityList = new ArrayList<>();
        activityList.add(activityEntity);
        activityList.add(activityEntity2);
        activityList.add(activityEntity3);
        activityList.add(activityEntity4);
    }

    @Override
    protected void initTitleBar() {

    }

    @Override
    protected void initView() {
        tbHomeSearch.addTab(tbHomeSearch.newTab().setText(mTabTitle.get(0)));
        tbHomeSearch.addTab(tbHomeSearch.newTab().setText(mTabTitle.get(1)));
        tbHomeSearch.addTab(tbHomeSearch.newTab().setText(mTabTitle.get(2)));
        tbHomeSearch.addTab(tbHomeSearch.newTab().setText(mTabTitle.get(3)));

        tvSticky = (TextView) findViewById(R.id.tv_sticky_header_view);

    }

    @Override
    protected void initListener() {
        etTitleCenter.addTextChangedListener(textWatcher);
        svSearch.setOnScrollChangeListener(onScrollChangeListener);
    }


    @Override
    protected void initAdapter() {
        //TabLayout
        HomeSearchAdapter homeSearchAdapter = new HomeSearchAdapter(this.getSupportFragmentManager(), mTabTitle);
        vpSearch.setAdapter(homeSearchAdapter);
        tbHomeSearch.setupWithViewPager(vpSearch);

        //first recycleView
        RecyclerView rvHotRecommend = (RecyclerView) findViewById(R.id.rv_hot_recommend);
        //设置rl的adapter
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        HotSearchAdapter eighteenAdapter = new HotSearchAdapter(getApplicationContext(), R.layout.item_search_hot_recommend, personEntities);
        rvHotRecommend.setLayoutManager(linearLayoutManager);
        rvHotRecommend.setAdapter(eighteenAdapter);

        //second RecyclerView
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        ActivitySearchAdapter activitySearchAdapter = new ActivitySearchAdapter(getApplicationContext(), R.layout.item_search_activity_recommend, activityList);
        rvActivityRecommend.setLayoutManager(linearLayoutManager1);
        rvActivityRecommend.setAdapter(activitySearchAdapter);


    }

    @Override
    protected void bindData() {

    }

    @Override
    protected void bindData4NoNet() {

    }

    private void initSrollerListener() {
        rvActivityRecommend.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                View stickyInfoView = recyclerView.findChildViewUnder(tvSticky.getMeasuredWidth() / 2, 5);
                if (stickyInfoView != null && stickyInfoView.getContentDescription() != null) {
                    tvSticky.setText(String.valueOf(stickyInfoView.getContentDescription()));
                }
                View transInfoView = recyclerView.findChildViewUnder(tvSticky.getMeasuredWidth() / 2, tvSticky.getMeasuredHeight() + 1);
                if (transInfoView != null && transInfoView.getTag() != null) {

                    int transViewStatus = (int) transInfoView.getTag();
                    int dealtY = transInfoView.getTop() - tvSticky.getMeasuredHeight();

                    if (transViewStatus == ActivitySearchAdapter.HAS_STICKY_VIEW) {
                        if (transInfoView.getTop() > 0) {
                            tvSticky.setTranslationY(dealtY);
                        } else {
                            tvSticky.setTranslationY(0);
                        }
                    } else if (transViewStatus == ActivitySearchAdapter.NONE_STICKY_VIEW) {
                        tvSticky.setTranslationY(0);
                    }
                }
            }
        });
    }

    @OnClick({R.id.iv_Title_left, R.id.tv_et_cancel, R.id.iv_et_close, R.id.etTitleCenter, R.id.rlEtTitle})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_Title_left:
                break;
            case R.id.tv_et_cancel:
                finish();
                break;
            case R.id.iv_et_close:
                etTitleCenter.getText().clear();
                break;
            case R.id.etTitleCenter:
                break;
            case R.id.rlEtTitle:
                break;
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * 滑动监听
     */
    NestedScrollView.OnScrollChangeListener onScrollChangeListener=new NestedScrollView.OnScrollChangeListener() {
        @Override
        public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
            Log.e("test", "onScrollChange: "+"scrollX="+scrollX+"scrollY"+scrollY+"oldScrollX"+oldScrollX+"oldScrollY"+oldScrollY );
        }
    };

    /**
     * 顶部输入框的监听
     */
    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!StringUtils.isEmpty(s)) {
                isSearch = true;
                llActivitySearch.setVisibility(View.GONE);
                llResult.setVisibility(View.VISIBLE);
            } else {
                isSearch = false;
                llActivitySearch.setVisibility(View.VISIBLE);
                llResult.setVisibility(View.GONE);
            }
        }
    };



}
