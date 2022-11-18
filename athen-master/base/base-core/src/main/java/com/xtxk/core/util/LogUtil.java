package com.xtxk.core.util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.io.IOException;

/** 日志管理, 使用此 utils 获取 log, 不要在类中使用 LoggerFactory.getLogger 的方式! */
public final class LogUtil {

    /** 根日志, LoggerFactory.getLogger(XXX.class) 使用的就是这种方式. */
    public static final Logger ROOT_LOG = LoggerFactory.getLogger("root");
    /**打印当前日志下带全类名*/
    public static final Logger LOG = LoggerFactory.getLogger(Thread.currentThread().getClass());

    /** SQL 相关的日志 */
    public static final Logger SQL_LOG = LoggerFactory.getLogger("sqlLog");

    public static final class Mdc {
        /** 在 log.xml 中配置的参数 */
        private static final String REQUEST_INFO = "requestInfo";
        private static final String HEAD_INFO = "headInfo";

        /** 输出当前请求信息, 在日志中显示. 非线上时打印出头部参数 */
        public static void bind(String userId, String userName) throws IOException {
            String ip = RequestUtils.getRealIp();
            String url = RequestUtils.getRequest().getRequestURL().toString();
            String method = RequestUtils.getRequest().getMethod();
            String param = RequestUtils.formatParam();
            MDC.put(REQUEST_INFO, String.format("%s  (%s %s) (%s) \n header(%s) ", ip,method, url, param,RequestUtils.formatHeadParam()));
            MDC.put(HEAD_INFO, String.format("header(%s)", RequestUtils.formatHeadParam()));
            if(LogUtil.ROOT_LOG.isDebugEnabled()){
                LogUtil.ROOT_LOG.debug(MDC.get(REQUEST_INFO));
            }
        }

        public static void unbind() {
            MDC.remove(REQUEST_INFO);
            MDC.remove(HEAD_INFO);
        }
    }

}
