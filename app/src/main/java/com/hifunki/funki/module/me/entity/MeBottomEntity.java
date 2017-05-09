package com.hifunki.funki.module.me.entity;

import android.graphics.drawable.Drawable;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.me.entity.MeBottomEntity.java
 * @link
 * @since 2017-05-08 16:28:28
 */
public class MeBottomEntity {
    private String name;
    private Drawable res;

    public MeBottomEntity() {
    }

    public MeBottomEntity(String name, Drawable res) {
        this.name = name;
        this.res = res;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getRes() {
        return res;
    }

    public void setRes(Drawable res) {
        this.res = res;
    }

    @Override
    public String toString() {
        return "MeBottomEntity{" +
                "name='" + name + '\'' +
                ", res=" + res +
                '}';
    }
}
