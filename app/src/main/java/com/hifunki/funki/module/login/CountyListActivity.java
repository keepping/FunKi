package com.hifunki.funki.module.login;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.base.adapter.BaseHeaderAdapter;
import com.hifunki.funki.base.entity.PinnedHeaderEntity;
import com.hifunki.funki.module.login.adapter.CityListAdapter;
import com.hifunki.funki.module.login.entity.City;
import com.hifunki.funki.module.login.entity.City2;
import com.hifunki.funki.util.DisplayUtil;
import com.oushangfeng.pinnedsectionitemdecoration.PinnedHeaderItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 国家选择页面
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.login.CountyListActivity.java
 * @link
 * @since 2017-02-23 20:24:24
 */
public class CountyListActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.rl_search)
    RelativeLayout rlSearch;
    @BindView(R.id.rv_all_country)
    RecyclerView recyclerView;
    @BindView(R.id.activity_country_list)
    LinearLayout activityCountryList;
    private CityListAdapter mCityAdapter;
    private List<City> cities;
    private String TAG = getClass().getSimpleName();
    private List<PinnedHeaderEntity<String>> data;
    private BaseHeaderAdapter<PinnedHeaderEntity<Integer>> mAdapter;
    private Context mContext;

    @Override
    protected int getViewResId() {
        return R.layout.activity_county_list;
    }

    @Override
    protected void initVariable() {
        mContext = CountyListActivity.this;
        cities = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            cities.add(new City("北京", "+011", "ss"));
        }
        List<City2> city2s = new ArrayList<>();
        final String[] b = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        final String[] a1 = {"鞍山", "安庆", "安阳", "阿拉善盟", "阿拉山口", "安康",};

        for (int i = 0; i < b.length; i++) {
            List<String> list = new ArrayList<>();
            for (int j = 0; j < a1.length; j++) {
                list.add(a1[j]);
            }
            City2 city2 = new City2(b[i], list);
            city2s.add(city2);
        }
        Log.e(TAG, "initVariable: " + city2s);

        data = new ArrayList<>();
        for (int i = 0; i < city2s.size(); i++) {
            data.add(new PinnedHeaderEntity<>("s", BaseHeaderAdapter.TYPE_HEADER, city2s.get(i).getTag()));
            for (String dog : city2s.get(i).getCountry()) {
                data.add(new PinnedHeaderEntity<String>(dog, BaseHeaderAdapter.TYPE_DATA, city2s.get(i).getTag()));
            }
        }


        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new PinnedHeaderItemDecoration.Builder(BaseHeaderAdapter.TYPE_HEADER).setDividerId(R.drawable.divider).enableDivider(true)
                .create());

        recyclerView.setAdapter(mAdapter);

//        data.add(new PinnedHeaderEntity<>(0, BaseHeaderAdapter.TYPE_HEADER, "B"));
//        for (int cat : mCats) {
//            data.add(new PinnedHeaderEntity<>(cat, BaseHeaderAdapter.TYPE_DATA, "B"));
//        }
//        data.add(new PinnedHeaderEntity<>(0, BaseHeaderAdapter.TYPE_HEADER, "C"));
//        for (int rabbit : mRabbits) {
//            data.add(new PinnedHeaderEntity<>(rabbit, BaseHeaderAdapter.TYPE_DATA, "C"));
//        }
//        data.add(new PinnedHeaderEntity<>(0, BaseHeaderAdapter.TYPE_HEADER, "D"));
//        for (int panda : mPandas) {
//            data.add(new PinnedHeaderEntity<>(panda, BaseHeaderAdapter.TYPE_DATA, "D"));
//        }


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

    @Override
    protected void bindData() {

    }

    @Override
    protected void bindData4NoNet() {

    }

    @Override
    protected void initTitleBar() {

    }

    @OnClick({R.id.rl_search, R.id.activity_country_list})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_search://搜索
                //跳转搜索结果界面
                CountryResultActivity.show(this);
                break;

            case R.id.activity_country_list://总的布局
                break;
        }
    }

    public static void show(Context context) {
        context.startActivity(new Intent(context, CountyListActivity.class));
    }
}

