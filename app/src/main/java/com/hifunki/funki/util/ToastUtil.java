package com.hifunki.funki.util;


import android.content.Context;
import android.widget.Toast;

/**
 * 自定义Toast提示框
 */
public class ToastUtil {

    private static Toast toast;

    /**
     * 显示toast
     * @param context
     * @param msg
     */
    public static void showToast(Context context, String msg) {
        showToast(context, msg, Toast.LENGTH_SHORT);
        // showToastUseDefulatPosition(context, msg, Toast.LENGTH_SHORT);
    }

    /**
     * @param context
     * @param resId
     * @desc 显示toast
     * @since 3.0.9
     */
    public static void showToast(Context context, int resId) {
        showToast(context, resId, Toast.LENGTH_SHORT);
        // showToast(context, resId, Toast.LENGTH_SHORT);
    }

    /**
     * @desc 显示toast
     * @param context
     * @param resId
     * @param duration
     *            值为：{@link Toast#LENGTH_SHORT}或 {@link Toast#LENGTH_LONG}
     * @since 3.0.9
     *
     *        public static void showToast(Context context, int resId, int
     *        duration) { if (context == null) { return; } showToast(context,
     *        resId, duration); // showToastUseDefulatPosition(context, //
     *        context.getResources().getString(resId), duration); }
     */

    /**
     * @param context
     * @param resId
     * @param duration 值为：{@link Toast#LENGTH_SHORT}或 {@link Toast#LENGTH_LONG}
     * @param gravity
     * @param xOffset
     * @param yOffset
     * @desc 显示toast(位置自定义)
     * @since 3.3
     */
    public static void showToast(Context context, int resId, int duration, int gravity, int xOffset, int yOffset) {
        if (context == null) {
            return;
        }
//        showToast(context, context.getResources().getString(resId), duration, gravity, xOffset, yOffset);
    }

    /**
     * 显示toast（V3.5后统一位置显示）
     *
     * @param context
     * @param resIdOrStr
     * @param duration
     * @since 3.5
     */
    public static void showToast(Context context, Object resIdOrStr, int duration) {
        if (context == null) {
            return;
        }
        String msg = "";
        if (resIdOrStr instanceof Integer) {
            msg = context.getResources().getString((Integer) resIdOrStr);
        } else if (resIdOrStr instanceof String) {
            msg = (String) resIdOrStr;
        }
//        showToast(context, msg, duration, Gravity.CENTER, 0, 0);
    }

    /**
     * @desc 显示toast
     * @param context
     * @param msg
     * @param duration
     *            值为：{@link Toast#LENGTH_SHORT}或 {@link Toast#LENGTH_LONG}
     * @since 3.3 public static void showToast(Context context, String msg, int
     *        duration) { showToast(context, msg, duration); //
     *        showToastUseDefulatPosition(context, msg, duration); }
     */

    /**
     * @desc 使用toast默认位置
     * @param context
     * @param msg
     * @param duration
     *
     *            private static void showToastUseDefulatPosition(Context
     *            context, String msg, int duration) { showToast(context, msg,
     *            duration, 81, 0, 128); }
     */

    /**
     * @param context
     * @param msg
     * @param duration 值为：{@link Toast#LENGTH_SHORT}或 {@link Toast#LENGTH_LONG}
     * @param gravity
     * @param xOffset
     * @param yOffset
     * @desc 显示toast
     * @since 3.0.9 (V3.3改版带有位置参数)
     */
//    @SuppressLint("InflateParams")
//    private static void showToast(Context context, String msg, int duration, int gravity, int xOffset, int yOffset) {
//        // gravity:81, x:0, y:128,
//        if (context == null || TextUtils.isEmpty(msg)) {
//            return;
//        }
//
//        if (toast == null) {
//            View view = LayoutInflater.from(context).inflate(R.layout.toast_layout, null);
//            Drawable drawable = context.getResources().getDrawable(R.drawable.bg_toast);
//            // 设置透明度为70%
//            drawable.setAlpha((int) (255 * 0.7));
//            view.setBackground(drawable);
//            TextView text = (TextView) view.findViewById(R.id.mText);
//
//            if (text == null) {
//                return;
//            }
//
//            text.setText(msg);
//            toast = new Toast(context);
//            toast.setView(view);
//        } else {
//            View view = toast.getView();
//            TextView tv = (TextView) view.findViewById(R.id.mText);
//            tv.setText(msg);
//        }
//
//        toast.setDuration(duration);
//        toast.setGravity(gravity, xOffset, yOffset);
//        toast.show();
//    }

}
