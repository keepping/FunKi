package com.hifunki.funki.module.photo.gallery.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.FileProvider;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.module.photo.gallery.adapter.FolderAdapter;
import com.hifunki.funki.module.photo.gallery.adapter.PhotoGalleryAdapter;
import com.hifunki.funki.module.photo.gallery.config.GalleryConfig;
import com.hifunki.funki.module.photo.gallery.config.GalleryPick;
import com.hifunki.funki.module.photo.gallery.inter.IHandlerCallBack;
import com.hifunki.funki.module.photo.gallery.entity.FolderInfo;
import com.hifunki.funki.module.photo.gallery.entity.PhotoInfo;
import com.hifunki.funki.module.photo.gallery.util.UCropUtils;
import com.hifunki.funki.module.photo.gallery.widget.FolderListPopupWindow;
import com.hifunki.funki.module.photo.ucrop.UCrop;
import com.hifunki.funki.util.FileUtils;
import com.hifunki.funki.util.HashMapUtil;
import com.hifunki.funki.util.ListUtil;
import com.hifunki.funki.util.StatusBarUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 图片选择页面
 * Created by Yancy on 2016/1/26.
 */
public class GalleryPickActivity extends BaseActivity implements View.OnClickListener {

    private Context mContext = null;
    private GalleryPickActivity mActivity = null;
    private final static String TAG = "GalleryPickActivity";

    private ArrayList<String> resultPhoto;      //照片路径集合
    private boolean isOpenImage;                //是否点击所有图片
    private TextView tvFinish;                  // 完成按钮
    private TextView tvGalleryFolder;           // 文件夹按钮
    private LinearLayout btnGalleryPickBack;    // 返回按钮
    private RecyclerView rvGalleryImage;        // 图片列表
    private ImageView ivGalleryFolder;          //图片向下角标
    private TextView tvGalleryPreview;          //预览图片
    private LinearLayout llGallerySourceImage;  //原图控件

    private PhotoGalleryAdapter photoAdapter;              // 图片适配器
    private FolderAdapter folderAdapter;            // 文件夹适配器


    private List<FolderInfo> folderInfoList = new ArrayList<>();    // 本地文件夹信息List
    private List<PhotoInfo> photoInfoList = new ArrayList<>();      // 本地图片信息List

    private static final int LOADER_ALL = 0;         // 获取所有图片
    private static final int LOADER_CATEGORY = 1;    // 获取某个文件夹中的所有图片

    private boolean hasFolderScan = false;           // 是否扫描过

    private GalleryConfig galleryConfig;   // GalleryPick 配置器

    private static final int REQUEST_CAMERA = 100;   // 设置拍摄照片的 REQUEST_CODE

    private IHandlerCallBack mHandlerCallBack;   // GalleryPick 生命周期接口

    private FolderListPopupWindow folderListPopupWindow;   // 文件夹选择弹出框

    private LoaderManager.LoaderCallbacks<Cursor> mLoaderCallback;
    private HashMap<Integer, Boolean> isSelected;   //是否已经选中hashmap

    private File cameraTempFile;           //原始图片
    private File cropTempFile;           //待裁剪图片
    private int mSelectedPosition;       //选中图片的下表

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;
        mActivity = this;

