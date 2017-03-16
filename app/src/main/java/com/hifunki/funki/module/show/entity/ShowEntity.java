package com.hifunki.funki.module.show.entity;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.show.entity.ShowEntity.java
 * @link
 * @since 2017-03-16 11:58:58
 */
public class ShowEntity {

    private String photo;
    private String tag;
    private String time;
    private String content;

    private boolean isBuy;

    //普通节目
    private boolean isNormalShow;

    //vip节目
    private boolean isVipShow;
    private int vipPrice;
    private int normalPrice;
    private int percent;

    public ShowEntity(String photo, String tag, String time, String content, boolean isBuy, boolean isNormalShow, boolean isVipShow, int vipPrice, int normalPrice, int percent) {
        this.photo = photo;
        this.tag = tag;
        this.time = time;
        this.content = content;
        this.isBuy = isBuy;
        this.isNormalShow = isNormalShow;
        this.isVipShow = isVipShow;
        this.vipPrice = vipPrice;
        this.normalPrice = normalPrice;
        this.percent = percent;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isBuy() {
        return isBuy;
    }

    public void setBuy(boolean buy) {
        isBuy = buy;
    }

    public boolean isNormalShow() {
        return isNormalShow;
    }

    public void setNormalShow(boolean normalShow) {
        isNormalShow = normalShow;
    }

    public boolean isVipShow() {
        return isVipShow;
    }

    public void setVipShow(boolean vipShow) {
        isVipShow = vipShow;
    }

    public int getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(int vipPrice) {
        this.vipPrice = vipPrice;
    }

    public int getNormalPrice() {
        return normalPrice;
    }

    public void setNormalPrice(int normalPrice) {
        this.normalPrice = normalPrice;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    @Override
    public String toString() {
        return "ShowEntity{" +
                "photo='" + photo + '\'' +
                ", tag='" + tag + '\'' +
                ", time='" + time + '\'' +
                ", content='" + content + '\'' +
                ", isBuy=" + isBuy +
                ", isNormalShow=" + isNormalShow +
                ", isVipShow=" + isVipShow +
                ", vipPrice=" + vipPrice +
                ", normalPrice=" + normalPrice +
                ", percent=" + percent +
                '}';
    }
}
