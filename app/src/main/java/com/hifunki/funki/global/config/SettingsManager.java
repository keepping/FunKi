package com.hifunki.funki.global.config;

import com.hifunki.funki.library.common.CommonConst;
import com.hifunki.funki.library.util.LogUtils;

/**
 * Created by WangTest on 2017/2/25.
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
