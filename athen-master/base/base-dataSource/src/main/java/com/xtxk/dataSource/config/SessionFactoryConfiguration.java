package com.xtxk.dataSource.config;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.xtxk.core.util.ContextHolder;
import com.xtxk.core.util.U;
import com.xtxk.dataSource.DynamicRoutingDataSource;
import com.xtxk.dataSource.toolkit.ResourceUtils;
import com.xtxk.core.util.A;
import com.xtxk.core.util.LogUtil;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * mybaties session 配置
 *
 * @author cheny
 * @date 2019-07-13 14:41
 * @time 14:41
 * @since 1.0.0
 **/
@Configuration
public class SessionFactoryConfiguration {

    @Value("${application:''}")
    private String application;
    @Value("${handler.packagesName:''}")
    private String typeHandler;

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        //加载mybaties的sql xml文件
        String dataSourceKey = U.isBlank(ContextHolder.peek())?((DynamicRoutingDataSource)dataSource).getPrimary(): ContextHolder.peek();
        Resource[] resources = ResourceUtils.getResourceArray(ResourceUtils.getResourcePath(dataSourceKey));
        if (LogUtil.ROOT_LOG.isDebugEnabled()) {
            LogUtil.ROOT_LOG.debug("mybatis load xml:({})", A.toStr(resources));
        }
        sessionFactory.setMapperLocations(resources);
        //处理mybaties特殊枚举对象转换
        sessionFactory.setTypeHandlers(ResourceUtils.getHandleArray(typeHandler, this.getClass().getClassLoader()));
        return sessionFactory.getObject();
    }

    @Bean
    @ConditionalOnMissingBean
    public SqlSessionTemplate sqlSessionTemplate(DataSource dataSource) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory(dataSource));
    }

    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        Map<String, String> initParams = new HashMap<>();
        initParams.put("loginUsername", "admin");
        initParams.put("loginPassword", "chenying521");
        initParams.put("allow", "");
        bean.setInitParameters(initParams);
        return bean;
    }

    @Bean
    public FilterRegistrationBean<WebStatFilter> webStatFilter() {
        FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());
        Map<String, String> initParams = new HashMap<>();
        initParams.put("exclusions", "*.js,*.css,/druid/*");
        bean.setInitParameters(initParams);
        return bean;
    }


}
