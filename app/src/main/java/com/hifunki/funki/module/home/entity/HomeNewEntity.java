package com.hifunki.funki.module.home.entity;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.home.entity.HomeNewEntity.java
 * @link
 * @since 2017-03-17 09:34:34
 */
public class HomeNewEntity {
    private String imagePath;
    private String location;
    private String language;
    private String name;
    private boolean isLive;

    public HomeNewEntity(String imagePath, String location, String language, String name, boolean isLive) {
        this.imagePath = imagePath;
        this.location = location;
        this.language = language;
        this.name = name;
        this.isLive = isLive;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    @Override
    public String toString() {
        return "HomeNewEntity{" +
                "imagePath='" + imagePath + '\'' +
                ", location='" + location + '\'' +
                ", language='" + language + '\'' +
                ", name='" + name + '\'' +
                ", isLive=" + isLive +
                '}';
    }

}
