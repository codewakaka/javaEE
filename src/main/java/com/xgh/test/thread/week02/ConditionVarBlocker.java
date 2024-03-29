package com.xgh.test.thread.week02;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * com.xgh.test.thread.week2.ConditionVarBlocker
 *
 * @author xgh <br/>
 * @description 延时等待
 * @date 2021年07月27日
 */
public class ConditionVarBlocker implements Blocker{

    private final Lock lock;

    private final Condition condition;

    private final boolean allowAccess2Lock;

    public ConditionVarBlocker(Lock lock, Condition condition, boolean allowAccess2Lock) {
        this.lock = lock;
        this.condition = condition;
        this.allowAccess2Lock = allowAccess2Lock;
    }

    public ConditionVarBlocker(boolean allowAccess2Lock) {
        this(new ReentrantLock(),allowAccess2Lock);
    }

    public ConditionVarBlocker(Lock lock, boolean allowAccess2Lock) {
        this.lock = lock;
        this.condition = lock.newCondition();
        this.allowAccess2Lock = allowAccess2Lock;
    }

    //判断是否与中心连接成功，连接成功就发送消息
    //如果没有连接成功就等待
    @Override
    public <V> V callWithGuard(GuardedAction<V> guardedAction) throws Exception {
        lock.lockInterruptibly();
        try {
            //判断条件是否满足满足则执行目标动作,不满足则进入条件等待队列中
            final Predicate predicate = guardedAction.predicate;
            //对应connectedToServer的值的改变
            while (!predicate.evaluate()){

                System.out.println("判断是否能够执行，如果不行，线程进入等待 alarm connecting alarm system,thread  wait");
                //条件不满足
                condition.await();
                //当线程从条件等待队列欢迎后，获取锁成功，然后再次尝试去判断条件是否满足
            }
            //条件满足，执行目标内容
            System.out.println("条件满足，执行线程，alarm connected execute call");
            return guardedAction.call();
        }finally {
            lock.unlock();
        }
    }

    @Override
    public void signalAfter(Callable<Boolean> stateOperation) throws Exception {
        //获取锁
        lock.lockInterruptibly();
        try {
            //执行传入的方法，看是否能唤醒线程
            if(stateOperation.call()){
                //条件满足唤醒
                System.out.println("唤醒等待线程，alarm connected,signal thread");
                condition.signal();
            }
        }finally {
            lock.unlock();
        }
    }

    @Override
    public void signal() throws Exception {
        lock.lockInterruptibly();
        try{
            condition.signal();
        }finally {
            lock.unlock();
        }
    }

    @Override
    public void broadcastAfter(Callable<Boolean> stateOperator) throws Exception {
            lock.lockInterruptibly();
            try {
                if(stateOperator.call()){
                    //条件满足唤醒所有
                    condition.signalAll();
                }
            }finally {
                lock.unlock();
            }
    }

    public Lock getLock(){
        if(allowAccess2Lock){
            return this.lock;
        }
        throw  new IllegalStateException("access to the lock disallowed");
    }

}
