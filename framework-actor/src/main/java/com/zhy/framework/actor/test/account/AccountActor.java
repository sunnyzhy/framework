package com.zhy.framework.actor.test.account;

import com.zhy.framework.actor.core.Actor;
import com.zhy.framework.actor.core.Message;

/**
 * @author zhy
 * @date 2020/9/25 15:26
 *
 * 转账的actor
 */
public class AccountActor extends Actor {
    /**
     * 现金总额
     */
    private float cash;

    public AccountActor() {
        super();
    }

    public AccountActor(String name) {
        super(name);
    }

    public void setCash(float cash) {
        this.cash = cash;
        System.out.println(String.format("%s 持有现金 %.2f 元", getName(), cash));
    }

    /**
     * 接收到源actor发送的消息之后所要处理的业务流程
     */
    @Override
    public void receive() {
        while (isActive()) {
            try {
                Message<Account> message = getMessages().poll();
                if (message != null) {
                    Account account = message.getData();
                    AccountActorManager accountActorManager = (AccountActorManager) getActorManager();
                    switch (account.getAccountStatus()) {
                        case DECREASE: // 扣款的消息
                            // 转账人扣款
                            cash -= account.getCash();
                            message.setSubject(String.format("%s 扣款 %.2f 元", getName(), account.getCash()));
                            // 向actors的管理者发送扣款成功的消息
                            account.setAccountStatus(ACCOUNT_STATUS.DECREASE_OK);
                            send(message, accountActorManager);

                            System.out.println(message.getSubject());
                            break;
                        case INCREASE: // 入账的消息
                            // 收款人入账
                            cash += account.getCash();
                            message.setSubject(String.format("%s 入账 %.2f 元", getName(), account.getCash()));
                            // 收款人向actors的管理者发送入账成功的消息
                            account.setAccountStatus(ACCOUNT_STATUS.INCREASE_OK);
                            send(message, accountActorManager);

                            System.out.println(message.getSubject());
                            break;
                        case TRANSACTION_OK: // 交易完成的消息
                            // 交易完成, 退出
                            setActive(false);

                            System.out.println(String.format("交易完成, %s 持有现金 %.2f 元", getName(), cash));
                            break;
                        default:
                            break;
                    }
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        // 交易完成, 退出
        System.out.println(String.format("%s 的任务退出", getName()));
    }

    /**
     * 转账
     *
     * @param cash
     * @param target
     * @return
     */
    public boolean remit(float cash, AccountActor target) {
        Message<Account> message = new Message<>();
        message.setSource(this);
        message.setTarget(target);
        message.setSubject(String.format("%s 向 %s 转账 %.2f 元", getName(), target.getName(), cash));
        Account account = new Account();
        account.setCash(cash);
        account.setAccountStatus(ACCOUNT_STATUS.REMIT);
        message.setData(account);
        AccountActorManager accountActorManager = (AccountActorManager) getActorManager();

        System.out.println(message.getSubject());

        try {
            send(message, accountActorManager);
            return true;
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
