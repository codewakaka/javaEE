package com.xgh.test.thread.week03;

/**
 * com.xgh.test.thread.week03.AbstractTerminationThread
 *
 * @author xgh <br/>
 * @description
 * @date 2021年08月02日
 */
public abstract class AbstractTerminationThread extends Thread implements Termination{


    /**
     *
     * 线程共享停止的标志实例对象
     */
    public final TerminationToken terminationToken;


    public AbstractTerminationThread(){
        this(new TerminationToken());
    }

    public AbstractTerminationThread(TerminationToken terminationToken){
        this.terminationToken = terminationToken;
        System.out.println("注册告警线程到线程停的标志实现对象队列中");
        terminationToken.register(this);
    }

    @Override
    public void terminate() {
        System.out.println("设置中断标志对象为中断状态");
        this.terminationToken.setToShutdown(true);
        try {
            doTerminate();
        }finally {
            //如果没有等待的任务，则强制停止线程
            if(terminationToken.reservations.get()<=0){
                super.interrupt();
            }
        }
    }

    //执行终止线程的逻辑，留给子类实现
    protected  void doTerminate(){

    };

    @Override
    public void run() {
        Exception ex = null;
        try {
            for (;;){
                System.out.println("告警线程执行，此时中断标志位："+terminationToken.isToShutdown() +",未完成的任务数量："+terminationToken.reservations.get());
                if(terminationToken.isToShutdown() && terminationToken.reservations.get() <= 0){
                    //线程已经终止了，中断退出
                    System.out.println("中断标志位true，未完成任务为0，告警线程退出");
                    break;
                }
                //执行具体逻辑
                doRun();
            }
        }catch (Exception e){
            ex = e;
            if(ex instanceof  InterruptedException){
                System.out.println("中断响应："+e);
            }
        }finally {
            try {
                System.out.println("告警线程停止，回调终止后的清理工作开始");
                doCleanup(ex);
            }finally {
                //通知terminationToken管理的所有线程退出
                System.out.println("标志实例对象中一个线程终止，通知其他线程终止");
                terminationToken.notifyThreadTermination(this);
            }
        }



    }

    //留给子类实现，完成线程终止后的一些清理工作
    protected  void doCleanup(Exception ex){

    };

    protected abstract void doRun()throws InterruptedException;
}
