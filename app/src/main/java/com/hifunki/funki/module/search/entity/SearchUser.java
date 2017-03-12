package com.hifunki.funki.module.search.entity;

/**
 * Created by WangTest on 2017/3/12.
 */

public class SearchUser {
    private String photo;
    private String nickname;
    private String signture;
    private int sex;
    private int grade;//等级
    private boolean isCare;//关注
    private boolean isSpecialCare;//特殊关注

    public SearchUser(String photo, String nickname, String signture, int sex, int grade, boolean isCare, boolean isSpecialCare) {
        this.photo = photo;
        this.nickname = nickname;
        this.signture = signture;
        this.sex = sex;
        this.grade = grade;
        this.isCare = isCare;
        this.isSpecialCare = isSpecialCare;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSignture() {
        return signture;
    }

    public void setSignture(String signture) {
        this.signture = signture;
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

    public boolean getIsCare() {
        return isCare;
    }

    public void setIsCare(boolean isCare) {
        this.isCare = isCare;
    }

    public boolean getIsSpecialCare() {
        return isSpecialCare;
    }

    public void setIsSpecialCare(boolean isSpecialCare) {
        this.isSpecialCare = isSpecialCare;
    }

    @Override
    public String toString() {
        return "SearchUser{" +
                "photo='" + photo + '\'' +
                ", nickname='" + nickname + '\'' +
                ", signture='" + signture + '\'' +
                ", sex=" + sex +
                ", grade=" + grade +
                ", isCare=" + isCare +
                ", isSpecialCare=" + isSpecialCare +
                '}';
    }
}
