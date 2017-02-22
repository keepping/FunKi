package com.hifunki.www.funki.util;

import android.app.ActivityManager;
import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by dell on 2017/2/22.
 */

public class AppUtil {

    /**
     * 是否App进程启动
     *
     * @param ctx 上下文
     * @return true:是
     */
    public static boolean isMyProcessStartApp(Context ctx) {
        if (ctx == null) {
            return false;
        }

        boolean isStartApp = true;
        String myProcessName = ctx.getPackageName();
        String pidProcessName = getProcessName(ctx, android.os.Process.myPid());

        if (!TextUtils.isEmpty(pidProcessName) && !pidProcessName.equals(myProcessName)) {
            isStartApp = false;
        }

        return isStartApp;
    }

    /**
     * 根据Pid获取进程名称
     *
     * @param cxt 上下文
     * @param pid 进程id
     * @return 进程名称
     */
    public static String getProcessName(Context cxt, int pid) {
        if (cxt == null) {
            return null;
        }

        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
        if (am == null) {
            return null;
        }

        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }

        for (int i = 0; i < runningApps.size(); i++) {
            ActivityManager.RunningAppProcessInfo procInfo = runningApps.get(i);
            if (procInfo != null && procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return null;
    }

    /**
     * 杀死App
     *
     * @param delayTime 延时时间，0表示立即杀死，无延时。
     */
    public static void killApp(long delayTime) {
        if (!killAppRightNow(delayTime)) {
            beginKillAppDelayJob(delayTime);
        }
    }

    //----------------- 私有函数 -------------------//

    /**
     * 开启延时杀死app任务
     *
     * @param delayTime 延时杀死app等待时间
     */
    private static void beginKillAppDelayJob(long delayTime) {
        new AsyncTask<Long, Void, Void>() {
            @Override
            protected Void doInBackground(Long... params) {
                try {
                    TimeUnit.MILLISECONDS.sleep(params[0]);
                } catch (InterruptedException e) {

                }
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                killAppRightNow(0);
                super.onPostExecute(result);
            }
        }.execute(delayTime);
    }

    /**
     * 立刻杀死app
     *
     * @param delayTime 延时时间 0表示立即杀死
     * @return false：未执行杀死操作；true：杀死进程
     */
    private static boolean killAppRightNow(long delayTime) {
        if (delayTime <= 0) {
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
            return true;
        }

        return false;
    }
}
