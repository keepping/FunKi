package com.hifunki.funki.module.photo.personal.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.photo.personal.entity.PersonalPhotoEntity.java
 * @link
 * @since 2017-04-13 11:31:31
 */
public class PersonalPhotoEntity implements MultiItemEntity {
    public final static int CAMERA=1;
    public final static int PHOTO=2;

    private  int type;

    private String path;

    public PersonalPhotoEntity(int type, String path) {
        this.type = type;
        this.path = path;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "PersonalPhotoEntity{" +
                "type=" + type +
                ", path='" + path + '\'' +
                '}';
    }

    @Override
    public int getItemType() {
        return type;
    }
}
