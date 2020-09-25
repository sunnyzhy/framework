package com.zhy.framework.common.to;

import lombok.Data;

/**
 * @author zhy
 * @date 2020/1/14 18:01
 */
@Data
public class ResourceTo {
    private String code;
    private String type;
    private String uri;
    private String method;
    private String name;
    private String menu;
}
