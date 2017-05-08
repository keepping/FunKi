package com.hifunki.funki.module.dynamic.post.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.hiteshsondhi88.libffmpeg.ExecuteBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.github.hiteshsondhi88.libffmpeg.FFmpegExecuteResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.LoadBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegNotSupportedException;
import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.module.dynamic.post.data.VideoHolder;
import com.hifunki.funki.util.FileUtils;
import com.hifunki.funki.util.PermissionUtil;
import com.hifunki.funki.util.ToastUtils;
import com.hifunki.funki.widget.bar.TopBarView;
import com.powyin.slide.widget.BannerSwitch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.media.RecodeUtil;
import io.media.av.AVRecorder;


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
    GLSurfaceView mSurfaceView;
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

    @BindView(R.id.iv_shot_video_dot)
    ImageView ivShotVideoDot;
    @BindView(R.id.tv_shot_video)
    TextView tvShotVideo;
    @BindView(R.id.iv_shot_take_photo)
    ImageView ivShotTakePhoto;
    @BindView(R.id.iv_shot_photo)
    ImageView ivShotPhoto;


    @BindView(R.id.tv_shot_time)
    TextView tvShotTime;
    @BindView(R.id.iv_shot_back)
    ImageView ivShotBack;

    @BindView(R.id.iv_shot_ok)
    ImageView ivShotOk;

    @BindView(R.id.recode_content)
    LinearLayout recodeContent;

    private List<VideoHolder> holders = new ArrayList<>();

    AVRecorder mAVRecorder;


    FFmpeg fFmpeg;


    private VideoHolder.OnRecodeOver nRecodeOverListener = new VideoHolder.OnRecodeOver() {
        @Override
        public void onStop(VideoHolder holder) {

        }
    };

    public static void show(Context context) {
        context.startActivity(new Intent(context, ShotActivity.class));
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_shot;
    }

    @Override
    protected void initVariable() {
        MediaRecorder recorder;
    }

    @Override
    protected void initView() {
        tbvLiveTag.setBackgroundColor(Color.parseColor("#790C001F"));

     //   mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);//surfaceview不维护自己的缓冲区，等待屏幕渲染引擎将内容推送到用户面前
        ImageView menuImageMore = tbvLiveTag.getMenuImageMore();

        try {
            mAVRecorder = new AVRecorder(RecodeUtil.create720pSessionConfig(this));
            mAVRecorder.setPreviewDisplay(mSurfaceView);
        }catch (Exception e){
            e.printStackTrace();
        }



        VideoHolder holder = new  VideoHolder(this);
        holder.setOnStopLister(nRecodeOverListener);
        holder.setRecorder(mAVRecorder,false);
        holders.add(holder);
        recodeContent.addView(holder.getItemView());

        fFmpeg = FFmpeg.getInstance(this);

        try {
            fFmpeg.loadBinary(new LoadBinaryResponseHandler() {
                @Override
                public void onFailure() {
                    Log.e(getClass().getSimpleName(),"error init ffmpeg");
                }
            });
        } catch (FFmpegNotSupportedException e) {
            Log.e(getClass().getSimpleName(),"error init ffmpeg");
        }

        checkPemison();
    }

    @Override
    protected void onDestroy() {
        if(mAVRecorder!=null){
            mAVRecorder.release();
        }

        super.onDestroy();
    }

    @OnClick({R.id.tbv_live_tag, R.id.sv_dynamic, R.id.iv_dynamic_mirror,
            R.id.iv_dynamic_beauty, R.id.iv_shot_photo_dot, R.id.tv_shot_photo,
            R.id.ll_dynamic_image, R.id.ll_shot_video, R.id.iv_shot_video_dot, R.id.tv_shot_video,
            R.id.iv_shot_take_photo, R.id.iv_shot_photo, R.id.tv_shot_time, R.id.iv_shot_back, R.id.iv_shot_ok})
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
            case R.id.ll_shot_video:

                break;
            case R.id.iv_shot_video_dot:
                break;
            case R.id.tv_shot_video:
                break;
            case R.id.iv_shot_take_photo:

                View current =  findViewById(R.id.iv_shot_take_photo);
                if(!current.isSelected()){
                    holders.get(holders.size()-1).startRecord(400,40000);
                }else {
                    holders.get(holders.size()-1).stopRecord();
                    VideoHolder holder = new  VideoHolder(this);
                    holder.setRecorder(mAVRecorder,true);
                    holder.setOnStopLister(nRecodeOverListener);
                    holders.add(holder);
                    recodeContent.addView(holder.getItemView());
                }
                current.setSelected(!current.isSelected());
                break;
            case R.id.iv_shot_photo:
                break;
            case R.id.tv_shot_time:
                break;
            case R.id.iv_shot_back:
                break;
            case R.id.iv_shot_ok:
                if(view.isSelected()){


                }else {

                }
                try {

                    if(holders.size() == 1) return;


                    StringBuilder common = new StringBuilder();
                    common.append("-i concat:");
                    for(int i=0 ; i<holders.size() -1 ;i++){
                        common.append(holders.get(i).getStoreFile());
                        common.append("|");
                    }
                    common.setLength(common.length()-1);
                    common.append(" -c copy ");
                    common.append(FileUtils.getRandomFilePath(this,".mp4",false).toString());

                    fFmpeg.execute(new String[]{ },new FFmpegExecuteResponseHandler(){

                        @Override
                        public void onSuccess(String message) {
                            System.out.println("...................................onSuccess");
                        }

                        @Override
                        public void onProgress(String message) {
                            System.out.println("...................................onProgress");
                        }

                        @Override
                        public void onFailure(String message) {
                            System.out.println("...................................onFailure");
                        }

                        @Override
                        public void onStart() {
                            System.out.println("...................................onStart");
                        }

                        @Override
                        public void onFinish() {
                            System.out.println("...................................onFinish");
                        }
                    });

                }catch (Exception e){
                    e.printStackTrace();
                }


                break;
        }
    }


    // 检查权限
    private void checkPemison(){
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
        }
    }







}
