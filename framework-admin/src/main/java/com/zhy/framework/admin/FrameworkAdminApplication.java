package com.zhy.framework.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.zhy.framework.admin.mapper")
public class FrameworkAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrameworkAdminApplication.class, args);
    }

}
