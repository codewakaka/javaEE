package com.xgh.test.thread.week02;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * com.xgh.test.thread.week2.RequestQueue
 *
 * @author xgh <br/>
 * @description
 * @date 2021年07月28日
 */
public class RequestQueue {


    private Queue<String> queue = new ArrayBlockingQueue<>(Integer.MAX_VALUE);

    ReentrantLock lock = new ReentrantLock();

    Condition condition = lock.newCondition();

    public String get() {
        String result = null;
        lock.lock();
        try {
            while (queue.isEmpty()) {
                condition.await();
            }
            result = queue.poll();
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
            condition.signalAll();
        } finally {
            lock.unlock();
        }

        return result;
    }

    public void put(String request){
        lock.lock();
        try {
            while (queue.size() >= Integer.MAX_VALUE){
                condition.await();
            }
            queue.offer(request);
            condition.signalAll();
        }catch (InterruptedException e){
            condition.signalAll();
        }finally {
            lock.unlock();
        }

    }


}
