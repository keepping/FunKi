package com.hifunki.funki.widget.bar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hifunki.funki.R;
import com.hifunki.funki.util.StatusBarUtil;

import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenzhaohua on 16/3/21.
 */
public class TopBarView extends RelativeLayout {

    @BindView(R.id.base_main)
    LinearLayout llBaseMain;
    @BindView(R.id.tv_title)
    TextView tvCenterTitle;
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.tv_left)
    TextView tvLeft;
    @BindView(R.id.tv_menu)
    TextView tvMenu;
    @BindView(R.id.iv_menu)
    ImageView ivMenu;
    @BindView(R.id.tv_menu_more)
    TextView tvMenuMore;
    @BindView(R.id.iv_menu_more)
    ImageView ivMenuMore;

    private Context mContext;
    protected View rootView;
    int mHeight;
    private String TAG = getClass().getSimpleName();

    public TopBarView(Context context) {
        this(context, null);
    }

    public TopBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TopBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        rootView = LayoutInflater.from(context).inflate(R.layout.bar_top, this);
        ButterKnife.bind(this, rootView);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TopBarView);
        String leftText = typedArray.getString(R.styleable.TopBarView_firstText);
        boolean leftImageVisible = typedArray.getBoolean(R.styleable.TopBarView_leftImageVisible, true);
        String titleValue = typedArray.getString(R.styleable.TopBarView_titleText);
        String menuText = typedArray.getString(R.styleable.TopBarView_menuText);
        Drawable rightImage_l = typedArray.getDrawable(R.styleable.TopBarView_menuImage);
        String rightValue_r = typedArray.getString(R.styleable.TopBarView_menuTextMore);
        Drawable rightImage_r = typedArray.getDrawable(R.styleable.TopBarView_menuImageMore);
        boolean hasBg = typedArray.getBoolean(R.styleable.TopBarView_hasBackGround, true);//是否展示background
        if (leftImageVisible) {
            ivLeft.setVisibility(VISIBLE);
        } else {
            ivLeft.setVisibility(INVISIBLE);
        }
        tvLeft.setText(leftText);
        tvCenterTitle.setText(titleValue);

        hideOrShowText(tvLeft, leftText);
        hideOrShowText(tvMenu, menuText);
        hideOrShowText(tvMenuMore, rightValue_r);

        showOrHideImageView(ivMenuMore, rightImage_r);
        showOrHideImageView(ivMenu, rightImage_l);

        if (!hasBg) {
            llBaseMain.setBackground(null);
        }

        typedArray.recycle();
        //Back
        ivLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                doBack(mContext);
            }
        });

        StatusBarUtil.adjustStatusBarHei(findViewById(R.id.base_main));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mHeight = llBaseMain.getMeasuredHeight();
        Log.e(TAG, "onMeasure: " + mHeight);
    }

    private void showOrHideImageView(ImageView imageView, Drawable rightImage_r) {
        if (rightImage_r != null) {
            imageView.setImageDrawable(rightImage_r);
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }
    }

    private void hideOrShowText(TextView textView, String leftText) {
        if (leftText != null && leftText.length() > 0) {
            textView.setText(leftText);
            textView.setVisibility(View.VISIBLE);
        } else {
            textView.setVisibility(View.GONE);
        }
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
        return tvCenterTitle;
    }

    public TextView getMenuText() {
        return tvMenu;
    }

    public TextView getMenuTextMore() {
        return tvMenuMore;
    }

    public ImageView getMenuImage() {
        return ivMenu;
    }

    //最后边的ImageView
    public ImageView getMenuImageMore() {
        return ivMenuMore;
    }

    public TextView getFirstText() {
        return tvLeft;
    }

    public ImageView getFirstImageView() {
        return ivLeft;
    }


}

