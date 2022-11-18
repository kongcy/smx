package com.xtxk.core.util;

import com.xtxk.core.exception.PluginServiceException;
import com.xtxk.core.json.JsonUtil;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * !!此工具类请只在 controller 中调用!!
 */
public final class RequestUtils {

    /**
     * 获取真实客户端IP
     * 关于 X-Forwarded-For 参考: http://zh.wikipedia.org/wiki/X-Forwarded-For<br>
     * 这一 HTTP 头一般格式如下:
     * X-Forwarded-For: client1, proxy1, proxy2,<br><br>
     * 其中的值通过一个 逗号 + 空格 把多个 IP 地址区分开, 最左边(client1)是最原始客户端的IP地址,
     * 代理服务器每成功收到一个请求，就把请求来源IP地址添加到右边
     */
    public static String getRealIp() {
        HttpServletRequest request = getRequest();
        String ip = request.getHeader("X-Real-IP");
        if (U.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip.trim();
        }

        ip = request.getHeader("X-Forwarded-For");
        if (U.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP
            return ip.split(",")[0].trim();
        }

        ip = request.getHeader("Proxy-Client-IP");
        if (U.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip.trim();
        }

        ip = request.getHeader("WL-Proxy-Client-IP");
        if (U.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip.trim();
        }

        ip = request.getHeader("HTTP_CLIENT_IP");
        if (U.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip.trim();
        }

        ip = request.getHeader("X-Cluster-Client-IP");
        if (U.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip.trim();
        }

        return request.getRemoteAddr();
    }

    /**
     * 判断当前请求是否是 ajax 请求, 是 ajax 则返回 true
     */
    public static boolean isAjaxRequest() {
        HttpServletRequest request = getRequest();
        String requestedWith = request.getHeader("X-Requested-With");
        String contentType = request.getHeader("Content-Type");
        return (requestedWith != null && "XMLHttpRequest".equals(requestedWith))
                || (contentType != null && MediaType.APPLICATION_JSON_VALUE.startsWith(contentType))
                || request.getParameter("ajax") != null || request.getParameter("json") != null;
    }

    public static String userAgent() {
        return getRequest().getHeader("User-Agent");
    }

    /**
     * 请求来源(PC, iOS, Android, Other)
     */
    public static String getOsTypeWithStr() {
        return osType().name();
    }

    private static OsType osType() {
        String userAgent = userAgent();

        if (U.checkRegexWithRelax(userAgent, "(?i)iP(hone|od|ad)")) {
            return OsType.iOS;
        }
        if (U.checkRegexWithRelax(userAgent, "(?i)Mobile|okhttp|Android")) {
            return OsType.Android;
        }
        if (U.checkRegexWithRelax(userAgent, "(?i)AppleWebKit|Mozilla|Chrome|Safari|MSIE|Windows NT")) {
            return OsType.PC;
        }
        return OsType.Other;
    }

    /**
     * 注册来源(1.pc, 2.iOS, 3.安卓, 4.其他)
     */
    public static enum OsType {
        PC(1), iOS(2), Android(3), Other(4);
        int code;

        OsType(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    /**
     * 请求来源(1.pc, 2.iOS, 3.安卓, 4.其他)
     */
    public static int getOsType() {
        return osType().code;
    }

    /**
     * 判断当前请求是否来自移动端, 来自移动端则返回 true
     */
    public static boolean isMobileRequest() {
        return U.checkMobile(userAgent());
    }

    /**
     * 格式化参数, 如果是文件流(form 表单中有 type="multipart/form-data" 这种), 则不打印出参数
     *
     * @return 示例: id=xxx&name=yyy
     */
    public static String formatParam() {
        HttpServletRequest request = getRequest();
        return ServletFileUpload.isMultipartContent(request)
                ? "(content-type start with multipart/) uploading file"
                : U.formatParam(request.getParameterMap());
    }

    /**
     * 返回 url 并且拼上参数, 非 get 请求将忽略参数
     */
    public static String getUrl() {
        HttpServletRequest request = getRequest();
        String url = request.getRequestURL().toString();
        if (HttpMethod.GET.equals(request.getMethod())) {
            String param = formatParam();
            if (U.isNotBlank(param)) {
                url = (U.appendUrl(url) + param);
            }
        }
        return url;
    }

    /**
     * 格式化头里的参数
     *
     * @return 示例: id=xxx&name=yyy
     */
    public static String formatHeadParam() {
        HttpServletRequest request = getRequest();
        StringBuilder sbd = new StringBuilder();
        int i = 0;
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            if (i > 0) sbd.append("\n");
            String headName = headerNames.nextElement();
            sbd.append(headName).append(" : ").append(request.getHeader(headName));
            i++;
        }
        return sbd.toString();
    }

