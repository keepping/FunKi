package com.hifunki.funki.ui.widget;


import android.content.Context;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hifunki.funki.R;
import com.hifunki.funki.util.DisplayUtil;

import static com.hifunki.funki.R.id.rlTitleLeft;

public class ToolTitleBar {

    /**
     * 按钮类型：图片
     */
    public static final byte BTN_TYPE_IMAGE = 1;

    /**
     * 按钮类型：文字
     */
    public static final byte BTN_TYPE_TEXT = 2;

    /**
     * 显示标题栏 左边 按钮
     *
     * @param mainLayout      当前FragmentView
     * @param btnType         按钮类型，值见{@link #BTN_TYPE_IMAGE} 或 {@link #BTN_TYPE_TEXT}
     * @param resIdOrTxt      资源id(int型)或文本(String)
     * @param onClickListener OnClick监听器 注意：设置在左边按钮的父容器上，即：R.id.rlTitleLeft
     * @return
     */
    public static TextView showLeftButton(Context context, View mainLayout, byte btnType, Object resIdOrTxt,
                                          View.OnClickListener onClickListener) {
        TextView tv = showTextViewButton(context, R.id.tvTitleLeft, mainLayout, btnType, resIdOrTxt, null);

        if (onClickListener != null) {
            mainLayout.findViewById(rlTitleLeft).setOnClickListener(onClickListener);
        }

        return tv;
    }

    public static ImageView showLeftImageView(Context context, View mainLayout, Object resIdOrTxt,
                                              View.OnClickListener listener) {
        ImageView iv = (ImageView) mainLayout.findViewById(R.id.iv_Title_left);
        if (iv == null) {
            return null;
        }

        // 设置监听器
//        if (listener != null) {
//            iv.setOnClickListener(listener);
//        }

        LinearLayout.LayoutParams ivLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ivLp.gravity = Gravity.CENTER_VERTICAL;
        iv.setLayoutParams(ivLp);

        iv.setVisibility(View.VISIBLE);


        if (listener != null) {
            mainLayout.findViewById(rlTitleLeft).setOnClickListener(listener);
        }

        return iv;
    }

    /**
     * 显示标题栏 中间 按钮
     *
     * @param fragmentView    当前FragmentView
     * @param btnType         按钮类型，值见{@link #BTN_TYPE_IMAGE} 或 {@link #BTN_TYPE_TEXT}
     * @param resIdOrTxt      资源id(int型)或文本(String)
     * @param onClickListener OnClick监听器 注意：设置在中间按钮上
     * @return
     */
    public static TextView showCenterButton(Context context, View fragmentView, byte btnType, Object resIdOrTxt,
                                            View.OnClickListener onClickListener) {
        TextView tv = showTextViewButton(context, R.id.tvTitleCenter, fragmentView, btnType, resIdOrTxt, onClickListener);
        return tv;
    }

    /**
     * 中间layout替换
     *
     * @param fragmentView 当前显示的Fragment视图
     * @param view         替换的view
     */
    public static void replaceCenterButton(View fragmentView, View view) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        RelativeLayout layout = (RelativeLayout) fragmentView.findViewById(R.id.rlTitleCenter);
        layout.removeAllViews();
        layout.addView(view, layoutParams);
    }

