package com.zhy.framework.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhy
 * @date 2019/12/26 17:50
 */
@RestController
public class TestController {
    @GetMapping(value = "/hello")
    public String get(){
        return "hello";
    }
}
