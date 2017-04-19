package com.hifunki.funki.module.dynamic.post.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Camera;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
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
import com.hifunki.funki.util.ToastUtils;
import com.hifunki.funki.util.ViewUtil;
import com.hifunki.funki.widget.bar.TopBarView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    private boolean mPermissions = false;
    int cameraPosition = 1;//0代表前置摄像头，1代表后置摄像头

    private enum STATUS {
        UNINIT, IMAGE, IMAGE_BEAUTY, MOVIE_INIT, MOVIE_LESS_NOTIFY, MOVIE_LESS_DEL, MOVIE_OK
    }

    public static void show(Context context) {
        context.startActivity(new Intent(context, ShotActivity.class));
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_shot;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {
        tbvLiveTag.setBackgroundColor(Color.parseColor("#790C001F"));
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

    }

    @Override
    protected void bindData() {

    }

    @Override
    protected void bindData4NoNet() {

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
                updateUI(STATUS.MOVIE_INIT);
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

        switch (uninit) {
            case UNINIT://初始化状态
                ViewUtil.showOrHideView(View.INVISIBLE, pbShot, tvShotTime);


            case IMAGE:
//                ViewUtil.showOrHideView(View.INVISIBLE, pbShot, tvShotTime);
                break;
            case IMAGE_BEAUTY:

                break;
            case MOVIE_INIT:

                break;
            case MOVIE_LESS_NOTIFY:

                break;
            case MOVIE_LESS_DEL:

                break;
            case MOVIE_OK:

                break;
        }
    }

    private SurfaceHolder.Callback recodeCallBack = new SurfaceHolder.Callback() {

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void surfaceCreated(SurfaceHolder holder) {              // 视频预浏览
            mSurfaceCreated = true;
            List<String> permissions = new ArrayList<>();
            if (!PermissionUtil.checkCameraAccess(ShotActivity.this)) {
                permissions.add(Manifest.permission.CAMERA);
            }
            if (!PermissionUtil.checkAudioAccess(ShotActivity.this)) {
                permissions.add(Manifest.permission.RECORD_AUDIO);
            }
            if (!PermissionUtil.checkWriteStorageAccess(ShotActivity.this)) {
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (permissions.size() != 0) {
                ActivityCompat.requestPermissions(ShotActivity.this, permissions.toArray(new String[permissions.size()]), 0);
            } else {
                mPermissions = true;
                openCamera();
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
        switch (requestCode) {
            case 0:
                for (int ret : grantResults) {
                    if (ret != PackageManager.PERMISSION_GRANTED) {

                        ToastUtils.showShortToastSafe("没有授权");
                    } else {
                        ToastUtils.showShortToastSafe("授权成功");
                    }
                }
                mPermissions = true;
                openCamera();
                break;
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
