package com.xtxk.core.annotation;

import java.lang.annotation.*;

/**
 * @Description 用来标识服务类不需要通过token令牌认证{com.xtxk.swagger.interceptor.BaseVerifyInterceptor.isServiceTokenAnnotation()}
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2022/5/10
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoTokenVerify {
    boolean flag() default true;
}
