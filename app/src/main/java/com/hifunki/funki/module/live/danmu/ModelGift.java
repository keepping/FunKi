package com.hifunki.funki.module.live.danmu;

import java.util.Random;

/**
 * 在此写用途
 *
 * @author yinhaoxiang
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.live.danmu.ModelGift.java
 * @link
 * @since 2017-04-05 15:32:32
 */
public class ModelGift {

    private static Random random = new Random(11);

    public ModelGift(){

    }

    public int count = random.nextInt(15);

    public String avatar;


    public String avatarUrl;

    public String contentUri;



}
