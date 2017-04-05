package com.hifunki.funki.module.live.start.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.widget.flowlayout.FlowLayout;
import com.hifunki.funki.widget.flowlayout.TagAdapter;
import com.hifunki.funki.widget.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 开启直播选择标签
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.live.start.activity.LiveTagActivity.java
 * @link
 * @since 2017-03-29 16:53:53
 */
public class LiveTagActivity extends BaseActivity {

    private TagFlowLayout mFlowLayout;
    private TagAdapter<String> mAdapter;
    private List<String> stringList;


    public static void show(Context context) {
        context.startActivity(new Intent(context, LiveTagActivity.class));
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_live_tag;
    }

    @Override
    protected void initDatas() {
        stringList = new ArrayList<>();
        stringList.add("萝莉");
        stringList.add("御姐");
        stringList.add("肌肉男");
        stringList.add("北欧");
        stringList.add("摇滚");
        stringList.add("森系");
        stringList.add("哥特");
        stringList.add("放克");
        stringList.add("酸豆角");
        stringList.add("爵士");
        stringList.add("SM");
        stringList.add("机车");
        stringList.add("铁路");
        stringList.add("壮熊");
        stringList.add("麻瓜");
        stringList.add("香蕉");
        stringList.add("雷神");
        stringList.add("朋克");
        stringList.add("瘦猴");
        stringList.add("暴雪");
        stringList.add("OL");
        stringList.add("MC");
    }

    @Override
    protected void initView() {
        mFlowLayout = (TagFlowLayout) findViewById(R.id.id_flowlayout);
        final LayoutInflater mInflater = LayoutInflater.from(this);
        mFlowLayout.setAdapter(mAdapter = new TagAdapter<String>(stringList) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.layout_live_tag,
                        mFlowLayout, false);
                tv.setText(s);
                //设置不同的颜色
                GradientDrawable drawable= (GradientDrawable) tv.getBackground();
                if(position%3==0) {
                    int color = getResources().getColor(R.color._9B44ED);
                    drawable.setColor(color);
                }else if(position%3==1){
                    int color1 = getResources().getColor(R.color._61E46D);
                    drawable.setColor(color1);
                }else{
                    int color1 = getResources().getColor(R.color._AB2D2D);
                    drawable.setColor(color1);
                }
                return tv;
            }
        });
        mAdapter.setSelectedList(1, 3, 5, 7, 8, 9);
        mFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                //Toast.makeText(getActivity(), mVals[position], Toast.LENGTH_SHORT).show();
                //view.setVisibility(View.GONE);
                return true;
            }
        });


        mFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                setTitle("choose:" + selectPosSet.toString());
            }
        });
    }


}
