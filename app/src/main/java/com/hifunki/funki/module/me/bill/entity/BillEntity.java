package com.hifunki.funki.module.me.bill.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.me.bill.entity.BillEntity.java
 * @link
 * @since 2017-03-20 18:53:53
 */
public class BillEntity implements MultiItemEntity {

    public static final int RECHARGE = 1;

    public static final int RED_PACKET_IN = 2;

    public static final int RED_PACKET_OUT = 3;

    public static final int LIVE_COST = 4;

    public static final int ACTIVE = 5;

    public static final int LIVE_INCOME = 6;

    public static final int PAYPAL = 7;

    private int itemType;
    private String date;
    private String time;
    private int gold;
    private String type;

    public BillEntity(int itemType, String date, String time, int gold, String type) {
        this.itemType = itemType;
        this.date = date;
        this.time = time;
        this.gold = gold;
        this.type = type;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "BillEntity{" +
                "itemType=" + itemType +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", gold=" + gold +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
