package com.hifunki.funki.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hifunki.funki.R;

/**
 * 个人资料 编辑
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.widget.EditProfileView.java
 * @link
 * @since 2017-03-24 17:46:46
 */
public class EditProfileView extends RelativeLayout {

    private Context mContext;
    private TextView tvProfileLeft;
    private ImageView ivProfileRight;
    private TextView tvProfileRight;

    public EditProfileView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView(context, attrs);
    }

    public EditProfileView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.view_edit_profile, this);
        tvProfileLeft = (TextView) findViewById(R.id.tv_profile_left);
        ivProfileRight = (ImageView) findViewById(R.id.iv_profile_right);
        tvProfileRight = (TextView) findViewById(R.id.tv_profile_right);


        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.EditProfileView);
        String leftText = typedArray.getString(R.styleable.EditProfileView_leftText);
        String rightText = typedArray.getString(R.styleable.EditProfileView_rightText);
        Drawable rightImage = typedArray.getDrawable(R.styleable.EditProfileView_rightImage);

        tvProfileLeft.setText(leftText);
        tvProfileRight.setText(rightText);

        ivProfileRight.setImageDrawable(rightImage);

        typedArray.recycle();
    }

}
