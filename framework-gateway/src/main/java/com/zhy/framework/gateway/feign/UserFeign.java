package com.zhy.framework.gateway.feign;

import com.zhy.framework.common.model.ResponseEntity;
import com.zhy.framework.gateway.model.User;
import com.zhy.framework.gateway.service.UserFeignFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * user-service的feign接口
 */
@FeignClient(value = "user-server", fallbackFactory = UserFeignFallbackFactory.class)
@RequestMapping(value = "/api")
public interface UserFeign {
    @RequestMapping(value = "/user/{name}", method = RequestMethod.GET)
    ResponseEntity<User> getUser(@PathVariable("name") String userName);
}
