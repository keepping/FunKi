package com.hifunki.funki.module.photo.gallery.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 图片信息
 * Created by Yancy on 2016/1/27.
 */
public class PhotoInfo implements Parcelable {

    public String name;                 // 图片名
    public String path;                 // 图片路径
    public long time;                    // 图片添加时间
    public int size;

    public PhotoInfo(String path, String name, long time, int size) {
        this.path = path;
        this.name = name;
        this.time = time;
        this.size = size;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(path);
        dest.writeLong(time);
        dest.writeInt(size);
    }

    public static final Creator<PhotoInfo> CREATOR = new Creator<PhotoInfo>() {
        @Override
        public PhotoInfo createFromParcel(Parcel in) {
            return new PhotoInfo(in.readString(), in.readString(), in.readLong(), in.readInt());
        }

        @Override
        public PhotoInfo[] newArray(int size) {
            return new PhotoInfo[size];
        }
    };

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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public static Creator<PhotoInfo> getCREATOR() {
        return CREATOR;
    }
}
