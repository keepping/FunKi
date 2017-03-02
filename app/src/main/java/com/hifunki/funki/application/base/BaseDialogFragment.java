//package com.hifunki.funki.ui.fragment.base;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Process;
//import android.support.v4.app.DialogFragment;
//import android.support.v4.app.FragmentManager;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.hifunki.funki.common.BundleConst;
//import com.hifunki.funki.global.config.SettingsManager;
//import com.hifunki.funki.global.config.UserConfigss;
//import com.hifunki.funki.application.ApplicationMain;
//import com.hifunki.funki.util.ViewUtil;
//
//
///**
// * desc 所有DialogFragment基类 <br/>
// * 关闭Dialog请使用 {@link #(int, Intent)} 函数,否则可能导致主程序挂bug <br/>
// */
//public abstract class BaseDialogFragment extends DialogFragment implements View.OnClickListener, IFragment {
//
//    /**
//     * BI参数：pvid
//     */
//    private String mBiParamPvid;
//
//    /**
//     * 当前Fragment视图对象，获取UI控件请用此对象，如：findViewById()...
//     */
//    protected View mFragmentView;
//
//    /**
//     * 主Activity对象
//     */
//    protected MainFragmentActivity mainActivity;
//
//    /**
//     * 全局配置对象
//     */
//    protected SettingsManager mSettingsMgr;
//
//    /**
//     * 回调对象
//     */
//    protected ICallback mCallback;
//
//    /**
//     * 请求当前页面Code码
//     */
//    protected short mRequestCode;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        mFragmentView = inflater.inflate(getFragmentViewResId(), container, false);
//        return mFragmentView;
//    }
//
//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//        // 程序内存已被系统回收
//        if (UserConfigss.getInstance(getActivity()).getAppId() != Process.myPid()) {
//            return;
//        }
//
//        // 初始化准备工作
//        initPrepare();
//
//        // 初始化Fragment
//        initFragment();
//
//        // 分析数据
//        analyseData4Init();
//    }
//
//    @Override
////    public void onDestroy() {
////        super.onDestroy();
////        ToolHttp.getInstance(ApplicationMain.instance).cancelAndFinishRequests(listener, getTagNameCls());
////        //TODO 发布注释
////        // ApplicationMain.getRefWatcher().watch(this);
////    }
//
//    /**
//     * 初始化准备
//     */
////    @Override
////    public final void initPrepare() {
////        mainActivity = (MainFragmentActivity) getActivity();
////        mSettingsMgr = SettingsManager.getSettingsManager(mainActivity);
////
////
//////        if (getArguments() != null) {
//////            mBiParamPvid = getArguments().getString(BundleConst.KEY_BI_PARAM_PVID, "");
//////        }
////
//////        BusinessCommon.setLayoutDirection4Language(mFragmentView);
////    }
//
//    /**
//     * 初始化Fragment
//     */
//    private final void initFragment() {
////        initVariable();
////        initView();
////        initListener();
////        initAdapter();
////        bindData();
//    }
//
//    /**
//     * 分析数据
//     */
//    protected void analyseData4Init() {
//        // GA
//        // 注：子类实现，请第一句主动调用 super.analyseData4Init();
//    }
//
////    @Override
////    public final void onClick(View v) {
////        // doSomething, 譬如GA事件收集，过滤相关点击等等
////        FragmentCommon.onClick(this, v);
////    }
//
//    /**
//     * View.OnClick点击事件
//     *
//     * @param v 点击的view
//     */
//    @Override
//    public void onViewClick(View v) {
//        //子类重写
//    }
//
//    /**
//     * 一次设置多个控件CLick监听事件
//     *
//     * @param views view控件
//     */
//    public void setViewsOnClickListener(View... views) {
//        ViewUtil.setViewsOnClickListener(this, views);
//    }
//
//    /**
//     * 请求服务器正常情况 回调对象
//     */
////    protected Response.JListener<String> listener = new Response.JListener<String>() {
////        @Override
////        public void onResponse(String response, String url, int seqNum, long requestBirthTime,
////                               byte responseContentParserType, Object fastJsonObj) {
////            FragmentCommon.onServerSuccess(BaseDialogFragment.this, response, url, seqNum, requestBirthTime,
////                    responseContentParserType, fastJsonObj);
////        }
////    };
//
//    /**
//     * 请求服务器异常情况 回调对象
//     */
////    protected Response.ErrorListener errorListener = new Response.ErrorListener() {
////        @Override
////        public void onErrorResponse(VolleyError error, String url, int seqNum, long requestBirthTime) {
////            FragmentCommon.onServerError(BaseDialogFragment.this, error, url, seqNum, requestBirthTime);
////        }
////    };
////
////    @Override
////    public void onServerJsonParseErr(int rtnCode, String url, String msg, int seqNum) {
////        //子类重写
////    }
////
////    @Override
////    public void onServerOkResponseFastJson(ServerResponseEntity entity, String url, int seqNum) {
////        // 子类重写
////    }
//
//    // ============== 回调 ============== ///
//
//    /**
//     * 显示对话框
//     * @param manager
//     * @param callback
//     * @param requestCode
//     */
////    public void showWithCallback(FragmentManager manager, ICallback callback, short requestCode) {
////        super.show(manager, getTagNameCls());
////        this.mCallback = callback;
////        this.mRequestCode = requestCode;
////    }
//
//    /**
//     * 关闭对框框，并回调
//     * @param responseCode
//     * @param intent
//     */
////    public int dismissWithCallback(int responseCode, Intent intent) {
////        int result = callback(responseCode, intent);
////        if(!ToolApp.isAppOnForeground(mainActivity)){
////            super.dismissAllowingStateLoss();
////        }else{
////            super.dismiss();
////        }
////        return result;
////    }
//
//    /**
//     * 回调
//     * @param responseCode
//     * @param intent
//     * @return
//     */
////    protected int callback(int responseCode, Intent intent) {
////        if (mCallback != null) {
////            return mCallback.onCallback(mRequestCode, responseCode, intent);
////        }
////
////        return ICallback.RETURN_DEFALUT_VALUE;
////    }
//
//    /**
//     * 显示dialog（禁止重写）
//     */
//    @Override
//    public final void show(FragmentManager manager, String tag) {
//        super.show(manager, tag);
//    }
//
//    /**
//     * 关闭dialog（禁止重写）
//     */
//    @Override
//    public final void dismiss() {
//        super.dismiss();
//    }
//
//    // /////////bi 相关/////////////
//
//    /**
//     * 修改pvid
//     *
//     * @param pvid pvid
//     * @since V3.8
//     */
//    public final void changeBiParamPvid(String pvid) {
//        mBiParamPvid = pvid;
//    }
//
//    /**
//     * 获取pvid
//     *
//     * @return pvid
//     * @since V3.8
//     */
//    public final String getBiParamPvidNew() {
//        //if(mBiParamPvid==null){
//        // TODO: 15/11/27 下面一句移动到 初始化过程中检测
//        //    ExceptionUtil.throwIllegalAccessError(this.getClass().getSimpleName(), "==getBiParamPvidNew() -> mBiParamPvid is null!");
//        // }
//        return mBiParamPvid;
//    }
//
//}
