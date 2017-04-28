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

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.widget.bar.TopBarView4Gallery.java
 * @link
 * @since 2017-04-28 14:43:43
 */
public class TopBarView4Gallery extends TopBarView {

    private final String TAG = getClass().getSimpleName();
    private Context mContext;
    private TextView tvAllPhoto;
    private ImageView ivGalleryicon;

    public TopBarView4Gallery(Context context) {
        this(context, null);
    }

    public TopBarView4Gallery(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TopBarView4Gallery(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.bar_top_gallery, this);
        LinearLayout rlTitleGallery = (LinearLayout) view.findViewById(R.id.rl_title_gallery);
        TopBarView topBarView = (TopBarView) view.findViewById(R.id.tbv_title);
        RelativeLayout rlAllPhoto = (RelativeLayout) view.findViewById(R.id.rl_all_photo);
        tvAllPhoto = (TextView) view.findViewById(R.id.tv_all_photo);
        ivGalleryicon = (ImageView) view.findViewById(R.id.iv_gallery_icon);
        StatusBarUtil.adjustStatusBarHei(rlAllPhoto);
//        rlTitleGallery.getLayoutParams().height=mHeight;
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.TopBarViewGallery);
        String leftText = typedArray.getString(R.styleable.TopBarViewGallery_galleryCenterText);
        Drawable image = typedArray.getDrawable(R.styleable.TopBarViewGallery_galleryCenterImage);
        tvAllPhoto.setText(leftText);
        ivGalleryicon.setImageDrawable(image);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
