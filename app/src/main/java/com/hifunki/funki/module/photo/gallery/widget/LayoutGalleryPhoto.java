package com.hifunki.funki.module.photo.gallery.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.hifunki.funki.R;
import com.hifunki.funki.module.photo.gallery.entity.PhotoInfo;

import java.util.ArrayList;

import static com.hifunki.funki.R.id.iv_gallery_photo;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.photo.gallery.widget.LayoutGalleryPhoto.java
 * @link
 * @since 2017-03-06 16:34:34
 */
public class LayoutGalleryPhoto extends RelativeLayout {
    private Context mContext;
    private ArrayList<PhotoInfo> mPhotoInfos;
    private int mIndex;

    public LayoutGalleryPhoto(Context context, ArrayList<PhotoInfo> photoInfoList, int index) {
        super(context);
        this.mContext = context;
        this.mPhotoInfos = photoInfoList;
        this.mIndex = index;
        initViews();
    }

    private void initViews() {
        View vGalleryPhoto = LayoutInflater.from(mContext).inflate(R.layout.layout_gallery_photo, this);
        ImageView imageView = (ImageView) vGalleryPhoto.findViewById(iv_gallery_photo);

        Bitmap bitmap = BitmapFactory.decodeFile(mPhotoInfos.get(mIndex).getName());//方形原图

        imageView.setImageDrawable(new BitmapDrawable(getResources(), bitmap));//设置头像

    }
}
