package com.athen.business;

import com.xtxk.core.util.LogUtil;
import com.xtxk.core.util.RequestUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.client.ConfigClientProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 系统设置启动类
 */
@SpringBootApplication(exclude = {WebSocketServletAutoConfiguration.class, RedisAutoConfiguration.class, DataSourceAutoConfiguration.class})
@MapperScan(basePackages = "com.athen.*.repository")
@ComponentScan({"com.athen","com.xtxk"})
@EnableTransactionManagement
@EnableDiscoveryClient
public class BusinessWebApplication {
    public static void main(String[] args) throws Exception {
        try {
            ConfigurableApplicationContext applicationContext = SpringApplication.run(BusinessWebApplication.class, args);
            RequestUtils.printInfo(applicationContext);
        }catch (Exception e){
            LogUtil.LOG.error(e.getMessage());
        }
    }

}
