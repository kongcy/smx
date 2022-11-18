package com.xtxk.dataSource.aop;

import com.xtxk.dataSource.annotation.Ds;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * User: cheny
 * Date: 2019-07-11
 * Time: 17:49
 * since: 1.0.0
 */
public class DataSourceAnnotationAdvisor extends AbstractPointcutAdvisor implements BeanFactoryAware {

    private Advice advice;
    private Pointcut pointcut;
    private DataSourceAnnotationInterceptor interceptor;

    public DataSourceAnnotationAdvisor(DataSourceAnnotationInterceptor interceptor) {
        this.advice = interceptor;
        this.pointcut = buildPointcut();
        this.interceptor = interceptor;
    }

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }

    @Override
    public Advice getAdvice() {
        return this.advice;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        if (this.advice instanceof BeanFactoryAware) {
            ((BeanFactoryAware) this.advice).setBeanFactory(beanFactory);
        }
    }

    private Pointcut buildPointcut() {
        Pointcut cpc = new AnnotationMatchingPointcut(Ds.class, true);
        Pointcut mpc = AnnotationMatchingPointcut.forMethodAnnotation(Ds.class);
        return new ComposablePointcut(cpc).union(mpc);
    }
}
