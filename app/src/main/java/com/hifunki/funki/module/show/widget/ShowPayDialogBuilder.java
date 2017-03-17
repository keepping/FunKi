package com.hifunki.funki.module.show.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.hifunki.funki.R;
import com.hifunki.funki.util.DisplayUtil;


/**
 * 自定义Dialog
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.show.widget.ShowPayDialogBuilder.java
 * @link
 * @since 2017-03-17 14:15:15
 */
public class ShowPayDialogBuilder extends Dialog implements DialogInterface {

    private View mDialogView;

    private static Context mContext;

    private static ShowPayDialogBuilder instance;
    private int measuredHeight;
    private int measuredWidth;

    protected ShowPayDialogBuilder(@NonNull Context context, @StyleRes int themeResId, int res) {
        super(context, themeResId);
        init(context, res);
    }

    protected ShowPayDialogBuilder(@NonNull Context context, @StyleRes int themeResId, View view) {
        super(context, themeResId);
        init(view);
    }

    public static ShowPayDialogBuilder getInstance(Context context, Object obj) {
        if (instance == null || !mContext.equals(context)) {
            synchronized (ShowPayDialogBuilder.class) {
                if (instance == null || !mContext.equals(context)) {
                    if (obj instanceof Integer) {
                        instance = new ShowPayDialogBuilder(context, R.style.show_pay_dialog, (int) obj);
                    } else if (obj instanceof View) {
                        instance = new ShowPayDialogBuilder(context, R.style.show_pay_dialog, (View) obj);
                    }
                }
            }
        }
        mContext = context;
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Window window = getWindow();
//        WindowManager.LayoutParams params = window.getAttributes();
//        params.height = (int) DisplayUtil.dip2Px(mContext, 210);
//        params.width = (int) DisplayUtil.dip2Px(mContext, 231);
//        getWindow().setAttributes(params);
//        window.setWindowAnimations(R.style.show_pay_dialog);

    }

    private void init(View view) {
        mDialogView = view;
    }

    private void init(Context context, int res) {
        mDialogView = View.inflate(context, res, null);
    }

    public void setViewHeight(int measuredWidth, int measuredHeight) {
        this.measuredWidth = measuredWidth;
        this.measuredHeight = measuredHeight;
    }

    /**
     * 这个能替代view
     *
     * @param view
     */
    public void refreshContentView(View view) {
        setContentView(view);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    /**
     * 调用关闭方法
     *
     * @return
     */
    public ShowPayDialogBuilder setDimiss() {
        dismiss();
        return this;
    }

    public void setShowListener(OnShowListener l) {
        this.setOnShowListener(l);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.height = (int) DisplayUtil.dip2Px(mContext, measuredHeight);
        params.width = (int) DisplayUtil.dip2Px(mContext, measuredWidth);
        getWindow().setAttributes(params);
        window.setWindowAnimations(R.style.show_pay_dialog);
        setContentView(mDialogView);

    }

}
