package com.xtxk.config.aop;

import com.xtxk.core.json.JsonResult;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * User: chenying
 * Date: 2019-08-14
 * Time: 17:47
 * since: 1.0.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
         registry.addInterceptor(new CheckTokenInterceptor()).addPathPatterns("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/templates/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/templates/");
        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
    }

    // 如果 Controller 方法的返回值是 JsonResult 则返回 json, 不需要额外在方法或类上标注 @ResponseBody
    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        handlers.add(new HandlerMethodReturnValueHandler() {
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
}
