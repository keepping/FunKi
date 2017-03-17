package com.hifunki.funki.module.home.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hifunki.funki.R;
import com.hifunki.funki.util.DisplayUtil;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.home.widget.MeHeadPhotoView.java
 * @link
 * @since 2017-03-17 18:30:30
 */
public class MeHeadPhotoView extends View {

    private Paint mPaint;
    private Path mPath;

    public MeHeadPhotoView(Context context) {
        super(context);
        init();
    }


    public MeHeadPhotoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MeHeadPhotoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);//消除锯齿
        mPaint.setColor(getResources().getColor(R.color.vistorTvTitle));
        mPath = new Path();

        //第一个点坐标
        float firstPointX = DisplayUtil.dip2Px(getContext(), 197);
        float firstPointY = DisplayUtil.dip2Px(getContext(), 112);
        //第二个点坐标
        float secondPointX = DisplayUtil.dip2Px(getContext(), 107);
        float secondPointY = DisplayUtil.dip2Px(getContext(), 105);
        //第三个点坐标
        float thirdPointX = DisplayUtil.dip2Px(getContext(), 137);
        float thirdPointY = DisplayUtil.dip2Px(getContext(), 47);
        //第四个点坐标
        float fouthPointX = DisplayUtil.dip2Px(getContext(), 205);
        float fouthPointY = DisplayUtil.dip2Px(getContext(), 30);
        //第五个点坐标
        float fifthPointX = DisplayUtil.dip2Px(getContext(), 228);
        float fifthPointY = DisplayUtil.dip2Px(getContext(), 39);

        mPath.moveTo(firstPointX, firstPointY);
        mPath.lineTo(secondPointX, secondPointY);
        mPath.lineTo(thirdPointX, thirdPointY);
        mPath.lineTo(fouthPointX, fouthPointY);
        mPath.lineTo(fifthPointX, fifthPointY);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
    }
}
