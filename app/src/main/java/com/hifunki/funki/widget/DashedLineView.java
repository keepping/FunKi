package com.hifunki.funki.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hifunki.funki.util.DisplayUtil;

import com.hifunki.funki.R;

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

    private final int lineColor;
    private final float dashWidth;
    private final float lingHeight;
    private final float lineWidth;
    private Path mPath;
    private Paint mPaint;
    private int widthSize;
    private int heightSize;


    public DashedLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.deshedLine);
        lineColor = typedArray.getColor(R.styleable.deshedLine_lineColor, context.getResources().getColor(R.color.loginTvUnClick));
        //px
        dashWidth = typedArray.getDimension(R.styleable.deshedLine_dashWidth, DisplayUtil.dip2Px(context,2));//间距
        //px
        lingHeight = typedArray.getDimension(R.styleable.deshedLine_lineHeight, DisplayUtil.dip2Px(context,1));//线高度
        //px
        lineWidth = typedArray.getDimension(R.styleable.deshedLine_lineWidth, DisplayUtil.dip2Px(context,4));//线宽度
        typedArray.recycle();

        initView();
    }

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
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawHorizontalLine(canvas);
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