    /**
     * 获取字符数组
     **/
    public static byte[] getRequestPostBytes() {
        int contentLength = getRequest().getContentLength();
        if (contentLength < 0) {
            return null;
        }
        byte buff[] = new byte[contentLength];
        for (int i = 0; i < contentLength; ) {
            int readlen = 0;
            try {
                readlen = getRequest().getInputStream().read(buff, i, contentLength - i);
            } catch (IOException e) {
                return null;
            }
            if (readlen == -1) {
                break;
            }
            i += readlen;
        }
        return buff;
    }

    /**
     * 转成字符串
     */
    public static String getRequestPostParam() {
        byte[] buff = getRequestPostBytes();
        String charEncoding = getRequest().getCharacterEncoding();
        if (charEncoding == null) {
            charEncoding = "UTF-8";
        }
        if (buff != null) {
            try {
                return new String(buff, charEncoding);
            } catch (UnsupportedEncodingException e) {
                return null;
            }
        }
        return null;
    }

    /**
     * 将request属性转成对象
     *
     * @param tClass
     **/
    public static <T> T convertObj(Class<T> tClass) {
        Map<String, Object> ret = getParameterMap(getRequest());
        return JsonUtil.fromJson(JsonUtil.toJson(ret), tClass);
    }

    public static <T> T convertObj(HttpServletRequest request, Class<T> tClass) {
        Map<String, Object> ret = getParameterMap(request);
        return JsonUtil.fromJson(JsonUtil.toJson(ret), tClass);
    }

    /**
     * request转成Map
     **/
    public static Map<String, Object> getParameterMap(HttpServletRequest request) {
        Map<String, String[]> map = request.getParameterMap();
        Map<String, Object> ret = new HashMap();
        Iterator iterator = map.entrySet().iterator();
        Map.Entry entry;
        Object value = null;
        String name = "";
        while (iterator.hasNext()) {
            entry = (Map.Entry) iterator.next();
            name = (String) entry.getKey();
            Object val = entry.getValue();
            if (val == null) {
                value = "";
            } else if (val instanceof String[]) {
                String[] values = (String[]) val;
                for (int i = 0; i < values.length; i++) {
                    value = values[i] + ",";
                }
                value = value.toString().substring(0, value.toString().length() - 1);
            } else {
                value = val;
            }
            ret.put(name, value);
        }
        return ret;
    }

    private static ServletRequestAttributes getRequestAttributes() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
    }

    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    public static HttpSession getSession() {
//        String url = RequestUtils.getRequest().getRequestURI();
//        String sessionId = RequestUtils.getRequest().getHeader("jsessionid");
//        if (StringUtils.equals(url,"/system/api/login")) {
//            sessionId = getRequest().getSession().getId();
//        }
//        if(StringUtils.isEmpty(sessionId)){
//            throw new PluginServiceException("sessionId为空！请添加jsessionid参数");
//        }
//        return SessionContext.getInstance().getSession(sessionId);
        return getRequest().getSession();
    }

    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    /**
     * 控制台打印项目相关URL信息
     */
    public static void printInfo(ConfigurableApplicationContext applicationContext) throws UnknownHostException {
        Environment env = applicationContext.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String active = env.getProperty("spring.profiles.active");
        String contextPath = env.getProperty("server.servlet.context-path");
        String nacosPath = env.getProperty("spring.cloud.nacos.discovery.server-addr");
        String springbootAdmin = env.getProperty("spring.boot.admin.client.url");
        String eurekaUrl = env.getProperty("eureka.client.service-url.defaultZone");
        eurekaUrl = eurekaUrl.replaceAll("eureka","");
        LogUtil.LOG.info("\n------------------------------------------------------------------------------------\n\t" +
                "Application is running! AccessURLS:\n\t" +
                "ApiDoc：\t\t\t http://" + ip + ":" + port +(U.isNotBlank(contextPath)?contextPath:U.EMPTY)+"/doc.html\n\t" +
                "nacos：\t\t\t\t http://" +(U.isNotBlank(nacosPath)?nacosPath:"127.0.0.1")+"/nacos\n\t" +
                "Eureka:\t\t\t\t " +eurekaUrl+"\n\t" +
                "springbootAdmin:\t " +(U.isNotBlank(springbootAdmin)?springbootAdmin:U.EMPTY)+"\n\t" +
                "druid：\t\t\t\t http://" + ip + ":" + port +(U.isNotBlank(contextPath)?contextPath:U.EMPTY)+"/druid\n\t" +
                "spring-profiles-active: \t\t" + active + "\n\t" +
                "\n------------------------------------------------------------------------------------");
    }

}
