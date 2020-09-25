package com.zhy.framework.actor.test.ticket;

import lombok.Data;

/**
 * @author zhy
 * @date 2020/9/25 15:28
 *
 * 购票信息
 */
@Data
public class Ticket {
    /**
     * 购票的状态
     */
    private TICKET_STATUS ticketStatus;
    /**
     * 购票的张数
     */
    private int ticketCount;
}
