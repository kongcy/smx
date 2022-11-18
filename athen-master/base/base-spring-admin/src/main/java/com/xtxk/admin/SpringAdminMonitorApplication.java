package com.xtxk.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class SpringAdminMonitorApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(SpringAdminMonitorApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
