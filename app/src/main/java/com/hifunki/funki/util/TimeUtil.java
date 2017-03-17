package com.hifunki.funki.util;

/**
 * 在此写用途
 *
 * @author yinhaoxiang
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.util.TimeUtil.java
 * @link
 * @since 2017-03-17 17:11:11
 */
public class TimeUtil {

    // milliseconds 时间长度 12:34:12
    public static String getTime(long milliseconds , int minTimeLenth) {

        StringBuilder stringBuffer = new StringBuilder();
        int value;
        int timeLen =0;
        milliseconds = milliseconds / 1000;



        // 秒
        value = (int) (milliseconds % 60);
        milliseconds = milliseconds / 60;
        stringBuffer.insert(0, value<10 ? "0"+String.valueOf( value) : String.valueOf(value));
        timeLen++;
        if (milliseconds == 0) {
            for(;timeLen<minTimeLenth;timeLen++){
                stringBuffer.insert(0, "00:");
            }
            return stringBuffer.toString();
        }

        // 分
        value = (int) (milliseconds%60);
        milliseconds = milliseconds/60;
        stringBuffer.insert(0, value<10 ? "0"+String.valueOf( value) + ":" : String.valueOf(value)+ ":" );
        timeLen++;
        if (milliseconds == 0) {
            for(;timeLen<minTimeLenth;timeLen++){
                stringBuffer.insert(0, "00:");
            }
            return stringBuffer.toString();
        }


        // 小时
        value = (int) (milliseconds%24);
        milliseconds = milliseconds%24;
        stringBuffer.insert(0, value<10 ? "0"+String.valueOf( value) + ":" : String.valueOf(value)+ ":" );
        timeLen++;
        if (milliseconds == 0) {
            for(;timeLen<minTimeLenth;timeLen++){
                stringBuffer.insert(0, "00:");
            }
            return stringBuffer.toString();
        }


        return stringBuffer.toString();
    }

    // 1:1分钟内   2:1小时以内   3:1年以内   4:1个月以内   5:1年以内  6:一年以上
    public static int getTimeLenth(long milliseconds){
        milliseconds = milliseconds/1000;

        if(milliseconds<60) return 1;

        milliseconds = milliseconds/60;
        if(milliseconds<60) return 2;

        milliseconds = milliseconds/24;
        if(milliseconds<24) return 3;

        milliseconds = milliseconds/30;
        if(milliseconds<30) return 4;

        milliseconds = milliseconds/12;
        if(milliseconds<12) return 5;

        return 6;
    }


}






















