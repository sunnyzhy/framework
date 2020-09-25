package com.zhy.framework.gateway.model;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String name;
    private String uuid;
    private String password;
}
