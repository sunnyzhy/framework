package com.zhy.framework.common.utils;

import java.util.UUID;

/**
 * @author zhy
 * @date 2019/12/30 10:59
 */
public class UuidUtil {
    public static String generateUuid(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
