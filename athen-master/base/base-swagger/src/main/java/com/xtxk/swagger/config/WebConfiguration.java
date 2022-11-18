package com.xtxk.swagger.config;
import com.xtxk.core.converter.String2DateConverter;
import com.xtxk.core.converter.StringToEnumConverter;
import com.xtxk.core.converter.StringToNumberConverter;
import com.xtxk.core.converter.StringTrimAndEscapeConverter;
import com.xtxk.core.interceptor.*;
import com.xtxk.core.json.JsonResult;
import com.xtxk.core.json.JsonUtil;
import com.xtxk.core.util.A;
import com.xtxk.core.util.SpringBeanFactory;
import com.xtxk.redis.service.IRedisService;
import com.xtxk.swagger.interceptor.FeignTokenRequestInterceptor;
import com.xtxk.swagger.interceptor.PermissionVerifyInterceptor;
import com.xtxk.swagger.interceptor.TokenVerifyInterceptor;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * web端相关配置
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer, Filter {

    @Bean
    public SpringBeanFactory springBeanFactory(){
        return new SpringBeanFactory();
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new FeignRequestInterceptor();
    }

    @Bean
    public RequestInterceptor FeignTokenRequestInterceptor() {
        return new FeignTokenRequestInterceptor();
    }
    @Autowired
    public IRedisService redisService;

    private static List<String> excludePathURL = A.lists("/doc.html", "/swagger-resources", "/error");

    @Override
    public void addFormatters(FormatterRegistry registry) {
        // 从前台过来的数据转换成对应类型的转换器
        registry.addConverter(new StringTrimAndEscapeConverter());
        registry.addConverterFactory(new StringToNumberConverter());
        registry.addConverterFactory(new StringToEnumConverter());
        registry.addConverter(new String2DateConverter());
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 默认转换器注册后, 插入自定义的请求响应转换器
        converters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
        converters.add(new MappingJackson2HttpMessageConverter(JsonUtil.RENDER));
    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        // 如果 Controller 方法的返回值是 JsonResult 则返回 json, 不需要额外在方法或类上标注 @ResponseBody
        returnValueHandlers.add(new HandlerMethodReturnValueHandler() {
            @Override
            public boolean supportsReturnType(MethodParameter returnType) {
                return JsonResult.class.isAssignableFrom(returnType.getParameterType());
            }

            @Override
            public void handleReturnValue(Object returnValue, MethodParameter returnType,
                                          ModelAndViewContainer mav, NativeWebRequest request) throws Exception {
                mav.setRequestHandled(true);
                ((JsonResult) returnValue).toJson(request.getNativeResponse(HttpServletResponse.class));
            }
        });
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加自定义的拦截器
        registry.addInterceptor(new LoggerInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new PageInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new PermissionVerifyInterceptor(redisService)).excludePathPatterns(excludePathURL);
        registry.addInterceptor(new TokenVerifyInterceptor(redisService)).excludePathPatterns(excludePathURL).order(-1);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/templates/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/templates/");
        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //  开启 cors filter 解决跨域问题. 在 nginx 中配置要添加一堆的 if 判断域名, 没有这里灵活
        registry.addMapping("/**")
                .allowedOrigins("*") // Access-Control-Allow-Origin: 允许向当前服务器提交请求的 url
                .allowCredentials(true) // Access-Control-Allow-Credentials: 响应是否可以被得到
                .allowedHeaders("*") // Access-Control-Allow-Headers: 指明在实际的请求中, 可以使用的自定义 http 请求头
                .maxAge(3600) // Access-Control-Max-Age: 请求结果的有效期时间是多少, 单位秒
                // .exposedHeaders(Const.GLOBAL_TRACK_ID) // Access-Control-Expose-Headers: 允许获取头信息里面的白名单
                .allowedMethods("*"); // Access-Control-Allow-Methods: 资源可以被请求的方式
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Content-Type", "application/json;charset=UTF-8");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    @Override
    public void destroy() {

    }
}
