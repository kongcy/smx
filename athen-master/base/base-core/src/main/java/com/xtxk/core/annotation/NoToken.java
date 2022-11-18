package com.xtxk.core.annotation;

import java.lang.annotation.*;

/**
 * @Description 不需要token
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2022/5/6
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoToken {
    boolean flag() default true;
}
