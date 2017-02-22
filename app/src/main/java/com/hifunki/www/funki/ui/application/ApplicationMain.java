package com.hifunki.www.funki.ui.application;

import android.app.Activity;
import android.app.Application;

import com.hifunki.www.funki.R;
import com.hifunki.www.funki.util.AppUtil;

import java.util.LinkedList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by dell on 2017/2/22.
 */

public class ApplicationMain extends Application {

    /**
     * 对于新增和删除操作add和remove，LinedList比较占优势，因为ArrayList实现了基于动态数组的数据结构，要移动数据。<br/>
     * LinkedList基于链表的数据结构,便于增加删除. XXX 作用不大
     */
    //如果解决冲突之后
    private static List<Activity> mActivityList = new LinkedList<>();

    @Override
    public void onCreate() {
        super.onCreate();

        // 非App进程启动拦截
        if (!AppUtil.isMyProcessStartApp(this)) {
            return;
        }

        //加载自定义字体
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Fan_Big.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Fan_Normal.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());

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

    // TODO: 2015/10/17 end 抽取ActivityManger

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