//    /**
//     * @param fragmentView    当前FragmentView
//     * @param btnType         按钮类型，值见{@link #BTN_TYPE_IMAGE} 或 {@link #BTN_TYPE_TEXT}
//     * @param resIdOrTxt      资源id(int型)或文本(String)
//     * @param onClickListener OnClick监听器 注意：设置在右边按钮上
//     * @return
//     * @desc 显示标题栏  边 按钮 (非购物车的普通按钮)
//     */
//    public static TextView showRightButton(View fragmentView, byte btnType, Object resIdOrTxt,
//                                           OnClickListener onClickListener) {
//        TextView tv = showTextViewButton(R.id.tvTitleRight, fragmentView, btnType, resIdOrTxt, onClickListener);
//
//        if (tv != null && btnType == BTN_TYPE_TEXT) {// 文本特殊处理
//            tv.setHeight((int) ToolDisplay.dip2Px(fragmentView.getContext(), 35));
//        }
//
//        return tv;
//    }
//
//    /**
//     * @param rootView
//     * @param ctx
//     * @param onClickListener 注意：设置在父容器上，即：R.id.rlTitleRightSb
//     * @return
//     * @desc 显示标题栏  边 按钮 (购物车按钮特殊处理)
//     */
//    public static TextView showRightButtonSb(View rootView, Context ctx, OnClickListener onClickListener) {
//        View rlSbRootView = rootView.findViewById(R.id.rlTitleRightSb);
//        TextView tvSbSum = null;
//
//        if (rlSbRootView != null) {
//            rlSbRootView.setVisibility(View.VISIBLE);
//            rlSbRootView.setOnClickListener(onClickListener);
//
//            int sbCount = BusinessShoppingBag.getSbCount4Show(ctx);
//            tvSbSum = (TextView) rootView.findViewById(R.id.tvTitleRightSb);
//            showShoppingBagSum(tvSbSum, sbCount);
//            changeShoppingBagImg(rootView, sbCount);
//        }
//
//        return tvSbSum;
//    }

    /**
     * 刷新标题栏的购物车数量
     *
     * @param rootView 父容器
     * @param ctx      上下文
     */
//    public static void refreshShoppingBagTitle(View rootView, Context ctx) {
//        TextView tvTitleRightSbBtn = (TextView) getTitleView(rootView, R.id.tvTitleRightSb);
//
//        if (tvTitleRightSbBtn != null) {
//            ViewParent parent = tvTitleRightSbBtn.getParent();
//
//            if (parent != null && (parent instanceof View)) {
//                if (((View) parent).getVisibility() == View.VISIBLE) {
//                    int sbCount = BusinessShoppingBag.getSbCount4Show(ctx);
//                    showShoppingBagSum(tvTitleRightSbBtn, sbCount);
//                    changeShoppingBagImg(rootView, sbCount);
//                }
//            }
//        }
//    }

    /**
     * 刷新标题栏的购物车数量
     *
     * @param chatCount 数量
     * @param rootView  根视图
     */
//    public static void refreshLiveChatTitle(int chatCount, View rootView) {
//        TextView tvTitleRightChatBtn = (TextView) getTitleView(rootView, R.id.tvTitleRightChat);
//
//        if (tvTitleRightChatBtn != null) {
//            ViewParent parent = tvTitleRightChatBtn.getParent();
//
//            if (parent != null && (parent instanceof View)) {
//                if (((View) parent).getVisibility() == View.VISIBLE) {
//                    if (chatCount > 0) {
//                        chatCount = (chatCount > 99) ? 99 : chatCount;// 最多显示99数量
//                    }
//                    showChatSum(tvTitleRightChatBtn, chatCount);
//                    changeChatImg(rootView, chatCount);
//                }
//            }
//        }
//    }


    /**
     * 根据ID获 标题栏控件
     *
     * @param fragmentView 当前显示的Fragment视图
     * @param id           控件Id
     * @return 标题栏控件
     */
    public static View getTitleView(View fragmentView, int id) {
        View titleView = null;

        if (fragmentView != null) {
            titleView = fragmentView.findViewById(id);
        }

        return titleView;
    }

    /**
     * 根据参数，显示或者隐藏消息未读红点标示
     *
     * @param rootView 父视图
     * @param isShow   true：显示，false：隐藏
     */
