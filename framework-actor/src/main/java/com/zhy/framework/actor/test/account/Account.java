package com.zhy.framework.actor.test.account;

import lombok.Data;

/**
 * @author zhy
 * @date 2020/9/25 15:25
 *
 * 账户信息
 */
@Data
public class Account {
    /**
     * 转账的状态
     */
    private ACCOUNT_STATUS accountStatus;
    /**
     * 现金总额
     */
    private float cash;
}
