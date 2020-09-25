package com.zhy.framework.actor.test.ticket;

import com.zhy.framework.actor.core.ActorManager;
import org.springframework.stereotype.Service;

/**
 * @author zhy
 * @date 2020/9/25 15:30
 */
@Service
public class TicketActorMain {
    public void start() {
        // 初始化ActorManager
        ActorManager actorManager = new ActorManager("ticketActorManager");
        actorManager.startThreadPool(10);

        // 初始化业务Actor
        TicketActor ticketActor = actorManager.createActor(TicketActor.class, "ticketActor");
        ticketActor.setTicketCount(15);
        CustomerActor customerActor1 = actorManager.createActor(CustomerActor.class, "customerActor1");
        customerActor1.setTicketActor(ticketActor);
        CustomerActor customerActor2 = actorManager.createActor(CustomerActor.class, "customerActor2");
        customerActor2.setTicketActor(ticketActor);
        CustomerActor customerActor3 = actorManager.createActor(CustomerActor.class, "customerActor3");
        customerActor3.setTicketActor(ticketActor);

        // 启动业务Actor
        actorManager.startActor(ticketActor);
        actorManager.startActor(customerActor1);
        actorManager.startActor(customerActor2);
        actorManager.startActor(customerActor3);

        // Actor执行业务
        customerActor1.buyTicket(5);
        customerActor1.buyTicket(2);
        customerActor2.buyTicket(8);
        customerActor3.buyTicket(6);

        try{
            Thread.sleep(10000);
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
        actorManager.terminate();
    }
}
