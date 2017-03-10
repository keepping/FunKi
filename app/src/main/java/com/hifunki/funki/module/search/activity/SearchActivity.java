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
import com.hifunki.funki.module.search.adapter.HotSearchAdapter;
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


    private List<String> mTabTitle;
    private boolean isSearch;
    private List<PersonEntity> personEntities;

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
        imagePath.add("https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=8e07ecabb7fb4316051f7d7a10a54642/5882b2b7d0a20cf482c772bf73094b36acaf997f.jpg");
        imagePath.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489148926748&di=f84808bb9a10572a964b824b3cf95545&imgtype=0&src=http%3A%2F%2Fg.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F10dfa9ec8a1363270c254f53948fa0ec09fac782.jpg");
        imagePath.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489148926912&di=61a3f3d5128f3fefb556d06deb159f99&imgtype=0&src=http%3A%2F%2Fb.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F0823dd54564e925838c205c89982d158ccbf4e26.jpg");
        imagePath.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489148926912&di=61a3f3d5128f3fefb556d06deb159f99&imgtype=0&src=http%3A%2F%2Fb.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F0823dd54564e925838c205c89982d158ccbf4e26.jpg");

        PersonEntity personEntity = new PersonEntity(
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489148832678&di=5d40ed37ef50274bcf1fe644ffd757ab&imgtype=0&src=http%3A%2F%2Fimg.25pp.com%2Fuploadfile%2Fapp%2Ficon%2F20160324%2F1458835090861273.jpg",
                1, "水源席子", 1, 31, "我爱北京天安门", imagePath
        );
        PersonEntity personEntity1 = new PersonEntity(
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489148832678&di=5d40ed37ef50274bcf1fe644ffd757ab&imgtype=0&src=http%3A%2F%2Fimg.25pp.com%2Fuploadfile%2Fapp%2Ficon%2F20160324%2F1458835090861273.jpg",
                1, "水源席子", 1, 31, "我爱北京天安门", imagePath
        );
        PersonEntity personEntity2 = new PersonEntity(
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489148832678&di=5d40ed37ef50274bcf1fe644ffd757ab&imgtype=0&src=http%3A%2F%2Fimg.25pp.com%2Fuploadfile%2Fapp%2Ficon%2F20160324%2F1458835090861273.jpg",
                1, "水源席子", 1, 31, "我爱北京天安门", imagePath
        );

        personEntities = new ArrayList<>();
        personEntities.add(personEntity);
        personEntities.add(personEntity1);
        personEntities.add(personEntity2);


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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);

        HotSearchAdapter eighteenAdapter = new HotSearchAdapter(getApplicationContext(), R.layout.list_search_hot_recommend, personEntities);

        rvHotRecommend.setLayoutManager(linearLayoutManager);

        rvHotRecommend.setAdapter(eighteenAdapter);


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
