package com.hifunki.funki.util;

import java.util.HashMap;
import java.util.Iterator;

/**
 * HashMap工具类
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.util.HashMapUtil.java
 * @link
 * @since 2017-03-03 18:27:27
 */
public class HashMapUtil {

    /**
     * 清空hashMap里面数据
     *
     * @param hashMap
     */
    public static void removeHashMap(HashMap hashMap) {
        if (!hashMap.isEmpty()) {
            Iterator<Integer> iterator = hashMap.keySet().iterator();
            while (iterator.hasNext()) {
                Integer integer = iterator.next();
                iterator.remove();
            }
        }
    }
}
