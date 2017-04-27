package com.hifunki.funki.animation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Interpolator;

/**
 * Created by powyin on 2017/4/27.
 */

public class TwoInterpolator implements Interpolator {
    public TwoInterpolator() {

    }
    public TwoInterpolator(Context context) {

    }
    public TwoInterpolator(Context context, AttributeSet attrs) {

    }

    @Override
    public float getInterpolation(float input) {
        return input>0.5f ? 0 : 1;
    }
}
