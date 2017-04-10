package com.hifunki.funki.module.live.anchor.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.common.CommonConst;
import com.hifunki.funki.module.live.anchor.CameraUtils;
import com.hifunki.funki.module.live.anchor.GlVideoRender;
import com.hifunki.funki.module.live.anchor.widget.RoundImageView;
import com.hifunki.funki.util.DisplayUtil;
import com.hifunki.funki.util.FileUtils;
import com.hifunki.funki.util.PermissionUtil;
import com.hifunki.funki.util.PopWindowUtil;
import com.hifunki.funki.util.StatusBarUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;

import static com.hifunki.funki.base.application.ApplicationMain.getContext;

/**
 * 开启直播界面
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.live.start.activity.StartLiveActivity1.java
 * @link
 * @since 2017-03-28 12:58:58
 */
public class StartLiveActivity extends BaseActivity {

    @BindView(R.id.sv_preview)
    SurfaceView mSurfaceView;
    @BindView(R.id.rl_start_live_head)
    RelativeLayout rlHead;
    @BindView(R.id.iv_location)
    ImageView ivLocation;
    @BindView(R.id.iv_beauty)
    ImageView ivBeauty;
    @BindView(R.id.iv_camera)
    ImageView ivCamera;
    @BindView(R.id.iv_mirror)
    ImageView ivMirror;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.iv_photo)
    RoundImageView ivPhoto;
    @BindView(R.id.et_topic)
    EditText etTopic;
    @BindView(R.id.tv_topic)
    TextView tvTopic;
    @BindView(R.id.rl_normal_live)
    RelativeLayout rlNormalLive;
    @BindView(R.id.rl_invite_live)
    RelativeLayout rlInviteLive;
    @BindView(R.id.rl_ticket_live)
    RelativeLayout rlTicketLive;
    @BindView(R.id.rl_level_live)
    RelativeLayout rlLevelLive;
    @BindView(R.id.ll_start_live_main)
    RelativeLayout llStartLiveMain;
    private SurfaceHolder mHolder;
    private SurfaceTexture mSurfaceTexture;
    private GlVideoRender mVideoRender = null;
    private Camera mCamera;
    private int mWidth;
    private int mHeight;

    private int mFramerate = 30;

    boolean surfaceCreated = false;
    private boolean isBeautyOpen = false;
    private boolean isCamerafront = false;
    private String TAG = "test";
    private PopWindowUtil sharePopWindow;//分享popWindow
    private View shareView;

    public static void show(Context context) {
        context.startActivity(new Intent(context, StartLiveActivity.class));
    }

    @Override
    protected int getViewResId() {
        return R.layout.layout_start_live;
    }


    @Override
    protected void initVariable() {
        //加载头像
        Glide.with(this).load(CommonConst.photo).into(ivPhoto);
    }

    @Override
    protected void initView() {
        mWidth = DisplayUtil.getScreenWidth(this);
        mHeight = DisplayUtil.getScreenHeight(this);
        //修复高度
        StatusBarUtil.adjustStatusBarHei(findViewById(R.id.ll_start_live_main));
        mVideoRender = new GlVideoRender(mWidth, mHeight);
        mVideoRender.prepare();
        mSurfaceTexture = mVideoRender.getInputSurfaceTexture();

        mSurfaceTexture.setOnFrameAvailableListener(new SurfaceTexture.OnFrameAvailableListener() {
            @Override
            public void onFrameAvailable(SurfaceTexture surfaceTexture) {
                if (mVideoRender != null) {
                    mVideoRender.drawFrame();

                }
            }
        });

        mHolder = mSurfaceView.getHolder();
        mHolder.addCallback(recodeCallBack);

        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

    }

    @Override
    protected void bindData() {

    }

    @Override
    protected void bindData4NoNet() {

    }

    private SurfaceHolder.Callback recodeCallBack = new SurfaceHolder.Callback() {

        @Override
        public void surfaceCreated(SurfaceHolder holder) {

            surfaceCreated = true;
            if (PermissionUtil.checkCameraAccess(getContext()) && PermissionUtil.checkWriteStorageAccess(getContext()) && surfaceCreated && mCamera == null) {//检查权限
                mCamera = openCamera(isCamerafront, mWidth, mHeight, mFramerate, mSurfaceTexture);
            } else {
                if (Build.VERSION.SDK_INT >= 23 && !PermissionUtil.checkCameraAccess(getContext())) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, 0);
                }
                if (Build.VERSION.SDK_INT >= 23 && !PermissionUtil.checkWriteStorageAccess(getContext())) {
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                }
            }
            mVideoRender.setViewSurface(holder.getSurface());
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            closeCamera();
            surfaceCreated = false;
            mVideoRender.setViewSurface(null);
        }
    };


    @Override
    protected void onResume() {
        super.onResume();
        if (PermissionUtil.checkCameraAccess(getContext()) && PermissionUtil.checkWriteStorageAccess(getContext()) && surfaceCreated && mCamera == null) {
            mCamera = openCamera(isCamerafront, mWidth, mHeight, mFramerate, mSurfaceTexture);
        } else {
            if (Build.VERSION.SDK_INT >= 23 && !PermissionUtil.checkCameraAccess(getContext())) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, 0);
            }
            if (Build.VERSION.SDK_INT >= 23 && !PermissionUtil.checkWriteStorageAccess(getContext())) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        closeCamera();
    }


    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (PermissionUtil.checkCameraAccess(getContext()) && PermissionUtil.checkWriteStorageAccess(getContext()) && surfaceCreated && mCamera == null) {
            mCamera = openCamera(isCamerafront, mWidth, mHeight, mFramerate, mSurfaceTexture);
        }


    }

    @OnClick({R.id.iv_location, R.id.iv_beauty, R.id.iv_camera, R.id.iv_mirror, R.id.iv_close, R.id.rl_start_live_head, R.id.iv_photo, R.id.et_topic, R.id.tv_topic, R.id.rl_normal_live, R.id.rl_invite_live, R.id.rl_ticket_live, R.id.rl_level_live, R.id.ll_start_live_main})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_location:
                break;
            case R.id.iv_camera:
                isCamerafront = !isCamerafront;
                if (mCamera != null && mVideoRender != null) {
                    closeCamera();
                    mCamera = null;
                    mCamera = openCamera(isCamerafront, mWidth, mHeight, mFramerate, mSurfaceTexture);
                    mVideoRender.setMirror(isCamerafront);
                }
                break;
            case R.id.iv_beauty://设置美颜等级
                isBeautyOpen = !isBeautyOpen;
                if (mVideoRender != null) {
                    mVideoRender.setFilterEnable(isBeautyOpen);
                }
                if (isBeautyOpen) {
                    mVideoRender.setBeautifyLevel(5);//这里取值0-5，其余值自行测试
                }
                break;
            case R.id.iv_mirror:
                break;
            case R.id.iv_close:
                finish();
                break;
            case R.id.rl_start_live_head:
                break;
            case R.id.iv_photo:
                break;
            case R.id.et_topic:
                break;
            case R.id.tv_topic://跳转到选择标签页
                LiveTagActivity.show(this);
                break;
            case R.id.rl_normal_live:
