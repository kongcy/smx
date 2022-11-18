package com.xtxk.swagger.interceptor;

import com.xtxk.core.annotation.NoToken;
import com.xtxk.core.annotation.NoTokenVerify;
import com.xtxk.core.annotation.NotNeedLogin;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.util.Arrays;

/**
 * @Description
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2022/5/23
 */
public abstract class BaseVerifyInterceptor implements HandlerInterceptor {
    protected String[] skipAuthUrls = new String[]{"/error"};

    /**
     * 检查是否含有NotNeedLogin.class 或NoToken.class；如果有则该接口不需要验证直接放过。
     */
    protected boolean checkAnnotation(HttpServletRequest request, Object handler) {
        if (!handler.getClass().isAssignableFrom(HandlerMethod.class)) return true;
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        NotNeedLogin notNeedLogin = handlerMethod.getMethodAnnotation(NotNeedLogin.class);
        NoToken token = handlerMethod.getMethodAnnotation(NoToken.class);
        if ((notNeedLogin != null && notNeedLogin.flag()) || (token != null && token.flag())) {
            return true;
        }
        String url = request.getRequestURI();
        if (null != skipAuthUrls && Arrays.asList(skipAuthUrls).contains(url)) {
            return true;
        }
        return false;
    }

    /**
     * 检查提供者端是否需要token验证
     */
    protected boolean isServiceTokenAnnotation(Object handler) {
        if (!handler.getClass().isAssignableFrom(HandlerMethod.class)) return false;
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Class<?> clazz = handlerMethod.getBeanType();
        //获取接口是否有注解com.xtxk.core.annotation.NoTokenVerify;
        Class[] classes = clazz.getInterfaces();
        if (null != classes) {
            for (Class<?> cl : classes) {
                NoTokenVerify noTokenVerify = cl.getAnnotation(NoTokenVerify.class);
                if (noTokenVerify != null && noTokenVerify.flag()) {
                    return true;
                }
            }
        }
        NoTokenVerify serviceToken = clazz.getAnnotation(NoTokenVerify.class);
        if (serviceToken != null && serviceToken.flag()) {
            return true;
        }
        return false;
    }

    protected <T extends Annotation> T getAnnotation(HandlerMethod handlerMethod, Class<T> clazz) {
        // 先找方法上的注解, 再找类上的注解
        T annotation = handlerMethod.getMethodAnnotation(clazz);
        return annotation == null ? handlerMethod.getBean().getClass().getAnnotation(clazz) : annotation;
    }


}
