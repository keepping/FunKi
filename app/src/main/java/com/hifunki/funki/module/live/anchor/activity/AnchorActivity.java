package com.hifunki.funki.module.live.anchor.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.faucamp.simplertmp.RtmpHandler;
import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseWindowActivity;
import com.hifunki.funki.common.CommonConst;
import com.hifunki.funki.util.PermissionUtil;
import com.hifunki.funki.util.PopWindowUtil;
import com.hifunki.funki.util.ToastUtils;
import com.hifunki.funki.widget.RoundImageView;
import com.seu.magicfilter.utils.MagicFilterType;

import net.ossrs.yasea.SrsCameraView;
import net.ossrs.yasea.SrsEncodeHandler;
import net.ossrs.yasea.SrsPublisher;
import net.ossrs.yasea.SrsRecordHandler;

import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.hifunki.funki.base.application.ApplicationMain.getContext;

/**
 * 主播直播间界面
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.live.start.activity.LiveTagActivity.java
 * @link
 * @since 2017-03-29 16:53:53
 */
public class AnchorActivity extends BaseWindowActivity implements RtmpHandler.RtmpListener, SrsRecordHandler.SrsRecordListener, SrsEncodeHandler.SrsEncodeListener {

    private static final String TAG = "AnchorActivity";
    Button btnRecord = null;
    Button btnSwitchEncoder = null;
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
    @BindView(R.id.rl_start_live_head)
    RelativeLayout rlStartLiveHead;
    @BindView(R.id.ll_start_live_boot)
    LinearLayout llStartLiveBoot;
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
    @BindView(R.id.rl_count)
    RelativeLayout rlCount;
    @BindView(R.id.tv_count_time)
    TextView tvCountTime;
    @BindView(R.id.fl_anchor)
    FrameLayout flAnchor;
    private PopWindowUtil sharePopWindow;//分享popWindow
    private View shareView;
    private SrsPublisher mPublisher;
    private String rtmpUrl = "rtmp://192.168.100.111/live/livestream";
    private String recPath = Environment.getExternalStorageDirectory().getPath() + "/test.mp4";
    private boolean mPermissions = false;
    private List<String> permissions;
    boolean isCountFinish = false;

    private enum STATUS {
        UNINIT,
        PUSHING,
        LIVEING,
    }

    @OnClick({R.id.iv_location, R.id.iv_beauty, R.id.iv_camera, R.id.iv_close, R.id.rl_start_live_head, R.id.iv_photo, R.id.tv_topic, R.id.rl_normal_live, R.id.rl_invite_live, R.id.rl_ticket_live, R.id.rl_level_live, R.id.ll_start_live_main})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_location:
                break;
            case R.id.iv_beauty:
                break;
            case R.id.iv_camera:
                if (mPublisher != null) {
                    mPublisher.switchCameraFace((mPublisher.getCamraId() + 1) % Camera.getNumberOfCameras());
                }
                break;
            case R.id.iv_close:
                break;
            case R.id.rl_start_live_head:
                break;
            case R.id.iv_photo:
                break;
            case R.id.tv_topic:
                break;
            case R.id.rl_normal_live://普通直播
                Animation animationUp = AnimationUtils.loadAnimation(this, R.anim.anchor_live_head_up);
                animationUp.setFillAfter(true);
                rlStartLiveHead.startAnimation(animationUp);
                Animation animationDown = AnimationUtils.loadAnimation(this, R.anim.anchor_live_head_down);
                animationDown.setFillAfter(true);
                llStartLiveBoot.startAnimation(animationDown);
                rlCount.setVisibility(View.VISIBLE);
                CountDownTimer countDownTimer = new CountDownTimer(3000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        tvCountTime.setText(String.valueOf((int) (millisUntilFinished / 1000)));
                    }

