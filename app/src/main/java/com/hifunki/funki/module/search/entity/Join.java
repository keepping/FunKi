package com.hifunki.funki.module.search.entity;

/**
 * Created by WangTest on 2017/3/11.
 */

public class Join {
    String joinName;
    String joinPhotot;

    public Join(String joinName, String joinPhotot) {
        this.joinName = joinName;
        this.joinPhotot = joinPhotot;
    }

    @Override
    public String toString() {
        return "Join{" +
                "joinName='" + joinName + '\'' +
                ", joinPhotot='" + joinPhotot + '\'' +
                '}';
    }

    public String getJoinName() {
        return joinName;
    }

    public void setJoinName(String joinName) {
        this.joinName = joinName;
    }

    public String getJoinPhotot() {
        return joinPhotot;
    }

    public void setJoinPhotot(String joinPhotot) {
        this.joinPhotot = joinPhotot;
    }
}
