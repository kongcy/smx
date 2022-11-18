package com.xtxk.dataSource.processor;

import org.aopalliance.intercept.MethodInvocation;

/**
 * 数据源key匹配选择器
 * User: chenying
 * Date: 2019-07-10
 * Time: 15:23
 * since: 1.0.0
 */
public interface DsProcessor {

    /**
     * 抽象匹配条件
     * 匹配才会走当前执行器否则走下一级执行器
     *
     * @param key DS注解里的内容
     * @return 是否匹配
     */
    boolean matches(String key);


    /**
     * 决定数据源
     * <pre>
     *     调用底层doDetermineDatasource，
     *     如果返回的是null则继续执行下一个，否则直接返回
     * </pre>
     *
     * @param invocation 方法执行信息
     * @param key        DS注解里的内容
     * @return 数据源名称
     */
    String determineDatasource(MethodInvocation invocation, String key);
}
