package com.zhy.frameworkauth.feign;

import com.zhy.framework.common.model.ResponseEntity;
import com.zhy.framework.common.model.User;
import com.zhy.framework.common.to.UserTo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author zhy
 * @date 2019/12/30 16:56
 */
@FeignClient(value = "${admin.service-id}")
public interface AdminFeign {
    @RequestMapping(value = "/api/user/auth", method = RequestMethod.POST)
    ResponseEntity<UserTo> getUser(@RequestBody User user);
}
