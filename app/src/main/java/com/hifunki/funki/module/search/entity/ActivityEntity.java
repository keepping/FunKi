package com.hifunki.funki.module.search.entity;

import java.util.List;

/**
 * Created by WangTest on 2017/3/11.
 */

public class ActivityEntity {
    String activityName;
    String activityImage;

    List<JoinEntity> joinEntityList;

    public ActivityEntity(String activityName, String activityImage, List<JoinEntity> joinEntityList) {
        this.activityName = activityName;
        this.activityImage = activityImage;
        this.joinEntityList = joinEntityList;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityImage() {
        return activityImage;
    }

    public void setActivityImage(String activityImage) {
        this.activityImage = activityImage;
    }

    public List<JoinEntity> getJoinEntityList() {
        return joinEntityList;
    }

    public void setJoinEntityList(List<JoinEntity> joinEntityList) {
        this.joinEntityList = joinEntityList;
    }



    @Override
    public String toString() {
        return "ActivityEntity{" +
                "activityName='" + activityName + '\'' +
                ", activityImage='" + activityImage + '\'' +
                ", joinEntityList=" + joinEntityList +
                '}';
    }
}
