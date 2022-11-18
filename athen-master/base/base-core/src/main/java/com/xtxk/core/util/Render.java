package com.xtxk.core.util;

import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

public final class Render {

    /** 页面注释 */
    private static final String PAGE_REGEX = "(?s)<!--.*?-->";
    /** css 和 js 的多行注释 */
    private static final String CSS_JS_REGEX = "(?s)/\\*.*?\\*/";

//    /** 找出页面中手写的 js 代码 */
//    private static final String SCRIPT_REGEX = "(?s)<script.*?>(.*?)</script>";
//    /** 手写的 js 代码中间的那一部分所在组, 就是上面正则中的 (括号) 索引, 头上的 (?s) 不参与计数 */
//    private static final int SCRIPT_GROUP = 1;
//    /** js 中的 行尾或单行 注释 */
//    private static final String JS_REGEX = "(\\\".*?\\\".*|^(\\s*?))//.*";
//    private static final String JS_REPLACE = "$1";

    public static String comment(String source) {
        if (U.isBlank(source)) return U.EMPTY;

        // 去掉页面上的 <!-- --> 注释. 如果 js 中有 var some = "<!-- xyz -->"; 这么替换将会很悲剧
        source = source.replaceAll(PAGE_REGEX, U.EMPTY);
        // 去掉页面上的 /* */ 注释. 如果 js 中有 var some = "/* xyz */"; 这么替换将会很悲剧
        source = source.replaceAll(CSS_JS_REGEX, U.EMPTY);

        // 处理 js 标签中的单行注释. !!!有问题!!!
//        Matcher matcher = Pattern.compile(SCRIPT_REGEX).matcher(source);
//        while (matcher.find()) {
//            String line = matcher.group(SCRIPT_GROUP);
//            source = source.replace(line, line.replaceAll(JS_REGEX, JS_REPLACE));
//        }
        return source;
    }

    /** 基于当前项目的绝对路径, 从 spring mvc 中获取 */
    public static MvcUriComponentsBuilder.MethodArgumentBuilder mapping(String name) {
        return MvcUriComponentsBuilder.fromMappingName(name);
    }

    /**
     * 拼接 domain 前缀(带 http:// 等)的绝对路径, 从 spring mvc 中获取.<br/>
     * 此方法得到的 url 将没有参数, 如果要构建参数请调用 {@link #mapping(String)} 方法
     */
    public static String mapping(String domain, String mappingName) {
        String path = mapping(mappingName).build();
        // 从 spring mvc 中的 controller 中获取的 path 去掉后面的空参数
        if (path.contains("?")) {
            path = path.substring(0, path.indexOf("?"));
        }
        return url(domain, path);
    }

    /** 拼接 domain 前缀(带 http:// 等)的绝对路径, 主要用来拼接静态文件 */
    public static String url(String domain, String fileName) {
        return url(domain, fileName, false);
    }

    /** 拼接 domain 前缀(带 http:// 等)的绝对路径, 主要用来拼接静态文件, 并且为链接带一个版本 */
    public static String url(String domain, String fileName, boolean version) {
        if (U.isNotBlank(domain)) {
            domain = U.addSuffix(domain);
        }
        if (U.isNotBlank(fileName)) {
            fileName = fileName.startsWith("/") ? fileName.substring(1) : fileName;

            // 资源文件前缀改成 // 开头(去掉 http 或 https), 版本只在资源文件中添加
            if (fileName.matches("^.*\\.(css|js|ico|gif|png|jpg)$")) {
                domain = domain.replaceFirst("http(s?)://", "//");
                if (version) fileName += ((fileName.contains("?") ? "&" : "?") + RenderViewResolver.getVersion());
            }
        }
        return domain + fileName;
    }
}
