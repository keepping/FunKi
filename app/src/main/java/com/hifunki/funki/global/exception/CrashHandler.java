package com.hifunki.funki.global.exception;

import android.os.Looper;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * 异常处理中心 (只针对运行时，未捕获的异常)
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.global.config.UserConfigss.java
 * @link
 * @since 2017-03-09 15:09:09
 */
public class CrashHandler implements UncaughtExceptionHandler {
	private static CrashHandler instance = new CrashHandler();

	private CrashHandler() {

	}

	/**
	 * @desc 获取CrashHandler实例 ,单例模式
	 * @return
	 */
	public static CrashHandler getInstance() {
		return instance;
	}

	/**
	 * 初始化
	 */
	public void init() {
		Thread.setDefaultUncaughtExceptionHandler(this);
	}

	/**
	 * 当UncaughtException发生时会转入该函数来处理
	 */
	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		try {
			// 记录日志
			long tid = Thread.currentThread().getId();
			saveLog(thread, ex, tid);

			// 主进程Crash处理
//			if (tid == CommonConst.THREAD_UI_ID) {
				showCrashTips();
//				ApplicationMain.exit(0);
//			} else {
				// dev tip
//			}
		} catch (Throwable e) {
			//e.printStackTrace();
		}
	}

	/**
	 * @desc 记录错误日志(如果ToolLog是正式环境，记录错误日志信息或发送邮件)
	 * @param thread
	 * @param ex
	 * @param threadId
	 */
	private void saveLog(Thread thread, Throwable ex, long threadId) {
		if (ex == null) {
			return;
		}

//		ToolLog.e(CrashHandler.class.getSimpleName(), ExceptionUtil.getErrLogMsg(ex), threadId);
	}

	/**
	 * 提示Crash，可以采用通知来解决
	 */
	private void showCrashTips() {
		new Thread() {
			@Override
			public void run() {
				Looper.prepare();
//				CustomToast.showToast(ApplicationMain.instance, R.string.app_crash_info);
				Looper.loop();
			}
		}.start();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			//e.printStackTrace();
		}
	}

}
