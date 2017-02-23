package com.hifunki.funki.ui.widget.layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.hifunki.funki.R;


/**
 * Created by dell on 2017/2/23.
 */

public class LayoutLoginWithType extends LinearLayout {

    private Context mContext;

    public LayoutLoginWithType(Context context, int type) {
        super(context);
        this.mContext = context;
        View vPhone = LayoutInflater.from(mContext).inflate(R.layout.layout_login_phone, this);

    }
}