//    public static void showOrHiddenRightMsgTip(View rootView, boolean isShow) {
//        View msgTipView = rootView.findViewById(R.id.iv_title_right_msg_tip);
//        ViewUtil.showOrHideView(isShow ? View.VISIBLE : View.GONE, msgTipView);
//    }

    /**
     * 显示标题栏右侧 按钮（普通类型）
     *
     * @param ctx             上下文
     * @param rootView        父容器
     * @param resId           按钮Id
     * @param btnType         按钮类型，值见{@link #BTN_TYPE_IMAGE} 或 {@link #BTN_TYPE_TEXT}
     * @param resIdOrTxt      资源id(int型)或文本(String)
     * @param onClickListener 点击监听
     */
    public static LinearLayout showRightButton(Context ctx, View rootView, int resId, byte btnType,
                                               Object resIdOrTxt, View.OnClickListener onClickListener) {
        LinearLayout rightRootView = (LinearLayout) rootView.findViewById(R.id.ll_title_right);
        LinearLayout ll = createRightButtonLinearLayout(ctx, resId, btnType, resIdOrTxt, onClickListener);
        if (ll != null) {
            rightRootView.addView(ll);
        }
        return ll;
    }

    /**
     * 显示标题栏右侧消息图标
     *
     * @param rootView        父容器
     * @param ctx             上下文
     * @param onClickListener 点击监听  即 R.id.fl_title_right_msg
     */
//    public static void showRightButtonMsg(View rootView, Context ctx, View.OnClickListener onClickListener) {
//        LinearLayout rightRootView = (LinearLayout) rootView.findViewById(R.id.ll_title_right);
//        View rightBtnMsgView = LayoutInflater.from(ctx).inflate(R.layout.layout_title_right_message, rightRootView, false);
//        if (rightBtnMsgView != null) {
//            if (onClickListener != null) {
//                rightBtnMsgView.setOnClickListener(onClickListener);
//            }
////            setRightLeftMargin4Language(ctx, rightBtnMsgView);
//            rightRootView.addView(rightBtnMsgView);
//        }
//    }

    /**
     * 显示标题栏右侧购物车图标
     *
     * @param rootView        父视图
     * @param ctx             上下文
     * @param onClickListener 点击监听  即：R.id.rlTitleRightSb
     */
//    public static void showRightButtonSb(View rootView, Context ctx, View.OnClickListener onClickListener) {
//        LinearLayout rightRootView = (LinearLayout) rootView.findViewById(R.id.ll_title_right);
//        View rightBtnSbView = LayoutInflater.from(ctx).inflate(R.layout.layout_title_right_bag, rightRootView, false);
//        if (rightBtnSbView != null) {
//
//            int sbCount = BusinessShoppingBag.getSbCount4Show(ctx);
//            TextView tvSbSum = (TextView) rightBtnSbView.findViewById(R.id.tvTitleRightSb);
//            showShoppingBagSum(tvSbSum, sbCount);
//            changeShoppingBagImg(rightBtnSbView, sbCount);
//
//            if (onClickListener != null) {
//                rightBtnSbView.setOnClickListener(onClickListener);
//            }
//
////            setRightLeftMargin4Language(ctx, rightBtnSbView);
//
//            rightRootView.addView(rightBtnSbView);
//        }
//    }

    /**
     * 显示标题栏右侧聊天图标
     *
     * @param rootView        父视图
     * @param ctx             上下文
     * @param onClickListener 点击监听  即：R.id.rlTitleRightChat
     */
//    public static void showRightButtonChat(View rootView, Context ctx, View.OnClickListener onClickListener) {
//        LinearLayout rightRootView = (LinearLayout) rootView.findViewById(R.id.ll_title_right);
//        View rightBtnChatView = LayoutInflater.from(ctx).inflate(R.layout.layout_title_right_chat, rightRootView, false);
//        if (rightBtnChatView != null) {
//            TextView tvChatSum = (TextView) rightBtnChatView.findViewById(R.id.tvTitleRightChat);
//            tvChatSum.setVisibility(View.GONE);
//            ImageView ivChat = (ImageView) getTitleView(rightBtnChatView, R.id.ivTitleRightChat);
//            ivChat.setImageResource(R.drawable.btn_title_chat_empty);
//            if (onClickListener != null) {
//                rightBtnChatView.setOnClickListener(onClickListener);
//            }
//
////            setRightLeftMargin4Language(ctx, rightBtnChatView);
//
//            rightRootView.addView(rightBtnChatView);
//        }
//    }

    /**
     * 改变购物车图片
     *
     * @param rootView 根视图
     * @param sbCount  购物车数量
     */
