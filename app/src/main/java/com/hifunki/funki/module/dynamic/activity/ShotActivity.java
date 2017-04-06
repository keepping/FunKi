package com.hifunki.funki.module.dynamic.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.util.PermissionUtil;
import com.hifunki.funki.widget.TopBarView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;

import static com.hifunki.funki.R.id.pb_shot;

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
    SurfaceView mSurfaceView;
    @BindView(R.id.iv_dynamic_mirror)
    ImageView ivDynamicMirror;//镜像按钮
    @BindView(R.id.iv_dynamic_beauty)
    ImageView ivDynamicBeauty;
    @BindView(R.id.iv_shot_photo_dot)
    ImageView ivShotPhotoDot;
    @BindView(R.id.tv_shot_photo)
    TextView tvShotPhoto;
    @BindView(R.id.ll_dynamic_image)
    LinearLayout llDynamicImage;
    @BindView(R.id.ll_shot_video)
    LinearLayout llShotVideo;//视频按钮
    @BindView(R.id.iv_shot_video_dot)
    ImageView ivShotVideoDot;
    @BindView(R.id.tv_shot_video)
    TextView tvShotVideo;
    @BindView(R.id.iv_shot_take_photo)
    ImageView ivShotTakePhoto;
    @BindView(R.id.iv_shot_photo)
    ImageView ivShotPhoto;
    @BindView(pb_shot)
    ProgressBar pbShot;
    @BindView(R.id.tv_shot_time)
    TextView tvShotTime;
    @BindView(R.id.iv_shot_back)
    ImageView ivShotBack;
    @BindView(R.id.iv_shot_ok)
    ImageView ivShotOk;
    private SurfaceHolder mSurfaceHolder;
    boolean mSurfaceCreated = false;
    Camera mCamera;

    private enum STATUS {
        UNINIT, IMAGE, IMAGE_BEAUTY, MOVIE, MOVIE_TIME_LACK, MOVIE_OK
    }

