package com.xtxk.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication(exclude = {WebSocketServletAutoConfiguration.class, DataSourceAutoConfiguration.class, RedisAutoConfiguration.class})
@EnableDiscoveryClient
public class SpringGatewayApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(SpringGatewayApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
