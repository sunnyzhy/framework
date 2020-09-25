package com.zhy.framework.actor.test.account;

import com.zhy.framework.actor.core.ActorManager;
import com.zhy.framework.actor.core.Message;

/**
 * @author zhy
 * @date 2020/9/25 15:26
 *
 * 转账管理者的actor
 */
public class AccountActorManager extends ActorManager {
    public AccountActorManager(String name) {
        super(name);
        setActive(true);
    }

    /**
     * 接收到转账的actor所发送的消息之后所要处理的业务流程
     */
    @Override
    public void receive() {
        while (isActive()) {
            try {
                Message<Account> message = getMessages().poll();
                if (message != null) {
                    Account account = message.getData();
                    switch (account.getAccountStatus()) {
                        case REMIT: // 转账请求的消息
                            // 向转账人发送扣款的消息
                            account.setAccountStatus(ACCOUNT_STATUS.DECREASE);
                            send(message, message.getSource());
                            break;
                        case DECREASE_OK: // 扣款成功的消息
                            // 向收款人发送入账的消息
                            account.setAccountStatus(ACCOUNT_STATUS.INCREASE);
                            send(message);
                            break;
                        case INCREASE_OK: // 入账成功的消息
                            // 向转账人、收款人发送交易成功的消息
                            account.setAccountStatus(ACCOUNT_STATUS.TRANSACTION_OK);
                            send(message, message.getSource());
                            send(message);
                            break;
                        default:
                            break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(String.format("%s 的任务退出", getName()));
    }
}
