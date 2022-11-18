package com.xtxk.core.exception;

import com.xtxk.core.util.U;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * Created by chenying on 2021/1/27.
 */
public abstract class Assert {

    /**
     * 验证是否为null
     */
    public static void checkNull(Object obj, String msg) {
        if (U.isBlank(obj)) {
            throw new ServiceException(msg);
        }
    }

    /**
     * 验证参数为空
     */
    public static void checkNoNull(Object obj, String msg) {
        if (U.isNotBlank(obj)) {
            throw new ServiceException(msg);
        }
    }

    /***
     * 验证字符串是否为空
     * @param obj
     * @param msg
     */
    public static void checkStringNull(String obj, String msg) {
        if (StringUtils.isEmpty(obj)) {
            throw new ServiceException(msg);
        }
    }

    /**
     * 验证是否为false
     */
    public static void checkIsFalse(boolean bool, String msg) {
        if (!bool) {
            throw new ServiceException(msg);
        }
    }

    public static void checkIsTrue(boolean bool, String msg) {
        if (bool) {
            throw new ServiceException(msg);
        }
    }


    public static boolean checkIPForm(String ip) {
        Pattern ipPattern = Pattern.compile("([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}");
        return ipPattern.matcher(ip).matches();
    }


    public static void checkFalseWithAuthError(boolean bool, String msg) {
        if (!bool) {
            throw new AuthenticationErrorException(501,msg);
        }
    }
}
