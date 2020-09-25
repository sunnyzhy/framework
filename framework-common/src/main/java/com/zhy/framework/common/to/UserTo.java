package com.zhy.framework.common.to;

import lombok.Data;

/**
 * @author zhy
 * @date 2019/12/30 10:19
 */
@Data
public class UserTo {
    private Integer id;
    private String username;
    private String password;
    private String tenantId;
}
