package com.hifunki.funki.util;


import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;

import java.lang.reflect.Field;

/**
 * PopWindowUtil
 */
public class PopWindowUtil {
    //attach location
    public static final int ATTACH_LOCATION_WINDOW = 1;
    public static final int ATTACH_LOCATION_VIEW = 2;

    private PopupWindow mPopupWindow;
    private static PopWindowUtil INSTANCE;

    private PopWindowUtil() {
    }

    private PopWindowUtil(Context context) {
        this.mPopupWindow = new PopupWindow(context);
    }

    public static PopWindowUtil getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new PopWindowUtil(context);
        }
        return INSTANCE;
    }

    /**
     * 调用工具类需要初始化一些信息
     *
     * @param height
     * @param width
     * @return
     */
    public PopWindowUtil init(int height, int width) {
        mPopupWindow.setHeight(height);
        mPopupWindow.setWidth(width);
//		mPopupWindow.setHeight(LayoutParams.WRAP_CONTENT);  
//      mPopupWindow.setWidth(LayoutParams.MATCH_PARENT);  
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
        return this;
    }

    public PopWindowUtil showPopWindow(View contentView, int location, View attachContainer, int x, int y) {
        mPopupWindow.setContentView(contentView);
        switch (location) {
            case ATTACH_LOCATION_WINDOW:
                //we don't use doctorView!
                //issue:http://stackoverflow.com/questions/8782250/popupwindow-badtokenexception-unable-to-add-window-token-null-is-not-valid
                mPopupWindow.showAtLocation(attachContainer == null ? mPopupWindow.getContentView() : attachContainer, x == 0 ? Gravity.BOTTOM : Gravity.NO_GRAVITY, x, y);
                break;
            default:
                mPopupWindow.showAsDropDown(attachContainer, 0, 0);
                break;
        }
        return INSTANCE;
    }
    /**
     * show popwindow
     * @param contentView
     * @param location
     * @param attachContainer
     * @return public PopWindowUtil showPopWindow(View contentView,int location,View attachContainer){
    mPopupWindow.setContentView(contentView);
    mPopupWindow.setHeight(LayoutParams.WRAP_CONTENT);
    mPopupWindow.setWidth(LayoutParams.WRAP_CONTENT);
    mPopupWindow.setFocusable(false);
    mPopupWindow.setOutsideTouchable(true);
    mPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
    switch (location) {
    case ATTACH_LOCATION_WINDOW:
    //we don't use doctorView!
    //issue:http://stackoverflow.com/questions/8782250/popupwindow-badtokenexception-unable-to-add-window-token-null-is-not-valid
    mPopupWindow.showAtLocation(attachContainer == null ? mPopupWindow.getContentView() : attachContainer, Gravity.BOTTOM, 0, 0);
    break;
    default:
    mPopupWindow.showAsDropDown(attachContainer, 0, 0);
    break;
    }
    return INSTANCE;
    }*/
    /**
     * hide pop window
     */
    public void hidePopWindow() {
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
        }
    }

    /**
     * dismiss pop window
     */
    public void dismissPopWindow() {
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
            mPopupWindow = null;
            INSTANCE = null;
        }
    }

    public PopupWindow getPopWindow() {
        return mPopupWindow;
    }

    public void fitPopupWindowOverStatusBar(boolean needFullScreen) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                Field mLayoutInScreen = PopupWindow.class.getDeclaredField("mLayoutInScreen");
                mLayoutInScreen.setAccessible(true);
                mLayoutInScreen.set(mPopupWindow, needFullScreen);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
