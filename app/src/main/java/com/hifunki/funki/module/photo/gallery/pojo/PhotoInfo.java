package com.hifunki.funki.module.photo.gallery.pojo;

/**
 * 图片信息
 * Created by Yancy on 2016/1/27.
 */
public class PhotoInfo {

    public String name;                 // 图片名
    public String path;                 // 图片路径
    public long time;                    // 图片添加时间
    public int size;

    public PhotoInfo(String path, String name, long time,int size) {
        this.path = path;
        this.name = name;
        this.time = time;
        this.size=size;
    }


    @Override
    public String toString() {
        return "PhotoInfo{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", time=" + time +
                ", size=" + size +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        try {
            PhotoInfo other = (PhotoInfo) object;
            return this.path.equalsIgnoreCase(other.path);
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return super.equals(object);
    }
}
