package com.hifunki.funki.module.login;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hifunki.funki.R;
import com.hifunki.funki.module.login.pojo.City;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.module.login.adapter.CityListAdapter;
import com.hifunki.funki.module.login.adapter.SideLetterBar;
import com.hifunki.funki.module.login.widget.ToolTitleBar;

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

    @BindView(R.id.tvTitleLeft)
    TextView tvTitleLeft;
    @BindView(R.id.rlTitleLeft)
    RelativeLayout rlTitleLeft;
    @BindView(R.id.tvTitleCenter)
    TextView tvTitleCenter;
    @BindView(R.id.rlTitleCenter)
    RelativeLayout rlTitleCenter;
    @BindView(R.id.ll_title_right)
    LinearLayout llTitleRight;
    @BindView(R.id.flTitleContainer)
    FrameLayout flTitleContainer;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.rl_search)
    RelativeLayout rlSearch;
    @BindView(R.id.lv_all_country)
    ListView lvAllCountry;
    @BindView(R.id.side_letter_bar)
    SideLetterBar sideLetterBar;
    @BindView(R.id.activity_country_list)
    LinearLayout activityCountryList;
    private CityListAdapter mCityAdapter;
    private List<City> cities;

    @Override
    protected int getViewResId() {
        return R.layout.activity_county_list;
    }

    @Override
    protected void initDatas() {
        cities = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            cities.add(new City("北京", "+011", "ss"));
        }
    }

    @Override
    protected void initView() {


    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initAdapter() {
        mCityAdapter = new CityListAdapter(this, cities);
        lvAllCountry.setAdapter(mCityAdapter);
    }

    @Override
    protected void initTitleBar() {
        ToolTitleBar.showLeftButton(this, activityCountryList, ToolTitleBar.BTN_TYPE_IMAGE, R.drawable.iv_back, this);
        ToolTitleBar.showCenterButton(this, activityCountryList, ToolTitleBar.BTN_TYPE_TEXT, R.string.choose_country, null);
    }


    @OnClick({R.id.rl_search, R.id.side_letter_bar, R.id.activity_country_list})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rlTitleLeft://左边的back
                break;
            case R.id.rl_search://搜索
                //跳转搜索结果界面
                CountryResultActivity.show(this);
                break;
            case R.id.side_letter_bar://右侧bar
                break;
            case R.id.activity_country_list://总的布局
                break;
        }
    }
}

