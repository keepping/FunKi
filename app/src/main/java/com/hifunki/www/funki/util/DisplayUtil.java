package com.hifunki.www.funki.util;

import android.content.Context;

/**
 * Created by dell on 2017/2/22.
 */

public class DisplayUtil {

    /**
     * 将sp值转换为px值
     *
     * @param spValue
     * @param context （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
