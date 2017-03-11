package com.hifunki.funki.module.search.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseTitleActivity;
import com.hifunki.funki.module.home.fragment.UserListFragment;
import com.hifunki.funki.module.search.adapter.ActivitySearchAdapter;
import com.hifunki.funki.module.search.adapter.HotSearchAdapter;
import com.hifunki.funki.module.search.entity.ActivityEntity;
import com.hifunki.funki.module.search.entity.Join;
import com.hifunki.funki.module.search.entity.PersonEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.search.activity.SearchActivity.java
 * @link
 * @since 2017-03-10 13:23:23
 */
public class SearchActivity extends BaseTitleActivity implements UserListFragment.OnFragmentInteractionListener {

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

//    @BindView(R.id.ll_result)
//    LinearLayout llResult;
//    @BindView(R.id.tb_home_search)
//    TabLayout tbHomeSearch;
//    @BindView(R.id.vp_search)
//    ViewPager vpSearch;

    //    @BindView(R.id.pull_recommend)
//    PullToRefreshScrollView pullRecommend;
    @BindView(R.id.rv_hot_recommend)
    RecyclerView rvHotRecommend;
    @BindView(R.id.rv_activity_recommend)
    RecyclerView rvActivityRecommend;

    public String imagePathss = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489236953984&di=3ba08e016f9b18d0be82354d4c18ff00&imgtype=0&src=http%3A%2F%2Ftupian.enterdesk.com%2F2013%2Fxll%2F011%2F13%2F2%2F7.jpg";
    private List<String> mTabTitle;
    private boolean isSearch;
    private List<PersonEntity> personEntities;
    private List<ActivityEntity> activityList;

    public static void show(Context context) {
        context.startActivity(new Intent(context, SearchActivity.class));
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initDatas() {
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

        Join join = new Join("酱油泡芙", imagePathss);
        Join join1 = new Join("酱油泡芙", imagePathss);
        List<Join> joinList = new ArrayList<>();
        joinList.add(join);
        joinList.add(join1);
        ActivityEntity activityEntity = new ActivityEntity("波多野结衣", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489236953984&di=3ba08e016f9b18d0be82354d4c18ff00&imgtype=0&src=http%3A%2F%2Ftupian.enterdesk.com%2F2013%2Fxll%2F011%2F13%2F2%2F7.jpg",
                joinList);
        ActivityEntity activityEntity2 = new ActivityEntity("波多野结衣", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489236953984&di=3ba08e016f9b18d0be82354d4c18ff00&imgtype=0&src=http%3A%2F%2Ftupian.enterdesk.com%2F2013%2Fxll%2F011%2F13%2F2%2F7.jpg",
                joinList);
        activityList = new ArrayList<>();
        activityList.add(activityEntity);
        activityList.add(activityEntity2);

    }

    @Override
    protected void initTitleBar() {

    }

    @Override
    protected void initView() {
//        tbHomeSearch.addTab(tbHomeSearch.newTab().setText(mTabTitle.get(0)));
//        tbHomeSearch.addTab(tbHomeSearch.newTab().setText(mTabTitle.get(1)));
//        tbHomeSearch.addTab(tbHomeSearch.newTab().setText(mTabTitle.get(2)));
//        tbHomeSearch.addTab(tbHomeSearch.newTab().setText(mTabTitle.get(3)));


    }

    @Override
    protected void initListener() {
        etTitleCenter.addTextChangedListener(textWatcher);
    }

    @Override
    protected void initAdapter() {
        //设置rl的adapter
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        HotSearchAdapter eighteenAdapter = new HotSearchAdapter(getApplicationContext(), R.layout.list_search_hot_recommend, personEntities);

        rvHotRecommend.setLayoutManager(linearLayoutManager);

        rvHotRecommend.setAdapter(eighteenAdapter);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        ActivitySearchAdapter activitySearchAdapter = new ActivitySearchAdapter(getApplicationContext(), R.layout.list_search_activity_recommend, activityList);
        activitySearchAdapter.setContextView(R.layout.list_search_activity_recommend);
        rvActivityRecommend.setLayoutManager(linearLayoutManager1);
        rvActivityRecommend.setAdapter(activitySearchAdapter);

//        HomeSearchAdapter homeSearchAdapter = new HomeSearchAdapter(this.getSupportFragmentManager(), mTabTitle);
//        vpSearch.setAdapter(homeSearchAdapter);
//        tbHomeSearch.setupWithViewPager(vpSearch);
    }
//    R.id.pull_recommend
//    R.id.tb_home_search, R.id.vp_search, R.id.ll_result,

    @OnClick({R.id.iv_Title_left, R.id.tv_et_cancel, R.id.iv_et_close, R.id.etTitleCenter, R.id.rlEtTitle, R.id.rv_hot_recommend})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_Title_left:
                break;
            case R.id.tv_et_cancel:
                finish();
                break;
            case R.id.iv_et_close:
                break;
            case R.id.etTitleCenter:
                break;
            case R.id.rlEtTitle:
                break;
            case R.id.rv_hot_recommend:
                break;

//            case R.id.tb_home_search:
//                break;
//            case R.id.vp_search:
//                break;
//            case R.id.ll_result:
//                break;
//
//            case R.id.pull_recommend:
//                break;
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * 中间输入框的监听
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
//            if (!StringUtils.isEmpty(s)) {
//                isSearch = true;
//                pullRecommend.setVisibility(View.GONE);
//                llResult.setVisibility(View.VISIBLE);
//            } else {
//                isSearch = false;
//                pullRecommend.setVisibility(View.VISIBLE);
//                llResult.setVisibility(View.GONE);
//            }
        }
    };

}
