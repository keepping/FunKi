package com.hifunki.funki.module.live.danmu;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

/**
 * 在此写用途
 *
 * @author yinhaoxiang
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.live.danmu.IDanMuItemBehave.java
 * @link
 * @since 2017-04-05 16:46:46
 */
public interface IDanMuItemBehave<T> {


    void dropIn();                                        //载入开始
    void onBindData(T data, IDanMuControl group);         //绑定数据
    View getItemView(Context context, ViewGroup group);   //获取View

    int gravity();                                        //屏幕其实放置位置；

    void repeatMore(int count);                           //增加显示时间

}
