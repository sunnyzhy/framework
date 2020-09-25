package com.zhy.framework.gateway.service;

import com.zhy.framework.common.model.ResponseEntity;
import com.zhy.framework.gateway.feign.UserFeign;
import com.zhy.framework.gateway.model.User;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 调用Feign请求失败时，回滚
 * 用于服务之间调用，即没有在配置文件里配置过的路由，例如登录请求
 */
@Component
public class UserFeignFallbackFactory implements FallbackFactory<UserFeign> {
    @Override
    public UserFeign create(Throwable throwable) {
        return new UserFeign() {
            @Override
            public ResponseEntity<User> getUser(String name) {
                return ResponseEntity.error(501, "The server is unavailable.");
            }
        };
    }
}
