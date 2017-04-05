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
 * @value com.hifunki.funki.module.live.danmu.IDanMuDelegate.java
 * @link
 * @since 2017-04-05 16:46:46
 */
public interface IDanMuDelegate {
    void dropIn();
    void dropOut();
    void onBindData(ModelGift data);
    View getItemView(Context context, ViewGroup group);

}
