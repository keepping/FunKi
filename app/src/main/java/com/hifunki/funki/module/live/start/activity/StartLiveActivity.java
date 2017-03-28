package com.hifunki.funki.module.live.start.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.common.CommonConst;
import com.hifunki.funki.module.live.start.CameraUtils;
import com.hifunki.funki.module.live.start.GlVideoRender;
import com.hifunki.funki.module.live.start.widget.RoundImageView;
import com.hifunki.funki.util.DisplayUtil;
import com.hifunki.funki.util.StatusBarUtil;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;

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
    private boolean isCamerafront = false;
    private String TAG = "test";

    @Override
    protected int getViewResId() {
        return R.layout.activity_start_live;
    }

    @Override
    protected void initDatas() {
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


        //Permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(StartLiveActivity.this, new String[]{Manifest.permission.CAMERA}, 1);
        } else {
            openCamera(false, mWidth, mHeight, mFramerate, mSurfaceTexture);
            mVideoRender.setMirror(false);
        }

        mHolder = mSurfaceView.getHolder();
        mHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                Log.d(TAG, "surfaceDestroyed ...");
                closeCamera(mCamera);
                surfaceCreated = false;
                mVideoRender.setViewSurface(null);
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Log.d(TAG, "surfaceCreated ...");
                surfaceCreated = true;
                mVideoRender.setViewSurface(holder.getSurface());
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int mWidth, int mHeight) {

            }
        });

        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);


    }

    @Override
    protected void onPause() {
        super.onPause();
        closeCamera(mCamera);
    }


    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if ( grantResults[0] ==PackageManager.PERMISSION_GRANTED) {
            openCamera(false, mWidth, mHeight, mFramerate, mSurfaceTexture);
            mVideoRender.setMirror(false);
        }else{
            Toast.makeText(this,"没有相机权限",Toast.LENGTH_LONG).show();
        }
    }

    @OnClick({R.id.iv_location, R.id.iv_camera, R.id.iv_mirror, R.id.iv_close, R.id.rl_start_live_head, R.id.iv_photo, R.id.et_topic, R.id.tv_topic, R.id.rl_normal_live, R.id.rl_invite_live, R.id.rl_ticket_live, R.id.rl_level_live, R.id.ll_start_live_main})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_location:
                break;
            case R.id.iv_camera:
                Log.e(TAG, "onClick: " + "iv_camera");
                isCamerafront = !isCamerafront;
                Log.e(TAG, "onClick: " + "isCamerafront="+isCamerafront);
                if (mCamera != null && mVideoRender != null) {
                    closeCamera(mCamera);
                    mCamera = null;
                    mCamera = openCamera(isCamerafront, mWidth, mHeight, mFramerate, mSurfaceTexture);
                    mVideoRender.setMirror(isCamerafront);
                }
                break;
            case R.id.iv_mirror:
                break;
            case R.id.iv_close:
                break;
            case R.id.rl_start_live_head:
                break;
            case R.id.iv_photo:
                break;
            case R.id.et_topic:
                break;
            case R.id.tv_topic:
                break;
            case R.id.rl_normal_live:
                break;
            case R.id.rl_invite_live:
                break;
            case R.id.rl_ticket_live:
                break;
            case R.id.rl_level_live:
                break;
            case R.id.ll_start_live_main:
                break;
        }
    }



    private void closeCamera(Camera camera) {
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

        closeCamera(mCamera);
        mCamera = null;

        mSurfaceTexture.setOnFrameAvailableListener(null);
        mSurfaceTexture = null;

        mVideoRender.release();
        mVideoRender = null;

        super.onDestroy();
    }

}
