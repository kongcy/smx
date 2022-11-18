package com.xtxk.dataSource.config;

import com.xtxk.dataSource.DynamicRoutingDataSource;
import com.xtxk.dataSource.aop.DataSourceAnnotationAdvisor;
import com.xtxk.dataSource.aop.DataSourceAnnotationInterceptor;
import com.xtxk.dataSource.creator.DataSourceCreator;
import com.xtxk.dataSource.model.DataSourceConfig;
import com.xtxk.dataSource.processor.DsHeaderProcessor;
import com.xtxk.dataSource.processor.DsProcessor;
import com.xtxk.dataSource.processor.DsSessionProcessor;
import com.xtxk.dataSource.processor.DsSpelExpressionProcessor;
import com.xtxk.dataSource.provider.DataSourceProvider;
import com.xtxk.dataSource.provider.YmlDataSourceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.context.WebApplicationContext;
import com.xtxk.dataSource.processor.iterator.ProcessBuilder;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 数据源bean
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2022/3/31
 */
@Configuration
@Import(SessionFactoryConfiguration.class)
@EnableConfigurationProperties(DataSourceConfig.class)
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
public class DynamicDataSourceConfiguration {

    @Autowired
    private DataSourceConfig config;

    /**
     * 创建dataSource
     */
    @Bean
    @ConditionalOnMissingBean
    public DataSource createDataSource(DataSourceProvider provider) {
        com.alibaba.druid.pool.DruidAbstractDataSource  druidAbstractDataSource=null;
        DynamicRoutingDataSource dataSource = new DynamicRoutingDataSource();
        dataSource.setProvider(provider);
        dataSource.setPrimary(config.getPrimary());
        return dataSource;
    }

    @Bean
    @ConditionalOnMissingBean
    public DataSourceProvider dynamicDataSourceProvider(DataSourceCreator dataSourceCreator) {
        return new YmlDataSourceProvider(dataSourceCreator, config);
    }

    @Bean
    @ConditionalOnMissingBean
    public DataSourceCreator dynamicDataSourceCreator(@Autowired WebApplicationContext webApplicationContext) {
        DataSourceCreator dataSourceCreator = new DataSourceCreator();
        dataSourceCreator.setApplicationContext(webApplicationContext);
        dataSourceCreator.setDruidGlobalConfig(config.getDruid());
        return dataSourceCreator;
    }

    @Bean
    @ConditionalOnMissingBean
    public ProcessBuilder dsProcessor() {
        List<DsProcessor> ds = new ArrayList();
        ds.add(new DsSpelExpressionProcessor());
        ds.add(new DsSessionProcessor());
        ds.add(new DsHeaderProcessor());
        return new ProcessBuilder(ds);
    }

    @Bean
    @ConditionalOnMissingBean
    public DataSourceAnnotationAdvisor dynamicDatasourceAnnotationAdvisor() {
        DataSourceAnnotationInterceptor interceptor = new DataSourceAnnotationInterceptor();
        interceptor.setBuilder(dsProcessor());
        DataSourceAnnotationAdvisor advisor = new DataSourceAnnotationAdvisor(interceptor);
        return advisor;
    }
}
