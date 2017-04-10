package com.hifunki.funki.module.live.danmu;

/**
 * 在此写用途
 *
 * @author yinhaoxiang
 * @version V1.0 <描述当前版本功能>    由此提供 Behave 控制
 * @value com.hifunki.funki.module.live.danmu.IDanMuData.java
 * @link
 * @since 2017-04-07 10:59:59
 */
public interface IDanMuData {


    boolean canRepeatWith(IDanMuData data);

    IDanMuItemBehave<IDanMuData> getBehave();

}
