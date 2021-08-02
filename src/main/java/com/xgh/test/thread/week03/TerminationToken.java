package com.xgh.test.thread.week03;

import java.lang.ref.WeakReference;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * com.xgh.test.thread.week03.TerminationToken
 *
 * @author xgh <br/>
 * @description 线程停止标识
 * @date 2021年08月02日
 */
public class TerminationToken {


    // 是否停止标识
    protected volatile boolean toShutdown = false;

    //未执行任务的数量
    public final  AtomicInteger reservations = new AtomicInteger(0);

    //当多个线程共享一个terminationToken实例时，通过队列来记录所有的停止线程，从而减少锁的方式实现
    private final Queue<WeakReference<Termination>> coordinatedThreads;

    public TerminationToken() {
        this.coordinatedThreads = new ConcurrentLinkedDeque<>();
    }

    //是否终止
    public boolean isToShutdown(){
        return  toShutdown;
    }

    public void setToShutdown(boolean toShutdown){
        this.toShutdown = toShutdown;
    }
    //注册一个线程到terminationToken上
    public void register(Termination thread){
        coordinatedThreads.add(new WeakReference<>(thread));
    }

    //通知terminationThread中所有实例，有一个线程停止了，通知其他线程停止
    public  void notifyThreadTermination(Termination thread){
        WeakReference<Termination> wrThread;
        Termination otherTermination;
        while ((wrThread = coordinatedThreads.poll()) != null){
            otherTermination = wrThread.get();
            if(otherTermination != null && otherTermination != thread){
                otherTermination.terminate();
            }
        }


    }





}
