package com.xtxk.dataSource.toolkit;

import com.xtxk.core.util.A;
import com.xtxk.core.util.LogUtil;
import com.xtxk.core.util.U;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.jar.JarEntry;

/**
 * 处理mybaties资源文件工具类
 *
 * @author chenying
 * @date 2019-07-13 14:14
 * @time 14:14
 * @since 1.0.0
 **/
public abstract class ResourceUtils {

//    public static final String[] RESOURCE_PATH = new String[]{"auto/*.xml", "custom/*.xml"};

    //    public static final String[] RESOURCE_PATH = new String[]{"dm/*.xml"};
    public static final Map<String, String[]> PATH_HASHMAP = A.maps(
            "master", new String[]{"master/auto/*.xml", "master/custom/*.xml"},
            "salve", new String[]{"salve/auto/*.xml", "salve/custom/*.xml"},
            "salve01", new String[]{"postgres/auto/*.xml", "postgres/custom/*.xml"});

    public static String[] getResourcePath(String key) {
        return PATH_HASHMAP.get(key);
    }

    /**
     * 获取 mybatis 要加载的 xml 文件
     */
    public static Resource[] getResourceArray(String[] resPaths) {
        List<Resource> resourceList = A.lists();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        for (String path : resPaths) {
            try {
                Resource[] resources = resolver.getResources(path);
                if (A.isNotEmpty(resources)) {
                    Collections.addAll(resourceList, resources);
                }
            } catch (IOException e) {
                if (LogUtil.ROOT_LOG.isErrorEnabled())
                    LogUtil.ROOT_LOG.error(String.format("load file(%s) exception", path), e);
            }
        }
        return resourceList.toArray(new Resource[resourceList.size()]);
    }

    /**
     * 获取 mybatis 装载的类型处理
     */
    public static TypeHandler[] getHandleArray(String packages, ClassLoader classLoader) {
        if (U.isBlank(packages)) {
            return null;
        }
        List<TypeHandler> handlerList = A.lists();
        String packageName = packages.replace(".", "/");
        URL url = classLoader.getResource(packageName);
        if (url != null) {
            if (LogUtil.ROOT_LOG.isDebugEnabled())
                LogUtil.ROOT_LOG.debug("current mybatis load TypeHandler protocol: " + url.getProtocol() + ": " + url.getPath());
            if ("file".equals(url.getProtocol())) {
                File parent = new File(toUtf8(url.getPath()));
                if (parent.isDirectory()) {
                    File[] files = parent.listFiles();
                    if (A.isNotEmpty(files)) {
                        for (File file : files) {
                            TypeHandler handler = getTypeHandler(packages, file.getName());
                            if (handler != null) {
                                handlerList.add(handler);
                            }
                        }
                    }
                }
            } else if ("jar".equals(url.getProtocol())) {
                try {
                    Enumeration<JarEntry> entries = ((JarURLConnection) url.openConnection()).getJarFile().entries();
                    while (entries.hasMoreElements()) {
                        String name = entries.nextElement().getName();
                        if (name.startsWith(packageName) && name.endsWith(".class")) {
                            TypeHandler handler = getTypeHandler(packages, name.substring(name.lastIndexOf("/") + 1));
                            if (handler != null) {
                                handlerList.add(handler);
                            }
                        }
                    }
                } catch (IOException e) {
                    if (LogUtil.ROOT_LOG.isErrorEnabled())
                        LogUtil.ROOT_LOG.error("can not load jar file", e);
                }
            }
        }
        return handlerList.toArray(new TypeHandler[handlerList.size()]);
    }

    private static TypeHandler getTypeHandler(String packages, String name) {
        if (U.isNotBlank(name)) {
            String className = packages + "." + name.replace(".class", "");
            try {
                Class<?> clazz = Class.forName(className);
             /*   if (clazz != null && clazz.isEnum()) {
                   TypeHandler handler = (TypeHandler) CglibProxyFactory.proxy(BaseTypeHandler.class, new CglibProxyFactory.ProxyMethodInterceptor(clazz));
                   return handler;
                }*/
                if (clazz != null && TypeHandler.class.isAssignableFrom(clazz)) {
                    return (TypeHandler) clazz.newInstance();
                }
            } catch (ClassNotFoundException | RuntimeException | InstantiationException | IllegalAccessException e) {
                if (LogUtil.ROOT_LOG.isErrorEnabled())
                    LogUtil.ROOT_LOG.error(String.format("TypeHandler clazz (%s) exception", className), e);
            }
        }
        return null;
    }

    private static String toUtf8(String path) {
        if (U.isNotBlank(path)) {
            try {
                return URLDecoder.decode(path, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                return path;
            }
        }
        return path;
    }

    public static void main(String args[]) throws UnsupportedEncodingException {
        String path = "/E:/3.%e6%ba%90%e4%bb%a3%e7%a0%81/3.4.%e4%b8%9a%e5%8a%a1%e7%b3%bb%e7%bb%9f%e5%af%b9%e6%8e%a5/athen-master/biz/system/biz-system-api/target/classes/com/xtxk/system/api/handler";
        System.out.println(URLDecoder.decode("D:\\Axure\\Axure RP 9\\Fonts", "utf-8"));
        File parent = new File(path);
        System.out.println(parent);
    }

}
