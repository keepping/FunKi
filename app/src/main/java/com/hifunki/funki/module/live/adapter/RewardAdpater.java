package com.hifunki.funki.module.live.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hifunki.funki.R;

import java.util.Random;

/**
 * 在此写用途
 *
 * @author yinhaoxiang
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.live.adapter.RewardAdpater.java
 * @link
 * @since 2017-03-30 13:44:44
 */
public class RewardAdpater extends PagerAdapter{
    Random random = new Random(10);
    @Override
    public int getCount() {
        return 100;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    public Object instantiateItem(ViewGroup container, int position) {

        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_reward_head,null,false);
        ViewPager.LayoutParams layoutParams = new ViewPager.LayoutParams();
        TextView textView = (TextView)view.findViewById(R.id.tv_account);

        textView.setText(String.valueOf((int)Math.pow(2,random.nextInt(30))));

        layoutParams.width = -2;
        layoutParams.height = -1;

        container.addView(view,layoutParams);

        System.out.println("------------------------");


        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {



    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public float getPageWidth(int position) {
        return 0.3f;
    }
}























