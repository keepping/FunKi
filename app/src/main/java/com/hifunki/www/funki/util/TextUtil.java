package com.hifunki.www.funki.util;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Created by dell on 2017/2/22.
 */

public class TextUtil {
    /**
     * 根据字体大小，计算字符串长度
     *
     * @param ctx     上下文
     * @param str     字符串
     * @param spValue 字体大小
     * @return 文本长度
     */
    public static int calStrWidth(Context ctx, String str, float spValue) {
        Paint paint = new Paint();
        paint.setTextSize(DisplayUtil.sp2px(ctx, spValue));
        Rect rect = new Rect();
        paint.getTextBounds(str, 0, str.length(), rect);
        return rect.width();
    }

    /**
     * 方正大黑繁体
     *
     * @param context
     * @param textViews
     */
    public static void setFzBigTypeFace(Context context, TextView... textViews) {
        //常规字体
        Typeface medTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/Fan_Big.ttf");
        for (int i = 0; i < textViews.length; i++) {
            if (textViews[i] != null) {
                textViews[i].setTypeface(medTypeface);
            }
        }
    }

    /**
     * 方正中等线繁体
     *
     * @param context
     * @param textViews
     */
    public static void setFzNormalTypeFace(Context context, TextView... textViews) {
        //常规字体
        Typeface medTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/Fan_Normal.ttf");
        for (int i = 0; i < textViews.length; i++) {
            if (textViews[i] != null) {
                textViews[i].setTypeface(medTypeface);
            }
        }
    }
}
