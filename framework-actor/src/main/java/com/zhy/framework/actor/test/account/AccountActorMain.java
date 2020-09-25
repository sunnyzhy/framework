package com.zhy.framework.actor.test.account;

import org.springframework.stereotype.Service;

/**
 * @author zhy
 * @date 2020/9/25 15:27
 */
@Service
public class AccountActorMain {
    public void start() {
        // 初始化ActorManager
        AccountActorManager actorManager = new AccountActorManager("actorManager");
        actorManager.startThreadPool(10);

        // 初始化业务Actor
        AccountActor accountA = actorManager.createActor(AccountActor.class,"accountA");
        accountA.setCash(50);
        AccountActor accountB = actorManager.createActor(AccountActor.class,"accountB");
        accountB.setCash(100);

        // 启动业务Actor
        actorManager.startActor(accountA);
        actorManager.startActor(accountB);

        // Actor执行业务
        accountA.remit(25, accountB);

        try{
            Thread.sleep(10000);
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
        actorManager.terminate();
    }
}
