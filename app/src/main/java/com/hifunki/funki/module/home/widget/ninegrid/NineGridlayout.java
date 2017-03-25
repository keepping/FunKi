package com.hifunki.funki.module.home.widget.ninegrid;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * creator  Lukey on 2016/6/14
 */
public class NineGridlayout extends ViewGroup {

    /**
     *
     * 图片之间的间隔
     */
    private int gap = 5;
    private int columns;//
    private int rows;//
    private List<String> listData;

    public NineGridlayout(Context context) {
        super(context);
    }

    public NineGridlayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    /**
     * 根据图片个数确定行列数量
     * 对应关系如下
     * num	row	column
     * 1	   1	1
     * 2	   1	2
     * 3	   1	3
     * 4	   2	2
     * 5	   2	3
     * 6	   2	3
     * 7	   3	3
     * 8	   3	3
     * 9	   3	3
     *
     * @param length
     */
    private void generateChildrenLayout(int length) {
        if (length <= 3) {
            rows = 1;
            columns = length;
        } else if (length <= 6) {
            rows = 2;
            columns = 3;
            if (length == 4) {
                columns = 2;
            }
        } else {
            rows = 3;
            columns = 3;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int wid = getMeasuredWidth();
        if(listData==null || listData.size() == 0){
            setMeasuredDimension(wid,0);
            return;
        }

        generateChildrenLayout(listData.size());
        int singleWid = (wid-(rows+1)*gap)/rows;
        int measureHei = singleWid*(columns) + (columns+1) * gap;

        int childWid = MeasureSpec.makeMeasureSpec(singleWid,MeasureSpec.EXACTLY);
        for(int i=0 ;i <getChildCount();i++){
            getChildAt(i).measure(childWid,childWid);
        }

        setMeasuredDimension(wid,measureHei);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(rows==0 || columns==0) return;

        int singleWidth = (getWidth() - getPaddingLeft() - getPaddingRight() - gap * (rows+1)) / rows;
        int singleHeight = (getHeight() - getPaddingTop() - getPaddingBottom() - gap*(columns+1)) / columns;

        System.out.println(" cil cou" +getChildCount());


        for (int i = 0; i < getChildCount(); i++) {
            CustomImageView childrenView = (CustomImageView) getChildAt(i);
            childrenView.setImageUrl(listData.get(i));

            int left = getPaddingLeft() + gap + i%rows * (singleWidth+gap);
            int top = getPaddingTop() + gap + i/columns * (singleHeight+gap);

            childrenView.layout(left, top, left+singleWidth, top+singleHeight);
            System.out.println("   " +left +""+top + "  ::   " + (left+singleWidth) + "    ::::     "+ (top+singleHeight));
        }

    }

    public void setImagesData(List<String> lists) {
        listData = lists;

        System.out.println(" size " +lists.size());

        int oldRows = rows;
        int oldColumns = columns;
        if (lists == null || lists.isEmpty()) {
            rows = 0; columns = 0;
        }else {
            generateChildrenLayout(lists.size());
        }

        // adapter size;
        int listCount = lists==null ? 0 : lists.size();
        int needMore = listCount-getChildCount();
        if(needMore>0){
            for(int i = 0 ;i<needMore ; i++){
                addView(generateImageView());
            }
        }
        if(needMore<0){
            for(int i=0;i< -needMore; i++){
                removeViewAt(0);
            }
        }

        if(oldRows!=rows || oldColumns!=columns){
            requestLayout();
        }

        for(int i = 0 ;i <getChildCount();i++){
            CustomImageView iv = (CustomImageView)getChildAt(i);
            iv.setImageUrl(listData.get(i));
        }

    }

    private CustomImageView generateImageView() {
        CustomImageView iv = new CustomImageView(getContext());
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        iv.setBackgroundColor(Color.parseColor("#f5f5f5"));
        return iv;
    }


}
