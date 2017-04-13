package com.hifunki.funki.widget.pick;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.Scroller;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by MT3020 on 2015/12/17.
 */
public class TextPicker<T> extends View {              //加入点击

    public TextPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // 数据集合
    private List<T> mDataList;
    private int[] mLocation ;
    private int mInitSelect;
    private String mFieldName;


    //触摸滚动相关
    private float origRawY;
    private float origScrollY;

    //展示选项相关
    private int mCanvasHei = 0;
    private int mCanvasWid = 0;
    private int mItemHei;

    private int mShowItemNumber = 6;
    private int mTextSize;

    private Scroller mScroller;
    private VelocityTracker mVelocityTracker;
    private TextPaint mTextPaint;

    private T mSelectItemObject;
    private ISelectListener mSelectListener;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        setClickable(true);

        mVelocityTracker = VelocityTracker.obtain();
        mScroller = new Scroller(getContext());
        mTextPaint = new TextPaint();
//        mBluePaint = new Paint();
//        mLinePaint = new Paint();
//        mShaderPaint = new Paint();
        mTextSize = sp2px(getContext(),20);
    }

//    RectF mShaderRectF;
//    RectF mBlueRectF;


//    Paint mShaderPaint;
//    Paint mBluePaint;
//    Paint mLinePaint;

    private int mLineTop;
    private int mLineBottom;

    // 处理点击
    private float lastActionUpX;
    private float lastActionUpY;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int measureHei = mShowItemNumber * 2 * mTextSize;
        setMeasuredDimension(getMeasuredWidth(),measureHei);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w, h, oldW, oldH);

        mCanvasHei = h;
        mCanvasWid = w;
        Log.e("test", "onSizeChanged: "+mCanvasHei+"mCanvasHei"+mCanvasWid );
        // 阴影
//        Shader mShader = new LinearGradient(0,0,0,h,new int[]{0xccffffff,0x22ffffff,0x22ffffff,0xccffffff},new float[]{ 0,0.44f,0.56f,1 }, Shader.TileMode.CLAMP);
//
//        mShaderPaint.setShader(mShader);
//        mShaderRectF = new RectF(0,0,w,h);

//        mBluePaint.setColor(0x094f8b8a);

