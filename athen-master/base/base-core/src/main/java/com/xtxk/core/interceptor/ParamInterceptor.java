package com.xtxk.core.interceptor;

import com.xtxk.core.util.U;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 设置dataSource key
 * User: cheny
 * Date: 2019-08-06
 * Time: 18:15
 * since: 1.0.0
 */
public class ParamInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        setAppKey(request);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
            cleanAppKey();
    }

    private void  setAppKey(HttpServletRequest request){
//        MDC.put(U.DATA_SOURCE_KEY, SessionUtil.getDataSourceKey(request));
    }

    private void cleanAppKey(){
        MDC.remove(U.DATA_SOURCE_KEY);
    }
}
