package com.hifunki.funki.module.photo.gallery.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.module.login.widget.ToolTitleBar;
import com.hifunki.funki.module.photo.gallery.config.GalleryConfig;
import com.hifunki.funki.module.photo.gallery.config.GalleryPick;
import com.hifunki.funki.module.photo.gallery.inter.GlideImageLoader;
import com.hifunki.funki.module.photo.gallery.inter.IHandlerCallBack;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.ui.activity.imageselect.PhotoActivity.java
 * @link
 * @since 2017-02-28 09:42:42
 */
public class PhotoActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.iv_selectimage)
    ImageView ivSelectimage;
    @BindView(R.id.et_nickname)
    EditText etNickname;
    @BindView(R.id.tv_complete)
    TextView tvComplete;
    @BindView(R.id.activity_select_image)
    LinearLayout activitySelectImage;

    private GalleryConfig galleryConfig;
    private IHandlerCallBack iHandlerCallBack;
    private List<String> path = new ArrayList<>();
    private final int PERMISSIONS_REQUEST_READ_CONTACTS = 8;
    private String TAG = "PhotoActivity";


    public static void show(Context context) {
        context.startActivity(new Intent(context, PhotoActivity.class));
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_select_image;
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initTitleBar() {
        ToolTitleBar.showLeftButton(this, activitySelectImage, ToolTitleBar.BTN_TYPE_IMAGE, R.drawable.iv_back, this);

        ToolTitleBar.showCenterButton(this, activitySelectImage, ToolTitleBar.BTN_TYPE_TEXT, R.string.visitor_title, null);
    }

    @Override
    protected void initView() {

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


    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initAdapter() {

    }


    @OnClick({R.id.iv_selectimage, R.id.et_nickname, R.id.tv_complete, R.id.activity_select_image})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rlTitleLeft:
                //back
                break;
            case R.id.iv_selectimage:
                initPermissions();
                break;
            case R.id.et_nickname:
                break;
            case R.id.tv_complete:
                break;
            case R.id.activity_select_image:
                break;
        }
    }


    private void initGallery() {
        iHandlerCallBack = new IHandlerCallBack() {
            @Override
            public void onStart() {
                Log.i(TAG, "onStart: 开启");
            }

            @Override
            public void onSuccess(List<String> photoList) {
                path.clear();
                for (String s : photoList) {
                    Log.i(TAG, s);
                    path.add(s);
                }

                String filePath = path.get(0);
                Bitmap bitmapSquare = BitmapFactory.decodeFile(filePath);//方形原图

                RoundedBitmapDrawable drawableA = RoundedBitmapDrawableFactory.create(getResources(), bitmapSquare);
                drawableA.setCircular(true);
                Bitmap bitmapCircle = drawableToBitmap(drawableA);

                ivSelectimage.setImageDrawable(new BitmapDrawable(getResources(), bitmapCircle));//设置头像

            }

            @Override
            public void onCancel() {
                Log.i(TAG, "onCancel: 取消");
            }

            @Override
            public void onFinish() {
                Log.i(TAG, "onFinish: 结束");
            }

            @Override
            public void onError() {
                Log.i(TAG, "onError: 出错");
            }
        };

    }


    // 授权管理
    private void initPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "需要授权 ");
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Log.i(TAG, "拒绝过了");
                Toast.makeText(this, "请在 设置-应用管理 中开启此应用的储存授权。", Toast.LENGTH_SHORT).show();
            } else {
                Log.i(TAG, "进行授权");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSIONS_REQUEST_READ_CONTACTS);
            }
        } else {
            Log.i(TAG, "不需要授权 ");
            GalleryPick.getInstance().setGalleryConfig(galleryConfig).open(PhotoActivity.this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.i(TAG, "同意授权");
                GalleryPick.getInstance().setGalleryConfig(galleryConfig).open(PhotoActivity.this);
            } else {
                Log.i(TAG, "拒绝授权");
            }
        }
    }


    public static Bitmap drawableToBitmap(Drawable drawable) {
        // 取 drawable 的长宽
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();

        // 取 drawable 的颜色格式
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        // 建立对应 bitmap
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        // 建立对应 bitmap 的画布
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        // 把 drawable 内容画到画布中
        drawable.draw(canvas);
        return bitmap;
    }

}