//                LiveActivity.show(this);
//                TestTitleActivity.show(this);
                break;
            case R.id.rl_invite_live://邀请直播
                //创建PopWindow
                if (sharePopWindow == null) {
                    sharePopWindow = PopWindowUtil.getInstance(getApplicationContext());
                    shareView = LayoutInflater.from(getContext()).inflate(R.layout. pop_live_invite_friend, null);
//                    sharePopWindow.getPopWindow().setOnDismissListener(onDissmissListener);
                }
                sharePopWindow.init(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                sharePopWindow.showPopWindow(shareView, PopWindowUtil.ATTACH_LOCATION_WINDOW, view, 0, 0);
                break;
            case R.id.rl_ticket_live:
                break;
            case R.id.rl_level_live:
                if (mCamera != null) {
                    mCamera.takePicture(null, null, mPicture);
                }
                break;
            case R.id.ll_start_live_main:
                break;
        }
    }

    /**
     * 保存相机图片
     */
    private Camera.PictureCallback mPicture = new Camera.PictureCallback() {

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

            String randomLivePath = FileUtils.getRandomLivePath(StartLiveActivity.this);
            Log.e("test", "onPictureTaken: " + randomLivePath);
            File pictureFile = new File(randomLivePath);
            if (pictureFile == null) {

                return;
            }

            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(data);
                fos.close();
            } catch (FileNotFoundException e) {
                Log.d(TAG, "File not found: " + e.getMessage());
            } catch (IOException e) {
                Log.d(TAG, "Error accessing file: " + e.getMessage());
            }
        }
    };

    /**
     * 关闭照相机
     */
    private void closeCamera() {
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.setPreviewCallbackWithBuffer(null);
            mCamera.release();
            mCamera = null;
        }
    }

    private Camera openCamera(boolean front_camera, int width, int height, int framerate, SurfaceTexture st) {
        Camera camera = null;

        Camera.CameraInfo info = new Camera.CameraInfo();
        int numCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numCameras; i++) {
            Camera.getCameraInfo(i, info);
            if (front_camera && info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                camera = Camera.open(i);
                break;
            }
            if (!front_camera && info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                camera = Camera.open(i);
                break;
            }
        }
        if (camera == null) {
            Log.d(TAG, "No front-facing camera found; opening default");
            camera = Camera.open(); // opens first back-facing camera
            if (camera == null) {
                throw new RuntimeException("Unable to open camera");
            }
        }

        Camera.Parameters parameters = camera.getParameters();
        // parameters.setPreviewFormat(ImageFormat.YV12);
        CameraUtils.choosePreviewSize(parameters, width, height);
        CameraUtils.chooseFixedPreviewFps(parameters, framerate * 1000);
        if (parameters.getSupportedAntibanding().contains(Parameters.ANTIBANDING_50HZ)) {
            parameters.setAntibanding(Parameters.ANTIBANDING_50HZ);
        }
        if (parameters.getSupportedFocusModes().contains(Parameters.FOCUS_MODE_AUTO)) {
            parameters.setFocusMode(Parameters.FOCUS_MODE_AUTO);
        }

        camera.setParameters(parameters);

        try {
            camera.setPreviewTexture(st);
        } catch (IOException e) {
            e.printStackTrace();
            camera.release();
            return null;
        }

        camera.startPreview();
        return camera;
    }

    @Override
    protected void onDestroy() {

        closeCamera();
        mCamera = null;

        mSurfaceTexture.setOnFrameAvailableListener(null);
        mSurfaceTexture = null;

        mVideoRender.release();
        mVideoRender = null;

        super.onDestroy();
    }


}
