package com.xgh.test.thread.week03.mytest;

import java.lang.ref.WeakReference;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * com.xgh.test.thread.week03.mytest.TerminableToken
 *
 * @author xgh <br/>
 * @description 中断标识
 * @date 2021年08月03日
 */
public class TerminableToken {

    //中断标识
    protected volatile  boolean isShutdown = false;

    //任务数
    public AtomicInteger taskCount = new AtomicInteger(0);


    private final Queue<WeakReference<Terminable>> queue;


    public TerminableToken() {
        this.queue = new ConcurrentLinkedDeque<>();
    }


    protected void register(Terminable thread){
        queue.add(new WeakReference<>(thread));
    }


    protected void notifyOther(Terminable thread){
        WeakReference<Terminable> weakReference;
        Terminable other;
        //如果队列任务不为空，就通知其他线程发送终止信号
        while (null != (weakReference = queue.poll())){
            other = weakReference.get();
            if(null != other && thread != other){
                other.terminable();
            }
        }
    }
}
