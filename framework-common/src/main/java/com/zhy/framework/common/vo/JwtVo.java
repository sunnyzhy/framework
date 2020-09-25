package com.zhy.framework.common.vo;

import lombok.Data;

/**
 * @author zhy
 * @date 2019/12/30 11:56
 */
public class JwtVo {
    private String token;

    public JwtVo(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
