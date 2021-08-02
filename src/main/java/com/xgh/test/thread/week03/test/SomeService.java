package com.xgh.test.thread.week03.test;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * com.xgh.test.thread.week03.test.SomeService
 *
 * @author xgh <br/>
 * @description
 * @date 2021年08月02日
 */
public class SomeService {

    private final BlockingQueue<String> queue = new ArrayBlockingQueue<>(100);

    private final Producer producer = new Producer();
    private final Consumer consumer = new Consumer();

    public static void main(String[] args) throws InterruptedException {
        SomeService ss = new SomeService();
        ss.init();
        TimeUnit.SECONDS.sleep(500);
        ss.shutdown();
    }

    // 停止生产者和消费者的执行
    public void shutdown() {
        producer.terminate(true);  // 先停止生产者，只有在生产者完全停止之后才会停止消费者
        consumer.terminate();  // 停止消费者
    }

    // 启动生产者和消费者
    public void init() {
        producer.start();
        consumer.start();
    }

    // 生产者
    private class Producer extends TerminatableSupport {
        private int i = 0;

        @Override
        protected void doRun() throws Exception {
            queue.put(String.valueOf(i++));  // 将任务添加到任务队列中
            consumer.terminationToken.reservations.incrementAndGet();  // 更新需要执行的任务数量
            System.out.println("待消费个数"+ consumer.terminationToken.reservations.get());
        }
    }

    // 消费者
    private class Consumer extends TerminatableSupport {
        @Override
        protected void doRun() throws Exception {
            String product = queue.take();  // 获取任务
            System.out.println("Processing product: " + product);
            try {
                TimeUnit.SECONDS.sleep(10);  // 模拟消费者对任务的执行
            } catch (InterruptedException e) {
                // ignore
            } finally {
                terminationToken.reservations.decrementAndGet();  // 更新需要执行的任务数量
            }
        }
    }
}
