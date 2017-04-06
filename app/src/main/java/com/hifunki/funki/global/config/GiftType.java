package com.hifunki.funki.global.config;

import com.hifunki.funki.R;

/**
 * 在此写用途
 *
 * @author yinhaoxiang
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.global.config.GiftType.java
 * @link
 * @since 2017-04-05 18:35:35
 */
public enum GiftType {
    ANGLE(R.drawable.gift_angle,100),
    CANDLE(R.drawable.gift_candle,100);


    public int imageRes;
    public int value;

    GiftType(int res , int value){
        this.imageRes = res;
        this.value = value;
    }



}