//    private STATUS status = STATUS.UNINIT;

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

    int cameraPosition = 1;//0代表前置摄像头，1代表后置摄像头

    @Override
    protected void initView() {
        updateUI(STATUS.UNINIT);
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(recodeCallBack);
        mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);//surfaceview不维护自己的缓冲区，等待屏幕渲染引擎将内容推送到用户面前
        ImageView menuImageMore = tbvLiveTag.getMenuImageMore();
        menuImageMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
                int cameraCount = Camera.getNumberOfCameras();
                for (int i = 0; i < cameraCount; i++) {
                    Camera.getCameraInfo(i, cameraInfo);
                    if (cameraPosition == 1 && cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                        mCamera.stopPreview();
                        mCamera.release();
                        mCamera = null;
                        mCamera = Camera.open(i);
                        Camera.Parameters params = mCamera.getParameters();
                        params.set("orientation", "portrait");
                        mCamera.setParameters(params);
                        mCamera.setDisplayOrientation(90);
                        try {
                            mCamera.setPreviewDisplay(mSurfaceHolder);//通过surfaceview显示取景画面
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        mCamera.startPreview();//开始预览
                        cameraPosition = 0;
                        break;
                    } else if (cameraPosition == 0 && cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                        //现在是前置， 变更为后置
                        mCamera.stopPreview();//停掉原来摄像头的预览
                        mCamera.release();//释放资源
                        mCamera = null;//取消原来摄像头
                        mCamera = Camera.open(i);//打开当前选中的摄像头
                        Camera.Parameters params = mCamera.getParameters();
                        params.set("orientation", "portrait");
                        mCamera.setParameters(params);
                        mCamera.setDisplayOrientation(90);
                        try {
                            mCamera.setPreviewDisplay(mSurfaceHolder);//通过surfaceview显示取景画面
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        mCamera.startPreview();//开始预览
                        cameraPosition = 1;
                        break;
                    }
                }
            }
        });

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


    @OnClick({R.id.tbv_live_tag, R.id.sv_dynamic, R.id.iv_dynamic_mirror, R.id.iv_dynamic_beauty, R.id.iv_shot_photo_dot, R.id.tv_shot_photo, R.id.ll_dynamic_image, R.id.ll_shot_video, R.id.iv_shot_video_dot, R.id.tv_shot_video, R.id.iv_shot_take_photo, R.id.iv_shot_photo, pb_shot, R.id.tv_shot_time, R.id.iv_shot_back, R.id.iv_shot_ok})
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
                updateUI(STATUS.IMAGE);
                break;
            case R.id.ll_shot_video:
                updateUI(STATUS.MOVIE);
                break;
            case R.id.iv_shot_video_dot:
                break;
            case R.id.tv_shot_video:
                break;
            case R.id.iv_shot_take_photo:
                break;
            case R.id.iv_shot_photo:
                break;
            case pb_shot:
                break;
            case R.id.tv_shot_time:
                break;
            case R.id.iv_shot_back:
                break;
            case R.id.iv_shot_ok:
                break;
        }
    }

    private void updateUI(STATUS uninit) {
        ivDynamicMirror.setVisibility(View.INVISIBLE);
        ivDynamicBeauty.setVisibility(View.INVISIBLE);
        //image
        ivShotPhotoDot.setVisibility(View.INVISIBLE);
        tvShotPhoto.setVisibility(View.INVISIBLE);
        //video
        ivShotVideoDot.setVisibility(View.INVISIBLE);
        tvShotVideo.setVisibility(View.INVISIBLE);
        pbShot.setVisibility(View.INVISIBLE);
        tvShotTime.setVisibility(View.INVISIBLE);
        ivShotBack.setVisibility(View.INVISIBLE);
        ivShotOk.setVisibility(View.INVISIBLE);
        switch (uninit) {
            case IMAGE:
                ivDynamicMirror.setVisibility(View.VISIBLE);
                ivDynamicBeauty.setVisibility(View.VISIBLE);
                ivShotPhotoDot.setVisibility(View.VISIBLE);
                tvShotPhoto.setVisibility(View.VISIBLE);
                break;
            case MOVIE:
                ivShotVideoDot.setVisibility(View.VISIBLE);
                tvShotVideo.setVisibility(View.VISIBLE);
                tvShotVideo.setVisibility(View.VISIBLE);
                pbShot.setVisibility(View.VISIBLE);
                tvShotTime.setVisibility(View.VISIBLE);
                ivShotBack.setVisibility(View.VISIBLE);
                ivShotOk.setVisibility(View.VISIBLE);
                break;

        }

    }


    private SurfaceHolder.Callback recodeCallBack = new SurfaceHolder.Callback() {

        @Override
        public void surfaceCreated(SurfaceHolder holder) {              // 视频预浏览
            mSurfaceCreated = true;
            if (PermissionUtil.checkCameraAccess(ShotActivity.this) && PermissionUtil.checkAudioAccess(ShotActivity.this) && PermissionUtil.checkWriteStorageAccess(ShotActivity.this) && mSurfaceCreated && mCamera == null) {
                openCamera();
            } else {
                if (Build.VERSION.SDK_INT >= 23 && !PermissionUtil.checkCameraAccess(ShotActivity.this)) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, 0);
                }
                if (Build.VERSION.SDK_INT >= 23 && !PermissionUtil.checkAudioAccess(ShotActivity.this)) {
                    requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO}, 1);
                }
                if (Build.VERSION.SDK_INT >= 23 && !PermissionUtil.checkWriteStorageAccess(ShotActivity.this)) {
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                }
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            releaseCamera();
            mSurfaceCreated = false;
        }

    };

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (PermissionUtil.checkCameraAccess(ShotActivity.this) && PermissionUtil.checkAudioAccess(ShotActivity.this) && mSurfaceCreated && mCamera == null) {
            openCamera();
        }
    }

    private void openCamera() {
        try {
            mCamera = Camera.open();
            Camera.Parameters params = mCamera.getParameters();
            params.set("orientation", "portrait");
            mCamera.setParameters(params);
            mCamera.setDisplayOrientation(90);
            mCamera.setPreviewDisplay(mSurfaceView.getHolder());
            mCamera.startPreview();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void releaseCamera() {
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.setPreviewCallbackWithBuffer(null);
            mCamera.release();
            mCamera = null;
        }
    }

}
