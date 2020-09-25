package com.zhy.framework.admin;

import com.zhy.framework.admin.mapper.BaseUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class FrameworkAdminApplicationTests {
    @Autowired
    private BaseUserMapper mapper;

    @Test
    void contextLoads() {
        BaseUser user = new BaseUser();
        user.setId(1);
        BaseUser one = mapper.selectOne(user);
        System.out.println(one);
    }

    @Test
    void md5() {
        BaseUser user = new BaseUser();
        user.setId(1);
        BaseUser one = mapper.selectOne(user);
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        String raw = "admin";
//        String value = encode.encode(raw);
//        System.out.println(value);
//        String value = "$2a$10$5HwxnePPr67bnegkLyGV8uTHBK5nlHDClNYIui2FlVUjvjLZcpW6K";
        String value = one.getPassword();
        if (encode.matches(raw, value)) {
            System.out.println(1);
        } else {
            System.out.println(2);
        }
    }

}
