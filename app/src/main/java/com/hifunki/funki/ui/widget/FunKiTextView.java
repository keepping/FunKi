package com.hifunki.funki.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import com.hifunki.funki.R;


/**
 * 带有上下横线的TextView
 */
public class FunKiTextView extends TextView {

    Paint mPaint;
    private boolean isShowLine;
    private int mLineColor;
    private int mLineHeight;
    private int mLocation;
    private final float DEF_LINE_HEIGHT = 1.0f;//线的高度
    private final int DEF_LINE_LOCATION = 2;//线的位置 0 top 1 bottom 2 both

    public FunKiTextView(Context context) {
        super(context);
        init(context, null);
    }

    public FunKiTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.JollyTextView);
            mLineColor = typedArray.getColor(R.styleable.JollyTextView_line_color,R.color.vistorTvbg);
            mLineHeight = (int) typedArray.getDimension(R.styleable.JollyTextView_line_height, DEF_LINE_HEIGHT);
            isShowLine = typedArray.getBoolean(R.styleable.JollyTextView_show_line, false);
            mLocation = typedArray.getInt(R.styleable.JollyTextView_line_location, DEF_LINE_LOCATION);
            typedArray.recycle();
        } else {
            mLineColor = getResources().getColor(R.color.colorAccent);
            mLineHeight = (int) DEF_LINE_HEIGHT;
            isShowLine = false;
            mLocation = DEF_LINE_LOCATION;
        }
        mPaint = new Paint();
        mPaint.setColor(mLineColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isShowLine) {
            if (mLocation == 0) {
                canvas.drawRect(0, 0, getWidth(), mLineHeight, mPaint);
            } else if (mLocation == 1) {
                canvas.drawRect(0, getHeight() - mLineHeight, getWidth(), getHeight(), mPaint);
            } else {
                canvas.drawRect(0, 0, getWidth(), mLineHeight, mPaint);
                canvas.drawRect(0, getHeight() - mLineHeight, getWidth(), getHeight(), mPaint);
            }
        }
    }

    public void toggleLine() {
        isShowLine = !isShowLine;
        invalidate();
    }

    public void showLine() {
        isShowLine = true;
        invalidate();
    }

    public void hideLine() {
        isShowLine = false;
        invalidate();
    }

    public void setLocation(int location) {
        this.mLocation = location;
        invalidate();
    }
}
