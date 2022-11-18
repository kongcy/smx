package com.xtxk.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, RedisAutoConfiguration.class})
@MapperScan(basePackages = "com.xtxk.*.repository")
@ComponentScan(value = "com.xtxk")
@EnableTransactionManagement
@EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling
public class SystemServiceApplication {
	
	public static void main(String args[]){
		//System.setProperty("nacos.logging.default.config.enabled","false");
		SpringApplication.run(SystemServiceApplication.class, args);
	}
}
