package com.hifunki.funki.widget.bar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

    private TextView tvMenu;
    private ImageView ivMenu;

    private TextView tvMenuMore;
    private ImageView ivMenuMore;

    private Context mContext;
    protected View rootView;

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
        rootView = LayoutInflater.from(context).inflate(R.layout.top_bar, this);
        //根布局
        LinearLayout llBaseMain = (LinearLayout) findViewById(R.id.base_main);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_left = (ImageView) findViewById(R.id.tv_left);

        tvMenu = (TextView) findViewById(R.id.tv_menu);
        ivMenu = (ImageView) findViewById(R.id.iv_menu);

        //最右边的TextView
        tvMenuMore = (TextView) findViewById(R.id.tv_menu_more);
        //最右边的ImageView
        ivMenuMore = (ImageView) findViewById(R.id.iv_menu_more);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TopBarView);
        String titleValue = typedArray.getString(R.styleable.TopBarView_titleText);
        tv_title.setText(titleValue);

        String menuText = typedArray.getString(R.styleable.TopBarView_menuText);
        Drawable rightImage_l = typedArray.getDrawable(R.styleable.TopBarView_menuImage);

        String rightValue_r = typedArray.getString(R.styleable.TopBarView_menuTextMore);
        Drawable rightImage_r = typedArray.getDrawable(R.styleable.TopBarView_menuImageMore);

        boolean hasBg = typedArray.getBoolean(R.styleable.TopBarView_hasBackGround, true);//是否展示background


        if (menuText != null && menuText.length() > 0) {
            tvMenu.setText(menuText);
            tvMenu.setVisibility(View.VISIBLE);
        } else {
            tvMenu.setVisibility(View.GONE);
        }

        if (rightImage_l != null) {
            ivMenu.setImageDrawable(rightImage_l);
            ivMenu.setVisibility(View.VISIBLE);
        } else {
            ivMenu.setVisibility(View.GONE);
        }

        if (rightValue_r != null && rightValue_r.length() > 0) {
            tvMenuMore.setText(rightValue_r);
            tvMenuMore.setVisibility(View.VISIBLE);
        } else {
            tvMenuMore.setVisibility(View.GONE);
        }

        if (rightImage_r != null) {
            ivMenuMore.setImageDrawable(rightImage_r);
            ivMenuMore.setVisibility(View.VISIBLE);
        } else {
            ivMenuMore.setVisibility(View.GONE);
        }


        if (!hasBg) {
            llBaseMain.setBackground(null);
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

    //获取中间的textView
    public TextView getTitileText() {
        return tv_title;
    }

    public TextView getMenuText() {
        return tvMenu;
    }

    public TextView getMenuTextMore() {
        return tvMenuMore;
    }

    public ImageView getIvMenu() {
        return ivMenu;
    }

    //最后边的ImageView
    public ImageView getMenuImageMore() {
        return ivMenuMore;
    }


}

