package com.hifunki.funki.ui.widget.layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.hifunki.funki.R;


/**
 * Created by dell on 2017/2/23.
 */

public class LayoutTest extends LinearLayout {
    public LayoutTest(Context context) {
        super(context);
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_login_email, this);
//        this.addView(inflate);
    }
}
