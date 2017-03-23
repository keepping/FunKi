package com.hifunki.funki.module.rank.world.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.rank.world.entity.AnchorEntity.java
 * @link
 * @since 2017-03-15 15:58:58
 */
public class AnchorEntity implements MultiItemEntity {

    private int itemType;

    public static final int FIRST = 1;
    public static final int SECOND = 2;
    public static final int THIRD = 3;
    public static final int NORMAL = 4;

    private int position;
    private String imagePath;
    private String username;
    private int sex;
    private int level;
    private int goldNumber;

    public AnchorEntity(int itemType, int position, String imagePath, String username, int sex, int level, int goldNumber) {
        this.itemType = itemType;
        this.position = position;
        this.imagePath = imagePath;
        this.username = username;
        this.sex = sex;
        this.level = level;
        this.goldNumber = goldNumber;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public static int getFIRST() {
        return FIRST;
    }

    public static int getSECOND() {
        return SECOND;
    }

    public static int getTHIRD() {
        return THIRD;
    }

    public static int getNORMAL() {
        return NORMAL;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getGoldNumber() {
        return goldNumber;
    }

    public void setGoldNumber(int goldNumber) {
        this.goldNumber = goldNumber;
    }

    @Override
    public String toString() {
        return "AnchorEntity{" +
                "itemType=" + itemType +
                ", position=" + position +
                ", imagePath='" + imagePath + '\'' +
                ", username='" + username + '\'' +
                ", sex=" + sex +
                ", level=" + level +
                ", goldNumber=" + goldNumber +
                '}';
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
