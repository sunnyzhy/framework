package com.zhy.framework.gateway;

import com.zhy.framework.gateway.configuration.ApplicationConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FrameworkGatewayApplicationTests {

    @Autowired
    private ApplicationConfiguration applicationConfiguration;
    @Test
    void contextLoads() {
        System.out.println(applicationConfiguration.getStartWith());
    }

}
