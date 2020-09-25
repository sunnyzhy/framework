package com.zhy.framework.actor.test.account;

/**
 * @author zhy
 * @date 2020/9/25 15:25
 *
 * 转账的状态
 */
public enum ACCOUNT_STATUS {
    /**
     * 转账
     */
    REMIT,
    /**
     * 允许转账
     */
    REMIT_OK,
    /**
     * 收款
     */
    INCREASE,
    /**
     * 收款成功
     */
    INCREASE_OK,
    /**
     * 扣款
     */
    DECREASE,
    /**
     * 扣款成功
     */
    DECREASE_OK,
    /**
     * 交易完成
     */
    TRANSACTION_OK
}
