package com.hifunki.funki.module.me.profile.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.module.photo.gallery.config.GalleryConfig;
import com.hifunki.funki.module.photo.gallery.config.GalleryPick;
import com.hifunki.funki.module.photo.gallery.inter.GlideImageLoader;
import com.hifunki.funki.module.photo.gallery.inter.IHandlerCallBack;
import com.hifunki.funki.module.photo.gallery.util.UCropUtils;
import com.hifunki.funki.module.photo.ucrop.UCrop;
import com.hifunki.funki.util.DisplayUtil;
import com.hifunki.funki.util.FileUtils;
import com.hifunki.funki.util.PermissionUtil;
import com.hifunki.funki.util.PopWindowUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.hifunki.funki.module.photo.gallery.activity.PhotoActivity.drawableToBitmap;

/**
 * 编辑个人资料
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.me.fans.EditProfileActivity.java
 * @link
 * @since 2017-03-23 12:01:01
 */
public class EditProfileActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.civ_profile_photo)
    ImageView civProfilePhoto;

    @BindView(R.id.rl_nickname)
    RelativeLayout rlNickname;
    @BindView(R.id.rl_age)
    RelativeLayout rlAge;

    private Context mContext = null;
    private EditProfileActivity mActivity = null;
    private View pwdView;
    private PopWindowUtil pwdPopWindow;
    private GalleryConfig galleryConfig;
    private IHandlerCallBack iHandlerCallBack;
    private List<String> path = new ArrayList<>();
    private ArrayList<String> resultPhoto;      //照片路径集合
    private static final int REQUEST_CAMERA = 100;   // 设置拍摄照片的 REQUEST_CODE
    private File cameraTempFile;           //原始图片
    private File cropTempFile;           //待裁剪图片
    private IHandlerCallBack mHandlerCallBack;   // GalleryPick 生命周期接口

    public static void show(Context context) {
        context.startActivity(new Intent(context, EditProfileActivity.class));
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_edit_profile;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {
        mContext = this;
        mActivity = this;

        initGallery();

        galleryConfig = new GalleryConfig.Builder()
                .imageLoader(new GlideImageLoader())    // ImageLoader 加载框架（必填）
                .iHandlerCallBack(iHandlerCallBack)     // 监听接口（必填）
                .provider("com.hifunki.funki.fileprovider")   // provider(必填)
                .pathList(path)                         // 记录已选的图片
                .multiSelect(false)                      // 是否多选   默认：false
                .multiSelect(false, 9)                   // 配置是否多选的同时 配置多选数量   默认：false ， 9
                .maxSize(9)                             // 配置多选时 的多选数量。    默认：9
                .crop(true)                             // 快捷开启裁剪功能，仅当单选 或直接开启相机时有效
                .crop(true, 1, 1, 500, 500)             // 配置裁剪功能的参数，   默认裁剪比例 1:1
                .isShowCamera(true)                     // 是否现实相机按钮  默认：false
                .filePath("/FunKi/Pictures")          // 图片存放路径
                .build();

        galleryConfig.getBuilder().isOpenCamera(false).build();
//        galleryConfig = GalleryPick.getInstance().getGalleryConfig();

        mHandlerCallBack = galleryConfig.getIHandlerCallBack();
        mHandlerCallBack.onStart();
        resultPhoto = galleryConfig.getPathList();


    }

    @Override
    protected void initAdapter() {
        super.initAdapter();
//        ProfileAdapter profileAdapter = new ProfileAdapter();

    }

    @Override
    protected void bindData() {

    }

    @Override
    protected void bindData4NoNet() {

    }


    @OnClick({R.id.civ_profile_photo, R.id.rl_nickname,R.id.rl_age})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.civ_profile_photo:
                //创建PopWindow
                if (pwdPopWindow == null) {
                    pwdPopWindow = PopWindowUtil.getInstance(this);
                    pwdView = LayoutInflater.from(this).inflate(R.layout.pop_profile_edit_photo, null);
                    pwdPopWindow.getPopWindow().setOnDismissListener(onDissmissListener);
                }
                pwdPopWindow.init((int) DisplayUtil.dip2Px(this, 173), LinearLayout.LayoutParams.MATCH_PARENT);
                pwdPopWindow.showPopWindow(pwdView, PopWindowUtil.ATTACH_LOCATION_WINDOW, null, 0, 0);
                TextView tvProfileGallery = (TextView) pwdView.findViewById(R.id.tv_profile_gallery);
                TextView tvProfilePhoto = (TextView) pwdView.findViewById(R.id.tv_profile_photo);
                ImageView ivClose = (ImageView) pwdView.findViewById(R.id.iv_close);
                //点击相册回调
                tvProfileGallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ContextCompat.checkSelfPermission(EditProfileActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            if (ActivityCompat.shouldShowRequestPermissionRationale(EditProfileActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                            } else {
                                ActivityCompat.requestPermissions(EditProfileActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                            }
                        } else {
                            GalleryPick.getInstance().setGalleryConfig(galleryConfig).open(EditProfileActivity.this);
                        }
                    }
                });
                //点击拍照回调
                tvProfilePhoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        resultPhoto.clear();
