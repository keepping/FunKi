package com.hifunki.funki.module.live.danmu;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 在此写用途
 *
 * @author yinhaoxiang
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.live.danmu.DanMa.java
 * @link
 * @since 2017-04-05 15:27:27
 */
public class DanMuGroup extends FrameLayout {


    private class ViewInfo {
        private View target;
        private boolean anmationIn;
        private boolean anmationOut;
        private IDanMuDelegate delegate;
        private Rect layoutRect;
    }


    public DanMuGroup(@NonNull Context context) {
        super(context);
    }

    public DanMuGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DanMuGroup(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    List<Rect> rects = new LinkedList<>();
    List<ViewInfo> noLayouts = new LinkedList<>();

    private Rect getFreeSpace(ViewInfo taget) {                            //得到剩余可以展示的空间

        rects.clear();
        rects.add(new Rect(0, 0, getWidth(), getHeight()));

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            ViewInfo info = (ViewInfo) child.getTag();
            Rect rect = info.layoutRect;
            if (rect == null) {
                noLayouts.add(info);
                continue;
            }
            Iterator<Rect> tem = rects.iterator();
            Rect div1 = null;
            Rect div2 = null;
            while (tem.hasNext()) {
                Rect current = tem.next();
                if (current.contains(rect)) {
                    tem.remove();
                    div1 = new Rect(current.left, rect.bottom, current.right, current.bottom);
                    div2 = new Rect(current.left, current.top, current.right, rect.top);
                    break;
                }
            }
            if (div1 != null) {
                rects.add(div1);
            }
            if (div2 != null) {
                rects.add(div2);
            }
        }

        for(Rect rect : rects){
            if (rect.bottom - rect.top >= taget.target.getMeasuredHeight()) {
                Rect tem = new Rect(rect);
                tem.top = tem.bottom - taget.target.getMeasuredHeight();
                return tem;
            }
        }

        return null;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            ViewInfo info = (ViewInfo) child.getTag();
            if (info.layoutRect == null) {
                info.layoutRect = getFreeSpace(info);
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        final int count = getChildCount();

        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);

            ViewInfo info = (ViewInfo) child.getTag();
            Rect layoutRect = info.layoutRect;

            if(layoutRect==null) continue;

            child.layout(layoutRect.left, layoutRect.top, layoutRect.right, layoutRect.bottom);
        }

        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            ViewInfo info = (ViewInfo) view.getTag();
            if (!info.anmationIn) {
                info.delegate.dropIn();
                info.anmationIn = true;
            }
        }
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        throw new RuntimeException("noSupport");
    }


    public void addData(ModelGift gift){

        IDanMuDelegate itemGift = new DanMuItemGift();

        ViewInfo info = new ViewInfo();
        info.target = itemGift.getItemView(getContext(),this);
        info.delegate = itemGift;
        itemGift.onBindData(gift);
        info.target.setTag(info);

        super.addView(info.target,-1,new FrameLayout.LayoutParams(-2,-2));
    }



    @Override
    public void removeView(View view) {


        super.removeView(view);
    }

    @Override
    public void removeAllViews() {
        super.removeAllViews();
    }
}


















