        galleryConfig = GalleryPick.getInstance().getGalleryConfig();
        init();
        initPhoto();  //加载图片

    }

    @Override
    protected int getViewResId() {
        return R.layout.gallery_main;
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initTitleBar() {
        StatusBarUtil.setStatusBarBackground(this, R.drawable.iv_bg_status);
    }

    /**
     * 初始化视图
     */
    @Override
    protected void initView() {
        tvFinish = (TextView) super.findViewById(R.id.tvFinish);
        tvGalleryFolder = (TextView) super.findViewById(R.id.tvGalleryFolder);
        ivGalleryFolder = (ImageView) super.findViewById(R.id.iv_gallery_folder);//图片角标
        btnGalleryPickBack = (LinearLayout) super.findViewById(R.id.btnGalleryPickBack);
        rvGalleryImage = (RecyclerView) super.findViewById(R.id.rvGalleryImage);
        tvGalleryPreview = (TextView) super.findViewById(R.id.tv_gallery_preview);
        llGallerySourceImage = (LinearLayout) super.findViewById(R.id.ll_gallery_sourceimage);

        tvFinish.setOnClickListener(this);
        btnGalleryPickBack.setOnClickListener(this);
        tvGalleryFolder.setOnClickListener(this);
        tvGalleryPreview.setOnClickListener(this);
        llGallerySourceImage.setOnClickListener(this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initAdapter() {

    }

    /**
     * 初始化
     */
    private void init() {
        mHandlerCallBack = galleryConfig.getIHandlerCallBack();
        mHandlerCallBack.onStart();

        resultPhoto = galleryConfig.getPathList();

        final GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
        rvGalleryImage.setLayoutManager(gridLayoutManager);

        isSelected = new HashMap<>();

        photoAdapter = new PhotoGalleryAdapter(mActivity, mContext, photoInfoList, isSelected);
        photoAdapter.setOnCallBack(new PhotoGalleryAdapter.OnCallBack() {
            @Override
            public void OnClickCamera(List<String> selectPhotoList) {
                resultPhoto.clear();
                resultPhoto.addAll(selectPhotoList);
                showCameraAction();
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


//        Log.e("test", "init: "+resultPhoto);
        photoAdapter.setSelectPhoto(resultPhoto);
        rvGalleryImage.setAdapter(photoAdapter);


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


    /**
     * 初始化配置
     */
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

                        fillIsSelected();

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CAMERA) {
//            Log.i(TAG, "onActivityResult: " + resultCode);
            if (resultCode == RESULT_OK) {
                if (cameraTempFile != null) {
                    if (!galleryConfig.isMultiSelect()) {
                        resultPhoto.clear();
                        if (galleryConfig.isCrop()) {
                            cropTempFile = FileUtils.getCorpFile(galleryConfig.getFilePath());
                            //启动裁剪
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
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 退出
     */
    private void exit() {
        mHandlerCallBack.onFinish();
        finish();

//        移除栈顶的activity
//        ApplicationMain.removeCurrentActivity();

    }

//        http://stackoverflow.com/questions/6092862/will-calling-finish-from-an-activity-free-up-my-memory-space

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放资源
        Runtime.getRuntime().gc();
    }

    /**
     * 回退键监听
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (folderListPopupWindow != null && folderListPopupWindow.isShowing()) {
                folderListPopupWindow.dismiss();
                return true;
            }
            mHandlerCallBack.onCancel();
            exit();
        }
        return true;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvFinish://确定按钮
//                if (resultPhoto != null && resultPhoto.size() > 0) {
//                    mHandlerCallBack.onSuccess(resultPhoto);
//                    exit();
//                }
                startCropImage();
                break;
            case R.id.btnGalleryPickBack://返回键
                mHandlerCallBack.onCancel();
                exit();
                break;
            case R.id.tvGalleryFolder://所有图片文件夹
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
                    GalleryVpActivity.show(this, mSelectedPosition, photoInfoList.size());
                }
                break;
            case R.id.ll_gallery_sourceimage://点击原图

                break;
        }
    }


    /**
     * 改变图片的选中状态
     */
    private void changeImageState() {
        isOpenImage = !isOpenImage;
        Drawable drawableDown = getResources().getDrawable(R.drawable.gallery_pick_dropdown_white);
        Drawable drawableUp = getResources().getDrawable(R.drawable.gallery_pick_up_white);
        if (isOpenImage) {
            ivGalleryFolder.setImageDrawable(drawableUp);
        } else {
            ivGalleryFolder.setImageDrawable(drawableDown);
        }
    }

    /**
     * 启动裁剪
     */
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

}