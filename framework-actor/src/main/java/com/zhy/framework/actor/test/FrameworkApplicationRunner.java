package com.zhy.framework.actor.test;

import com.zhy.framework.actor.test.account.AccountActorMain;
import com.zhy.framework.actor.test.ticket.TicketActorMain;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author zhy
 * @date 2020/9/25 15:37
 */
@Component
public class FrameworkApplicationRunner implements ApplicationRunner {
    private final TicketActorMain ticketActorMain;
    private final AccountActorMain accountActorMain;

    public FrameworkApplicationRunner(TicketActorMain ticketActorMain, AccountActorMain accountActorMain) {
        this.ticketActorMain = ticketActorMain;
        this.accountActorMain = accountActorMain;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ticketActorMain.start();
//        accountActorMain.start();
    }
}
