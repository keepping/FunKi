package com.hifunki.funki.module.photo.gallery.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.FileProvider;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.module.photo.gallery.adapter.FolderAdapter;
import com.hifunki.funki.module.photo.gallery.adapter.PhotoGalleryAdapter;
import com.hifunki.funki.module.photo.gallery.config.GalleryConfig;
import com.hifunki.funki.module.photo.gallery.config.GalleryPick;
import com.hifunki.funki.module.photo.gallery.entity.FolderInfo;
import com.hifunki.funki.module.photo.gallery.entity.PhotoInfo;
import com.hifunki.funki.module.photo.gallery.inter.IHandlerCallBack;
import com.hifunki.funki.module.photo.gallery.util.UCropUtils;
import com.hifunki.funki.module.photo.gallery.widget.FolderListPopupWindow;
import com.hifunki.funki.module.photo.ucrop.UCrop;
import com.hifunki.funki.util.FileUtils;
import com.hifunki.funki.util.HashMapUtil;
import com.hifunki.funki.util.ListUtil;
import com.hifunki.funki.util.PermissionUtil;
import com.hifunki.funki.widget.bar.TopBarView4Gallery;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 图片选择页面
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.photo.gallery.activity.GalleryVpActivity.java
 * @link
 * @since 2017-03-03 17:36:36
 */
