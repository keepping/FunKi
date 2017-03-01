package com.hifunki.funki.ui.activity.login;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.hifunki.funki.R;
import com.hifunki.funki.model.City;
import com.hifunki.funki.ui.activity.base.BaseActivity;
import com.hifunki.funki.ui.adapter.CityListAdapter;
import com.hifunki.funki.ui.widget.ToolEditTitleBar;
import com.hifunki.funki.ui.widget.ToolTitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 国家检索结果页面
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.ui.activity.login.CountryResultActivity.java
 * @link
 * @since 2017-02-27 14:55:55
 */
public class CountryResultActivity extends BaseActivity implements View.OnClickListener {

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

        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.activity_country_result);
        //设置左边的search ImageView
//        ToolEditTitleBar.showLeftImageView(this, relativeLayout, R.drawable.iv_search, this);
//        ToolEditTitleBar.showLeftButton(this, relativeLayout, ToolTitleBar.BTN_TYPE_IMAGE, R.drawable.iv_search, null);
        ToolEditTitleBar.showCenterEditText(this, relativeLayout, null, null);
        //iv_close
        ToolEditTitleBar.showRightButton(this, relativeLayout, R.layout.activity_login_search, ToolTitleBar.BTN_TYPE_IMAGE, R.drawable.iv_back, null);
        //右边的搜索
        ToolEditTitleBar.showRightButton(this, relativeLayout, R.layout.activity_login_search, ToolTitleBar.BTN_TYPE_TEXT, R.string.search, null);
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
