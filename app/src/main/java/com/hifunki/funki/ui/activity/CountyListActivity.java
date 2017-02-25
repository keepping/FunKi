package com.hifunki.funki.ui.activity;

import android.widget.ListView;

import com.hifunki.funki.R;
import com.hifunki.funki.model.City;
import com.hifunki.funki.ui.adapter.CityListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 国家页面
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.ui.activity.CountyListActivity.java
 * @link
 * @since 2017-02-23 20:24:24
 */
public class CountyListActivity extends BaseActivity {
    private CityListAdapter mCityAdapter;
    private List<City> cities;
    private ListView mListView;

    @Override
    protected int getViewResId() {
        return R.layout.activity_county_list;
    }

    @Override
    protected void initView() {
        cities = new ArrayList<>();
        cities.add(new City("北京", "ss"));
        cities.add(new City("北京", "ss"));
        cities.add(new City("北京", "ss"));
        cities.add(new City("北京", "ss"));
        cities.add(new City("北京", "ss"));
        cities.add(new City("北京", "ss"));
        cities.add(new City("北京", "ss"));
        cities.add(new City("北京", "ss"));
        mCityAdapter = new CityListAdapter(this, cities);
        mListView = (ListView) findViewById(R.id.listview_all_city);
        mListView.setAdapter(mCityAdapter);
    }

    @Override
    protected void initTitleBar() {

    }

    @Override
    protected void loadDatas() {

    }
}
