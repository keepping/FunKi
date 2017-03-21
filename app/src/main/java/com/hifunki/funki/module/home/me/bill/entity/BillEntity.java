package com.hifunki.funki.module.home.me.bill.entity;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.home.me.bill.entity.BillEntity.java
 * @link
 * @since 2017-03-20 18:53:53
 */
public class BillEntity  {
    private String date;
    private String time;
    private int gold;
    private String type;

    public BillEntity(String date, String time, int gold, String type) {
        this.date = date;
        this.time = time;
        this.gold = gold;
        this.type = type;
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
                "date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", gold=" + gold +
                ", type='" + type + '\'' +
                '}';
    }
}
