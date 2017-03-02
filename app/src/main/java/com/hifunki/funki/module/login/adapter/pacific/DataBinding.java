/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hifunki.funki.module.login.adapter.pacific;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.StringRes;
import android.util.SparseArray;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Checkable;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Wrapped by ViewHolder
 */
public final class DataBinding {
    /**
     * View SparseArray, to cache views
     */
    private SparseArray<View> views;
    /**
     * item view
     */
    private View itemView;

    public DataBinding(View itemView) {
        this.itemView = itemView;
        this.views = new SparseArray<>();
    }

    /**
     * Find view by id in this itemView
     *
     * @param viewId view id
     * @param <V>    type of view you need
     * @return view object you need
     */
    public <V extends View> V findView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (V) view;
    }

    /**
     * set text for TextView
     *
     * @param viewId TextView id
     * @param value  text
     * @return ViewHolder itself
     */
    public DataBinding setText(int viewId, CharSequence value) {
        TextView view = findView(viewId);
        view.setText(value);
        return this;
    }

    /**
     * set text for TextView
     *
     * @param viewId    TextView id
     * @param stringRes text resource id
     * @return ViewHolder itself
     */
    public DataBinding setText(int viewId, @StringRes int stringRes) {
        TextView view = findView(viewId);
        view.setText(stringRes);
        return this;
    }

    /**
     * set image resource for ImageView
     *
     * @param viewId   ImageView id
     * @param imageRes drawable resource id
     * @return ViewHolder itself
     */
    public DataBinding setImageResource(int viewId, @DrawableRes int imageRes) {
        ImageView view = findView(viewId);
        view.setImageResource(imageRes);
        return this;
    }

    /**
     * set background color for view
     *
     * @param viewId view Id
     * @param color  color int value , note : not color resource id
     * @return ViewHolder itself
     */
    public DataBinding setBackgroundColor(int viewId, @ColorInt int color) {
        View view = findView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    /**
     * set background resource for view
     *
     * @param viewId        view id
     * @param backgroundRes drawable resource id
     * @return ViewHolder itself
     */
    public DataBinding setBackgroundRes(int viewId, @DrawableRes int backgroundRes) {
        View view = findView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    /**
     * set text color for TextView
     *
     * @param viewId    TextView id
     * @param textColor color int value , note : not color resource id
     * @return ViewHolder itself
     */
    public DataBinding setTextColor(int viewId, @ColorInt int textColor) {
        TextView view = findView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    /**
     * set text color for TextView
     *
     * @param viewId       TextView id
     * @param textColorRes color resource id
     * @return ViewHolder itself
     */
    public DataBinding setTextColorRes(int viewId, @ColorRes int textColorRes) {
        TextView view = findView(viewId);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.setTextColor(itemView.getContext().getResources().getColor(textColorRes, null));
        } else {
            view.setTextColor(itemView.getContext().getResources().getColor(textColorRes));
        }
        return this;
    }

    /**
     * set drawable for ImageView
     *
     * @param viewId   ImageView id
     * @param drawable drawable
     * @return ViewHolder itself
     */
    public DataBinding setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = findView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    /**
     * set bitmap for ImageView
     *
     * @param viewId ImageView id
     * @param bitmap bitmap
     * @return ViewHolder itself
     */
    public DataBinding setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = findView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    /**
     * set alpha for view
     *
     * @param viewId view id
     * @param value  alpha value , from 0.0 to 1.0
     * @return ViewHolder itself
     */
    public DataBinding setAlpha(int viewId, @FloatRange(from = 0.0, to = 1.0) float value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            findView(viewId).setAlpha(value);
        } else {
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            findView(viewId).startAnimation(alpha);
        }
        return this;
    }

    /**
     * set visibility for view
     *
     * @param viewId     view id
     * @param visibility View.VISIBLE, View.GONE,View.INVISIBLE
     * @return ViewHolder itself
     */
    public DataBinding setVisible(int viewId, int visibility) {
        View view = findView(viewId);
        view.setVisibility(visibility);
        return this;
    }

    /**
     * set typeFace for TextView
     *
     * @param viewId   view id
     * @param typeface TypeFace
     * @return ViewHolder itself
     */
    public DataBinding setTypeface(int viewId, Typeface typeface) {
        TextView view = findView(viewId);
        view.setTypeface(typeface);
        view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        return this;
    }

    /**
     * set typeFace for TextView
     *
     * @param viewIds  view ids for multiple TextViews
     * @param typeface TypeFace
     * @return ViewHolder itself
     */
    public DataBinding setTypeface(Typeface typeface, int... viewIds) {
        for (int viewId : viewIds) {
            TextView view = findView(viewId);
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }
        return this;
    }

    /**
     * set progress value for Progress
     *
     * @param viewId   Progress view id
     * @param progress value
     * @return ViewHolder itself
     */
    public DataBinding setProgress(int viewId, int progress) {
        ProgressBar view = findView(viewId);
        view.setProgress(progress);
        return this;
    }

    /**
     * set progress value and progress max value for Progress
     *
     * @param viewId   Progress view id
     * @param progress value
     * @param max      max value
     * @return ViewHolder itself
     */
    public DataBinding setProgress(int viewId, int progress, int max) {
        ProgressBar view = findView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    /**
     * set progress max value for Progress
     *
     * @param viewId progress view id
     * @param max    max value
     * @return ViewHolder itself
     */
    public DataBinding setMax(int viewId, int max) {
        ProgressBar view = findView(viewId);
        view.setMax(max);
        return this;
    }

    /**
     * set rating value for RatingBar
     *
     * @param viewId RatingBar view id
     * @param rating value
     * @return ViewHolder itself
     */
    public DataBinding setRating(int viewId, float rating) {
        RatingBar view = findView(viewId);
        view.setRating(rating);
        return this;
    }

    /**
     * set rating value and rating max value for RatingBar
     *
     * @param viewId RatingBar view id
     * @param rating value
     * @param max    max value
     * @return ViewHolder itself
     */
    public DataBinding setRating(int viewId, float rating, int max) {
        RatingBar view = findView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }

    /**
     * set OnClickListener for view
     *
     * @param viewId   view id
     * @param listener listener
     * @return ViewHolder itself
     */
    public DataBinding setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = findView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    /**
     * set OnTouchListener for view
     *
     * @param viewId   view id
     * @param listener listener
     * @return ViewHolder itself
     */
    public DataBinding setOnTouchListener(int viewId, View.OnTouchListener listener) {
        View view = findView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }

    /**
     * set OnLongClickListener for view
     *
     * @param viewId   view id
     * @param listener listener
     * @return ViewHolder itself
     */
    public DataBinding setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        View view = findView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }

    /**
     * set OnCheckedChangeListener for CompoundButton
     *
     * @param viewId   CompoundButton view id
     * @param listener listener
     * @return ViewHolder itself
     */
    public DataBinding setOnCheckedChangeListener(
            int viewId, CompoundButton.OnCheckedChangeListener listener) {
        CompoundButton view = findView(viewId);
        view.setOnCheckedChangeListener(listener);
        return this;
    }

    /**
     * set RadioGroupOnCheckedChangeListener for RadioGroup
     *
     * @param viewId   RadioGroup view id
     * @param listener listener
     * @return ViewHolder itself
     */
    public DataBinding setRadioGroupOnCheckedChangeListener(
            int viewId, RadioGroup.OnCheckedChangeListener listener) {
        RadioGroup view = findView(viewId);
        view.setOnCheckedChangeListener(listener);
        return this;
    }

    /**
     * set tag for view
     *
     * @param viewId view id
     * @param tag    tag value
     * @return ViewHolder itself
     */
    public DataBinding setTag(int viewId, Object tag) {
        View view = findView(viewId);
        view.setTag(tag);
        return this;
    }

    /**
     * set tag for view
     *
     * @param viewId view id
     * @param key    tag key
     * @param tag    tag value
     * @return ViewHolder itself
     */
    public DataBinding setTag(int viewId, int key, Object tag) {
        View view = findView(viewId);
        view.setTag(key, tag);
        return this;
    }

    /**
     * set check states for CheckBox
     *
     * @param viewId  CheckBox id
     * @param checked check state
     * @return ViewHolder itself
     */
    public DataBinding setChecked(int viewId, boolean checked) {
        View view = findView(viewId);
        if (view instanceof CompoundButton) {
            ((CompoundButton) view).setChecked(checked);
        } else if (view instanceof CheckedTextView) {
            ((CheckedTextView) view).setChecked(checked);
        } else {
            ((Checkable) view).setChecked(checked);
        }
        return this;
    }
}
