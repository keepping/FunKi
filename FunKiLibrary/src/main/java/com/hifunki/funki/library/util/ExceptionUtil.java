package com.hifunki.funki.library.util;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * 1，规范异常处理。<br/>
 * 2，收集错误日志信息。<br/>
 * 3，在开发期抛出任何可能的异常，便于开发者定位问题。<br/>
 * 4，在正式版本中，会禁止任何异常抛出，减少crash率。<br/>
 */
public class ExceptionUtil {

    //--------------------- printStackTrace()---------------------------//

    /**
     * @param flag
     * @param e
     * @desc 错误打印(默认抛出异常)
     */
    public static void printStackTrace(String flag, Throwable e) {
        printStackTraceBase(flag, "", e, true);
    }

    /**
     * @param cls
     * @param e
     * @desc 错误打印(默认抛出异常)
     */
    public static void printStackTrace(Class<?> cls, Throwable e) {
        if (cls != null) {
            printStackTraceBase(cls.getSimpleName(), "", e, true);
        }
    }

    /**
     * @param flag
     * @param msg
     * @param e
     * @desc 错误打印(默认抛出异常)
     */
    public static void printStackTrace(String flag, String msg, Throwable e) {
        printStackTraceBase(flag, msg, e, true);
    }

    /**
     * @param cls
     * @param msg
     * @param e
     * @desc 错误打印(默认抛出异常)
     */
    public static void printStackTrace(Class<?> cls, String msg, Throwable e) {
        if (cls != null) {
            printStackTraceBase(cls.getSimpleName(), msg, e, true);
        }
    }

    /**
     * @param flag
     * @param e
     * @param isThrowException true:打印并抛出异常；false：只打印异常
     * @desc 错误打印。<br/>
     * 注意：建议优先使用{@link #printStackTrace(String, Throwable)}
     * ，只有当因为某些特定的环境或者无法避免的已知异常，且这种异常会影响开发、UI下一步操作、或者测试进度的时候才使用该函数
     */
    public static void printStackTrace(String flag, Throwable e, boolean isThrowException) {
        printStackTraceBase(flag, "", e, isThrowException);
    }

    /**
     * @param cls
     * @param e
     * @param isThrowException true:打印并抛出异常；false：只打印异常
     * @desc 错误打印<br/>
     * 注意：建议优先使用{@link #printStackTrace(Class, Throwable)}
     * ，只有当因为某些特定的环境或者无法避免的已知异常，且这种异常会影响开发、UI下一步操作、或者测试进度的时候才使用该函数
     */
    public static void printStackTrace(Class<?> cls, Throwable e, boolean isThrowException) {
        if (cls != null) {
            printStackTraceBase(cls.getSimpleName(), "", e, isThrowException);
        }
    }

    /**
     * @param flag
     * @param msg
     * @param e
     * @param isThrowExcep true:打印并抛出异常；false：只打印异常
     * @desc 错误打印<br/>
     * 注意：建议优先使用{@link #printStackTrace(String, String, Throwable)}
     * ，只有当因为某些特定的环境或者无法避免的已知异常，且这种异常会影响开发、UI下一步操作、或者测试进度的时候才使用该函数
     */
    public static void printStackTrace(String flag, String msg, Throwable e, boolean isThrowExcep) {
        printStackTraceBase(flag, msg, e, isThrowExcep);
    }

    /**
     * @param cls
     * @param msg
     * @param e
     * @param isThrowExcep
     * @desc 错误打印<br/>
     * 注意：建议优先使用{@link #printStackTrace(Class, String, Throwable)}
     * ，只有当因为某些特定的环境或者无法避免的已知异常，且这种异常会影响开发、UI下一步操作、或者测试进度的时候才使用该函数
     */
    public static void printStackTrace(Class<?> cls, String msg, Throwable e, boolean isThrowExcep) {
        if (cls != null) {
            printStackTraceBase(cls.getSimpleName(), msg, e, isThrowExcep);
        }
    }

    // --------------------- throw****Error()--------------------------- //

    /**
     * @param flag
     * @param msg
     * @desc 非法访问错误
     */
    public static void throwIllegalAccessError(String flag, String msg) {
        if (msg == null) {
            return;
        }

        LogUtils.e("ExceptionUtil", "throwIllegalAccessError() -> flag:" + flag + ", msg:" + msg);

//        if (isThrowException()) {
//            throw new IllegalAccessError(msg);
//        }
    }

    /**
     * @param flag
     * @param msg
     * @desc 非法状态错误
     */
    public static void throwIllegalStateExceptionError(String flag, String msg) {
        if (msg == null) {
            return;
        }

        LogUtils.e("ExceptionUtil", "throwIllegalStateExceptionError() -> flag:" + flag + ", msg:" + msg);

//        if (isThrowException()) {
//            throw new IllegalStateException(msg);
//        }
    }

    /**
     * @param flag
     * @param msg
     * @desc 空错误
     */
    public static void throwNullPointerExceptionError(String flag, String msg) {
        if (msg == null) {
            return;
        }

        LogUtils.e("ExceptionUtil", "throwNullPointerExceptionError() -> flag:" + flag + ", msg:" + msg);

//        if (isThrowException()) {
//            throw new NullPointerException(msg);
//        }
    }

    /**
     * @param flag
     * @param msg
     * @desc 类转换错误
     */
    public static void throwClassFormatError(String flag, String msg) {
        if (msg == null) {
            return;
        }

        LogUtils.e("ExceptionUtil", "throwClassFormatError() -> flag:" + flag + ", msg:" + msg);

//        if (isThrowException()) {
//            throw new ClassFormatError(msg);
//        }
    }

    /**
     * @return
     * @desc 收集Throwable错误日志信息
     */
    public static String getErrLogMsg(Throwable e) {
        if (e == null) {
            return "";
        }

        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        e.printStackTrace(printWriter);
        Throwable cause = e.getCause();

        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }

        String crashInfo = writer.toString();
        try {
            printWriter.close();
            writer.close();
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }

        return crashInfo;
    }

    // --------------------- private --------------------------- //

    /**
     * @param flag
     * @param msg
     * @param e
     * @param isThrowExcep true:打印并抛出异常；false：只打印异常
     * @desc 错误打印
     */
    private static void printStackTraceBase(String flag, String msg, Throwable e, boolean isThrowExcep) {
        if (e == null) {
            return;
        }

        e.printStackTrace();
        LogUtils.e(ExceptionUtil.class.getSimpleName(), "printStackTraceBase() -> flag:" + flag + ", msg:" + msg
                + "\r\n errMsg:" + getErrLogMsg(e));

//		if (isThrowException(isThrowExcep)) {
//			throw new IllegalAccessError("======= ExceptionUtil-> printStackTraceBase() =======");
//		}
    }

    /**
     * @return true:抛出异常；false：禁止抛出异常
     * @desc 非正式环境，抛出异常
     */
//    private static boolean isThrowException() {
//        return isThrowException(true);
//    }

    /**
     * @desc 是否抛出异常 （App正式环境禁止抛出异常，非正式环境根据isThrowEx决定是否抛出异常）
     * @param isThrowEx
     * @return true:抛出异常；false：禁止抛出异常
     */
//	private static boolean isThrowException(boolean isThrowEx) {
//		if (ApplicationMain.instance != null) {
//			byte currAppEnv = SettingsManager.getSettingsManager(ApplicationMain.instance).getAppEnvironment();
//
//			if (CommonConst.ENVIRONMENT_PRODUCTION == currAppEnv) {// App正式环境
//				return false;
//			} else {
//				return isThrowEx;
//			}
//		}
//
//		return false;
//	}

}
