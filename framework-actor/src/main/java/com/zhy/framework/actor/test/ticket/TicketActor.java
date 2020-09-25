package com.zhy.framework.actor.test.ticket;

import com.zhy.framework.actor.core.Actor;
import com.zhy.framework.actor.core.Message;

/**
 * @author zhy
 * @date 2020/9/25 15:29
 *
 * TicketActor
 */
public class TicketActor extends Actor {
    /**
     * 库存里的票数
     */
    private int ticketCount;

    public TicketActor() {
    }

    public TicketActor(String name) {
        super(name);
    }

    public void setTicketCount(int ticketCount) {
        this.ticketCount = ticketCount;
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
                    Actor source = message.getSource();
                    TICKET_STATUS ticketStatus = ticket.getTicketStatus();
                    switch (ticketStatus) {
                        // 购票请求的消息
                        case REQUIRED:
                            String subject = message.getSubject();
                            int ticketCount = ticket.getTicketCount();
                            if (ticketCount > this.ticketCount) {
                                // 购票失败，数量超出了剩余数量
                                ticket.setTicketStatus(TICKET_STATUS.FAILED);
                                message.setSubject(subject + " failed: Sold Out!");
                            } else {
                                // 购票成功
                                this.ticketCount -= ticketCount;
                                ticket.setTicketStatus(TICKET_STATUS.SUCCESS);
                                message.setSubject(subject + " success!");
                            }
                            message.setSource(this);
                            message.setTarget(source);
                            send(message);
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
        System.out.println(String.format("%s 的任务退出", getName()));
    }
}
