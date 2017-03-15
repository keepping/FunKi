package com.hifunki.funki.module.home.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * 首页热门实体类
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.search.entity.HomeHotEntity.java
 * @link
 * @since 2017-03-13 10:09:09
 */
public class HomeHotEntity implements MultiItemEntity {

    private int itemType;
    //普通直播--big
    public static final int NORMAL_LIVE = 1;
    //等级直播--big
    public static final int LEVEL_LIVE = 2;
    //普通视频--big --->only pgc
    public static final int NORMAL_VIDEO = 3;


    //门票直播--small view
    public static final int TICKET_LIVE = 4;
    //门票视频--small --->only pgc
    public static final int TICKET_VIDEO = 5;

    //判断是PGC还是UGC
    private boolean isPAC;

    private String photo;
    private String username;
    private String location;
    private String language;
    private int WatchCount;
    private String welcome;
    private String signature;
    private String signature2;
    private String imagePath;
    //普通直播
    private boolean isNormalLive;
    //等级直播
    private boolean isLevelLive;
    private int level;
    //普通视频
    private boolean isNormalVideo;

    //门票直播
    private boolean isTicketLive;
    private int noVipPrice;
    private int perCent;
    private int vipPrice;
    //门票视频
    private boolean isTicetVideo;

    public HomeHotEntity(int itemType, boolean isPAC, String photo, String username, String location, String language, int watchCount, String welcome, String signature, String signature2, String imagePath, boolean isNormalLive, boolean isLevelLive, int level, boolean isNormalVideo, boolean isTicketLive, int noVipPrice, int perCent, int vipPrice, boolean isTicetVideo) {
        this.itemType = itemType;
        this.isPAC = isPAC;
        this.photo = photo;
        this.username = username;
        this.location = location;
        this.language = language;
        WatchCount = watchCount;
        this.welcome = welcome;
        this.signature = signature;
        this.signature2 = signature2;
        this.imagePath = imagePath;
        this.isNormalLive = isNormalLive;
        this.isLevelLive = isLevelLive;
        this.level = level;
        this.isNormalVideo = isNormalVideo;
        this.isTicketLive = isTicketLive;
        this.noVipPrice = noVipPrice;
        this.perCent = perCent;
        this.vipPrice = vipPrice;
        this.isTicetVideo = isTicetVideo;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public static int getNormalLive() {
        return NORMAL_LIVE;
    }

    public static int getLevelLive() {
        return LEVEL_LIVE;
    }

    public static int getNormalVideo() {
        return NORMAL_VIDEO;
    }

    public boolean isTicetVideo() {
        return isTicetVideo;
    }

    public void setTicetVideo(boolean ticetVideo) {
        isTicetVideo = ticetVideo;
    }

    public static int getTicketLive() {
        return TICKET_LIVE;
    }

    public static int getTicketVideo() {
        return TICKET_VIDEO;
    }

    public boolean isPAC() {
        return isPAC;
    }

    public void setPAC(boolean PAC) {
        isPAC = PAC;
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

    public int getWatchCount() {
        return WatchCount;
    }

    public void setWatchCount(int watchCount) {
        WatchCount = watchCount;
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

    public boolean isNormalLive() {
        return isNormalLive;
    }

    public void setNormalLive(boolean normalLive) {
        isNormalLive = normalLive;
    }

    public boolean isLevelLive() {
        return isLevelLive;
    }

    public void setLevelLive(boolean levelLive) {
        isLevelLive = levelLive;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isNormalVideo() {
        return isNormalVideo;
    }

    public void setNormalVideo(boolean normalVideo) {
        isNormalVideo = normalVideo;
    }

    public boolean isTicketLive() {
        return isTicketLive;
    }

    public void setTicketLive(boolean ticketLive) {
        isTicketLive = ticketLive;
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

    public boolean getIsTicetVideo() {
        return isTicetVideo;
    }

    public void setIsTicetVideo(boolean isTicetVideo) {
        this.isTicetVideo = isTicetVideo;
    }

    @Override
    public String toString() {
        return "HomeHotEntity{" +
                "itemType=" + itemType +
                ", isPAC=" + isPAC +
                ", photo='" + photo + '\'' +
                ", username='" + username + '\'' +
                ", location='" + location + '\'' +
                ", language='" + language + '\'' +
                ", WatchCount=" + WatchCount +
                ", welcome='" + welcome + '\'' +
                ", signature='" + signature + '\'' +
                ", signature2='" + signature2 + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", isNormalLive=" + isNormalLive +
                ", isLevelLive=" + isLevelLive +
                ", level=" + level +
                ", isNormalVideo=" + isNormalVideo +
                ", isTicketLive=" + isTicketLive +
                ", noVipPrice=" + noVipPrice +
                ", perCent=" + perCent +
                ", vipPrice=" + vipPrice +
                ", isTicetVideo=" + isTicetVideo +
                '}';
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
