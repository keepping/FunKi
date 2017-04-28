package com.hifunki.funki.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.util.PackageUtil.java
 * @link
 * @since 2017-04-28 13:41:41
 */
public class PackageUtil {

    public static String getPackageName(Context context) throws PackageManager.NameNotFoundException {
        PackageInfo packageInfo;
        packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        return packageInfo.versionName;
    }

    public static int getPackageCode(Context context) throws PackageManager.NameNotFoundException {
        PackageInfo packageInfo;
        packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        return packageInfo.versionCode;
    }

}
