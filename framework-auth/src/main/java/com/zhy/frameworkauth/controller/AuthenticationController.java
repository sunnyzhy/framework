package com.zhy.frameworkauth.controller;

import com.zhy.framework.common.model.ResponseEntity;
import com.zhy.framework.common.model.User;
import com.zhy.frameworkauth.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 认证
 */
@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    /**
     * 登录
     * @param user
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody User user) {
        return authenticationService.login(user);
    }

    /**
     * 退出登录
     * @param token
     * @param user
     * @return
     */
    @RequestMapping(value = "/login-out", method = RequestMethod.POST)
    public ResponseEntity loginOut(@RequestHeader("token") String token, @RequestBody User user) {
        return authenticationService.loginOut(token, user);
    }
}
