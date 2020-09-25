package com.zhy.framework.admin.mapper;

import com.zhy.framework.admin.model.BaseUser;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface BaseUserMapper extends Mapper<BaseUser> {
    BaseUser selectUser(@Param("username") String username);
}