package com.xtxk.config.aop;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * User: chenying
 * Date: 2019-08-14
 * Time: 16:32
 * since: 1.0.0
 */
public class CheckTokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Enumeration enumeration=request.getHeaderNames();
        System.out.println(handler);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }


    private void check(HttpServletRequest request){

    }
}
