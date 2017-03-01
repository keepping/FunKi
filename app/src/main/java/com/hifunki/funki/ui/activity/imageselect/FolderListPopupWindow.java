package com.hifunki.funki.ui.activity.imageselect;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.hifunki.funki.R;
import com.hifunki.funki.util.DisplayUtil;


/**
 * 文件夹列表选择
 * Created by Yancy on 2016/11/1.
 */
public class FolderListPopupWindow extends PopupWindow {

    private final static String TAG = "FolderListPopupWindow";

    private RecyclerView rvFolderList;

    private Context mContext;
    private Activity mActivity;
    private View popupWindow;
    private int mSize;

    private FolderAdapter folderAdapter;   // 文件夹适配器


    public FolderListPopupWindow(Activity mActivity, Context mContext, FolderAdapter folderAdapter, int size) {
        super(mContext);
        this.mContext = mContext;
        this.mActivity = mActivity;
        this.folderAdapter = folderAdapter;
        this.mSize = size;

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        popupWindow = inflater.inflate(R.layout.gallery_popup_folder, null);

        initView();
        init();

    }

    private void initView() {
        //设置透明度
        LinearLayout popopWindowLL =  (LinearLayout) popupWindow.findViewById(R.id.galley_popup_window);

        popopWindowLL.setAlpha((float) 0.9);

        rvFolderList = (RecyclerView) popupWindow.findViewById(R.id.rvFolderList);
    }

    private void init() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvFolderList.setLayoutManager(linearLayoutManager);
        rvFolderList.setAdapter(folderAdapter);

        //设置PopupWindow的View
        this.setContentView(popupWindow);
        //设置PopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);

        setPopupWindowHeight(mSize);


        //设置PopupWindow弹出窗体可点击
        this.setFocusable(false);

        //设置PopupWindow弹出窗体动画效果
//        this.setAnimationStyle(R.style.popupAnimation);



        //实例化一个ColorDrawable颜色为半透明
//        ColorDrawable dw = new ColorDrawable(0x60000000);
        ColorDrawable dw = new ColorDrawable();

        int f=(int)0.9;
        dw.setAlpha(f);

        //设置PopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);

    }

    /**
     * 设置PopupWindow高度
     *
     * @param size
     */
    public void setPopupWindowHeight(int size) {

        int screenHeight = DisplayUtil.getScreenHeight(mContext);

        Rect outRect = new Rect();
        mActivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect);
        int statusBarHeight = outRect.top;

        float singleHeight = DisplayUtil.dip2Px(mContext, 97);

        int totalHeight = (int) (singleHeight * (size+1));

        Log.d(TAG, "setPopupWindowHeight: ="+totalHeight);
        if (screenHeight + statusBarHeight > totalHeight) {
            this.setHeight(totalHeight);
        } else {
            //设置PopupWindow弹出窗体的高
            this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        }
    }


}
