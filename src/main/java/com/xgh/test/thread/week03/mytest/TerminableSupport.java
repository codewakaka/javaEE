package com.xgh.test.thread.week03.mytest;

import java.util.concurrent.ExecutorService;

/**
 * com.xgh.test.thread.week03.mytest.TerminableSupport
 *
 * @author xgh <br/>
 * @description 控制中断主要逻辑，具体实现留给子类继承
 * @date 2021年08月03日
 */
public abstract class TerminableSupport extends Thread implements Terminable {

    //标识
    public final TerminableToken terminableToken;


    public TerminableSupport() {
        this(new TerminableToken());
    }

    public TerminableSupport(TerminableToken terminableToken) {
        this.terminableToken = terminableToken;
        terminableToken.register(this);
    }


    @Override
    public void run() {
        Exception ex = null;
        try {
            if(!terminableToken.isShutdown || terminableToken.taskCount.get() > 0){
                doRun();
            }
        }catch (Exception e){
            ex = e;
        }finally {
            try {
                doClean(ex);
            }finally {
                terminableToken.notifyOther(this);
            }
        }
    }

    @Override
    public void interrupt() {
        terminable();
    }

    protected abstract void doClean(Exception ex);

    protected abstract void doRun()throws Exception;

    @Override
    public void terminable() {
        terminableToken.isShutdown = true;
        try {
            doTerminable();
        }finally {
            if(terminableToken.taskCount.get() < 0){
                super.interrupt();
            }
        }
    }

    public void terminable(Boolean is){
        terminable();
        if(is){
            try {
                this.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    protected abstract void doTerminable();

}
