package com.hifunki.funki.module.me.recharge.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.me.recharge.entity.RechargeItem.java
 * @link
 * @since 2017-03-21 14:30:30
 */
public class RechargeItem implements MultiItemEntity {

    private int itemType;
    public static final int INPUT = 0;
    public static final int NORMAL = 1;

    private int num;
    private float price;

    public RechargeItem(int itemType, int num, float price) {
        this.itemType = itemType;
        this.num = num;
        this.price = price;
    }

    public static int getINPUT() {
        return INPUT;
    }

    public static int getNORMAL() {
        return NORMAL;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "RechargeItem{" +
                "num=" + num +
                ", price=" + price +
                '}';
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
