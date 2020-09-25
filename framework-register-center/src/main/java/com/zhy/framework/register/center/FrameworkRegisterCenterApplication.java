package com.zhy.framework.register.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class FrameworkRegisterCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrameworkRegisterCenterApplication.class, args);
    }

}
