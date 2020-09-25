package com.zhy.framework.actor.core;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author zhy
 * @date 2020/9/25 15:21
 *
 * Actor
 */
public abstract class Actor {
    /**
     * actor的名称
     */
    private String name;
    /**
     * actors的管理者
     */
    private ActorManager actorManager;
    /**
     * actor的消息队列
     */
    private BlockingQueue<Message> messages = new LinkedBlockingDeque<>();
    /**
     * actor是否处于活动状态
     */
    private boolean isActive;

    public Actor(){

    }

    public Actor(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ActorManager getActorManager() {
        return actorManager;
    }

    public void setActorManager(ActorManager actorManager) {
        this.actorManager = actorManager;
    }

    public String getName() {
        return name;
    }

    public BlockingQueue<Message> getMessages() {
        return messages;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    /**
     * 发送消息
     * @param message
     * @throws InterruptedException
     */
    public void send(Message message) throws InterruptedException {
        send(message, message.getTarget());
    }

    /**
     * 发送消息
     * @param message
     * @param target
     * @throws InterruptedException
     */
    public void send(Message message, Actor target) throws InterruptedException {
        if (target == null) {
            return;
        }
        target.getMessages().put(message);
    }

    /**
     * 接收到源actor发送的消息之后，处理内部的业务流程
     */
    public abstract void receive();
}
