package com.xtxk.system.api.handler;

/**
 * Created by chenying on 2019-01-26.
 * 获取枚举处理类包名
 */
public abstract class DefaultHandler {
    public static String PACK_NAME = DefaultHandler.class.getPackage().getName();
}
