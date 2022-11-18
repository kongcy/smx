package com.xtxk.core.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2020/12/16.
 */
public final class RequestContextUtil {

    private RequestContextUtil() {
    }

    private static ServletRequestAttributes getRequestContext(){
        return (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    }

    public static HttpServletRequest getRequest(){
        return getRequestContext().getRequest();
    }

    public static HttpServletResponse getResponse() {
        return getRequestContext().getResponse();
    }
}
