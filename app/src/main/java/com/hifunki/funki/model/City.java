package com.hifunki.funki.model;

/**
 * Created by WangTest on 2017/2/25.
 */

public class City {
    private String name;
    private String letter;
    private String pinyin;

    public City(String name, String letter, String pinyin) {
        this.name = name;
        this.letter = letter;
        this.pinyin = pinyin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", letter='" + letter + '\'' +
                ", pinyin='" + pinyin + '\'' +
                '}';
    }
}
