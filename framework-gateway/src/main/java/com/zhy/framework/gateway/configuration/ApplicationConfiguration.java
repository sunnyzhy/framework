package com.zhy.framework.gateway.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author zhy
 * @date 2019/12/27 17:59
 *
 * 读取 application.yml 数组的注意事项:
 * 1. 必须使用 @ConfigurationProperties 注解
 * 2. 成员的变量名称要和 application.yml 里配置的名称一样
 * 3. 必须有 get 和 set 方法
 */
@Configuration
@ConfigurationProperties(prefix = "gate.ignore")
@Data
public class ApplicationConfiguration {
    @Value("${zuul.prefix}")
    private String zuulPrefix;
    private List<String> startWith;
}
