package com.hifunki.funki.widget.bessel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Linhh on 16/3/28.
 */
public class DivergeView3 extends View {

    private final Random mRandom = new Random();

    private ArrayList<DivergeInfo> mDivergeInfos;
    private boolean mIsDiverge = false;

    private PointF mPtStart;
    private PointF mPtEnd;

    private Paint mPaint;

    private static final float mDuration = 0.008F;
    private static final int mDefaultHeight = 100;
//    private static final int mDefaultWidth = 100;
//    private static final int mAlphaOffset = 50;

    private DivergeViewProvider mDivergeViewProvider;

    public DivergeView3(Context context) {
        this(context, null);
    }

    public DivergeView3(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DivergeView3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public interface DivergeViewProvider{
        public Bitmap getBitmap(Object obj);
    }


    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //不需要支持wrap_content

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }

    public void setDivergeViewProvider(DivergeViewProvider divergeViewProvider){
        mDivergeViewProvider = divergeViewProvider;
    }

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };

    public void start(PointF startPoint){
        setStartPoint(startPoint);
        start();
    }

    public PointF getStartPoint(){
        return mPtStart;
    }

    public boolean isRunning(){
        return mIsDiverge;
    }

    public void setDiverges(Object... objs){
        if(mDivergeInfos == null){
            mDivergeInfos = new ArrayList<>();
        }
        for(Object obj : objs){
            mDivergeInfos.add(createDivergeNode(obj));
        }
    }

    public void start(){
        mIsDiverge = true;
        if(mDivergeInfos == null){
            mDivergeInfos = new ArrayList<>();
        }
        this.post(mRunnable);
    }

    public void stop(){
        this.removeCallbacks(mRunnable);
        if(mDivergeInfos != null){
            mDivergeInfos.clear();
        }
        mIsDiverge = false;
    }

    public void release(){
        stop();
        mPtEnd = null;
        mPtStart = null;
        mDivergeInfos = null;
    }

    public void setStartPoint(PointF point){
        mPtStart = point;
    }

    public void setEndPoint(PointF point){
        mPtEnd = point;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(mIsDiverge) {
            release();
        }
    }

    private DivergeInfo createDivergeNode(Object type){
        PointF endPoint = mPtEnd;
        if(endPoint == null){
            endPoint = new PointF(mRandom.nextInt(getMeasuredWidth()),0);
        }
        int height = mDivergeViewProvider == null ? mDefaultHeight : mDivergeViewProvider.getBitmap(type).getHeight();
        if(mPtStart == null) {
            mPtStart = new PointF(getMeasuredWidth() / 2, getMeasuredHeight() - height);//默认起始高度
        }
        return new DivergeInfo(
                mPtStart.x,
                mPtStart.y,
                getBreakPointF(2),
                getBreakPointF(1),
                endPoint,
                type);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(mDivergeViewProvider == null){
            return;
        }
        if(mDivergeInfos == null){
            return;
        }
        if(mIsDiverge){
            for(int i = 0 ; i < mDivergeInfos.size(); i ++){
                DivergeInfo divergeInfo = mDivergeInfos.get(i);
                if(divergeInfo.mY <=  divergeInfo.mEndPoint.y ){
                    mDivergeInfos.remove(i);
                    i--;
                    continue;
                }
                mPaint.setAlpha((int)(255 * divergeInfo.mY / mPtStart.y));
                canvas.drawBitmap(mDivergeViewProvider.getBitmap(divergeInfo.mType), divergeInfo.mX, divergeInfo.mY, mPaint);

                float timeLeft = 1.0F - divergeInfo.mDuration;

                divergeInfo.mDuration += mDuration;

                float x, y;

//                PointF point = new PointF();

                //三次贝塞尔
                float time1 = timeLeft * timeLeft * timeLeft;
                float time2 = 3 * timeLeft * timeLeft * divergeInfo.mDuration;
                float time3 = 3 * timeLeft * divergeInfo.mDuration * divergeInfo.mDuration;
                float time4 = divergeInfo.mDuration * divergeInfo.mDuration * divergeInfo.mDuration;
                x = time1 * (mPtStart.x)
                        + time2 * (divergeInfo.mBreakPoint1.x)
                        + time3 * (divergeInfo.mBreakPoint2.x)
                        + time4 * (divergeInfo.mEndPoint.x);

                divergeInfo.mX = x;

                y = time1 * (mPtStart.y)
                        + time2 * (divergeInfo.mBreakPoint1.y)
                        + time3 * (divergeInfo.mBreakPoint2.y)
                        + time4 * (divergeInfo.mEndPoint.y);

                divergeInfo.mY = y;
            }
            this.post(mRunnable);
        }
    }

    private PointF getBreakPointF(int scale) {

        PointF pointF = new PointF();
        pointF.x = mRandom.nextInt(getMeasuredWidth() - getPaddingRight()) + getPaddingLeft();
        pointF.y = (mRandom.nextInt(getMeasuredHeight() - getPaddingBottom()) + getPaddingTop())/scale;
        return pointF;
    }

    public class DivergeInfo {
        public float mDuration;
        public PointF mBreakPoint1;
        public PointF mBreakPoint2;
        public PointF mEndPoint;
        public float mX;
        public float mY;
        public Object mType;
        public DivergeInfo(float x, float y, PointF breakPoint1, PointF breakPoint2, PointF endPoint, Object type){
            mDuration = 0.0f;
            mEndPoint = endPoint;
            mX = x;
            mY = y;
            mBreakPoint1 = breakPoint1;
            mBreakPoint2 = breakPoint2;
            mType = type;
        }
    }
}
