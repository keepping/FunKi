package com.hifunki.funki.ui.activity.imageselect;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hifunki.funki.R;
import com.hifunki.funki.util.DisplayUtil;

import java.util.List;

/**
 * 文件夹列表适配器
 * Created by Yancy on 2016/11/1.
 */
public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.ViewHolder> {

    private Context mContext;
    private Activity mActivity;
    private LayoutInflater mLayoutInflater;
    private List<FolderInfo> result;
    private final static String TAG = "FolderAdapter";

    private GalleryConfig galleryConfig = GalleryPick.getInstance().getGalleryConfig();
    private int mSelector = 0;
    private OnClickListener onClickListener;

    public FolderAdapter(Activity mActivity, Context mContext, List<FolderInfo> result) {
        mLayoutInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.mActivity = mActivity;
        this.result = result;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.gallery_item_folder, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (position == 0) {
            String allImageSize = String.format(mContext.getString(R.string.gallery_folder_name), mContext.getString(R.string.gallery_all_folder), getTotalImageSize());
            holder.tvGalleryFolderName.setText(allImageSize);

            if (result.size() > 0) {
                galleryConfig.getImageLoader().displayImage(mActivity, mContext, result.get(0).photoInfo.path, holder.ivGalleryFolderImage, DisplayUtil.getScreenWidth(mContext) / 3, DisplayUtil.getScreenWidth(mContext) / 3);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSelector = 0;
                    onClickListener.onClick(null);
                }
            });

            if (mSelector == 0) {
                holder.ivGalleryFolderbg.setBackgroundColor(mContext.getResources().getColor(R.color.vistorTvbgNormal));
                //设置透明度
                holder.ivGalleryFolderbg.setAlpha((float) 0.9);
            } else {
                holder.ivGalleryFolderbg.setBackgroundColor(mContext.getResources().getColor(R.color.forgetPwdBg));
                holder.ivGalleryFolderbg.setAlpha((float) 0.9);
            }
            return;
        }
        final FolderInfo folderInfo = result.get(position - 1);

        String format = String.format(mContext.getString(R.string.gallery_folder_name), folderInfo.name, folderInfo.photoInfoList.size());
        holder.tvGalleryFolderName.setText(format);
        galleryConfig.getImageLoader().displayImage(mActivity, mContext, folderInfo.photoInfo.path, holder.ivGalleryFolderImage, DisplayUtil.getScreenWidth(mContext) / 3, DisplayUtil.getScreenWidth(mContext) / 3);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelector = holder.getAdapterPosition() + 1;
                onClickListener.onClick(folderInfo);
            }
        });

        if (mSelector == holder.getAdapterPosition() + 1) {
            holder.ivGalleryFolderbg.setBackgroundColor(mContext.getResources().getColor(R.color.vistorTvbgNormal));
            //设置透明度
            holder.ivGalleryFolderbg.setAlpha((float) 0.9);
        } else {
            holder.ivGalleryFolderbg.setBackgroundColor(mContext.getResources().getColor(R.color.forgetPwdBg));
            holder.ivGalleryFolderbg.setAlpha((float) 0.9);
        }
    }

    @Override
    public int getItemCount() {
        return result.size() + 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout ivGalleryFolderbg;
        private GalleryImageView ivGalleryFolderImage;
        private TextView tvGalleryFolderName;

        public ViewHolder(View itemView) {
            super(itemView);
            ivGalleryFolderbg = (RelativeLayout) itemView.findViewById(R.id.ivGalleryFolderbg);
            ivGalleryFolderImage = (GalleryImageView) itemView.findViewById(R.id.ivGalleryFolderImage);
            tvGalleryFolderName = (TextView) itemView.findViewById(R.id.tvGalleryFolderName);

        }
    }

    public interface OnClickListener {
        void onClick(FolderInfo folderInfo);
    }

    /**
     * @return 所有图片数量
     */
    private int getTotalImageSize() {
        int result = 0;
        if (this.result != null && this.result.size() > 0) {
            for (FolderInfo folderInfo : this.result) {
                result += folderInfo.photoInfoList.size();
            }
        }
        return result;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
