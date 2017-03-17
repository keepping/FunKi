package com.hifunki.funki.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.util.DialogUtil.java
 * @link
 * @since 2017-03-17 15:44:44
 */
public class DialogUtil {

    /**
     * 显示对话框(View专用)
     * @param ctx
     * @param  res
     * @param posBtnResId
     * @param negaBtnResId
     * @param cancelListener
     * @param posiClickListener
     * @param negaiClickListener
     */
    public static void showDialog(Context ctx, int res, int posBtnResId, int negaBtnResId,
                                  DialogInterface.OnCancelListener cancelListener, DialogInterface.OnClickListener posiClickListener,
                                  DialogInterface.OnClickListener negaiClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        View view = LayoutInflater.from(ctx).inflate(res, null);
        builder.setView(view);

        // 物理返回取消事件
        if (cancelListener != null) {
            builder.setOnCancelListener(cancelListener);
        }

        // 事件
        if (posiClickListener != null) {
            builder.setPositiveButton(posBtnResId, posiClickListener);
        }

        // 事件
        if (negaiClickListener != null) {
            builder.setNegativeButton(negaBtnResId, negaiClickListener);
        }

        builder.show();
    }
}
