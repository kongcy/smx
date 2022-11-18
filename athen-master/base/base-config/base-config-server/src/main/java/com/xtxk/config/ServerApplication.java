package com.xtxk.config;

import com.xtxk.core.annotation.Env;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * User: chenying
 * Date: 2019-08-13
 * Time: 17:02
 * since: 1.0.0
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableConfigServer
@MapperScan(basePackages = "com.xtxk.config.repository")
@ComponentScan(value = "com.xtxk")
@Env(value = "server")
@EnableTransactionManagement
public class ServerApplication {
    public static void main(String [] args){
        try{
            SpringApplication.run(ServerApplication.class,args);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
