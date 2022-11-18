package com.xtxk.core.interceptor;

import com.google.common.collect.Lists;
import com.xtxk.core.util.LogUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 日志拦截器
 */
public class LoggerInterceptor implements HandlerInterceptor {

    @Value("${online:false}")
    private boolean online;

    private static final List<String> LET_IT_GO = Lists.newArrayList("/error");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        bindParam();
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        if (ex != null) {
            if (LogUtil.ROOT_LOG.isErrorEnabled())
                LogUtil.ROOT_LOG.error("request was over, but have exception: " + ex.getMessage());
        }
        unbindParam();
    }

    private void bindParam() throws IOException {
        // 打印日志上下文中的数据
        LogUtil.Mdc.bind("","");
    }

    private void unbindParam() {
        // 删除打印日志上下文中的数据
        LogUtil.Mdc.unbind();
    }


}
