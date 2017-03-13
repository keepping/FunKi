package com.hifunki.funki.module.search.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.search.entity.LiveEntity.java
 * @link
 * @since 2017-03-13 10:09:09
 */
public class LiveEntity implements MultiItemEntity {

    private int itemType;

    public static final int VIP_LIVE = 1;
    public static final int LEVEL_LIVE = 2;
    public static final int NORMAL_LIVE = 3;
    public static final int INVITE_LIVE = 4;

    private String photo;
    private String username;
    private String location;
    private String language;
    private int count;
    private String welcome;
    private String signature;
    private String signature2;
    private String imagePath;
    //门票直播
    private boolean isLive;
    private int noVipPrice;
    private int perCent;
    private int vipPrice;
    //等级直播
    private int level;
    //普通直播
    private boolean isNormalLive;
    //邀请直播
    private boolean isInviteLive;

    public LiveEntity(int itemType, String photo, String username, String location, String language, int count, String welcome, String signature, String signature2, String imagePath, boolean isLive, int noVipPrice, int perCent, int vipPrice, int level, boolean isNormalLive, boolean isInviteLive) {
        this.itemType = itemType;
        this.photo = photo;
        this.username = username;
        this.location = location;
        this.language = language;
        this.count = count;
        this.welcome = welcome;
        this.signature = signature;
        this.signature2 = signature2;
        this.imagePath = imagePath;
        this.isLive = isLive;
        this.noVipPrice = noVipPrice;
        this.perCent = perCent;
        this.vipPrice = vipPrice;
        this.level = level;
        this.isNormalLive = isNormalLive;
        this.isInviteLive = isInviteLive;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public static int getVipLive() {
        return VIP_LIVE;
    }

    public static int getLevelLive() {
        return LEVEL_LIVE;
    }

    public static int getNormalLive() {
        return NORMAL_LIVE;
    }

    public static int getInviteLive() {
        return INVITE_LIVE;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getWelcome() {
        return welcome;
    }

    public void setWelcome(String welcome) {
        this.welcome = welcome;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSignature2() {
        return signature2;
    }

    public void setSignature2(String signature2) {
        this.signature2 = signature2;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public int getNoVipPrice() {
        return noVipPrice;
    }

    public void setNoVipPrice(int noVipPrice) {
        this.noVipPrice = noVipPrice;
    }

    public int getPerCent() {
        return perCent;
    }

    public void setPerCent(int perCent) {
        this.perCent = perCent;
    }

    public int getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(int vipPrice) {
        this.vipPrice = vipPrice;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isNormalLive() {
        return isNormalLive;
    }

    public void setNormalLive(boolean normalLive) {
        isNormalLive = normalLive;
    }

    public boolean isInviteLive() {
        return isInviteLive;
    }

    public void setInviteLive(boolean inviteLive) {
        isInviteLive = inviteLive;
    }

    @Override
    public String toString() {
        return "LiveEntity{" +
                "itemType=" + itemType +
                ", photo='" + photo + '\'' +
                ", username='" + username + '\'' +
                ", location='" + location + '\'' +
                ", language='" + language + '\'' +
                ", count=" + count +
                ", welcome='" + welcome + '\'' +
                ", signature='" + signature + '\'' +
                ", signature2='" + signature2 + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", isLive=" + isLive +
                ", noVipPrice=" + noVipPrice +
                ", perCent=" + perCent +
                ", vipPrice=" + vipPrice +
                ", level=" + level +
                ", isNormalLive=" + isNormalLive +
                ", isInviteLive=" + isInviteLive +
                '}';
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
