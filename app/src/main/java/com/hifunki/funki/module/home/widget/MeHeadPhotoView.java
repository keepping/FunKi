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
 * 个人中心-->星星布局
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
        mPaint.setStrokeWidth(4);//线宽度
        mPaint.setStyle(Paint.Style.STROKE);//实心线
        mPaint.setAntiAlias(true);//消除锯齿
        mPaint.setColor(getResources().getColor(R.color.vistorTvTitle));
        mPath = new Path();

        //第一个点坐标
        float firstPointX = DisplayUtil.dip2Px(getContext(), 96);
        float firstPointY = DisplayUtil.dip2Px(getContext(), 67);
        //第二个点坐标
        float secondPointX = DisplayUtil.dip2Px(getContext(), 21);
        float secondPointY = DisplayUtil.dip2Px(getContext(), 82);
        //第三个点坐标
        float thirdPointX = DisplayUtil.dip2Px(getContext(), 52);
        float thirdPointY = DisplayUtil.dip2Px(getContext(), 25);
        //第四个点坐标
        float fouthPointX = DisplayUtil.dip2Px(getContext(), 122);
        float fouthPointY = DisplayUtil.dip2Px(getContext(), 11);
        //第五个点坐标
        float fifthPointX = DisplayUtil.dip2Px(getContext(), 150);
//        float fifthPointX = DisplayUtil.dip2Px(getContext(), 180);
        float fifthPointY = DisplayUtil.dip2Px(getContext(), 23);

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
