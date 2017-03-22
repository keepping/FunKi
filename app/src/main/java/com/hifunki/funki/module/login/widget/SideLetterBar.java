package com.hifunki.funki.module.login.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.hifunki.funki.R;
import com.hifunki.funki.util.DisplayUtil;


public class SideLetterBar extends View {
    private static final String[] b = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private int choose = -1;
    private Paint paint;
    private Paint mPaint;
    private boolean showBg = false;
    private OnLetterChangedListener onLetterChangedListener;
    private TextView overlay;
    private Context context;
    private Bitmap bitmapSearch;
    private float xPos;

    public SideLetterBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        initViews();
    }


    public SideLetterBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initViews();
    }

    public SideLetterBar(Context context) {
        super(context);
        this.context = context;
        initViews();
    }

    private void initViews() {
        paint = new Paint();


        mPaint = new Paint();
        bitmapSearch = BitmapFactory.decodeResource(context.getResources(), R.drawable.iv_search_small);
    }

    /**
     * 设置悬浮的textview
     *
     * @param overlay
     */
    public void setOverlay(TextView overlay) {
        this.overlay = overlay;
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (showBg) {
            canvas.drawColor(Color.TRANSPARENT);
        }

        int height = getHeight();
        int width = getWidth();
        int widthStart = width / 2 - bitmapSearch.getWidth();
        Log.e("test", "onDraw: " + width + "test" + widthStart);


        int singleHeight = height / b.length;
//        int singleHeight = (int) (11+paint.measureText(b[0]));

        Log.e("test", "onDraw:singleHeight+ " + singleHeight);
        for (int i = 0; i < b.length; i++) {
            paint.setColor(getResources().getColor(R.color._230C47));
            //通过sp装换成dp
            int px = DisplayUtil.sp2px(context, 9);
            paint.setTextSize(px);
            paint.setColor(getResources().getColor(R.color.loginTvUnClick));
            paint.setAntiAlias(true);
//            if (i == choose) {
//                paint.setColor(getResources().getColor(R.color.loginTvUnClick));
//                paint.setFakeBoldText(true);
//            }
//                paint.setFakeBoldText(true);  //加粗

            xPos = width / 2 - paint.measureText(b[i]) / 2;
            float yPos = singleHeight * i + singleHeight;
            canvas.drawText(b[i], xPos, yPos, paint);
            paint.reset();
        }

        Rect srcRect = new Rect(0, 0, bitmapSearch.getWidth(), bitmapSearch.getHeight());
        Rect destRect = new Rect((int) xPos, 0, bitmapSearch.getWidth(), bitmapSearch.getHeight());

        canvas.drawBitmap(bitmapSearch, srcRect, destRect, mPaint);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        final float y = event.getY();
        final int oldChoose = choose;
        final OnLetterChangedListener listener = onLetterChangedListener;
        final int c = (int) (y / getHeight() * b.length);

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                showBg = true;
                if (oldChoose != c && listener != null) {
                    if (c >= 0 && c < b.length) {
                        listener.onLetterChanged(b[c]);
                        choose = c;
                        invalidate();
                        if (overlay != null) {
                            overlay.setVisibility(VISIBLE);
                            overlay.setText(b[c]);
                        }
                    }
                }

                break;
            case MotionEvent.ACTION_MOVE:
                if (oldChoose != c && listener != null) {
                    if (c >= 0 && c < b.length) {
                        listener.onLetterChanged(b[c]);
                        choose = c;
                        invalidate();
                        if (overlay != null) {
                            overlay.setVisibility(VISIBLE);
                            overlay.setText(b[c]);
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                showBg = false;
                choose = -1;
                invalidate();
                if (overlay != null) {
                    overlay.setVisibility(GONE);
                }
                break;
        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    public void setOnLetterChangedListener(OnLetterChangedListener onLetterChangedListener) {
        this.onLetterChangedListener = onLetterChangedListener;
    }

    public interface OnLetterChangedListener {
        void onLetterChanged(String letter);
    }

}
