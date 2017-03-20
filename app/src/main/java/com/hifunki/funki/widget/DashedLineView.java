package com.hifunki.funki.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.hifunki.funki.R;
import com.hifunki.funki.util.DisplayUtil;

/**
 * 节目预告页面画虚线
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.widget.DashedLineView.java
 * @link
 * @since 2017-03-17 11:38:38
 */
public class DashedLineView extends View {

    private String TAG="DashedLineView";
    private final int lineColor;
    private final float dashWidth;
    private final float lingHeight;
    private final float lineWidth;
    private Path mPath;
    private Paint mPaint;
    private int widthSize;
    private int heightSize;
    private int dashOrientation;//虚线方向-->横向的为0，竖向的为1

    public DashedLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.deshedLine);
        lineColor = typedArray.getColor(R.styleable.deshedLine_lineColor, context.getResources().getColor(R.color.loginTvUnClick));
        //px
        dashWidth = typedArray.getDimension(R.styleable.deshedLine_dashWidth, DisplayUtil.dip2Px(context,2));//间距
        //px
        lingHeight = typedArray.getDimension(R.styleable.deshedLine_lineHeight, DisplayUtil.dip2Px(context,1));//单条线高度
        //px
        lineWidth = typedArray.getDimension(R.styleable.deshedLine_lineWidth, DisplayUtil.dip2Px(context,4));//单条线宽度
        dashOrientation =typedArray.getInteger(R.styleable.deshedLine_dashOrientation,0);//线宽度-->默认为水平方向
        typedArray.recycle();

        initView();
    }

    /**
     * 初始化画笔
     */
    private void initView() {
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(lineColor);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(lingHeight);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        widthSize = MeasureSpec.getSize(widthMeasureSpec)-getPaddingLeft()-getPaddingRight();
        heightSize = MeasureSpec.getSize(heightMeasureSpec )- getPaddingTop() - getPaddingBottom();
        if(dashOrientation==0){
            setMeasuredDimension(widthSize, (int) lingHeight);
        }else{
            Log.e(TAG, "onMeasure: "+"lingHeight="+lingHeight+"heightSize="+heightSize );
//            setMeasuredDimension((int) lingHeight, heightSize);
            setMeasuredDimension((int) lineWidth, heightSize);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch(dashOrientation){
            case 0:
                drawHorizontalLine(canvas);
                break;
            default:
                drawVerticalLine(canvas);
        }

    }


    /**
     * 画竖直方向虚线
     * @param canvas
     */
    private void drawVerticalLine(Canvas canvas) {
        float totalHeight = 0;
        canvas.save();
        float[] pts = {0,0,0,lingHeight};
        //在画线之前需要先把画布向右平移半个线段高度的位置，目的就是为了防止线段只画出一半的高度
        //因为画线段的起点位置在线段左下角
        canvas.translate(lineWidth/2,0);
        while(totalHeight<=heightSize){
            canvas.drawLines(pts,mPaint);
            canvas.translate(0,lingHeight + dashWidth);
            totalHeight += lingHeight + dashWidth;
        }
        canvas.restore();
    }


    /**
     * 画水平方向虚线
     * @param canvas
     */
    public void drawHorizontalLine(Canvas canvas){
        float totalWidth = 0;
        canvas.save();
        float[] pts = {0,0,lineWidth,0};
        //在画线之前需要先把画布向下平移办个线段高度的位置，目的就是为了防止线段只画出一半的高度
        //因为画线段的起点位置在线段左下角
        canvas.translate(0,lingHeight/2);
        while(totalWidth<=widthSize){
            canvas.drawLines(pts,mPaint);
            canvas.translate(lineWidth + dashWidth,0);
            totalWidth += lineWidth + dashWidth;
        }
        canvas.restore();
    }

}
