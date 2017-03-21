package com.hifunki.funki.module.fans.entity;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.fans.entity.FansEntity.java
 * @link
 * @since 2017-03-21 12:05:05
 */
public class FansEntity {
    private String name;
    private String photo;
    private int sex;
    private int level;
    private String tag;
    private boolean isFriend;

    public FansEntity(String name, String photo, int sex, int level, String tag, boolean isFriend) {
        this.name = name;
        this.photo = photo;
        this.sex = sex;
        this.level = level;
        this.tag = tag;
        this.isFriend = isFriend;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public boolean isFriend() {
        return isFriend;
    }

    public void setFriend(boolean friend) {
        isFriend = friend;
    }

    @Override
    public String toString() {
        return "FansEntity{" +
                "name='" + name + '\'' +
                ", photo='" + photo + '\'' +
                ", sex=" + sex +
                ", level=" + level +
                ", tag='" + tag + '\'' +
                ", isFriend=" + isFriend +
                '}';
    }
}
