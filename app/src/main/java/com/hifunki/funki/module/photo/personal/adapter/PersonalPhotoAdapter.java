package com.hifunki.funki.module.photo.personal.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hifunki.funki.R;
import com.hifunki.funki.module.photo.personal.entity.PersonalPhotoEntity;

import java.util.List;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.photo.personal.adapter.PersonalPhotoAdapter.java
 * @link
 * @since 2017-04-13 11:25:25
 */
public class PersonalPhotoAdapter extends BaseMultiItemQuickAdapter<PersonalPhotoEntity, BaseViewHolder> {

    private String TAG = getClass().getSimpleName();
    private ImageView ivIcon;
    private TextView tv;

    public PersonalPhotoAdapter(List<PersonalPhotoEntity> data) {
        super(data);
        addItemType(PersonalPhotoEntity.CAMERA, R.layout.photo_gallery_item_camera);
        addItemType(PersonalPhotoEntity.PHOTO, R.layout.item_personal_photo);
    }

    @Override
    protected void convert(BaseViewHolder helper, PersonalPhotoEntity item) {
        switch (helper.getItemViewType()) {
            case PersonalPhotoEntity.CAMERA:

                break;
            case PersonalPhotoEntity.PHOTO:
                ivIcon = helper.getView(R.id.iv_personal_photo_icon);
                tv = helper.getView(R.id.tv);
                onSelectAllListener.onSelectAll(ivIcon,tv);

                Glide.with(mContext).load(item.getPath()).into((ImageView) helper.getView(R.id.iv_personal_photo));
                break;
        }
    }


    private OnSelectAllListener onSelectAllListener;

    public interface OnSelectAllListener {
        void onSelectAll(ImageView b,TextView tv);
    }

    public void setSelectAll(OnSelectAllListener onSelectAllListener){
        this.onSelectAllListener=onSelectAllListener;
    }

}
