package com.hifunki.funki.module.dynamic.activity;

import android.content.Context;
import android.content.Intent;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;

/**
 * 随手拍界面
 *
 * @author yinhaoxiang
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.post.activity.PostDynamicActivity.java
 * @link
 * @since 2017-03-24 13:27:27
 */
public class ShotActivity extends BaseActivity {

    public static void show(Context context) {
        context.startActivity(new Intent(context,ShotActivity.class));
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_shot;
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initView() {

//        if (PermissionUtil.checkCameraAccess(getContext()) && PermissionUtil.checkWriteStorageAccess(getContext()) && surfaceCreated && mCamera == null) {//检查权限
//            mCamera = openCamera(isCamerafront, mWidth, mHeight, mFramerate, mSurfaceTexture);
//        } else {
//            if (Build.VERSION.SDK_INT >= 23 && !PermissionUtil.checkCameraAccess(getContext())) {
//                requestPermissions(new String[]{Manifest.permission.CAMERA}, 0);
//            }
//            if (Build.VERSION.SDK_INT >= 23 && !PermissionUtil.checkWriteStorageAccess(getContext())) {
//                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
//            }
//        }
    }


}