//                resultPhoto.addAll(selectPhotoList);
                        //检查相机权限
                        if (PermissionUtil.checkCameraAccess(getApplicationContext())) {
                            showCameraAction();
                        } else {
                            if (Build.VERSION.SDK_INT >= 23 && !PermissionUtil.checkCameraAccess(getApplicationContext())) {
                                requestPermissions(new String[]{Manifest.permission.CAMERA}, 2);
                            }
                        }
                    }
                });
                //点击图片关闭按钮
                ivClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pwdPopWindow.hidePopWindow();
                    }
                });
                break;
            case R.id.rl_nickname:

                EditNicknameActivity.show(EditProfileActivity.this);
                break;
            case R.id.rl_age:
                EditAgeActivity.show(EditProfileActivity.this);
                break;
            default:
        }

    }

    /**
     * 选择相机
     */
    private void showCameraAction() {
        // 跳转到系统照相机
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(mActivity.getPackageManager()) != null) {
            // 设置系统相机拍照后的输出路径
            // 创建临时文件
            cameraTempFile = FileUtils.createTmpFile(mActivity, galleryConfig.getFilePath());
            Uri imageUri = FileProvider.getUriForFile(mContext, galleryConfig.getProvider(), cameraTempFile);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            cameraIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            List<ResolveInfo> resInfoList = mContext.getPackageManager().queryIntentActivities(cameraIntent, PackageManager.MATCH_DEFAULT_ONLY);
            for (ResolveInfo resolveInfo : resInfoList) {
                String packageName = resolveInfo.activityInfo.packageName;
                mContext.grantUriPermission(packageName, imageUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }

            startActivityForResult(cameraIntent, REQUEST_CAMERA);
        } else {
            Toast.makeText(mContext, R.string.gallery_msg_no_camera, Toast.LENGTH_SHORT).show();
            galleryConfig.getIHandlerCallBack().onError();
        }
    }

    /**
     * popupWindow dimiss
     */
    PopupWindow.OnDismissListener onDissmissListener = new PopupWindow.OnDismissListener() {
        @Override
        public void onDismiss() {

        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                同意授权
                GalleryPick.getInstance().setGalleryConfig(galleryConfig).open(EditProfileActivity.this);
            } else {
//                拒绝授权
            }
        } else if (requestCode == 2) {
            if (PermissionUtil.checkCameraAccess(getApplicationContext())) {
                showCameraAction();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CAMERA) {//调用相机回调数据
            if (resultCode == RESULT_OK) {
                if (cameraTempFile != null) {
                    if (!galleryConfig.isMultiSelect()) {
                        resultPhoto.clear();
                        if (galleryConfig.isCrop()) {
                            cropTempFile = FileUtils.getCorpFile(galleryConfig.getFilePath());
                            if (cropTempFile != null) {
                                //启动裁剪
                                startCropImage();
                            }
                            return;
                        }
                    }
                    resultPhoto.add(cameraTempFile.getAbsolutePath());
                    Log.e("test", "onActivityResult: " + resultPhoto);
                    mHandlerCallBack.onSuccess(resultPhoto);
                    exit();
                }
            } else {
                if (cameraTempFile != null && cameraTempFile.exists()) {
                    cameraTempFile.delete();
                }
                if (galleryConfig.isOpenCamera()) {
                    exit();
                }
            }
        } else if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {//裁剪回调数据
//            final Uri resultUri = UCrop.getOutput(data);
//            if (cameraTempFile != null && cameraTempFile.exists()) {
//                cameraTempFile.delete();
//            }
            resultPhoto.clear();
            resultPhoto.add(cropTempFile.getAbsolutePath());
            mHandlerCallBack.onSuccess(resultPhoto);
            exit();
        } else if (resultCode == UCrop.RESULT_ERROR) {
            galleryConfig.getIHandlerCallBack().onError();
//            final Throwable cropError = UCrop.getError(data);
        } else if (requestCode == EditNicknameActivity.requestCode) {
            Bundle bundle = data.getBundleExtra("intent");
            String bundle1 = bundle.getString("bundle");
            Log.e("test", "onActivityResult: " + bundle1);
        }
    }

    /**
     * 退出
     */
    private void exit() {
        mHandlerCallBack.onFinish();
//        finish();

//        移除栈顶的activity
//        ApplicationMain.removeCurrentActivity();

    }


    /**
     * 启动裁剪
     */
    private void startCropImage() {
        UCropUtils.start(mActivity, cameraTempFile, cropTempFile, galleryConfig.getAspectRatioX(), galleryConfig.getAspectRatioY(), galleryConfig.getMaxWidth(), galleryConfig.getMaxHeight());
    }


    /**
     * 初始化相册数据回调
     */
    private void initGallery() {
        iHandlerCallBack = new IHandlerCallBack() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(List<String> photoList) {
                Log.e("test", "onSuccess: " + photoList);
                path.clear();
                for (String s : photoList) {
                    path.add(s);
                }

                //TODO需要改成圆形头像

                String filePath = path.get(0);
                Bitmap bitmapSquare = BitmapFactory.decodeFile(filePath);//方形原图

                RoundedBitmapDrawable drawableA = RoundedBitmapDrawableFactory.create(getResources(), bitmapSquare);
                drawableA.setCircular(true);
                Bitmap bitmapCircle = drawableToBitmap(drawableA);

                //Gif会报错

                //设置头像
                civProfilePhoto.setImageBitmap(bitmapCircle);
//                Glide.with(EditProfileActivity.this).load(bitmapCircle).into(civProfilePhoto);

            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onFinish() {
            }

            @Override
            public void onError() {
            }
        };

    }

}
