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
import com.hifunki.funki.widget.FunKiPlayer;

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
    @BindView(R.id.fp_show_detail)
    FunKiPlayer funKiPlayer;

    public static void show(Context context) {
        context.startActivity(new Intent(context, ShowDetailActivity.class));
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_show_detail;
    }

    @Override
    protected void initDatas() {
        funKiPlayer.play("http://vliveachy.tc.qq.com/vcloud.tc.qq.com/n0011yeemua.m301.mp4?vkey=85E512C3DDCF24AF50663F9F9C64B698218C0F1025233FBED75CD1825E03B74AD0197596D5DC67EB04C5F5EF109F54E7E2D815CF0E28D2D7DC4BDC929B8BB811E6EA52C744C30EF5C5B874F363659BD18D58613C4D8447D50CE703CDC34C8CC19DF6EE057F4014CAE3C75F6A019E4D66&guid=714F50E7615D5EBAF3296F3A66B43579");
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
