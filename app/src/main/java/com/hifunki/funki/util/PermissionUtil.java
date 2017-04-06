package com.hifunki.funki.util;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

/**
 * 检查权限util
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.util.PermissionUtil.java
 * @link
 * @since 2017-03-31 10:46:46
 */
public class PermissionUtil {

    // 检查相机权限
    public static boolean checkCameraAccess(Context context) {
        return Build.VERSION.SDK_INT < 23 || PackageManager.PERMISSION_GRANTED == context.checkPermission(Manifest.permission.CAMERA, android.os.Process.myPid(), android.os.Process.myUid());
    }

    // 检查写入sdk权限
    public static boolean checkWriteStorageAccess(Context context) {
        return Build.VERSION.SDK_INT < 23 || PackageManager.PERMISSION_GRANTED == context.checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, android.os.Process.myPid(), android.os.Process.myUid());
    }

    public static boolean checkAudioAccess(Context context) {
        return Build.VERSION.SDK_INT < 23 || PackageManager.PERMISSION_GRANTED == context.checkPermission(Manifest.permission.RECORD_AUDIO, android.os.Process.myPid(), android.os.Process.myUid());
    }

}
