package com.xtxk.dataSource.aop;

import com.xtxk.core.util.ContextHolder;
import com.xtxk.core.util.U;
import com.xtxk.dataSource.annotation.Ds;
import lombok.Setter;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import com.xtxk.dataSource.processor.iterator.ProcessBuilder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

import static com.xtxk.core.Constant.DATA_SOURCE_KEY;

/**
 * 注解方法拦截器
 * User: cheny
 * Date: 2019-07-11
 * Time: 17:44
 * since: 1.0.0
 */
public class DataSourceAnnotationInterceptor implements MethodInterceptor {
    @Setter
    private ProcessBuilder builder;
    public  DataSourceAnnotationInterceptor(){}

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        try {
            ContextHolder.push(determineDatasource(invocation));
            return invocation.proceed();
        }finally {
            ContextHolder.poll();
        }
    }
    /**
     *如果作为服务端单独部署，session这部分应该在消费端处理，不需要在此处处理
     * **/
    private String determineDatasource(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        Ds ds = method.isAnnotationPresent(Ds.class) ? method.getAnnotation(Ds.class) : null;
        return builder.determineDatasource(invocation, U.isBlank(ds)?DATA_SOURCE_KEY:ds.value());
    }
}
