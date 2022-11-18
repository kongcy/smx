package com.xtxk.config.cache;

import com.xtxk.core.util.A;
import com.xtxk.core.util.U;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * User: chenying
 * Date: 2019-08-14
 * Time: 16:06
 * since: 1.0.0
 */
public abstract class Cache {

    /**
     * 存放配置数据
     */
    private static Map<String, Object> concurrentHashMap = new ConcurrentHashMap<>();

    /**
     * 获取缓存数据
     */
    public static Map<String, Object> getCache() {
        return concurrentHashMap;
    }

    /**
     * 存放配置数据
     */
    public static void setCache(Map<String, Object> dataMap) {
        if (A.isNotEmpty(dataMap)) {
            concurrentHashMap.clear();
            concurrentHashMap.putAll(dataMap);
        }
    }

    /**
     * 获取value值
     */
    public static Object get(String key) {
        if (U.isBlank(key)) {
            return U.EMPTY;
        }
        return concurrentHashMap.get(key);
    }
}
