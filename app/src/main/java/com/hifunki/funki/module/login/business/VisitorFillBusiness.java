package com.hifunki.funki.module.login.business;

import android.content.Context;
import android.widget.TextView;

import com.hifunki.funki.R;


/**
 * 游客的业务类
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.login.business.VisitorFillBusiness.java
 * @link
 * @since 2017-03-06 15:10:10
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
        tvs.setTextColor(context.getResources().getColor(R.color._FFD71B));
        tvs.setBackground(context.getResources().getDrawable(R.drawable.visitor_tv_click_bg));

        for (TextView tv : textViews) {
            tv.setTextColor(context.getResources().getColor(R.color._BBABD4));
            tv.setBackground(context.getResources().getDrawable(R.drawable.visitor_tv_bg));
        }
    }

    @SuppressWarnings("deprecation")
    public static void changeTvStyle(Context context, boolean isclick, TextView tvs) {
//        if (Build.VERSION.SDK_INT <= 23) {
        if (isclick) {
            tvs.setTextColor(context.getResources().getColor(R.color._FFD71B));
            tvs.setBackground(context.getResources().getDrawable(R.drawable.visitor_tv_click_bg));
        } else {
            tvs.setTextColor(context.getResources().getColor(R.color._BBABD4));
            tvs.setBackground(context.getResources().getDrawable(R.drawable.visitor_tv_bg));
        }

    }

    /**
     * 设置确定的背景颜色
     *
     * @param context
     * @param textView
     * @param sex
     * @param jump
     */
    public static void isJump(Context context, TextView textView, int sex, boolean... jump) {
        if (jump.length != 5) {
            return;
        }
        boolean isJump = ((sex != 0) || jump[0] || jump[1] || jump[2] || jump[3] || jump[4]);
        if (!isJump) {
            textView.setBackground(context.getResources().getDrawable(R.drawable.visitor_unconfirm_bg));
        } else {
            textView.setBackground(context.getResources().getDrawable(R.drawable.visitor_confirm_bg));
        }
    }

    /**
     * 判断是否能跳转
     * @param sex
     * @param jump
     * @return
     */
    public static boolean isJump( int sex, boolean... jump) {
        if (jump.length != 5) {
            return false;
        }
        boolean isJump = ((sex != 0) || jump[0] || jump[1] || jump[2] || jump[3] || jump[4]);
        if (!isJump) {
            return false;
        } else {
            return true;
        }
    }
}
