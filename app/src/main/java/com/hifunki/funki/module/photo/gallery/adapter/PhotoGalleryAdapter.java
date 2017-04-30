package com.hifunki.funki.module.photo.gallery.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.hifunki.funki.R;
import com.hifunki.funki.module.photo.gallery.config.GalleryConfig;
import com.hifunki.funki.module.photo.gallery.config.GalleryPick;
import com.hifunki.funki.module.photo.gallery.entity.PhotoInfo;
import com.hifunki.funki.module.photo.gallery.widget.GalleryImageView;
import com.hifunki.funki.util.DisplayUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 列表中图片的适配器
 * Created by Yancy on 2016/1/27.
 */
public class PhotoGalleryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private Activity mActivity;
    private LayoutInflater mLayoutInflater;
    private List<PhotoInfo> photoInfoList;                      // 本地照片数据
    private List<String> selectPhoto = new ArrayList<>();                   // 选择的图片路径数据
    private OnCallBack onCallBack;

    private GalleryConfig galleryConfig = GalleryPick.getInstance().getGalleryConfig();

    private final static int HEAD = 0;    // 开启相机时需要显示的布局
    private final static int ITEM = 1;    // 照片布局

    private HashMap<Integer, Boolean> isSelected;
    private final Drawable noSelectDrawable;
    private final Drawable selectDrawable;

    public PhotoGalleryAdapter(Activity mActivity, Context mContext, List<PhotoInfo> photoInfoList, HashMap<Integer, Boolean> isSelected) {
        mLayoutInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.photoInfoList = photoInfoList;
        this.mActivity = mActivity;
        this.isSelected = isSelected;

        selectDrawable = mContext.getResources().getDrawable(R.drawable.iv_photo_select);
        noSelectDrawable = mContext.getResources().getDrawable(R.drawable.iv_photo_noselect);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEAD) {
            return new HeadHolder(mLayoutInflater.inflate(R.layout.photo_gallery_item_camera, parent, false));
        }
        return new ViewHolder(mLayoutInflater.inflate(R.layout.photo_gallery_item_photo, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        // 设置 每个imageView 的大小
        ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();

        //平均分配宽高
        params.height = DisplayUtil.getScreenWidth(mContext) / 3;
        params.width = DisplayUtil.getScreenWidth(mContext) / 3;

        //自定义高度
//        params.height = (int) DisplayUtil.dip2Px(mContext, (float) 111);
//        params.width = (int) DisplayUtil.dip2Px(mContext, (float) 111);

        holder.itemView.setLayoutParams(params);

        if (getItemViewType(position) == HEAD) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCallBack.OnClickCamera(selectPhoto);
                }
            });
            return;
        }

        final PhotoInfo photoInfo;
        if (galleryConfig.isShowCamera()) {
            photoInfo = photoInfoList.get(position - 1);
        } else {
            photoInfo = photoInfoList.get(position);
        }

        final ViewHolder viewHolder = (ViewHolder) holder;

        galleryConfig.getImageLoader().displayImage(mActivity, mContext, photoInfo.path, viewHolder.ivPhotoImage, DisplayUtil.getScreenWidth(mContext) / 3, DisplayUtil.getScreenWidth(mContext) / 3);

        //图片点击
        viewHolder.ivPhotoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!galleryConfig.isMultiSelect()) {
                    selectPhoto.clear();
                    selectPhoto.add(photoInfo.path);
                    onCallBack.OnClickPhoto(selectPhoto);
                    return;
                }

                onCallBack.OnClickPhoto(selectPhoto);
            }
        });


        viewHolder.imageViewSelected.setTag(position);

        if (isSelected.get(position)) {
            viewHolder.imageViewSelected.setImageDrawable(selectDrawable);
        } else {
            viewHolder.imageViewSelected.setImageDrawable(noSelectDrawable);
        }

        viewHolder.imageViewSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer tag = (Integer) v.getTag();
                if (isSelected.get(tag)) {
                    isSelected.put(tag, false);
                    selectPhoto.clear();
                    selectPhoto.add(photoInfo.path);
                    onCallBack.OnNoSelectPhoto(selectPhoto, tag);
                } else {
                    for (int i = 0; i < isSelected.size(); i++) {
                        isSelected.put(i, false);
                        isSelected.put(tag, true);
                    }
                    selectPhoto.clear();
                    selectPhoto.add(photoInfo.path);
                    onCallBack.OnSelectPhoto(selectPhoto, tag);
                }

                notifyDataSetChanged();
            }
        });
    }

    /**
     * 照片的 Holder
     */
    private class ViewHolder extends RecyclerView.ViewHolder {
        private GalleryImageView ivPhotoImage;

        private FrameLayout flGalleryPhoto;
        private ImageView imageViewSelected;

        private ViewHolder(View itemView) {
            super(itemView);
            ivPhotoImage = (GalleryImageView) itemView.findViewById(R.id.ivGalleryPhotoImage);
            imageViewSelected = (ImageView) itemView.findViewById(R.id.iv_gallery_selected);
            flGalleryPhoto = (FrameLayout) itemView.findViewById(R.id.fl_gallery_photo_image);
        }
    }

    /**
     * 相机按钮的 Holder
     */
    private class HeadHolder extends RecyclerView.ViewHolder {
        private HeadHolder(View itemView) {
            super(itemView);
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (galleryConfig.isShowCamera() && position == 0) {
            return HEAD;
        }
        return ITEM;
    }

    @Override
    public int getItemCount() {
        if (galleryConfig.isShowCamera())
            return photoInfoList.size() + 1;
        else
            return photoInfoList.size();
    }

    public interface OnCallBack {
        void OnClickPhoto(List<String> selectPhoto);

        void OnClickCamera(List<String> selectPhoto);

        void OnSelectPhoto(List<String> selectPhoto, int position);//选择了照片的回调

        void OnNoSelectPhoto(List<String> selectPhoto, int position);//没有选中照片回调
    }

    public void setOnCallBack(OnCallBack onCallBack) {
        this.onCallBack = onCallBack;
    }


}
