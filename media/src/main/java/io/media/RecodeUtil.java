package io.media;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.text.format.DateUtils;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;

import io.media.av.SessionConfig;

/**
 * Created by David Brodsky on 3/20/14.
 */
public class RecodeUtil {

    /**
     * Create a {@link SessionConfig}
     * corresponding to a 720p video stream
     *
     * @return the resulting SessionConfig
     */
    public static SessionConfig create720pSessionConfig(Activity context) {
        HashMap<String, String> extraData = new HashMap<String,String>();
        extraData.put("key", "value");

        File temFile = getRandomFilePath(context,".mp4",false);
        String temFilePath = temFile==null ? context.getCacheDir().getAbsolutePath() + System.currentTimeMillis()+".mp4" : temFile.getAbsolutePath();

        SessionConfig config = new SessionConfig.Builder()
                .withVideoResolution(1280, 720)
            //    .withVideoResolution(720, 1280)
                .withVideoBitrate(2 * 1000 * 1000)
                .withAudioBitrate(192 * 1000)
                .withExtraInfo(extraData)
                .withPrivateVisibility(false)
                .withOutFilePath(temFilePath)
                .build();
        return config;
    }

    /**
     * Create a {@link SessionConfig}
     * corresponding to a 420p video stream
     * @return the resulting SessionConfig
     */
    public static SessionConfig create420pSessionConfig(Activity activity) {
        File temFile = getRandomFilePath(activity,".mp4",false);
        String temFilePath = temFile==null ? activity.getCacheDir().getAbsolutePath() + System.currentTimeMillis()+".mp4" : temFile.getAbsolutePath();

        SessionConfig config = new SessionConfig.Builder()

                .withVideoBitrate(1000 * 1000)
                .withPrivateVisibility(false)
                .withVideoResolution(720, 480)
                .withOutFilePath(temFilePath)
                .build();
        return config;
    }



    /**
     *                       获取 随机文件   可设置后缀名
     * @param context       content
     * @param suffixName    后缀名
     * @param isDir         是否目录
     * @return
     */
    private static File getRandomFilePath(Context context, String suffixName , boolean isDir) {
        String path;

//        // 正式用法
//        if (context.getExternalCacheDir() != null) {
//            path = context.getExternalCacheDir().getAbsolutePath() + "/funki";
//        } else if (context.getCacheDir() != null) {
//            path = context.getCacheDir().getAbsolutePath() + "/funki";
//        }else {
//            path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/funki";
//        }

        // 测试用法 用于查看文件

        path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/funki";

        if (suffixName != null && !suffixName.startsWith(".")) {
            suffixName = "." + suffixName;
        }

        path = path + "/" + System.currentTimeMillis() + suffixName;
        File pathFile = new File(path);

        if(ensureFileExist(pathFile,isDir)){
            return pathFile;
        }else {
            return null;
        }
    }

    /**
     *                   确保文件存在 否则创建它
     * @param file      目标检查的文件
     * @param isDir     目标文件是否为目录
     * @return
     */
     private static boolean ensureFileExist(File file , boolean isDir){
        if(!isDir && file.isDirectory() || isDir && file.isFile()){
            if(!file.delete()){
                throw new RuntimeException("cannot delete file : "+file);
            }
        }
        if(file.exists()){
            return true;
        }
        if(ensureFileExist(file.getParentFile(),true)){
            if(isDir){
                return file.mkdir();
            } else {
                try {
                    return file.createNewFile();
                }catch (Exception e){
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return false;
    }

}
