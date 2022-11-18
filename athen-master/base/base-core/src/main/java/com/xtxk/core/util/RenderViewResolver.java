package com.xtxk.core.util;

import com.google.common.collect.Maps;
import com.xtxk.core.date.DateUtil;
import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.Configuration;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModelException;
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.Map;

/**
 * 项目里的视图渲染解析器, 这里主要是为了在上下文中注入一些公用类<br/>
 * 参考: https://qbgbook.gitbooks.io/spring-boot-reference-guide-zh/content/IX.%20%E2%80%98How-to%E2%80%99%20guides/65.8.%20Customize%20ViewResolvers.html
 */
public class RenderViewResolver extends FreeMarkerViewResolver {

    /** 静态资源用到的版本号 */
    private static String version = U.random(6);

    private static final BeansWrapper BEANS_WRAPPER = new BeansWrapperBuilder(Configuration.getVersion()).build();
    private static final TemplateHashModel STATIC_HASH_MODEL = BEANS_WRAPPER.getStaticModels();
    //private static final TemplateHashModel ENUM_HASH_MODEL = BEANS_WRAPPER.getEnumModels();

    private static final Class[] CLASSES = new Class[] {
            A.class, U.class, DateUtil.class, Render.class, ImgResolver.class,
    };

    public RenderViewResolver() {
        super();
        // 构造器只被加载一次
        Map<String, Object> context = Maps.newHashMap();
        // 使用下面这一句后, 页面上使用 ${C["...date.DateUtil"].nowTime()}
        //context.put("C", STATIC_HASH_MODEL);
        // 使用下面这一句后, 页面上使用 ${E["...enums.OrderStatus"].Create} 获取枚举
        //context.put("E", ENUM_HASH_MODEL);
        for (Class clazz : CLASSES) {
            String clazzName = clazz.getName();
            try {
                context.put(clazz.getSimpleName(), STATIC_HASH_MODEL.get(clazzName));
            } catch (TemplateModelException e) {
                if (LogUtil.ROOT_LOG.isErrorEnabled())
                    LogUtil.ROOT_LOG.error(String.format("add class(%s) in Render context exception", clazzName), e);
            }
        }
        setAttributesMap(context);
    }

    public RenderViewResolver putVariableInContext(boolean online, RenderDomain domain) {
        setAttributesMap(A.<String, Object>maps(
                "online", online,
                "domain", domain
        ));
        return this;
    }

    @Override
    protected AbstractUrlBasedView buildView(String viewName) throws Exception {
        setAttributesMap(A.<String, Object>maps("version", version));
        return super.buildView(viewName);
    }

    public static String changeVersion() {
        version = U.random(6);
        return version;
    }
    public static String getVersion() {
        return version;
    }

}
