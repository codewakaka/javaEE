package com.xgh.test.thread.week03;

/**
 * com.xgh.test.thread.week03.ThreadExecutor
 *
 * @author xgh <br/>
 * @description 两阶段终止模式
 * 1.发出信号，告知正在运行的线程将被终止
 * 2.接收到此信号的线程，做完善后工作，停止运行
 * @date 2021年07月28日
 */
public class ThreadExecutor {

    /**
     * 执行线程
     */
    private Thread executeThread;

    /**
     * 运行状态
     */
    private volatile boolean isRunning = false;

    /**
     * task 为发生阻塞的线程
     */
    public void execute(Runnable task) {
        executeThread = new Thread(() -> {
            Thread childThread = new Thread(task);
            //子线程设置为守护线程
            childThread.setDaemon(true);
            childThread.start();

            try {
                childThread.join();
                isRunning = true;
            } catch (InterruptedException e) {
                System.out.println("是我打了异常");
                e.printStackTrace();
            }

        });
        executeThread.start();
    }


    public void shutdown(long mills) {
        long currentTime = System.currentTimeMillis();
        while (!isRunning) {
            if (System.currentTimeMillis() - currentTime >= mills) {
                System.out.println("任务超时，需要结束他");
                executeThread.interrupt();
                break;
            }
        }

    }

    public static void main(String[] args) {
        ThreadExecutor executor = new ThreadExecutor();
        long start = System.currentTimeMillis();
        executor.execute(()->{
            try {
                Thread.sleep(5000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });
        executor.shutdown(1000);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

}
