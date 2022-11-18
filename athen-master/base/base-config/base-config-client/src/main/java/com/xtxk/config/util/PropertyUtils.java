package com.xtxk.config.util;


import com.xtxk.config.cache.Cache;
import com.xtxk.config.model.Config;
import com.xtxk.core.date.DateFormatType;
import com.xtxk.core.date.DateUtil;
import com.xtxk.core.json.JsonUtil;
import com.xtxk.core.util.A;
import com.xtxk.core.util.HttpUtil;
import com.xtxk.core.util.LogUtil;
import com.xtxk.core.util.U;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 属性文件工具类
 * User: chenying
 * Date: 2019-08-14
 * Time: 14:18
 * since: 1.0.0
 */
public abstract class PropertyUtils {
    private static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    static {
        init();
    }

    private static void init() {
        if (isConfig()) {
            Cache.setCache(getDataFromConfig());
            refreshData();
        }
    }

    /**
     * 读取配置
     **/
    private static Map<String, Object> getDataFromConfig() {
        String configUrl = getEnvValue("confCenterUrl");
        if (U.isBlank(configUrl)) {
            // throw new RuntimeException("启动参数配置中心地址找不到: DconfCenterUrl");
            LogUtil.ROOT_LOG.warn("----------------------------------启动参数配置中心地址找不到: DconfCenterUrl,将读取项目配置文件属性--------------------------------");
        }
        String user = getEnvValue("DconfUser");
        try {
            if (LogUtil.ROOT_LOG.isInfoEnabled()) {
                LogUtil.ROOT_LOG.info("获取配置中心地址URL: {}", configUrl);
            }
            Map<String, Object> params = A.maps("user", user);
            String json = HttpUtil.get(configUrl, params);
            if (U.isNotBlank(json)) {
                Config config = JsonUtil.fromJson(json, Config.class);
                if (config != null) {
                    return config.getData();
                }
            }
        } catch (Exception e) {
            LogUtil.ROOT_LOG.error("获取配置属性出现错！" + e.getMessage());
        }
        return null;
    }


    private static String getEnvValue(String key) {
        if (U.isBlank(key)) {
            return null;
        }
        String val = System.getProperty(key);
        if (U.isBlank(val)) {
            val = System.getenv(key);
        }
        return val;
    }

    /**
     * 定时刷新配置文件 延迟10s 每隔2分钟
     **/
    private static void refreshData() {
        executorService.scheduleAtFixedRate(() -> {
            try {
                Map<String, Object> ret = getDataFromConfig();
                if (LogUtil.ROOT_LOG.isInfoEnabled()) {
                    LogUtil.ROOT_LOG.info("定时刷新配置文件 当前时间： {}, 配置属性count: {} ", DateUtil.now(DateFormatType.CN_YYYY_MM_DD_HH_MM_SS), ret == null ? 0 : ret.size());
                }
                if (ret != null) {
                    Cache.setCache(ret);
                }
            } catch (Exception e) {
                LogUtil.ROOT_LOG.error(e.getMessage());
            }
        }, 10L, 120L, TimeUnit.SECONDS);
    }

    public static Object getProperty(String key) {
        if (U.isBlank(key)) {
            return U.EMPTY;
        }
        return Cache.get(key);
    }


    /**
     * 获取字符串属性值
     **/
    public static String getStrPropertyValue(String key) {
        if (U.isBlank(key)) {
            return U.EMPTY;
        }
        Object ret = Cache.get(key);
        return U.isBlank(ret) ? U.EMPTY : ret.toString();
    }

    /**
     * @param application 应用名
     * @param key 数据key值
     **/
    public static String getStrPropertyValue(String application, String key) {
        if (U.isBlank(key)) {
            LogUtil.ROOT_LOG.warn("属性值key：不能为空! ");
            return U.EMPTY;
        }
        if (U.isNotBlank(application)&&!"*".equals(application)) {
            key = key + "&" + application;
        }
        Object ret = Cache.get(key);
        return U.isBlank(ret) ? U.EMPTY : ret.toString();
    }

    /**
     * 判断是否走配置
     */
    public static boolean isConfig() {
        return U.isNotBlank(getEnvValue("confCenterUrl"));
    }

    /**
     * 获取环境变量下
     */
    public static String findEnvValue(){
         return U.EMPTY;
    }

    public static void main(String[] args) {
        String loginUrl = "http://localhost:9072/config-server/login";
        Map<String, Object> params = new HashMap<>();
        params.put("username", "admin");
        params.put("password", "chenying521");
        //  System.out.println(HttpUtil.post(loginUrl,params));
        String url = "http://localhost:9072/config-server/configTable/dev";
        String ret = HttpUtil.get(url, params);
        System.out.println(ret);
        Config config = JsonUtil.fromJson(ret, Config.class);
        Map<String, Object> dataMap = config.getData();
        System.out.println(dataMap.get("system.url"));
    }
}
