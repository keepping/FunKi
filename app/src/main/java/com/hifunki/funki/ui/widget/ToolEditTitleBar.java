package com.hifunki.funki.ui.widget;

import android.content.Context;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.hifunki.funki.R;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.ui.widget.ToolEditTitleBar.java
 * @link
 * @since 2017-02-27 16:11:11
 */
public class ToolEditTitleBar extends ToolTitleBar {

    public static EditText showCenterEditText(Context context, View mainLayout, Object resIdOrTxt,
                                              TextWatcher textWatcher) {
        EditText editText = (EditText) mainLayout.findViewById(R.id.etTitleCenter);
        if (textWatcher != null) {
            editText.addTextChangedListener(textWatcher);
        }

        editText.setVisibility(View.VISIBLE);

        return editText;
    }
//    public static ImageView showRightClose(Context context,View mainLayout,)
}
