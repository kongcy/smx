package com.xtxk.core.interceptor;

import com.github.pagehelper.PageHelper;
import com.xtxk.core.Constant;
import com.xtxk.core.annotation.Page;
import com.xtxk.core.util.LogUtil;
import com.xtxk.core.util.U;
import com.xtxk.core.vo.PageInfo;
import com.xtxk.core.vo.PageList;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 分页切面
 */
@Aspect
@Component
public class PageAspect {
    /**
     * 切入点
     * 业务方法实现page注解可实现分页功能
     */
    //  @Pointcut("execution (* com.xtxk.*.repository.*.*(..))")
    @Pointcut("@annotation(com.xtxk.core.annotation.Page)")
    public void pointCut() {
    }

    /**
     * 前置通知--在目标方法之前调用
     */
    @Before("pointCut()")
    public void beforeAdvice(JoinPoint joinPoint) {
        LogUtil.LOG.debug("[beforeAdvice]执行业务方法：-------------" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
    }

    /**
     * 后置通知--在目标方法完成之后调用
     */
    @After("pointCut()")
    public void afterAdvice() {
        //TODO.....
    }

    /**
     * 返回通知--在目标方法成功执行后调用
     */
    @AfterReturning(value = "pointCut()", returning = "result")
    public void afterReturnAdvice(JoinPoint joinPoint, Object result) {
        //TODO.....
        LogUtil.LOG.debug("result: " + result);
    }

    /**
     * 异常通知--在目标方法抛出异常之后调用
     */
    @AfterThrowing(value = "pointCut()", throwing = "e")
    public void afterThrowingAdvice(JoinPoint joinPoint, Throwable e) {
        //TODO.....
    }

    /**
     * 方法拦截--在被通知的方法调用之前和调用之后调用通知
     */
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        verifyPage(joinPoint);
        try {
            Object o = joinPoint.proceed();
            if (LogUtil.LOG.isDebugEnabled()) {
                LogUtil.LOG.debug("执行around()---->o: " + o);
            }
            return o;
        } catch (Throwable e) {
            LogUtil.LOG.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 检查是否需要分页（优先使用注解）
     */
    private void verifyPage(ProceedingJoinPoint joinPoint) {
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            Page page = method.getAnnotation(Page.class);
            PageInfo pageInfo = null;
            if (page != null) {
                if (page.open()) {
                    String currentPage = page.currentPage();
                    String limit = page.limit();
                    if (U.isNotBlank(currentPage) && U.isNotBlank(limit)) {
                        pageInfo = new PageInfo(Integer.parseInt(currentPage), Integer.parseInt(limit));
                    } else {
                        pageInfo = (PageInfo) Constant.PAGE_LOCAL.get();
                    }
                    if (pageInfo != null) {
                        LogUtil.LOG.info("使用注解Page设置分页参数： --------------->" + pageInfo.toString());
                        PageHelper.startPage(pageInfo.getCurrentPage(), pageInfo.getLimit());
                        return;
                    }
                }
            }
            Class clazz = method.getReturnType();
            if (clazz != null && clazz.isAssignableFrom(PageList.class)) {
                pageInfo = (PageInfo) Constant.PAGE_LOCAL.get();
                if (pageInfo != null) {
                    LogUtil.LOG.info("使用返回参数PageList设置分页参数：--------------->" + pageInfo.toString());
                    PageHelper.startPage(pageInfo.getCurrentPage(), pageInfo.getLimit());
                }
            }
        } catch (Exception e) {
            LogUtil.LOG.error("设置分页参数失败!: " + e.getMessage(), e);
        }
    }
}
