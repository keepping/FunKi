package com.hifunki.funki.module.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hifunki.funki.base.adapter.PagerBaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.home.adapter.HomePagerAdapter.java
 * @link
 * @since 2017-03-13 17:43:43
 */
public class HomePagerAdapter extends PagerBaseAdapter {

    private Context context;
    private List<String> datas;
    private List<TextView> tvDATAS;

    public HomePagerAdapter(ArrayList viewList) {
        super(viewList);
    }


//    public HomePagerAdapter(Context context, List<String> datas) {
//        this.context = context;
//        this.datas = datas;
//        tvDATAS = new ArrayList();
//        //对基本数据源做一个转化
//        for (int i = 0; i < datas.size(); i++) {
//            TextView tv = new TextView(context);
//            tv.setText(datas.get(i));
//            tv.setGravity(Gravity.CENTER);
//            tvDATAS.add(tv);
//        }
//    }

    @Override
    public int getCount() {
        return tvDATAS.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * 从Viewpager中移除Item
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(tvDATAS.get(position));
    }

    /**
     * 向ViewPager中添加Item
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(tvDATAS.get(position));
        return tvDATAS.get(position);
    }
}
