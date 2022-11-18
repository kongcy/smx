package com.xtxk.dataSource.processor;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * session中获取
 * User: chenying
 * Date: 2019-07-12
 * Time: 16:55
 * since: 1.0.0
 */
public class DsSessionProcessor implements DsProcessor {
    @Override
    public boolean matches(String key) {
        return false;
    }

    @Override
    public String determineDatasource(MethodInvocation invocation, String key) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Object ret = request.getSession().getAttribute(key);
        return ret==null?null:ret.toString();
    }
}
