package com.athen.sync;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author chenying
 * @date 2022/11/7 11:24 AM
 * @time 11:24 AM
 * @since 1.0.0
 **/
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
        RedisAutoConfiguration.class, MybatisAutoConfiguration.class})
    public class DatasyncApplication {
    public static void main(String[] args) {
        SpringApplication.run(DatasyncApplication.class, args);
    }
}
