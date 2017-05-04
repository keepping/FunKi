package com.hifunki.funki.widget;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by powyin on 2016/10/26.
 */

// 特化支持 帖子 详情页展示
public class MeScrollView extends NestedScrollView {

    public MeScrollView(Context context) {
        super(context);
    }

    public MeScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MeScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(target, dx, dy, consumed);

        if( (dy<0 && target instanceof NestedScrollView) && target.canScrollVertically(dy)  ||
                (dy>0 && target instanceof RecyclerView)){  // target instanceof RecyclerView &&  target.canScrollVertically(dy)
            int unConsumeDy = dy - consumed[1];
            final int oldScrollY = getScrollY();
            scrollBy(0, unConsumeDy);
            final int myConsumed = getScrollY() - oldScrollY;
            consumed[1] = consumed[1] + myConsumed;

        }

    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {

        return super.onNestedFling(target, velocityX, velocityY, consumed);
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        boolean consume = super.onNestedPreFling(target,velocityX,velocityY);
        if(consume ) return true;

        if(target instanceof RecyclerView){
            boolean t1 = velocityY > 0  &&  flingWithNestedDispatch((int) velocityY) ;
            boolean t2 = velocityY < 0  && !target.canScrollVertically((int)velocityY) && flingWithNestedDispatch((int) velocityY);
            return t1 || t2;
        }


        return !target.canScrollVertically((int)velocityY) && flingWithNestedDispatch((int) velocityY) ;

    }

    @Override
    public void dispatchWindowFocusChanged(boolean hasFocus) {
        //super.dispatchWindowFocusChanged(hasFocus);
    }

    @Override
    protected boolean dispatchGenericFocusedEvent(MotionEvent event) {
        return true;
        //return super.dispatchGenericFocusedEvent(event);
    }

    // from NestScrollView Code
    private boolean flingWithNestedDispatch(int velocityY) {
        final int scrollY = getScrollY();
        final boolean canFling = (scrollY > 0 || velocityY > 0)
                && (scrollY < getScrollRange() || velocityY < 0);
        if (canFling) {
            fling(velocityY);
        }
        return canFling;
    }

    // from NestScrollView Code
    int getScrollRange() {
        int scrollRange = 0;
        if (getChildCount() > 0) {
            View child = getChildAt(0);
            scrollRange = Math.max(0,
                    child.getHeight() - (getHeight() - getPaddingBottom() - getPaddingTop()));
        }
        return scrollRange;
    }

}



















