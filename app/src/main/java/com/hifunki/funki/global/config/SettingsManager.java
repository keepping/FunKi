package com.hifunki.funki.global.config;

import com.hifunki.funki.common.CommonConst;
import com.hifunki.funki.util.LogUtils;

/**
 * Fragment TAG 之类的参数
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.global.config.SettingsManager.java
 * @link
 * @since 2017-03-09 15:09:09
 */

public class SettingsManager {

    /**
     * 开发模式，值为：<br/>
     * 开发：{@link CommonConst#ENVIRONMENT_DEV} <br/>
     * 测试：{@link CommonConst#ENVIRONMENT_TEST} <br/>
     * beta：{@link CommonConst#ENVIRONMENT_BETA} <br/>
     * 正式：{@link CommonConst#ENVIRONMENT_PRODUCTION} <br/>
     */
    private static final byte mAppEnvironment = CommonConst.ENVIRONMENT_TEST;

    /**
     * 日志当前开发环境
     */
    public static final byte LOG_ENVIRONMENT = LogUtils.ENVIRONMENT_TEST;

}
