package com.xtxk.core.annotation;

import java.lang.annotation.*;

/**
 * 分页注解
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Page {
    String value() default "";

    /**
     * 默认是否开启分页
     */
    boolean open() default true;

    /**
     * 当前页数
     */
    String currentPage() default "";

    /**
     * 每页数量
     */
    String limit() default "";

    /**
     * 排序
     */
    String orderBy() default "";
}
