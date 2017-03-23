package com.hifunki.funki.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hifunki.funki.R;
import com.hifunki.funki.util.StatusBarUtil;

import java.lang.reflect.Method;

/**
 * Created by chenzhaohua on 16/3/21.
 */
public class TopBarView extends RelativeLayout {

    private TextView tv_title;

    private ImageView tv_left;

    private TextView tv_right_1;
    private ImageView iv_right_1;

    private TextView tv_right_r;
    private ImageView iv_right_r;

    private Context mContext;

    public TopBarView(Context context) {
        super(context);
    }

    public TopBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView(context, attrs);
    }

    public TopBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView(context, attrs);
    }


    private void initView(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.top_bar, this);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_left = (ImageView) findViewById(R.id.tv_left);

        tv_right_1 = (TextView) findViewById(R.id.tv_right_1);
        iv_right_1 = (ImageView) findViewById(R.id.iv_right_1);

        tv_right_r = (TextView) findViewById(R.id.tv_right);
        //最右边的ImageView
        iv_right_r = (ImageView) findViewById(R.id.iv_right);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TopBarView);
        String titleValue = typedArray.getString(R.styleable.TopBarView_titleText);
        tv_title.setText(titleValue);

        String rightValue_l = typedArray.getString(R.styleable.TopBarView_menuText);
        Drawable rightImage_l = typedArray.getDrawable(R.styleable.TopBarView_menuImage);

        String rightValue_r = typedArray.getString(R.styleable.TopBarView_menuTextMore);
        Drawable rightImage_r = typedArray.getDrawable(R.styleable.TopBarView_menuImageMore);


        if (rightValue_l != null && rightValue_l.length() > 0) {
            tv_right_1.setText(rightValue_l);
            tv_right_1.setVisibility(View.VISIBLE);
        } else {
            tv_right_1.setVisibility(View.GONE);
        }

        if (rightImage_l != null) {
            iv_right_1.setImageDrawable(rightImage_l);
            iv_right_1.setVisibility(View.VISIBLE);
        } else {
            iv_right_1.setVisibility(View.GONE);
        }

        if (rightValue_r != null && rightValue_r.length() > 0) {
            tv_right_r.setText(rightValue_r);
            tv_right_r.setVisibility(View.VISIBLE);
        } else {
            tv_right_r.setVisibility(View.GONE);
        }

        if (rightImage_r != null) {
            iv_right_r.setImageDrawable(rightImage_r);
            iv_right_r.setVisibility(View.VISIBLE);
        } else {
            iv_right_r.setVisibility(View.GONE);
        }


        typedArray.recycle();

        //Back
        tv_left.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                doBack(mContext);
            }
        });

        StatusBarUtil.adjustStatusBarHei(findViewById(R.id.base_main));
    }



    private void doBack(final Context context) {
        Class c = context.getClass();
        try {
            Method method = c.getMethod("onBackPressed");
            method.invoke(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TextView getRightText() {
        return tv_right_1;
    }

    public TextView getRightTextMore() {
        return tv_right_r;
    }

    public ImageView getRightImage() {
        return iv_right_1;
    }

    //最后边的ImageView
    public ImageView getRightImageMore() {
        return iv_right_r;
    }



}

