package com.hifunki.funki.module.live.danmu.vDanMu;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

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
public class DanMuGroup extends FrameLayout implements IDanMuControl {
    private class ViewInfo {
        private View target;
        private boolean attach;
        private IDanMuData gift;
        private IDanMuItemBehave delegate;
        private Rect layoutRect;
    }
    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
    private List<Rect> spaceCase = new LinkedList<>();
    private List<ViewInfo> noLayouts = new LinkedList<>();
    private List<ViewInfo> preList = new LinkedList<>();

    private int maxWaitCount = 5;

    private void getFreeSpace() {                                                                      //得到剩余可以展示的空间
        spaceCase.clear();
        noLayouts.clear();

        //未layout时 位置信息错误；

        spaceCase.add(new Rect(0, 0, getWidth(), getHeight()));

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            ViewInfo info = (ViewInfo) child.getTag();
            Rect rect = info.layoutRect;
            if (rect == null) {
                noLayouts.add(info);
                continue;
            }
            Iterator<Rect> tem = spaceCase.iterator();
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
                spaceCase.add(div1);
            }
            if (div2 != null) {
                spaceCase.add(div2);
            }
        }
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

    private Rect copy(Rect rect){
        return new Rect(rect);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            ViewInfo info = (ViewInfo) child.getTag();
            if (info.layoutRect == null) {
                getFreeSpace();
                for (Rect rect : spaceCase) {
                    if (rect.bottom - rect.top >= info.target.getMeasuredHeight()) {
                        Rect tem = copy(rect);
                        tem.top = tem.bottom - info.target.getMeasuredHeight();
                        info.layoutRect = tem;



                    }
                }
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

            if (layoutRect == null) {
                child.layout(right, bottom, right+right, bottom+bottom);
            }else{
                child.layout(layoutRect.left, layoutRect.top, layoutRect.right, layoutRect.bottom);
                if (!info.attach) {
                    info.delegate.dropIn();
                    info.attach = true;
                }
            }
        }
    }



    @Override
    public void removeIDanMuData(final IDanMuData danMuData) {
        post(new Runnable() {
            @Override
            public void run() {
                System.out.println("2222222222222222222222222222222222222  7 "+danMuData);
                for(int i=0;i<getChildCount() ;i ++){
                    View view = getChildAt(i);
                    ViewInfo info  = (ViewInfo) view.getTag();
                    if(info.gift==danMuData){
                        removeView(view);
                        System.out.println("2222222222222222222222222222222222222  8 "+danMuData);
                        break;
                    }
                }
            }
        });
    }

    @Override
    public void addData(IDanMuData data) {

        IDanMuItemBehave<IDanMuData> itemGift = data.getBehave();

        ViewInfo info = new ViewInfo();
        info.target = itemGift.getItemView(getContext(), this);
        info.delegate = itemGift;

        info.gift = data;
        itemGift.onBindData(data,this);


        info.target.setTag(info);

        preList.add(info);

        // 获取未展示 等待的数量
        int waitCount = 0;
        for(int i=0 ;i <getChildCount() ;i ++){
            ViewInfo viewInfo = (ViewInfo)getChildAt(i).getTag();
            if(viewInfo.layoutRect == null) waitCount++;
        }

        for (int i = 0; (i < (maxWaitCount - waitCount)) && preList.size() > 0; i++) {
            ViewInfo remove = preList.remove(0);

            DanMuGroup.super.addView(remove.target, layoutParams);
        }

    }
}


















































