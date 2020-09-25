package com.zhy.frameworkauth.service;

import com.zhy.framework.common.constant.CommonConstants;
import com.zhy.framework.common.model.ResponseEntity;
import com.zhy.framework.common.model.User;
import com.zhy.framework.common.to.UserTo;
import com.zhy.framework.common.utils.JwtUtil;
import com.zhy.framework.common.vo.JwtVo;
import com.zhy.frameworkauth.feign.AdminFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 认证服务
 */
@Service
public class AuthenticationService {
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    @Autowired
    private AdminFeign adminFeign;

    public ResponseEntity<JwtVo> login(User user) {
        ResponseEntity<UserTo> responseEntity = adminFeign.getUser(user);
        if (responseEntity.getCode().equals(CommonConstants.SUCCESS)) {
            UserTo userTo = responseEntity.getData();
            if (userTo != null && encoder.matches(user.getPassword(), userTo.getPassword())) {
                String token = JwtUtil.generateToken(userTo);
                return ResponseEntity.success(new JwtVo(token));
            } else {
                return ResponseEntity.error(-1, "用户名或密码错误!");
            }
        } else {
            return ResponseEntity.error(-1, responseEntity.getMsg());
        }
    }

    public ResponseEntity loginOut(String token, User user) {
//        if (!jwt.checkToken(token)) {
//            return ResponseEntity.error(401, "token error!");
//        }
//        User u = jwt.getUserFromToken(token);
//        if (u == null) {
//            return ResponseEntity.error(401, "token error!");
//        }
//        if (!u.getUsername().equals(user.getUsername())) {
//            return ResponseEntity.error(401, "token error!");
//        }
//        jwt.invalidToken(token);
        return ResponseEntity.success();
    }
}
