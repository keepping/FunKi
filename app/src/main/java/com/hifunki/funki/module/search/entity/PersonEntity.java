package com.hifunki.funki.module.search.entity;

import java.util.List;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.search.entity.PersonEntity.java
 * @link
 * @since 2017-03-10 14:28:28
 */
public class PersonEntity {
    String photo;//头像
    int liveStatus;//是否直播
    String name;
    int sex;
    int grade;
    String signture;
    List<String> imagePath;

    public PersonEntity(String photo, int liveStatus, String name, int sex, int grade, String signture, List<String> imagePath) {
        this.photo = photo;
        this.liveStatus = liveStatus;
        this.name = name;
        this.sex = sex;
        this.grade = grade;
        this.signture = signture;
        this.imagePath = imagePath;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getLiveStatus() {
        return liveStatus;
    }

    public void setLiveStatus(int liveStatus) {
        this.liveStatus = liveStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getSignture() {
        return signture;
    }

    public void setSignture(String signture) {
        this.signture = signture;
    }

    public List<String> getImagePath() {
        return imagePath;
    }

    public void setImagePath(List<String> imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "PersonEntity{" +
                "photo='" + photo + '\'' +
                ", liveStatus=" + liveStatus +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", grade=" + grade +
                ", signture=" + signture +
                ", imagePath=" + imagePath +
                '}';
    }
}
