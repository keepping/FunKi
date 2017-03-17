package com.hifunki.funki.module.home.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * 在此写用途
 *
 * @author yinhaoxiang
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.home.widget.FKVideoView.java
 * @link
 * @since 2017-03-17 13:43:43
 */
public class FKVideoView extends VideoView {

    public FKVideoView(Context context) {
        super(context);
    }

    public FKVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FKVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);

        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                System.out.println(" width " + "UNSPECIFIED" + " :: "+specSize);
                break;
            case MeasureSpec.AT_MOST:
                System.out.println(" width " + "AT_MOST" + " :: "+specSize);
                break;
            case MeasureSpec.EXACTLY:
                System.out.println(" width " + "EXACTLY" + " :: "+specSize);
                break;
        }

        specMode = MeasureSpec.getMode(heightMeasureSpec);
        specSize = MeasureSpec.getSize(heightMeasureSpec);

        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                System.out.println(" hei " + "UNSPECIFIED" + " :: "+specSize);
                break;
            case MeasureSpec.AT_MOST:
                System.out.println(" hei " + "AT_MOST" + " :: "+specSize);
                break;
            case MeasureSpec.EXACTLY:
                System.out.println(" hei " + "EXACTLY" + " :: "+specSize);
                break;
        }


        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
