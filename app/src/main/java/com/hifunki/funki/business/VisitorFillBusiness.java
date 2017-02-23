package com.hifunki.funki.business;

import android.content.Context;
import android.widget.TextView;

import com.hifunki.funki.R;


/**
 * Created by dell on 2017/2/23.
 */

public class VisitorFillBusiness {
    /**
     * 改变字体style
     *
     * @param tvs
     * @param textViews
     */
    public static void changeTvStyle(Context context, TextView tvs, TextView... textViews) {

        tvs.setTextColor(context.getResources().getColor(R.color.vistorTvClickbg));
        tvs.setBackground(context.getResources().getDrawable(R.drawable.visitor_tv_click_bg));

        for (TextView tv : textViews) {
            tv.setTextColor(context.getResources().getColor(R.color.vistorTvbg));
            tv.setBackground(context.getResources().getDrawable(R.drawable.visitor_tv_bg));
        }

    }
}
