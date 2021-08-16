package com.xgh.test.thread.week03.mytest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * com.xgh.test.thread.week03.mytest.MyServer
 *
 * @author xgh <br/>
 * @description
 * @date 2021年08月03日
 */
public class MyServer {

    private final BlockingQueue<String> queue = new ArrayBlockingQueue<>(100);

    private final C c = new C();
    private final P p = new P();

    public static void main(String[] args) throws InterruptedException {
        MyServer myServer = new MyServer();
        myServer.init();
        Thread.sleep(10);
        myServer.shutdown();

    }

    // 停止生产者和消费者的执行
    public void shutdown() {
        p.terminable(true);  // 先停止生产者，只有在生产者完全停止之后才会停止消费者
        c.terminable();  // 停止消费者
    }

    // 启动生产者和消费者
    public void init() {
        p.start();
        c.start();
    }


    class C extends TerminableSupport {

        @Override
        protected void doClean(Exception ex) {

        }

        @Override
        protected void doRun() throws Exception {
            String poll = queue.poll();
            System.out.println("消费"+poll);
            try {
                Thread.sleep(10);
            }finally {
                terminableToken.taskCount.decrementAndGet();
            }


        }

        @Override
        protected void doTerminable() {

        }
    }

    class P extends TerminableSupport {

        @Override
        protected void doClean(Exception ex) {

        }

        @Override
        protected void doRun() throws Exception {
            int i = 0;
            queue.put(++i +"");
            terminableToken.taskCount.incrementAndGet();
        }

        @Override
        protected void doTerminable() {

        }
    }
}
