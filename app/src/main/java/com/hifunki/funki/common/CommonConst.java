package com.hifunki.funki.common;

import java.util.Arrays;
import java.util.List;

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


    String IMAGE_VIEW = "http://pic.58pic.com/58pic/13/86/80/95h58PIC5jK_1024.jpg";
    String VIDEO = "http://v.cctv.com/flash/mp4video6/TMS/2011/01/05/cf752b1c12ce452b3040cab2f90bc265_h264818000nero_aac32-1.mp4";
    List<String> NINE_PHOTO = Arrays.asList("http://t2.27270.com/uploads/tu/201606/112/17.jpg",
            "http://t2.27270.com/uploads/tu/201510/249/3.jpg",
            "http://t2.27270.com/uploads/tu/201606/62/28.jpg",
            "http://t2.27270.com/uploads/tu/201606/76/34.jpg",
            "http://t2.27270.com/uploads/tu/201606/73/slt.jpg",
            "http://img2.imgtn.bdimg.com/it/u=3347259689,1828160575&fm=21&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=3607821315,1190508392&fm=21&gp=0.jpg");
}
