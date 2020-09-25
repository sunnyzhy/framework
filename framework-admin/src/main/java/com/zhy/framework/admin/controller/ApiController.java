package com.zhy.framework.admin.controller;

import com.zhy.framework.admin.model.User;
import com.zhy.framework.admin.service.ApiService;
import com.zhy.framework.common.model.ResponseEntity;
import com.zhy.framework.common.to.ResourceTo;
import com.zhy.framework.common.to.UserTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class ApiController {
    @Autowired
    private ApiService apiService;

    @RequestMapping(value = "/user/auth", method = RequestMethod.POST)
    public ResponseEntity<UserTo> getUser(@RequestBody User user) {
        return apiService.getUser(user);
    }

    @RequestMapping(value = "/user/login-out", method = RequestMethod.POST)
    public ResponseEntity loginOut(@RequestHeader("token") String token, @RequestBody User user) {
        return apiService.loginOut(token, user);
    }

    @RequestMapping(value = "/resource", method = RequestMethod.GET)
    public ResponseEntity<ResourceTo> getResource() {
        return apiService.getResource();
    }
}