//    private static void changeShoppingBagImg(View rootView, int sbCount) {
//        ImageView ivShoppingBag = (ImageView) getTitleView(rootView, R.id.ivTitleRightSb);
//
//        if (ivShoppingBag != null) {
//            ivShoppingBag.setImageResource((sbCount > 0) ? R.drawable.btn_title_bag_fill
//                    : R.drawable.btn_title_bag_empty);
//        }
//    }

    /**
     * 改变在线聊天图片
     *
     * @param rootView  根视图
     * @param chatCount 购物车数量
     */
//    private static void changeChatImg(View rootView, int chatCount) {
//        ImageView ivChat = (ImageView) getTitleView(rootView, R.id.ivTitleRightChat);
//
//        if (ivChat != null) {
//            ivChat.setImageResource((chatCount > 0) ? R.drawable.btn_title_chat_fill
//                    : R.drawable.btn_title_chat_empty);
//        }
//    }

    /**
     * 通过父容器Id，获取容器内部的TextView子容器。（目前主要用于右侧普通按钮）
     *
     * @param rootView 主视图
     * @param parentId 父容器ID
     * @return TextView子视图
     */
    public static TextView getChild4ParentView(View rootView, int parentId) {
        ViewGroup viewGroup = (ViewGroup) getTitleView(rootView, parentId);
        if (viewGroup != null) {
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View view = viewGroup.getChildAt(i);
                if (view instanceof TextView) {
                    return (TextView) view;
                }
            }
        }
        return null;
    }

    /**
     * 显示按钮
     *
     * @param context
     * @param id           按钮id
     * @param fragmentView 当前FragmentView
     * @param btnType      按钮类型，值见{@link #BTN_TYPE_IMAGE} 或 {@link #BTN_TYPE_TEXT}
     * @param resIdOrTxt   资源id(int型)或文本(String)
     * @param listener     OnClick监听器
     * @return
     */
    private static TextView showTextViewButton(Context context, int id, View fragmentView, byte btnType, Object resIdOrTxt,
                                               View.OnClickListener listener) {
        fragmentView.findViewById(R.id.rlTitleLeft);

        TextView tv = (TextView) fragmentView.findViewById(id);

        if (tv == null) {
            return null;
        }

        // 设置背景图片 或 文字
        if (btnType == BTN_TYPE_IMAGE) {
            tv.setTextSize(17);
            tv.setBackgroundResource((Integer) resIdOrTxt);

            RelativeLayout.LayoutParams tvLp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            tvLp.gravity = Gravity.CENTER_VERTICAL;

            tv.setLayoutParams(tvLp);
        } else if (btnType == BTN_TYPE_TEXT) {
            if (resIdOrTxt instanceof Integer) {
                tv.setText((Integer) resIdOrTxt);
            } else if (resIdOrTxt instanceof String) {
                tv.setText((String) resIdOrTxt);
            }
            tv.setTextSize(17);
            tv.setTextColor(context.getResources().getColor(R.color.titleText));
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/FZZDXFW.ttf");
            tv.setTypeface(typeface);
            //Bold
            TextPaint tp = tv.getPaint();
            tp.setFakeBoldText(true);
        }

        // 设置监听器
        if (listener != null) {
            tv.setOnClickListener(listener);
        }

        tv.setVisibility(View.VISIBLE);
        return tv;
    }

    /**
     * 显示标题栏购物车数量
     *
     * @param tvSbSum 数字文本视图
     * @param sbCount 数量
     */
    private static void showShoppingBagSum(TextView tvSbSum, int sbCount) {
        if (tvSbSum != null) {
            tvSbSum.setText(sbCount + "");
            tvSbSum.setVisibility((sbCount > 0) ? View.VISIBLE : View.GONE);
        }
    }

    /**
     * 显示标题栏购物车数量
     *
     * @param tvChatSum 数字文本视图
     * @param chatCount 数量
     */
    private static void showChatSum(TextView tvChatSum, int chatCount) {
        if (tvChatSum != null) {
            tvChatSum.setText(chatCount + "");
            tvChatSum.setVisibility((chatCount > 0) ? View.VISIBLE : View.GONE);
        }
    }

    /**
     * 创建标题栏右边普通LinearLayout视图
     *
     * @param ctx             上下文
     * @param resId           视图Id
     * @param btnType         按钮类型，值见{@link #BTN_TYPE_IMAGE} 或 {@link #BTN_TYPE_TEXT}
     * @param resIdOrTxt      资源id(int型)或文本(String)
     * @param onClickListener OnClick监听器
     * @return 普通TextView视图
     */
    private static LinearLayout createRightButtonLinearLayout(Context ctx, int resId, byte btnType, Object resIdOrTxt,
                                                              View.OnClickListener onClickListener) {
        //设置最外层容器，加大点击区域
        LinearLayout linearLayout = new LinearLayout(ctx);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        setRightLeftMargin4anguage(ctx, linearLayout);
//        lp.topMargin = (int) DisplayUtil.dip2Px(ctx, 2f);
        lp.rightMargin = (int) DisplayUtil.dip2Px(ctx, 14f);
        linearLayout.setLayoutParams(lp);
        linearLayout.setId(resId);

        if (onClickListener != null) {
            linearLayout.setOnClickListener(onClickListener);
        }

        TextView tv = createRightButtonTextView(ctx, btnType, resIdOrTxt);

//        if (onClickListener != null) {
//            tv.setOnClickListener(onClickListener);
//        }

        linearLayout.addView(tv);
        return linearLayout;
    }

    /**
     * 创建标题栏右边普通TextView视图
     *
     * @param ctx        上下文
     * @param btnType    按钮类型，值见{@link #BTN_TYPE_IMAGE} 或 {@link #BTN_TYPE_TEXT}
     * @param resIdOrTxt 资源id(int型)或文本(String)
     * @return 普通TextView视图
     */
    private static TextView createRightButtonTextView(Context ctx, byte btnType, Object resIdOrTxt) {
        TextView tv = new TextView(ctx);
        LinearLayout.LayoutParams tvLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvLp.gravity = Gravity.CENTER_VERTICAL;
        tv.setLayoutParams(tvLp);
        // 设置背景图片 或 文字
        if (btnType == BTN_TYPE_IMAGE) {
            tv.setTextSize(14);
            tv.setBackgroundResource((Integer) resIdOrTxt);
        } else if (btnType == BTN_TYPE_TEXT) {
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
            tv.setTextColor(ctx.getResources().getColor(R.color.vistorTvTitle));
            Typeface typeface = Typeface.createFromAsset(ctx.getAssets(), "fonts/FZDHTFW.ttf");
            tv.setTypeface(typeface);
            if (resIdOrTxt instanceof Integer) {
                tv.setText((Integer) resIdOrTxt);
            } else if (resIdOrTxt instanceof String) {
                tv.setText((String) resIdOrTxt);
            }
        }
        return tv;
    }


