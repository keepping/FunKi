package com.hifunki.funki.widget;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 在此写用途
 *
 * @author yinhaoxiang
 * @version V1.0 <描述当前版本功能>    用于支持 浸入式
 * @value com.hifunki.funki.widget.Coordinator.java
 * @link
 * @since 2017-03-16 09:29:29
 */
public class Coordinator extends CoordinatorLayout {
    public Coordinator(Context context) {
        super(context);
    }

    public Coordinator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Coordinator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}
