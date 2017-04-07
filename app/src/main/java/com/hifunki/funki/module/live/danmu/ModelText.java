package com.hifunki.funki.module.live.danmu;

/**
 * 在此写用途
 *
 * @author yinhaoxiang
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.live.danmu.ModelText.java
 * @link
 * @since 2017-04-05 16:39:39
 */
public class ModelText implements IDanMuData {




    @Override
    public boolean canRepeatWith(IDanMuData data) {
        return false;
    }

    @Override
    public IDanMuItemBehave getBehave() {
        return null;
    }
}
