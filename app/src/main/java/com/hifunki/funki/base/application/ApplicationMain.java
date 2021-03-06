package com.hifunki.funki.base.application;

import android.app.Activity;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.hifunki.funki.R;

import com.hifunki.funki.util.AppUtil;

import java.util.LinkedList;
import java.util.List;

import eu.siacs.conversations.clent.ImManager;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * ApplicationMain
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.base.application.ApplicationMain.java
 * @link
 * @since 2017-03-15 10:27:27
 */

public class ApplicationMain extends MultiDexApplication {
    static ApplicationMain application;
    static Context appContext;
    /**
     * 对于新增和删除操作add和remove，LinedList比较占优势，因为ArrayList实现了基于动态数组的数据结构，要移动数据。<br/>
     * LinkedList基于链表的数据结构,便于增加删除. XXX 作用不大
     */
    //如果解决冲突之后
    private static List<Activity> mActivityList = new LinkedList<>();

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        appContext = getApplicationContext();
        // 非App进程启动拦截
        if (!AppUtil.isMyProcessStartApp(this)) {
            return;
        }

        ImManager.init(this);

        //加载自定义字体
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/arial_0.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/FZDHTFW.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/FZZDXFW.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }

    public static Context getContext() {
        return appContext;
    }

    public static ApplicationMain getInstance() {
        return application;
    }

    /**
     * 获取所有activity的list
     *
     * @return
     */
    public static List<Activity> getActivityList() {
        return mActivityList;
    }

    /**
     * 退出栈顶的activity
     */
    public static void removeCurrentActivity() {
        mActivityList.size();
        mActivityList.remove(mActivityList.size()-1);
    }

    /**
     * 添加Activity到容器中
     *
     * @param activity
     */
    public static void addActivity(Activity activity) {
        if (mActivityList != null) {
            mActivityList.add(activity);
        }
    }

    /**
     * 删除Activity
     *
     * @param activity
     */
    public static void removeActivity(Activity activity) {
        if (mActivityList != null) {
            mActivityList.remove(activity);
        }
    }

    /**
     * 结束所有Activity
     */
    public static void finishAllActivity() {
        if (mActivityList != null) {
            for (Activity activity : mActivityList) {
                activity.finish();
            }
        }
    }

    /**
     * desc 退出app
     *
     * @param delayTime 延时退出时间
     */
    public static void exit(long delayTime) {
        finishAllActivity();
        AppUtil.killApp(delayTime);
    }
}
