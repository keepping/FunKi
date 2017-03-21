package com.hifunki.funki.module.show.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.common.CommonConst;
import com.hifunki.funki.module.home.widget.ninegrid.NineGridlayout;
import com.hifunki.funki.module.show.adapter.ShowDetailMsgAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.show.activity.ShowDetailActivity.java
 * @link
 * @since 2017-03-21 10:20:20
 */
public class ShowDetailActivity extends BaseActivity {


    @BindView(R.id.nine_show_detail)
    NineGridlayout nineShowDetail;

    @BindView(R.id.rl_show_detail)
    RecyclerView rlShowDetail;

    public static void show(Context context) {
        context.startActivity(new Intent(context, ShowDetailActivity.class));
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_show_detail;
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initView() {
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            strings.add(CommonConst.photo);
        }
        nineShowDetail.setImagesData(strings);

        List<String> strings1 = new ArrayList<>();
        strings1.add("xxx");
        strings1.add("xxx");
        strings1.add("xxx");
        strings1.add("xxx");
        strings1.add("xxx");
        strings1.add("xxx");
        strings1.add("xxx");
        strings1.add("xxx");
        strings1.add("xxx");
        strings1.add("xxx");
        strings1.add("xxx");
        strings1.add("xxx");
        strings1.add("xxx");
        strings1.add("xxx");
        strings1.add("xxx");
        strings1.add("xxx");
        strings1.add("xxx");
        strings1.add("xxx");
        strings1.add("xxx");
        strings1.add("xxx");
        strings1.add("xxx");

        ShowDetailMsgAdapter adapter = new ShowDetailMsgAdapter(R.layout.item_show_detail, strings1);
        rlShowDetail.setNestedScrollingEnabled(false);//防止滑动事件传递到RecycleView
        rlShowDetail.setLayoutManager(new LinearLayoutManager(this));
        rlShowDetail.setAdapter(adapter);
    }


}
