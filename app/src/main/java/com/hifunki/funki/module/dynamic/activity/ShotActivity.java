package com.hifunki.funki.module.dynamic.activity;

import android.content.Context;
import android.content.Intent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.widget.TopBarView;

import butterknife.BindView;
import butterknife.OnClick;

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

    @BindView(R.id.tbv_live_tag)
    TopBarView tbvLiveTag;
    @BindView(R.id.sv_dynamic)
    SurfaceView svDynamic;
    @BindView(R.id.iv_dynamic_mirror)
    ImageView ivDynamicMirror;
    @BindView(R.id.iv_dynamic_beauty)
    ImageView ivDynamicBeauty;
    @BindView(R.id.iv_shot_photo_dot)
    ImageView ivShotPhotoDot;
    @BindView(R.id.tv_shot_photo)
    TextView tvShotPhoto;
    @BindView(R.id.ll_dynamic_image)
    LinearLayout llDynamicImage;
    @BindView(R.id.iv_shot_video_dot)
    ImageView ivShotVideoDot;
    @BindView(R.id.tv_shot_video)
    TextView tvShotVideo;
    @BindView(R.id.iv_shot_take_photo)
    ImageView ivShotTakePhoto;
    @BindView(R.id.iv_shot_photo)
    ImageView ivShotPhoto;
    @BindView(R.id.pb_shot)
    ProgressBar pbShot;
    @BindView(R.id.tv_shot_time)
    TextView tvShotTime;
    @BindView(R.id.iv_shot_back)
    ImageView ivShotBack;
    @BindView(R.id.iv_shot_ok)
    ImageView ivShotOk;

    public static void show(Context context) {
        context.startActivity(new Intent(context, ShotActivity.class));
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


    @OnClick({R.id.tbv_live_tag, R.id.sv_dynamic, R.id.iv_dynamic_mirror, R.id.iv_dynamic_beauty, R.id.iv_shot_photo_dot, R.id.tv_shot_photo, R.id.ll_dynamic_image, R.id.iv_shot_video_dot, R.id.tv_shot_video, R.id.iv_shot_take_photo, R.id.iv_shot_photo, R.id.pb_shot, R.id.tv_shot_time, R.id.iv_shot_back, R.id.iv_shot_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tbv_live_tag:
                break;
            case R.id.sv_dynamic:
                break;
            case R.id.iv_dynamic_mirror:
                break;
            case R.id.iv_dynamic_beauty:
                break;
            case R.id.iv_shot_photo_dot:
                break;
            case R.id.tv_shot_photo:
                break;
            case R.id.ll_dynamic_image:
                break;
            case R.id.iv_shot_video_dot:
                break;
            case R.id.tv_shot_video:
                break;
            case R.id.iv_shot_take_photo:
                break;
            case R.id.iv_shot_photo:
                break;
            case R.id.pb_shot:
                break;
            case R.id.tv_shot_time:
                break;
            case R.id.iv_shot_back:
                break;
            case R.id.iv_shot_ok:
                break;
        }
    }
}
