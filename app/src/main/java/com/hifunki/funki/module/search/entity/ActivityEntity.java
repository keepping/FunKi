package com.hifunki.funki.module.search.entity;

import java.util.List;

/**
 * Created by WangTest on 2017/3/11.
 */

public class ActivityEntity {
    String activityName;
    String activityImage;

    List<Join> joinList;

    public ActivityEntity(String activityName, String activityImage, List<Join> joinList) {
        this.activityName = activityName;
        this.activityImage = activityImage;
        this.joinList = joinList;
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

    public List<Join> getJoinList() {
        return joinList;
    }

    public void setJoinList(List<Join> joinList) {
        this.joinList = joinList;
    }



    @Override
    public String toString() {
        return "ActivityEntity{" +
                "activityName='" + activityName + '\'' +
                ", activityImage='" + activityImage + '\'' +
                ", joinList=" + joinList +
                '}';
    }
}
