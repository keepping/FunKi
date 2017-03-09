package com.hifunki.funki.module.login.business;

import android.animation.ValueAnimator;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;

/**
 * ActivityLogin的业务类
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.login.business.LoginBusiness.java
 * @link
 * @since 2017-03-06 15:10:10
 */
public class LoginBusiness {

    /**
     * 设置margin
     *
     * @param llIcon
     * @param height
     * @param start
     * @param end
     */
    public static void setTopMarginAnimator(final LinearLayout llIcon, final int height, int start, int end) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(start, end);//设置属性动画的开始值和结束值
        valueAnimator.setDuration(400).setInterpolator(new AccelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) llIcon.getLayoutParams();
                marginLayoutParams.topMargin = -(int) (height * animatedValue);
                llIcon.requestLayout();
            }
        });

        if (valueAnimator.isRunning()) {
            valueAnimator.cancel();
        }
        valueAnimator.start();
    }

    /**
     * 设置alpha
     *
     * @param llIcon
     * @param start
     * @param end
     */
    public static void setAlphaAnimator(final LinearLayout llIcon, final int start, final int end) {
        ValueAnimator valueAnimatorAlpha = ValueAnimator.ofFloat(start, end);
        valueAnimatorAlpha.setDuration(400).setInterpolator(new AccelerateInterpolator());
        valueAnimatorAlpha.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                llIcon.requestLayout();
                llIcon.setAlpha(animatedValue);
            }
        });
        if (valueAnimatorAlpha.isRunning()) {
            valueAnimatorAlpha.cancel();
        }
        valueAnimatorAlpha.start();
    }

    /**
     * 是否登录
     */
    public void isLogin() {

    }
}
