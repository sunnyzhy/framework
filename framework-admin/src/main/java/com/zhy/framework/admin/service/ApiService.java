package com.zhy.framework.admin.service;

import com.zhy.framework.admin.mapper.BaseElementMapper;
import com.zhy.framework.admin.mapper.BaseMenuMapper;
import com.zhy.framework.admin.mapper.BaseUserMapper;
import com.zhy.framework.admin.model.BaseElement;
import com.zhy.framework.admin.model.BaseMenu;
import com.zhy.framework.admin.model.BaseUser;
import com.zhy.framework.admin.model.User;
import com.zhy.framework.common.model.ResponseEntity;
import com.zhy.framework.common.to.ResourceTo;
import com.zhy.framework.common.to.UserTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 认证服务
 */
@Service
public class ApiService {
    @Autowired
    private BaseUserMapper userMapper;
    @Autowired
    private BaseMenuMapper menuMapper;
    @Autowired
    private BaseElementMapper elementMapper;

    public ResponseEntity<UserTo> getUser(User user) {
        BaseUser baseUser = userMapper.selectUser(user.getUsername());
        if (baseUser != null) {
            UserTo userTo = new UserTo();
            userTo.setId(baseUser.getId());
            userTo.setUsername(baseUser.getUsername());
            userTo.setPassword(baseUser.getPassword());
            userTo.setTenantId(baseUser.getTenantId());
            return ResponseEntity.success(userTo);
        } else {
            return ResponseEntity.success(null);
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

    public ResponseEntity<ResourceTo> getResource() {
        List<BaseMenu> menuList = menuMapper.selectAll();

        List<BaseElement> elementList = elementMapper.selectAll();
        return ResponseEntity.success();
    }


}
