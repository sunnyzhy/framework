package com.zhy.framework.actor.test.ticket;

import com.zhy.framework.actor.core.Actor;
import com.zhy.framework.actor.core.Message;

/**
 * @author zhy
 * @date 2020/9/25 15:30
 *
 * 购票的actor
 */
public class CustomerActor extends Actor {
    /**
     * ticket的actor
     */
    private TicketActor ticketActor;

    public CustomerActor() {
    }

    public CustomerActor(String name) {
        super(name);
    }

    public void setTicketActor(TicketActor ticketActor) {
        this.ticketActor = ticketActor;
    }

    /**
     * 接收到源actor发送的消息之后所要处理的业务流程
     */
    @Override
    public void receive() {
        while (isActive()) {
            try {
                Message<Ticket> message = getMessages().poll();
                if (message != null) {
                    Ticket ticket = message.getData();
                    TICKET_STATUS ticketStatus = ticket.getTicketStatus();
                    switch (ticketStatus) {
                        // 购票成功或失败的消息
                        case SUCCESS:
                        case FAILED:
                            System.out.println(message.getSubject());
                            setActive(false);
                            break;
                        default:
                            break;
                    }
                }
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        // 购票完成, 退出
        System.out.println(String.format("%s 的任务退出", getName()));
    }

    /**
     * 购票
     *
     * @param ticketCount
     */
    public void buyTicket(Integer ticketCount) {
        Message<Ticket> message = new Message<>();
        message.setSource(this);
        message.setTarget(ticketActor);
        message.setSubject(String.format("%s buy %d tickets", getName(), ticketCount));
        Ticket ticket = new Ticket();
        ticket.setTicketStatus(TICKET_STATUS.REQUIRED);
        ticket.setTicketCount(ticketCount);
        message.setData(ticket);

        System.out.println(String.format("%s buy %d tickets request", getName(), ticketCount));

        try {
            send(message);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
