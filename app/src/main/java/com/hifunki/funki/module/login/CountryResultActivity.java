package com.hifunki.funki.module.login;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseTitleActivity;
import com.hifunki.funki.module.login.adapter.CityListAdapter;
import com.hifunki.funki.module.login.entity.City;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 国家检索结果页面
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.login.CountryResultActivity.java
 * @link
 * @since 2017-02-27 14:55:55
 */
public class CountryResultActivity extends BaseTitleActivity implements View.OnClickListener {

    @BindView(R.id.lv_res_country)
    ListView lvResCountry;
    @BindView(R.id.activity_country_result)
    RelativeLayout activityCountryResult;
    private List<City> cities;
    private CityListAdapter mCityAdapter;

    public static void show(Context context) {
        context.startActivity(new Intent(context, CountryResultActivity.class));
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_country_result;
    }

    @Override
    protected void initDatas() {
        cities = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            cities.add(new City("北京", "+011", "ss"));
        }
    }

    @Override
    protected void initTitleBar() {

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
        lvResCountry.setAdapter(mCityAdapter);
    }

    @OnClick({R.id.activity_country_result})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_country_result:
                break;
        }
    }
}
