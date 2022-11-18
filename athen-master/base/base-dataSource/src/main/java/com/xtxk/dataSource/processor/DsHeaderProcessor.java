package com.xtxk.dataSource.processor;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * http头部获取
 * User: chenying
 * Date: 2019-07-12
 * Time: 16:52
 * since: 1.0.0
 */
public class DsHeaderProcessor implements DsProcessor {
    @Override
    public boolean matches(String key) {
        return false;
    }

    @Override
    public String determineDatasource(MethodInvocation invocation, String key) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getHeader(key);
    }
}
