package com.xtxk.core.interceptor;

import com.xtxk.core.Constant;
import com.xtxk.core.vo.PageInfo;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 分页拦截器
 */
public class PageInterceptor implements HandlerInterceptor {
    private static final int DEFAULT_PAGE =1;
    private static final int  DEFAULT_LIMIT=10000000;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        setPageParam(request, handler);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //请求完成处理掉缓存中内容
        Constant.PAGE_LOCAL.remove();
    }

    /**
     * 设置分页参数
     */
    private void setPageParam(HttpServletRequest request, Object handler) {
        if (handler != null) {
            if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
                int currentPage = NumberUtils.toInt(request.getParameter(Constant.GLOBAL_PAGE));
                int limit = NumberUtils.toInt(request.getParameter(Constant.GLOBAL_LIMIT));
                if(currentPage>0&&limit>0){
                    Constant.PAGE_LOCAL.set(new PageInfo(currentPage,limit));
                }else{
                    Constant.PAGE_LOCAL.set(new PageInfo(DEFAULT_PAGE,DEFAULT_LIMIT));
                }
            }
        }
    }

}
