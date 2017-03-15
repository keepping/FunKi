package com.hifunki.funki.util;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;


public class ViewUtil {

    /**
     * 一次设置多个控件CLick监听事件
     *
     * @param views 参数
     */
    public static void setViewsOnClickListener(View.OnClickListener onClickListener, View... views) {
        for (int i = 0; i < views.length; i++) {
            if (views[i] != null) {
                views[i].setOnClickListener(onClickListener);
            }
        }
    }

    /**
     * Text view设值
     *
     * @param tv text view
     * @param cs 文案
     */
    public static void setText(TextView tv, CharSequence cs) {
        if (tv != null) {
            tv.setText(cs);
        }
    }

    /**
     * Text view设值
     *
     * @param tv    text view
     * @param resId 文案id
     */
    public static void setText(TextView tv, int resId) {
        if (tv != null && resId != 0) {
            tv.setText(resId);
        }
    }

    /**
     * 设置图片id
     *
     * @param ivImage 图片空间
     * @param resId   资源id
     */
    public static void setImageResource(ImageView ivImage, int resId) {
        if (ivImage != null && resId != 0) {
            ivImage.setImageResource(resId);
        }
    }

    /**
     * 设置图片背景id
     *
     * @param ivImage 图片空间
     * @param resId   资源id
     */
    public static void setBackgroundResource(ImageView ivImage, int resId) {
        if (ivImage != null && resId != 0) {
            ivImage.setBackgroundResource(resId);
        }
    }

    /**
     * 显示或隐藏View
     *
     * @param visibleOrGone 值见：{@link View#VISIBLE}、{@link View#GONE}
     * @param views         视图
     */
    public static void showOrHideView(int visibleOrGone, View... views) {
        if (views != null) {
            for (int i = 0; i < views.length; i++) {
                if (views[i] != null) {
                    views[i].setVisibility(visibleOrGone);
                }
            }
        }
    }

    /**
     * 添加视图
     *
     * @param viewGroup 父容器
     * @param views     子视图集合
     */
    public static void addViews(ViewGroup viewGroup, View... views) {
        if (viewGroup != null && views != null) {
            for (int i = 0; i < views.length; i++) {
                viewGroup.addView(views[i]);
            }
        }
    }

    /**
     * 创建 首次网络失败时的视图
     *
     * @param ctx Context
     * @return 返回网络加载失败视图
     */
//    @SuppressLint("InflateParams")
//    public static View createLoadedFailedView(Context ctx) {
//        return LayoutInflater.from(ctx).inflate(R.layout.net_loaded_error, null, false);
//    }

//    /**
//     * 列表刷新控件刷新完成
//     *
//     * @param ptrBase 刷新控件
//     */
//    public static <T extends View> void refreshCompleted(final PullToRefreshBase<T> ptrBase) {
//        if (ptrBase != null) {
//            ptrBase.getRefreshableView().post(new Runnable() {
//                @Override
//                public void run() {
//                    ptrBase.setRequetSuccess(true);
//                    ptrBase.onRefreshComplete();
//                }
//            });
//        }
//    }

    /**
     * 加载SimpleDraweeView控件 图片
     *
     * @param imgView 控件
     * @param url     图片网络地址
     */
//    public static void setSimpleDraweeViewImg(SimpleDraweeView imgView, String url) {
//        if (null == url) {
//            url = "";
//        }
//        Uri uri = Uri.parse(url);
//        imgView.setImageURI(uri);
//    }

    /**
     * 设置标签
     *
     * @param v   view控件
     * @param tag tag标签
     * @return v
     */
    public static View setTag(View v, Object tag) {
        if (v != null) {
            v.setTag(tag);
        }

        return v;
    }

    /**
     * 设置标签
     *
     * @param v      view控件
     * @param tagKey tag key
     * @param tag    tag标签
     * @return v
     */
    public static View setTag(View v, int tagKey, Object tag) {
        if (v != null) {
            v.setTag(tagKey, tag);
        }
        return v;
    }

    /**
     * 删除监听
     *
     * @param v      视图
     * @param victim TouchMode监听
     */
    public static void removeOnTouchModeChangeListener(View v, ViewTreeObserver.OnTouchModeChangeListener victim) {
        if (v != null && victim != null) {
            ViewTreeObserver observer = v.getViewTreeObserver();

            if (observer != null) {
                observer.removeOnTouchModeChangeListener(victim);
            }
        }
    }

    /**
     * 设置视图点击id
     *
     * @param clickId id值
     * @param views   视图集合
     */
    public static void setViewClickId(int clickId, View... views) {
        for (int i = 0; i < views.length; i++) {
            if (views[i] != null) {
                views[i].setId(clickId);
            }
        }
    }

}
