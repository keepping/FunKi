package com.hifunki.funki.module.login.entity;

import java.util.List;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.login.entity.City2.java
 * @link
 * @since 2017-05-09 15:12:12
 */
public class City2 {
    private String tag;
    private List<String> country;


    public City2() {
    }

    public City2(String tag, List<String> country) {
        this.tag = tag;
        this.country = country;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<String> getCountry() {
        return country;
    }

    public void setCountry(List<String> country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "City2{" +
                "tag='" + tag + '\'' +
                ", country=" + country +
                '}';
    }
}
