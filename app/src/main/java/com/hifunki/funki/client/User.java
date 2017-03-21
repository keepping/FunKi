package com.hifunki.funki.client;

import java.util.Random;

/**
 * 在此写用途
 *
 * @author yinhaoxiang
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.client.User.java
 * @link
 * @since 2017-03-21 13:10:10
 */
public class User {
    private  static Random  random = new Random(1);


    public static int getAvatarVerficationStatus(){


        return random.nextInt(2);

    }





}
