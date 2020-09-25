package com.zhy.framework.actor.core;

import lombok.Data;

/**
 * @author zhy
 * @date 2020/9/25 15:22
 *
 * actors之间传递的消息
 */
@Data
public class Message<T> {
    /**
     * 消息的发送方
     */
    private Actor source;
    /**
     * 消息的接收方
     */
    private Actor target;
    /**
     * 消息的主题
     */
    private String subject;
    /**
     * 消息的内容
     */
    private T data;
}