//        mLinePaint . setColor(0x66000000);
//        mLinePaint . setStrokeWidth(2);

        resetState();

    }

    private void resetState(){
        mScroller.abortAnimation();

        mItemHei = mCanvasHei / mShowItemNumber;


        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(0xff222222);
        mTextPaint.setAntiAlias(true);

        //双线
        mLineTop = mItemHei*(mShowItemNumber/2);
        mLineBottom = mLineTop +mItemHei;
//        mBlueRectF = new RectF(0,mLineTop,mCanvasWid,mLineBottom);

        int firstLocation = -mShowItemNumber /2* mItemHei;

        if(mDataList!=null && mDataList.size()!=0){
            mLocation = new int[mDataList.size()];
            for(int i=0;i< mLocation.length;i++){
                mLocation[i]=firstLocation + i* mItemHei;
            }
        }else {
            mLocation = null;
        }



        if(mLocation!=null && mInitSelect>=mLocation.length){
            mInitSelect = mLocation.length-1;
        }

        if(mInitSelect<0){
            mInitSelect=0;
        }


        attemptInvokeStateChangeListener(mInitSelect);

        if(mCanvasHei != 0 && mLocation!=null && mLocation.length>0){
            scrollTo(0,mLocation[mInitSelect]);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mDataList !=null && mDataList.size()!=0){
            for(int i= 0;i< mDataList.size();i++){

                if(mDataList.get(i)==null)   continue;

                String text = getValueByFieldName(i);

                float textTransX = mCanvasWid /2 - mTextPaint.measureText(text)/2;
                float textTransY = i* mItemHei + mItemHei /2 + mTextSize / 2.7f;

                canvas.save();
                canvas.translate(textTransX, textTransY);
                canvas.drawText(text, 0, 0,mTextPaint);
                canvas.restore();
            }

        }


        canvas.save();
        canvas.translate(0, getScrollY());

        //大影印
//        canvas.drawRect(mShaderRectF, mShaderPaint);

        //蓝色部分
//        canvas.drawRect(mBlueRectF,mBluePaint);

        // 双线
//        canvas.drawLine(0, mLineTop,mCanvasWid, mLineTop,mLinePaint);
//        canvas.drawLine(0, mLineBottom,mCanvasWid, mLineBottom,mLinePaint);

        canvas.restore();
    }

    private String getValueByFieldName(int index){
        Object object =mDataList.get(index);
        String value = "";
        if(object==null)  return value;
        if(TextUtils.isEmpty(mFieldName)) return object.toString();

        Class targetClass = object.getClass();
        try {
            Field field = targetClass.getDeclaredField(mFieldName);
            Object targetValue = field.get(object);
            if(targetValue!=null)
                value = targetValue.toString();
        }catch (Exception e){
            e.printStackTrace();;
        }

        return value;
    }


    @Override
    public boolean performClick() {
        return performItemSelect();
    }

    private boolean performItemSelect(){
        if(lastActionUpY<(mCanvasHei+mItemHei)/2 && lastActionUpY> (mCanvasHei-mItemHei)/2){
            return super.performClick();                                //中心位置点击
        }else {


        }

        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        super.dispatchTouchEvent(event);

        if(mLocation==null || mLocation.length==0) return false;

        mVelocityTracker.addMovement(event);

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(mScroller !=null){
                    mScroller.abortAnimation();
                }
                origRawY = event.getY();
                origScrollY = getScrollY();

                break;
            case MotionEvent.ACTION_MOVE:
                this.scrollTo(0, (int)(origScrollY+origRawY - event.getY()));

                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                lastActionUpX = event.getX();
                lastActionUpY = event.getY();

                startScroller();
                mVelocityTracker.clear();
                break;
        }

        return true;
    }

    private void startScroller(){
        mVelocityTracker.computeCurrentVelocity(1000);
        float speed = - mVelocityTracker.getYVelocity();

        mScroller.abortAnimation();

        int targetScrollY =(int)( speed * 0.000035f * Math.abs(speed) )+getScrollY();            //移动最终地点
        targetScrollY = mLocation[binarySearch(targetScrollY)];

        int diffY = targetScrollY-getScrollY();                                                  //移动差值
        int durTime = (speed/ mItemHei <50f) ? 650: (int) Math.abs( diffY * 4000f / speed );     //低速状态      可以优化
        mScroller.startScroll(0, getScrollY(), 0, diffY, durTime);
        invalidate();         // 触发滚动

    }

    private int binarySearch( int target) {                                                      //查找 最近位置
        if(mLocation==null || mLocation.length==0)  return -1;

        //--------------------- 2分法 获得 不小于des 的最近值 ------------------------//
        int low = 0;
        int high = mLocation.length - 1;

        while ((low <= high) && (low <= mLocation.length - 1) && (high <= mLocation.length - 1)) {
            int middle = low + ((high - low) >> 1);
            if (target == mLocation[middle]) {
                low = middle;
                break;
            } else if (target < mLocation[middle]) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }
        //---------------------------------------------------------------------------//

        if(low == mLocation.length){
            return mLocation.length-1;
        }else if(low == 0){
            return 0;
        }else if(target== mLocation[low]){
            return low;
        } else {
            int sm = Math.abs(mLocation[low-1]-target);
            int bg = Math.abs(mLocation[low]-target);
            if(sm<bg){
                return low-1;
            }else {
                return low;
            }
        }
    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
        int scrollY = getScrollY();
        int select = binarySearch(scrollY);
        attemptInvokeStateChangeListener(select);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller !=null&& mScroller.computeScrollOffset()){
            int yy = getScrollY();
            int aa = mScroller.getCurrY();
            if(aa==yy){

                invalidate();   //强行保持刷新状态
            }else {
                scrollTo(0, aa);
            }
        }
    }

    private void attemptInvokeStateChangeListener(int select){

        T object = null;
        if(mDataList!=null && select>=0&&select<mDataList.size()){
            object = mDataList.get(select);
        }

        if(mSelectItemObject!=object){
            mSelectItemObject = object;
            if(mSelectListener!=null){
                mSelectListener.onSelectItemChange(this, select, mSelectItemObject);
            }
        }
    }

    private int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    //----------------------------------------------------ITextPickImp----------------------------------------//

    public int getSelectItemIndex() {
        return mDataList==null? -1 : mDataList.indexOf(mSelectItemObject);
    }

    // 得到选择object
    public T getSelectItemData() {
        return mSelectItemObject;
    }

    // 设置选择object
    public TextPicker setItemSelect(T data) {
        if(mDataList!=null&&mDataList.contains(data)){
            int index = mDataList.indexOf(data);
            setItemSelect(index);
            invalidate();
        }
        return this;
    }

    // 设置选择item
    public TextPicker setItemSelect(int index) {
        if(mLocation!=null && mLocation.length>index && index>=0){
            mInitSelect = index;
            resetState();
        }

        invalidate();
        return this;
    }

    /**
     *
     * @param dataList                 //数据源
     * @param fieldName                用于展示的字段名(通过反射获取值)  null 则取T.toString();
     * @param defaultSelectIndex
     * @return
     */
    public TextPicker setDataList(List<T> dataList , String fieldName, int defaultSelectIndex){
        this.mDataList = dataList;
        this.mInitSelect = defaultSelectIndex;
        this.mFieldName = fieldName;
        resetState();
        invalidate();
        return this;
    }

    // 设置展示多少行
    public TextPicker setShowItemNumber(int count){
        this.mShowItemNumber = count;
        this.resetState();

        invalidate();
        return this;
    }

    // 设置监听器
    public TextPicker setSelectChangeListener(ISelectListener listener){
        mSelectListener = listener;
        return this;
    }


    // 选择监听器
    public interface ISelectListener {
        void onSelectItemChange(TextPicker<?> textPicker, int selectItem, Object object);
    }



}












