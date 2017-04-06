package com.hifunki.funki.module.me.profile.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.me.profile.entity.ProfileEntity.java
 * @link
 * @since 2017-03-24 13:38:38
 */
public class ProfileEntity implements MultiItemEntity {

    private int itemType;

    private static final int photo=1;
    private static final int margintop=2;
    private static final int twoWords=3;
    private static final int normal=4;

    private String key;
    private String value;

    public ProfileEntity(int itemType, String key, String value) {
        this.itemType = itemType;
        this.key = key;
        this.value = value;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public static int getPhoto() {
        return photo;
    }

    public static int getNormal() {
        return normal;
    }

    public static int getMargintop() {
        return margintop;
    }

    public static int getTwoWords() {
        return twoWords;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ProfileEntity{" +
                "itemType=" + itemType +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