//    /**
//     * 根据语言设置动态视图的左右间距
//     *
//     * @param ctx  上下文
//     * @param view 添加的视图
//     */
//    private static void setRightLeftMargin4Language(Context ctx, View view) {
//        if (BusinessCommon.isLanguageArab()) {
//            view.setPadding((int) ctx.getResources().getDimension(R.dimen.dim_title_right_margin), 0, 0, 0);
//        } else {
//            view.setPadding(0, 0, (int) ctx.getResources().getDimension(R.dimen.dim_title_right_margin), 0);
//        }
//    }

    /**
     * 抖动标题栏 购物袋
     *
     * @param rootView 父视图
     * @param ctx      上下文
     */
//    public static void shakeShoppingBag(View rootView, Context ctx) {
//        TextView tvTitleRightSbBtn = (TextView) getTitleView(rootView, R.id.tvTitleRightSb);
//
//        if (tvTitleRightSbBtn != null && ctx != null) {
//            int sbCount = BusinessShoppingBag.getSbCount4Show(ctx);
//            showShoppingBagSum(tvTitleRightSbBtn, sbCount);
//            changeShoppingBagImg(rootView, sbCount);
//
//            ViewParent parent = tvTitleRightSbBtn.getParent();
//            if (parent != null && (parent instanceof View)) {
//                ((View) parent).startAnimation(AnimationUtils.loadAnimation(ctx, R.anim.ani_shake));
//            }
//        }
//    }


}
