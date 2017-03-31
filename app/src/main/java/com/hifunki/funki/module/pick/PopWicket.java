package com.hifunki.funki.module.pick;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.FrameLayout;


/**
 * Created by MT3020 on 2016/3/1.
 * // 平滑背景阴影渐变
 * // 内容视图移动动画
 * // Diolog 类似
 * // powWindow Diolog  结合体
 * // 推荐用dialog代替(兼容)
 */
public class PopWicket {

    public enum EnterAnimationDirection {
        LEFT,
        RIGHT,
        BUTTON,
        TOP
    }

    protected Activity mActivity;


    protected DecorView mDecorView;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mWindowParams;
    private FrameLayout.LayoutParams mContentParams;

    protected View mainView;                                                                         //内容视图
    private EnterAnimationDirection mEnterAnimationDirection = EnterAnimationDirection.BUTTON;       //载入动画方向
    private int mAnimationDuration = 300;                                                           //载入动画时间
    private int mShadowColor = 0x77000000;                                                           //影印部分颜色
    private boolean mDismissForKeyBack = true;                                                       //点击返回键是否退出
    private boolean mDismissForShadowClick = true;                                                   //触摸阴影是否退出
    private boolean mDismissForOutSideTouch = true;

    private WicketStateListener mWicketStateListener;
    private InputEventInterruptListener mInputEventInterruptListener;

    public PopWicket(Activity activity) {
        this.mActivity = activity;
        mWindowManager = (WindowManager) mActivity.getSystemService(Context.WINDOW_SERVICE);
    }


    private void ensureDecorViewAndParams() {

        if (mDecorView == null) {
            mDecorView = new DecorView(mActivity);
        }

        if (mWindowParams == null) {
            mWindowParams = new WindowManager.LayoutParams();

            mWindowParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG;

            mWindowParams.format = PixelFormat.RGBA_8888;
            mWindowParams.gravity = Gravity.TOP | Gravity.START;
            mWindowParams.flags = computeFlags(mWindowParams.flags);


            mWindowParams.packageName = mActivity.getPackageName();

            mWindowParams.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
                    | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE;
        }


        if (mContentParams == null) {
            mContentParams = new FrameLayout.LayoutParams(-1, -2);
            mContentParams.gravity = Gravity.BOTTOM;
        }

    }

    private int computeFlags(int curFlags) {
        curFlags &= ~(
                WindowManager.LayoutParams.FLAG_IGNORE_CHEEK_PRESSES |
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE |
                        WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH |
                        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS |
                        WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM |
                        WindowManager.LayoutParams.FLAG_SPLIT_TOUCH);

        curFlags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        curFlags |= WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR;

        curFlags |= WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        curFlags |= WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;

        return curFlags;
    }


    public void update(int x, int y, int width, int height) {                             //更新视图位置 不可用于动画 卡顿严重

        if (!isShow() || mainView == null || mDecorView == null) {
            return;
        }

        boolean force = false;
        if (width >= 0 && mWindowParams.x != x) {
            mWindowParams.x = x;
            force = true;
        }

        if (height >= 0 && mWindowParams.y != y) {
            mWindowParams.y = y;
            force = true;
        }

        if (width >= -2 && mWindowParams.width != width) {
            mWindowParams.width = width;
            force = true;
        }

        if (height >= -2 && mWindowParams.height != height) {
            mWindowParams.height = height;
            force = true;
        }

        if (force) {
            mWindowManager.updateViewLayout(mDecorView, mWindowParams);
        }
    }


    public boolean isShow() {
        return mDecorView.getParent() != null;
    }


    public void setStateListener(WicketStateListener delegate) {
        this.mWicketStateListener = delegate;

    }

    public void setInputEventInteruptListener(InputEventInterruptListener listener) {
        mInputEventInterruptListener = listener;
    }


    // 滚入动画时间  （返回<=0 取消载入动画)
    public void setAnimationDuration(int duration) {
        this.mAnimationDuration = duration;
    }


    // 设置点击影印是否消失
    public void setDismissForShadowClick(boolean dismiss) {
        this.mDismissForShadowClick = dismiss;
    }

    // 设置点击返回键是否消失
    public void setDismissForKeyBack(boolean dismiss) {
        this.mDismissForKeyBack = dismiss;
    }

    // 设置点击decodeView外部是否消失
    public void setDismissForOutSideTouch(boolean dismiss) {
        this.mDismissForOutSideTouch = dismiss;
    }

    // 设置内容视图
    public void setContentView(View contentView) {
        this.mainView = contentView;
    }

    public void setContentView(int resId) {
        this.mainView = mActivity.getLayoutInflater().inflate(resId, null);
    }

    public View getContentView() {
        return mainView;
    }


    // 阴影颜色
    public void setShadowColor(int color) {
        this.mShadowColor = color;
    }