public class GalleryPickActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tbv_bill)
    TopBarView4Gallery topBarView4Gallery;
    @BindView(R.id.rl_all_photo)
    RelativeLayout rlAllPhoto;
    @BindView(R.id.ll_preview)// 文件夹按钮
            LinearLayout llPreview;
    @BindView(R.id.tv_gallery_preview)
    TextView tvGalleryPreview;
    @BindView(R.id.ll_gallery_source_image)
    LinearLayout llImageSize;
    @BindView(R.id.rvGalleryImage)// 图片列表
            RecyclerView rvGalleryImage;
    @BindView(R.id.iv_gallery_icon)//图片向下角标
            ImageView ivGalleryIcon;
    @BindView(R.id.tv_all_photo)
    TextView tvGalleryFolder;
    private TextView tvFinish;
    private Context mContext;
    private Activity mActivity;
    private String TAG = getClass().getSimpleName();
    private ArrayList<String> resultPhoto;      //照片路径集合
    private boolean isOpenImage;                //是否点击所有图片

    private PhotoGalleryAdapter photoAdapter;              // 图片适配器
    private FolderAdapter folderAdapter;            // 文件夹适配器

    private List<FolderInfo> folderInfoList = new ArrayList<>();    // 本地文件夹信息List
    private List<PhotoInfo> photoInfoList = new ArrayList<>();      // 本地图片信息List
    private boolean hasFolderScan = false;           // 是否扫描过
    private GalleryConfig galleryConfig;   // GalleryPick 配置器
    private IHandlerCallBack mHandlerCallBack;   // GalleryPick 生命周期接口
    private FolderListPopupWindow folderListPopupWindow;   // 文件夹选择弹出框
    private LoaderManager.LoaderCallbacks<Cursor> mLoaderCallback;
    private HashMap<Integer, Boolean> isSelected;   //是否已经选中hashmap

    private File cameraTempFile;           //原始图片
    private File cropTempFile;           //待裁剪图片
    private int mSelectedPosition;       //选中图片的下表

    private static final int LOADER_ALL = 0;         // 获取所有图片
    private static final int LOADER_CATEGORY = 1;    // 获取某个文件夹中的所有图片
    private static final int REQUEST_CAMERA = 100;   // 设置拍摄照片的 REQUEST_CODE
    private GridLayoutManager gridLayoutManager;

    @Override
    protected int getViewResId() {
        return R.layout.photo_gallery_main;
    }

    @Override
    protected void initVariable() {
        mContext = GalleryPickActivity.this;
        mActivity = this;
        galleryConfig = GalleryPick.getInstance().getGalleryConfig();
        mHandlerCallBack = galleryConfig.getIHandlerCallBack();
        mHandlerCallBack.onStart();
        resultPhoto = galleryConfig.getPathList();
        isSelected = new HashMap<>();
    }

    @Override
    protected void initView() {
        tvFinish = topBarView4Gallery.getMenuText();

        initPhoto();  //加载图片
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initAdapter() {
        initPhotoAdapter();
        initFolderAdapter();
    }

    private void initPhotoAdapter() {
        gridLayoutManager = new GridLayoutManager(mContext, 3);
        rvGalleryImage.setLayoutManager(gridLayoutManager);

        photoAdapter = new PhotoGalleryAdapter(mActivity, mContext, photoInfoList, isSelected);
        photoAdapter.setOnCallBack(new PhotoGalleryAdapter.OnCallBack() {
            @Override
            public void OnClickCamera(List<String> selectPhotoList) {
                resultPhoto.clear();
                resultPhoto.addAll(selectPhotoList);
                //检查相机权限
                if (PermissionUtil.checkCameraAccess(getApplicationContext())) {
                    showCameraAction();
                } else {
                    if (Build.VERSION.SDK_INT >= 23 && !PermissionUtil.checkCameraAccess(getApplicationContext())) {
                        requestPermissions(new String[]{Manifest.permission.CAMERA}, 1);
                    }
                }
            }

            @Override
            public void OnClickPhoto(List<String> selectPhotoList) {
                resultPhoto.clear();
                resultPhoto.addAll(selectPhotoList);
                if (!galleryConfig.isMultiSelect() && resultPhoto != null && resultPhoto.size() > 0) {
                    if (galleryConfig.isCrop()) {
                        cameraTempFile = new File(resultPhoto.get(0));
                        cropTempFile = FileUtils.getCorpFile(galleryConfig.getFilePath());
                        startCropImage();
                        return;
                    }
                    mHandlerCallBack.onSuccess(resultPhoto);
                    exit();
                }
            }

            //选中照片操作
            @Override
            public void OnSelectPhoto(List<String> selectPhotoList, int position) {
                resultPhoto.clear();
                resultPhoto.addAll(selectPhotoList);
                if (resultPhoto != null && resultPhoto.size() > 0) {
                    cameraTempFile = new File(resultPhoto.get(0));
                    cropTempFile = FileUtils.getCorpFile(galleryConfig.getFilePath());
                }
                mSelectedPosition = position;
                tvGalleryPreview.setClickable(true);
                tvFinish.setVisibility(View.VISIBLE);
            }

            //未选中照片操作
            @Override
            public void OnNoSelectPhoto(List<String> selectPhotoList, int position) {
                resultPhoto.clear();
                resultPhoto.addAll(selectPhotoList);
                mSelectedPosition = -1;
                tvGalleryPreview.setClickable(false);
                tvFinish.setVisibility(View.GONE);
            }
        });
//        photoAdapter.setSelectPhoto(resultPhoto);
        rvGalleryImage.setAdapter(photoAdapter);
    }

    private void initFolderAdapter() {
        //设置文件夹适配器
        folderAdapter = new FolderAdapter(mActivity, mContext, folderInfoList);
        folderAdapter.setOnClickListener(new FolderAdapter.OnClickListener() {
            @Override
            public void onClick(FolderInfo folderInfo) {
                if (folderInfo == null) {
                    getSupportLoaderManager().restartLoader(LOADER_ALL, null, mLoaderCallback);
                    tvGalleryFolder.setText(R.string.gallery_all_folder);
                } else {
                    photoInfoList.clear();
                    photoInfoList.addAll(folderInfo.photoInfoList);

                    //刷新PopupWindow高度
                    folderListPopupWindow.setPopupWindowHeight(photoInfoList.size());

                    fillIsSelected();

                    photoAdapter.notifyDataSetChanged();
                    tvGalleryFolder.setText(folderInfo.name);
                }
                folderListPopupWindow.dismiss();
                gridLayoutManager.scrollToPosition(0);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (PermissionUtil.checkCameraAccess(getApplicationContext())) {
            showCameraAction();
        }
    }

    private void initPhoto() {
        mLoaderCallback = new LoaderManager.LoaderCallbacks<Cursor>() {

            private final String[] IMAGE_PROJECTION = {
                    MediaStore.Images.Media.DATA,
                    MediaStore.Images.Media.DISPLAY_NAME,
                    MediaStore.Images.Media.DATE_ADDED,
                    MediaStore.Images.Media._ID,
                    MediaStore.Images.Media.SIZE
            };

            @Override
            public Loader<Cursor> onCreateLoader(int id, Bundle args) {
                if (id == LOADER_ALL) {
                    return new CursorLoader(mActivity, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_PROJECTION, null, null, IMAGE_PROJECTION[2] + " DESC");
                } else if (id == LOADER_CATEGORY) {
                    return new CursorLoader(mActivity, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_PROJECTION, IMAGE_PROJECTION[0] + " like '%" + args.getString("path") + "%'", null, IMAGE_PROJECTION[2] + " DESC");
                }
                return null;
            }

            @Override
            public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
                if (data != null) {
                    int count = data.getCount();
                    if (count > 0) {
                        List<PhotoInfo> tempPhotoList = new ArrayList<>();
                        data.moveToFirst();
                        do {
                            String path = data.getString(data.getColumnIndexOrThrow(IMAGE_PROJECTION[0]));
                            String name = data.getString(data.getColumnIndexOrThrow(IMAGE_PROJECTION[1]));
                            long dateTime = data.getLong(data.getColumnIndexOrThrow(IMAGE_PROJECTION[2]));
                            int size = data.getInt(data.getColumnIndexOrThrow(IMAGE_PROJECTION[4]));//获取图片大小，单位bytes

                            boolean showFlag = size > 1024 * 5;                           //是否大于5K
                            PhotoInfo photoInfo = new PhotoInfo(path, name, dateTime, size);
                            if (showFlag) {
                                tempPhotoList.add(photoInfo);
                            }
                            if (!hasFolderScan && showFlag) {
                                File photoFile = new File(path);                  // 获取图片文件
                                File folderFile = photoFile.getParentFile();      // 获取图片上一级文件夹

                                FolderInfo folderInfo = new FolderInfo();
                                folderInfo.name = folderFile.getName();
                                folderInfo.path = folderFile.getAbsolutePath();
                                folderInfo.photoInfo = photoInfo;
                                if (!folderInfoList.contains(folderInfo)) {      // 判断是否是已经扫描到的图片文件夹
                                    List<PhotoInfo> photoInfoList = new ArrayList<>();
                                    photoInfoList.add(photoInfo);
                                    folderInfo.photoInfoList = photoInfoList;
                                    folderInfoList.add(folderInfo);
                                } else {
                                    FolderInfo f = folderInfoList.get(folderInfoList.indexOf(folderInfo));
                                    f.photoInfoList.add(photoInfo);
                                }
                            }
                        } while (data.moveToNext());
                        photoInfoList.clear();
                        photoInfoList.addAll(tempPhotoList);
                        List<String> tempPhotoPathList = new ArrayList<>();
                        for (PhotoInfo photoInfo : photoInfoList) {
                            tempPhotoPathList.add(photoInfo.path);
                        }
                        //添加之前初始化选中的图片
                        for (String mPhotoPath : galleryConfig.getPathList()) {
                            if (!tempPhotoPathList.contains(mPhotoPath)) {
                                PhotoInfo photoInfo = new PhotoInfo(mPhotoPath, null, 0L, 0);
                                photoInfoList.add(0, photoInfo);
                            }
                        }
                        for (int i = 0; i < photoInfoList.size() + 1; i++) {
                            isSelected.put(i, false);
                        }
                        photoAdapter.notifyDataSetChanged();
                        hasFolderScan = true;
                    }
                }
            }

            @Override
            public void onLoaderReset(Loader<Cursor> loader) {

            }
        };
        getSupportLoaderManager().restartLoader(LOADER_ALL, null, mLoaderCallback);   // 扫描手机中的图片
    }

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CAMERA) {
            if (resultCode == RESULT_OK) {
                if (cameraTempFile != null) {
                    if (!galleryConfig.isMultiSelect()) {
                        resultPhoto.clear();
                        if (galleryConfig.isCrop()) {
                            cropTempFile = FileUtils.getCorpFile(galleryConfig.getFilePath());
                            startCropImage();
                            return;
                        }
                    }
                    resultPhoto.add(cameraTempFile.getAbsolutePath());
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
        } else if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {

            resultPhoto.clear();
            resultPhoto.add(cropTempFile.getAbsolutePath());
            mHandlerCallBack.onSuccess(resultPhoto);
            exit();
        } else if (resultCode == UCrop.RESULT_ERROR) {
            galleryConfig.getIHandlerCallBack().onError();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @OnClick({R.id.tv_menu, R.id.tv_all_photo, R.id.tv_gallery_preview, R.id.ll_gallery_source_image})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_menu://确定按钮
                startCropImage();
                break;
            case R.id.tv_all_photo://所有图片文件夹
                changeImageState();

                if (folderListPopupWindow != null && folderListPopupWindow.isShowing()) {
                    folderListPopupWindow.dismiss();
                    return;
                }
                if (!ListUtil.isEmpty(folderInfoList)) {
                    int size = folderInfoList.size();
                    folderListPopupWindow = new FolderListPopupWindow(mActivity, mContext, folderAdapter, size);
                    folderListPopupWindow.showAsDropDown(tvGalleryFolder);
                }
                break;
            case R.id.tv_gallery_preview://预览按钮
                if (!ListUtil.isEmpty(photoInfoList) && mSelectedPosition != -1 && mSelectedPosition != 0) {
                    ArrayList<PhotoInfo> arrayList = new ArrayList<>();
                    arrayList.addAll(photoInfoList);
                    GalleryVpActivity.show(this, mSelectedPosition, arrayList);
                }
                break;
            case R.id.ll_gallery_source_image://点击原图
                break;
        }
    }

    private void changeImageState() {
        isOpenImage = !isOpenImage;
        Drawable drawableDown = getResources().getDrawable(R.drawable.iv_gallery_pick_dropdown_white);
        Drawable drawableUp = getResources().getDrawable(R.drawable.iv_gallery_pick_up_white);
        if (isOpenImage) {
            ivGalleryIcon.setImageDrawable(drawableUp);
        } else {
            ivGalleryIcon.setImageDrawable(drawableDown);
        }
    }

    private void startCropImage() {
        UCropUtils.start(mActivity, cameraTempFile, cropTempFile, galleryConfig.getAspectRatioX(), galleryConfig.getAspectRatioY(), galleryConfig.getMaxWidth(), galleryConfig.getMaxHeight());
    }

    /**
     * 填充hashmap数据
     */
    private void fillIsSelected() {
        HashMapUtil.removeHashMap(isSelected);
        for (int i = 0; i < photoInfoList.size() + 1; i++) {
            isSelected.put(i, false);
        }
    }


    @Override
    protected void bindData() {

    }

    @Override
    protected void bindData4NoNet() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (folderListPopupWindow != null && folderListPopupWindow.isShowing()) {
            folderListPopupWindow.dismiss();
        }
        mHandlerCallBack.onCancel();
        exit();
    }

    private void exit() {
        mHandlerCallBack.onFinish();
        finish();
    }


}
