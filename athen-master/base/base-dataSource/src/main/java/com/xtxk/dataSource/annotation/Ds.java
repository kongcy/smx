package com.xtxk.dataSource.annotation;

import java.lang.annotation.*;

/**
 * @Description 数据源
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2022/3/30
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Ds {
    String value() default "";
}