    // 得到内容视图 展示方位
    public void setLayoutGrivaty(int grivaty) {
        ensureDecorViewAndParams();
        mContentParams.gravity = grivaty;
    }

    // 滚入动画方向 （左 下 右 上）
    public void setEnterAnimationDirection(EnterAnimationDirection animationDirection) {
        this.mEnterAnimationDirection = animationDirection;
    }


    // 定位显示
    public void showAtLocation(View view, int wit, int hei, int xOffset, int yOffset) {
        if (mainView == null) {
            throw new RuntimeException("contentView must be Inflate by Method : setContentView()");
        }
        ensureDecorViewAndParams();
        if (mDecorView.getParent() != null) return;                  //视图已经在windowManger 中

        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);

        mWindowParams.token = view.getWindowToken();

        mWindowParams.x = rect.left + xOffset;
        mWindowParams.y = rect.bottom + yOffset;


        mWindowParams.width = wit >= -2 ? wit : -1;
        mWindowParams.height = hei >= -2 ? hei : 700;

        mDecorView.performEnter();
    }


    /**
     * 定位显示
     *
     * @param lineViewUp   //此view的下限为开头
     * @param lineViewDown //此view的下限为结束
     */
    public void showAtLocation(View lineViewUp, View lineViewDown) {
        if (mainView == null) {
            throw new RuntimeException("contentView must be Inflate by Method : setContentView()");
        }

        ensureDecorViewAndParams();
        if (mDecorView.getParent() != null) return;                  //视图已经在windowManger 中

        Rect rect = new Rect();
        lineViewUp.getGlobalVisibleRect(rect);

        mWindowParams.token = lineViewUp.getWindowToken();
        mWindowParams.x = 0;
        mWindowParams.y = rect.bottom;
        mWindowParams.width = -1;

        lineViewDown.getGlobalVisibleRect(rect);
        mWindowParams.height = rect.bottom - mWindowParams.y;

        mDecorView.performEnter();

    }


    // 全局显示
    public void showFullScreen() {
        if (mainView == null) {
            throw new RuntimeException("contentView must be Inflate by Method : setContentView()");
        }
        ensureDecorViewAndParams();
        if (mDecorView.getParent() != null) return;                  //视图已经在windowManger 中

        mWindowParams.width = -1;
        mWindowParams.height = -1;

        mDecorView.performEnter();
    }


    // 退出
    public void hidden() {
        if (mDecorView == null || mDecorView.getParent() == null) return;                  //视图已经被移出
        mDecorView.performExit();

    }


    // 自定义载入动画
    public void performEnterAnim() {
        if (mAnimationDuration <= 0 || mEnterAnimationDirection == null) {
            return;
        }

        int startTransLocation = 0;
        switch (mEnterAnimationDirection) {
            case LEFT:
                startTransLocation -= mainView.getLeft() + mainView.getWidth();
                break;
            case RIGHT:
                startTransLocation = mDecorView.getWidth() - mainView.getRight() + mainView.getWidth();
                break;
            case TOP:
                startTransLocation -= mainView.getTop() + mainView.getHeight();
                break;
            case BUTTON:
                startTransLocation = mDecorView.getHeight() - mainView.getBottom() + mainView.getHeight();
                break;
        }

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(startTransLocation, 0);


        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // contentView 位移
                switch (mEnterAnimationDirection) {
                    case LEFT:
                    case RIGHT:
                        mainView.setTranslationX((Float) animation.getAnimatedValue());
                        break;
                    case TOP:
                    case BUTTON:
                        mainView.setTranslationY((Float) animation.getAnimatedValue());
                        break;
                }
            }
        });

        valueAnimator.setDuration(mAnimationDuration);
        valueAnimator.start();
    }


    // 自定义退出动画
    public void performExitAnim() {
        if (mAnimationDuration <= 0 || mEnterAnimationDirection == null)
            return;

        int startTransLocation = 0;
        switch (mEnterAnimationDirection) {
            case LEFT:
                startTransLocation -= mainView.getLeft() + mainView.getWidth();
                break;
            case RIGHT:
                startTransLocation = mDecorView.getWidth() - mainView.getRight() + mainView.getWidth();
                break;
            case TOP:
                startTransLocation -= mainView.getTop() + mainView.getHeight();
                break;
            case BUTTON:
                startTransLocation = mDecorView.getHeight() - mainView.getBottom() + mainView.getHeight();
                break;
        }

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, startTransLocation);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                switch (mEnterAnimationDirection) {
                    case LEFT:
                    case RIGHT:
                        mainView.setTranslationX((Float) animation.getAnimatedValue());
                        break;
                    case TOP:
                    case BUTTON:
                        mainView.setTranslationY((Float) animation.getAnimatedValue());
                        break;
                }
            }
        });

        valueAnimator.setDuration(mAnimationDuration);
        valueAnimator.start();

    }


    // 内容视图的容器 代理到WindowManager中
    private final class DecorView extends FrameLayout {

        public DecorView(Context context) {
            super(context);

            setClickable(true);
        }

        boolean completeLayout = false;
        boolean completeExit = false;

        private void performEnter() {                                                                              // 展示阴影渐变 展示视图位移
            if (mDecorView.getParent() != null)
                return;                                                              // 视图已经在windowManger 中
            removeAllViews();
            mDecorView.setBackgroundColor(0x00000000);
            getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    if (completeLayout || getWindowToken() == null)
                        return;                                                   //加入延迟键盘退出后启动
                    completeLayout = true;

                    //   背景颜色渐变实现--------------------------------------------------------------------
                    ValueAnimator valueAnimatorEnter = ValueAnimator.ofFloat(100, 0);
                    valueAnimatorEnter.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            // 阴影颜色渐变
                            int shadowColor = mShadowColor;

                            int shadowAlpha = shadowColor >>> 24;
                            shadowAlpha = (int) (shadowAlpha * (1f * animation.getCurrentPlayTime() / animation.getDuration()));
                            shadowAlpha = shadowAlpha << 24;
                            shadowColor = shadowColor & 0x00ffffff;                                                                   // 得到颜色通道
                            shadowColor = shadowColor | shadowAlpha;
                            mDecorView.setBackgroundColor(shadowColor);
                        }
                    });

                    valueAnimatorEnter.setDuration(250);
                    valueAnimatorEnter.start();
                    //    ---------------------------------------------------------------------------------

                    //    自定义载入动画实现------------------------------------------------------------------
                    performEnterAnim();
                    //    ---------------------------------------------------------------------------------


                }
            });


            addView(mainView, mContentParams);
            mWindowManager.addView(this, mWindowParams);

            if (mWicketStateListener != null) {
                mWicketStateListener.onShow();
            }
        }


        public void performExit() {                                                                                          // 展示阴影渐变 && 视图位移
            if (mDecorView.getParent() == null) return;

            if (completeExit) return;
            completeExit = true;

            //   背景颜色渐变实现--------------------------------------------------------------------
            ValueAnimator valueAnimatorExit = ValueAnimator.ofFloat(0, 100);
            valueAnimatorExit.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {


                    int shadowColor = mShadowColor;

                    int shadowAlpha = shadowColor >>> 24;
                    shadowAlpha = (int) (shadowAlpha * (1f * Math.abs(animation.getDuration() - animation.getCurrentPlayTime()) / animation.getDuration()));
                    shadowAlpha = shadowAlpha << 24;

                    shadowColor = shadowColor & 0x00ffffff;                                                                             // 得到颜色通道
                    shadowColor = shadowColor | shadowAlpha;

                    setBackgroundColor(shadowColor);

                }
            });
            valueAnimatorExit.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    if (mDecorView.getParent() == null) return;
                    mWindowManager.removeViewImmediate(mDecorView);
                    if (mWicketStateListener != null) {
                        mWicketStateListener.onDismiss();
                    }
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }
            });

            valueAnimatorExit.setDuration(250);
            valueAnimatorExit.start();
            //    ---------------------------------------------------------------------------------

            //    自定义退出动画实现------------------------------------------------------------------
            performExitAnim();
            //    ---------------------------------------------------------------------------------
        }


        @Override
        public boolean dispatchKeyEvent(KeyEvent event) {

            if (mInputEventInterruptListener != null && mInputEventInterruptListener.onKeyEvent(event)) {
                return true;
            }

            if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                if (mDismissForKeyBack && event.getAction() == KeyEvent.ACTION_UP) {
                    hidden();
                }
                return true;
            }

            return super.dispatchKeyEvent(event);
        }

        @Override
        public boolean dispatchTouchEvent(MotionEvent ev) {

            if (mInputEventInterruptListener != null && mInputEventInterruptListener.onTouchEvent(ev)) {
                return true;
            }

            if ((ev.getAction() == MotionEvent.ACTION_OUTSIDE) && mDismissForOutSideTouch) {
                return true;
            }

            return super.dispatchTouchEvent(ev);
        }

        @Override
        public boolean performClick() {
            if (mDismissForShadowClick) {
                hidden();
            }
            return true;
        }
    }


    public interface WicketStateListener {
        //mPopupWindow 消失触发;
        void onDismiss();

        //mPopupWindow 显示触发
        void onShow();
    }

    public interface InputEventInterruptListener {
        //拦截键盘输入事件
        boolean onKeyEvent(KeyEvent event);

        //拦截触摸事件
        boolean onTouchEvent(MotionEvent event);
    }

}











