package com.hifunki.funki.module.live.danmu.vDanMu;

import com.hifunki.funki.module.live.danmu.vDanMu.DanMuItemGift;
import com.hifunki.funki.module.live.danmu.vDanMu.IDanMuData;
import com.hifunki.funki.module.live.danmu.vDanMu.IDanMuItemBehave;

import java.util.Random;

/**
 * 在此写用途
 *
 * @author yinhaoxiang
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.live.danmu.vDanMu.ModelGift.java
 * @link
 * @since 2017-04-05 15:32:32
 */
public class ModelGift implements IDanMuData{

    private static Random random = new Random(11);

    public ModelGift(){

    }

    public int count = random.nextInt(15);

    public String avatar;


    public String avatarUrl;

    public String contentUri;




    @Override
    public boolean canRepeatWith(IDanMuData data) {
        return false;
    }

    @Override
    public IDanMuItemBehave getBehave() {
        return new DanMuItemGift();
    }



}
