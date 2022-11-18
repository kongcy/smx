package com.xtxk.system;

import com.xtxk.core.util.RequestUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * 系统设置启动类
 */
@SpringBootApplication(exclude = {WebSocketServletAutoConfiguration.class, DataSourceAutoConfiguration.class, RedisAutoConfiguration.class})
@ComponentScan(value = "com.xtxk")
@EnableDiscoveryClient
@EnableFeignClients
public class SystemWebApplication {
    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SystemWebApplication.class, args);
        RequestUtils.printInfo(applicationContext);
    }

}
