package com.hifunki.funki.widget.bar;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.widget.bar.TopBarEditView.java
 * @link
 * @since 2017-04-25 14:05:05
 */
public class TopBarEditView extends RelativeLayout {

    public TopBarEditView(Context context) {
        this(context,null);
    }

    public TopBarEditView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public TopBarEditView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {

    }

}
