package com.hifunki.funki.common;

/**
 * 全局参数管理类
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.common.CommonConst.java
 * @link
 * @since 2017-03-15 10:27:27
 */
public interface CommonConst {
    int NETTYPE_NONE = 0;
    int NETTYPE_LINE = 1;
    int NETTYPE_WIFI = 2;
    int NETTYPE_3G = 3;
    int NETTYPE_2G = 4;
    int NETTYPE_4G = 5;
    // ----------------开发环境------------------//

    byte ENVIRONMENT_DEV = 1;

    byte ENVIRONMENT_BETA = 2;

    byte ENVIRONMENT_TEST = 3;

    byte ENVIRONMENT_PRODUCTION = 4;

    //全局头像类-->需要修改
     String photo = "http://img0.imgtn.bdimg.com/it/u=2329110913,2614087554&fm=214&gp=0.jpg";

}
