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
    @SuppressWarnings("deprecation")
    public static void changeTvStyle(Context context, TextView tvs, TextView... textViews) {
//        if (Build.VERSION.SDK_INT <= 23) {
        tvs.setTextColor(context.getResources().getColor(R.color.vistorTvClickbg));
        tvs.setBackground(context.getResources().getDrawable(R.drawable.visitor_tv_click_bg));

        for (TextView tv : textViews) {
            tv.setTextColor(context.getResources().getColor(R.color.titleText));
            tv.setBackground(context.getResources().getDrawable(R.drawable.visitor_tv_bg));
        }
//        } else {
//            tvs.setTextColor(context.getResources().getColor(R.color.vistorTvClickbg, context.getTheme()));
//            tvs.setBackground(context.getResources().getDrawable(R.drawable.visitor_tv_click_bg, context.getTheme()));
//
//            for (TextView tv : textViews) {
//                tv.setTextColor(context.getResources().getColor(R.color.vistorTvbg, context.getTheme()));
//                tv.setBackground(context.getResources().getDrawable(R.drawable.visitor_tv_bg, context.getTheme()));
//            }
//        }


    }

    @SuppressWarnings("deprecation")
    public static void changeTvStyle(Context context, boolean isclick, TextView tvs) {
//        if (Build.VERSION.SDK_INT <= 23) {
        if (isclick) {
            tvs.setTextColor(context.getResources().getColor(R.color.vistorTvClickbg));
            tvs.setBackground(context.getResources().getDrawable(R.drawable.visitor_tv_click_bg));
        } else {
            tvs.setTextColor(context.getResources().getColor(R.color.titleText));
            tvs.setBackground(context.getResources().getDrawable(R.drawable.visitor_tv_bg));
        }

//        } else {
//            tvs.setTextColor(context.getResources().getColor(R.color.vistorTvClickbg, context.getTheme()));
//            tvs.setBackground(context.getResources().getDrawable(R.drawable.visitor_tv_click_bg, context.getTheme()));
//
//            for (TextView tv : textViews) {
//                tv.setTextColor(context.getResources().getColor(R.color.vistorTvbg, context.getTheme()));
//                tv.setBackground(context.getResources().getDrawable(R.drawable.visitor_tv_bg, context.getTheme()));
//            }
//        }


    }
}