                    @Override
                    public void onFinish() {
                        rlCount.setVisibility(View.GONE);
                        flAnchor.setVisibility(View.VISIBLE);
                        startPublisher();
                    }
                };
                countDownTimer.start();

                break;
            case R.id.rl_invite_live:
                //创建PopWindow
                if (sharePopWindow == null) {
                    sharePopWindow = PopWindowUtil.getInstance(getApplicationContext());
                    shareView = LayoutInflater.from(getContext()).inflate(R.layout.pop_live_invite_friend, null);
//                    sharePopWindow.getPopWindow().setOnDismissListener(onDissmissListener);
                }
                sharePopWindow.init(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                sharePopWindow.showPopWindow(shareView, PopWindowUtil.ATTACH_LOCATION_WINDOW, view, 0, 0);
                break;
            case R.id.rl_ticket_live:
                break;
            case R.id.rl_level_live:
                break;
            case R.id.ll_start_live_main:
                break;
        }
    }

    public static void show(Context context) {
        context.startActivity(new Intent(context, AnchorActivity.class));
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_anchor;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {
        // //根据重力变换朝向
//        SCREEN_ORIENTATION_FULL_SENSOR
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        checkPermission();
//        StatusBarUtil.adjustStatusBarHei(rlStartLiveHead);
        Glide.with(this).load(CommonConst.photo).into(ivPhoto);//加载头像
    }

    public void checkPermission() {
        permissions = new ArrayList<>();
        if (!PermissionUtil.checkCameraAccess(this)) {
            permissions.add(Manifest.permission.CAMERA);
        }
        if (!PermissionUtil.checkAudioAccess(this)) {
            permissions.add(Manifest.permission.RECORD_AUDIO);
        }
        if (!PermissionUtil.checkWriteStorageAccess(this)) {
            permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (permissions.size() != 0) {
            ActivityCompat.requestPermissions(this, permissions.toArray(new String[permissions.size()]), 0);
        } else {
            mPermissions = true;
            startCamera();
        }
    }


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
                if (permissions.length == grantResults.length) {
                    mPermissions = true;
                    startCamera();
                }
                break;
        }
    }


    /**
     * 开启摄像机
     */
    private void startCamera() {
        mPublisher = new SrsPublisher((SrsCameraView) findViewById(R.id.glsurfaceview_camera));
        mPublisher.setEncodeHandler(new SrsEncodeHandler(this));
        mPublisher.setRtmpHandler(new RtmpHandler(this));
        mPublisher.setRecordHandler(new SrsRecordHandler(this));
        mPublisher.setPreviewResolution(640, 360);
        mPublisher.setOutputResolution(720, 1280);
        mPublisher.setVideoHDMode();
        mPublisher.startCamera();
    }

    private void startPublisher() {
        mPublisher.startPublish(rtmpUrl);
        mPublisher.startCamera();
    }

    private void stopPublisher() {
        mPublisher.stopPublish();
        mPublisher.stopRecord();
        btnRecord.setText("record");
        btnSwitchEncoder.setEnabled(true);
    }


    @Override
    protected void bindData() {

    }

    @Override
    protected void bindData4NoNet() {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else {
            switch (id) {
                case R.id.cool_filter:
                    mPublisher.switchCameraFilter(MagicFilterType.COOL);
                    break;
                case R.id.beauty_filter:
                    mPublisher.switchCameraFilter(MagicFilterType.BEAUTY);
                    break;
                case R.id.early_bird_filter:
                    mPublisher.switchCameraFilter(MagicFilterType.EARLYBIRD);
                    break;
                case R.id.evergreen_filter:
                    mPublisher.switchCameraFilter(MagicFilterType.EVERGREEN);
                    break;
                case R.id.n1977_filter:
                    mPublisher.switchCameraFilter(MagicFilterType.N1977);
                    break;
                case R.id.nostalgia_filter:
                    mPublisher.switchCameraFilter(MagicFilterType.NOSTALGIA);
                    break;
                case R.id.romance_filter:
                    mPublisher.switchCameraFilter(MagicFilterType.ROMANCE);
                    break;
                case R.id.sunrise_filter:
                    mPublisher.switchCameraFilter(MagicFilterType.SUNRISE);
                    break;
                case R.id.sunset_filter:
                    mPublisher.switchCameraFilter(MagicFilterType.SUNSET);
                    break;
                case R.id.tender_filter:
                    mPublisher.switchCameraFilter(MagicFilterType.TENDER);
                    break;
                case R.id.toast_filter:
                    mPublisher.switchCameraFilter(MagicFilterType.TOASTER2);
                    break;
                case R.id.valencia_filter:
                    mPublisher.switchCameraFilter(MagicFilterType.VALENCIA);
                    break;
                case R.id.walden_filter:
                    mPublisher.switchCameraFilter(MagicFilterType.WALDEN);
                    break;
                case R.id.warm_filter:
                    mPublisher.switchCameraFilter(MagicFilterType.WARM);
                    break;
                case R.id.original_filter:
                default:
                    mPublisher.switchCameraFilter(MagicFilterType.NONE);
                    break;
            }
        }
        setTitle(item.getTitle());

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        final Button btn = (Button) findViewById(R.id.publish);
//        btn.setEnabled(true);
        if (mPublisher != null) {
            mPublisher.resumeRecord();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mPublisher != null) {
            mPublisher.pauseRecord();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPublisher != null) {
            mPublisher.stopPublish();
            mPublisher.stopRecord();
        }
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mPublisher.stopEncode();
        mPublisher.stopRecord();
        btnRecord.setText("record");
        mPublisher.setScreenOrientation(newConfig.orientation);
//        if ("stop") {
//            mPublisher.startEncode();
//        }
        mPublisher.startCamera();
    }


    private void handleException(Exception e) {
        try {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            mPublisher.stopPublish();
            mPublisher.stopRecord();
            btnRecord.setText("record");
            btnSwitchEncoder.setEnabled(true);
        } catch (Exception e1) {
            //
        }
    }

    // Implementation of SrsRtmpListener.

    @Override
    public void onRtmpConnecting(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRtmpConnected(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRtmpVideoStreaming() {
    }

    @Override
    public void onRtmpAudioStreaming() {
    }

    @Override
    public void onRtmpStopped() {
        Toast.makeText(getApplicationContext(), "Stopped", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRtmpDisconnected() {
        Toast.makeText(getApplicationContext(), "Disconnected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRtmpVideoFpsChanged(double fps) {
        Log.i(TAG, String.format("Output Fps: %f", fps));
    }

    @Override
    public void onRtmpVideoBitrateChanged(double bitrate) {
        int rate = (int) bitrate;
        if (rate / 1000 > 0) {
            Log.i(TAG, String.format("Video bitrate: %f kbps", bitrate / 1000));
        } else {
            Log.i(TAG, String.format("Video bitrate: %d bps", rate));
        }
    }

    @Override
    public void onRtmpAudioBitrateChanged(double bitrate) {
        int rate = (int) bitrate;
        if (rate / 1000 > 0) {
            Log.i(TAG, String.format("Audio bitrate: %f kbps", bitrate / 1000));
        } else {
            Log.i(TAG, String.format("Audio bitrate: %d bps", rate));
        }
    }

    @Override
    public void onRtmpSocketException(SocketException e) {
        handleException(e);
    }

    @Override
    public void onRtmpIOException(IOException e) {
        handleException(e);
    }

    @Override
    public void onRtmpIllegalArgumentException(IllegalArgumentException e) {
        handleException(e);
    }

    @Override
    public void onRtmpIllegalStateException(IllegalStateException e) {
        handleException(e);
    }

    // Implementation of SrsRecordHandler.

    @Override
    public void onRecordPause() {
        Toast.makeText(getApplicationContext(), "Record paused", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRecordResume() {
        Toast.makeText(getApplicationContext(), "Record resumed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRecordStarted(String msg) {
        Toast.makeText(getApplicationContext(), "Recording file: " + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRecordFinished(String msg) {
        Toast.makeText(getApplicationContext(), "MP4 file saved: " + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRecordIOException(IOException e) {
        handleException(e);
    }

    @Override
    public void onRecordIllegalArgumentException(IllegalArgumentException e) {
        handleException(e);
    }

    // Implementation of SrsEncodeHandler.

    @Override
    public void onNetworkWeak() {
        Toast.makeText(getApplicationContext(), "Network weak", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNetworkResume() {
        Toast.makeText(getApplicationContext(), "Network resume", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEncodeIllegalArgumentException(IllegalArgumentException e) {
        handleException(e);
    }

    //        if (btnSwitchEncoder.getText().toString().contentEquals("soft encoder")) {
//            Toast.makeText(getApplicationContext(), "Use hard encoder", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(getApplicationContext(), "Use soft encoder", Toast.LENGTH_SHORT).show();
//        }
//        btnSwitchEncoder.setEnabled(false);


//    btnRecord = (Button) findViewById(R.id.record);
//    btnSwitchEncoder = (Button) findViewById(R.id.swEnc);
//
//
//        btnRecord.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            if (btnRecord.getText().toString().contentEquals("record")) {
//                if (mPublisher.startRecord(recPath)) {
//                    btnRecord.setText("pause");
//                }
//            } else if (btnRecord.getText().toString().contentEquals("pause")) {
//                mPublisher.pauseRecord();
//                btnRecord.setText("resume");
//            } else if (btnRecord.getText().toString().contentEquals("resume")) {
//                mPublisher.resumeRecord();
//                btnRecord.setText("pause");
//            }
//        }
//    });
//
//        btnSwitchEncoder.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            if (btnSwitchEncoder.getText().toString().contentEquals("soft encoder")) {
//                mPublisher.switchToSoftEncoder();
//                btnSwitchEncoder.setText("hard encoder");
//            } else if (btnSwitchEncoder.getText().toString().contentEquals("hard encoder")) {
//                mPublisher.switchToHardEncoder();
//                btnSwitchEncoder.setText("soft encoder");
//            }
//        }
//    });
}
