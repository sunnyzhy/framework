package com.zhy.framework.actor.core;

import java.util.*;

/**
 * @author zhy
 * @date 2020/9/25 15:23
 *
 * actors的管理者
 * <p>
 * 调用的流程：
 * 1. 初始化ActorManager的对象
 * 2. 开启ActorManager的线程池
 * 3. 创建业务actor的实例
 * 4. 把业务actor加入到ActorManager的actors队列
 */
public class ActorManager extends Actor {
    /**
     * ActorManager所管理的线程池
     */
    private List<Thread> threads = new ArrayList<>();
    /**
     * ActorManager所管理的actors队列
     */
    private Map<String, Actor> actors = new HashMap<>();
    /**
     * ActorManager所管理的处于运行状态中的actors队列
     */
    private Map<String, Actor> runnableActors = new HashMap<>();

    public ActorManager() {
        super();
    }

    public ActorManager(String name) {
        super(name);
        setActive(true);
    }

    /**
     * 接收消息
     */
    @Override
    public void receive() {
    }

    /**
     * 开启ActorManager的线程池
     *
     * @param threadCount
     */
    public void startThreadPool(int threadCount) {
        // 开启处理actor-manager的线程
        String actorManagerThreadName = "actor-manager-thread";
        Thread actorManagerThread = new Thread(new ActorManagerRunnable(actorManagerThreadName, this), actorManagerThreadName);
        threads.add(actorManagerThread);
        actorManagerThread.start();

        // 开启处理actor的线程
        for (int i = 0; i < threadCount; i++) {
            String actorThreadName = "actor-thread-" + i;
            Thread actorThread = new Thread(new ActorRunnable(actorThreadName), actorThreadName);
            threads.add(actorThread);
            actorThread.start();
        }
    }

    /**
     * 终止ActorManager的线程池以及actor的处理业务
     */
    public void terminate() {
        synchronized (runnableActors) {
            // 把runnableActors的活动队列里的业务actor的活动状态设置为false
            for (String actor : runnableActors.keySet()) {
                runnableActors.get(actor).setActive(false);
            }
        }
        // 终止线程池里的线程
        for (Thread thread : threads) {
            thread.interrupt();
            System.out.println(String.format("%s terminate", thread.getName()));
        }
    }

    /**
     * 创建业务actor
     *
     * @param clazz
     * @param name
     * @param <T>
     * @return
     */
    public <T extends Actor> T createActor(Class<T> clazz, String name) {
        T actor = null;
        synchronized (actors) {
            if (!actors.containsKey(name)) {
                try {
                    actor = clazz.newInstance();
                    actor.setName(name);
                    actor.setActorManager(this);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return actor;
    }

    /**
     * 把业务actor加入到ActorManager的actors队列
     *
     * @param actor
     */
    public void startActor(Actor actor) {
        String name = actor.getName();
        synchronized (actors) {
            if (!actors.containsKey(name)) {
                // 设置actor为活动状态
                actor.setActive(true);
                // 把actor加入到actors的队列
                actors.put(name, actor);
            }
        }
    }

    /**
     * ActorManager线程
     */
    private class ActorManagerRunnable implements Runnable {
        private String name;
        private ActorManager actorManager;

        public ActorManagerRunnable(String name, ActorManager actorManager) {
            this.name = name;
            this.actorManager = actorManager;
        }

        @Override
        public void run() {
            System.out.println(name + " running ...");
            synchronized (runnableActors) {
                // 把actorManager加入到runnableActors的活动队列里
                runnableActors.put(getName(), actorManager);
            }
            receive();
        }
    }

    /**
     * ActorManager线程池里的actor线程
     * <p>
     * 每个actor线程每次从actors队列里取出一个actor，并调用其receive方法，
     * 待actor内部的receive方法执行完成之后，actor线程再从actors队列里取出下一个actor并调用其receive方法
     */
    private class ActorRunnable implements Runnable {
        /**
         * actor线程的名称
         */
        private String name;

        public ActorRunnable(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(name + " running ...");
            while (isActive()) {
                try {
                    Actor actor = null;
                    synchronized (actors) {
                        if (!actors.isEmpty()) {
                            // 从actors队列里取出一个actor，并把该actor从actors队列里移除
                            Iterator<String> iterator = actors.keySet().iterator();
                            if (iterator.hasNext()) {
                                actor = actors.get(iterator.next());
                                // 把actor加入到runnableActors的活动队列里
                                runnableActors.put(actor.getName(), actor);
                                // 把actor从actors的队列里移除
                                iterator.remove();
                            }
                        }
                    }
                    if (actor != null) {
                        System.out.println(String.format("%s 执行 %s 的任务", name, actor.getName()));
                        // 执行actor内部的业务流程
                        actor.receive();
                        synchronized (runnableActors) {
                            // 把actor从runnableActors的活动队列里移除
                            runnableActors.remove(actor.getName());
                        }
                    }
                    // 处于sleep中的线程被调用interrupt方法时，会抛出InterruptException异常
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            System.out.println(String.format("%s quit", name));
        }
    }
}
