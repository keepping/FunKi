package com.hifunki.funki.module.photo.gallery.inter;

import android.app.Activity;
import android.content.Context;

import com.hifunki.funki.module.photo.gallery.widget.GalleryImageView;

import java.io.Serializable;

/**
 * 自定义图片加载框架
 * Created by Yancy on 2016/1/27.
 */
public interface ImageLoader extends Serializable {
    void displayImage(Activity activity, Context context, String path, GalleryImageView galleryImageView, int width, int height);

    void clearMemoryCache();
}
