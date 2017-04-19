package com.hifunki.funki.widget.bar;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.hifunki.funki.R;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.widget.bar.TopBarView4Shot.java
 * @link
 * @since 2017-04-13 17:51:51
 */
public class TopBarView4Shot extends TopBarView {

    public TopBarView4Shot(Context context) {
        this(context, null);
    }


    public TopBarView4Shot(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TopBarView4Shot(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (rootView != null) {
            LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.base_main);
            linearLayout.setBackground(null);
            linearLayout.setBackgroundColor(Color.parseColor("#590C001F"));

        }
    }
}
