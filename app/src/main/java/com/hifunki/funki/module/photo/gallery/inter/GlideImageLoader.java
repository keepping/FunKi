package com.hifunki.funki.module.photo.gallery.inter;

import android.app.Activity;
import android.content.Context;

import com.bumptech.glide.Glide;
import com.hifunki.funki.R;
import com.hifunki.funki.module.photo.gallery.widget.GalleryImageView;

/**
 * GlideBannerImageLoader
 * Created by Yancy on 2016/10/28.
 */
public class GlideImageLoader implements ImageLoader {


    @Override
    public void displayImage(Activity activity, Context context, String path, GalleryImageView galleryImageView, int width, int height) {
        Glide.with(context)
                .load(path)
                .placeholder(R.mipmap.ic_launcher)
                .centerCrop()
                .into(galleryImageView);
    }

    @Override
    public void clearMemoryCache() {

    }


}
