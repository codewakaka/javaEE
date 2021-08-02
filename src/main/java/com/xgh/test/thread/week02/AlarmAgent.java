package com.xgh.test.thread.week02;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * com.xgh.test.thread.week2.AlarmAgent
 *
 * @author xgh <br/>
 * @description
 * @date 2021年07月27日
 */
public class AlarmAgent {

    //报警系统是否连接上了报警服务器
    private volatile boolean connectedToServer = false;

    //保护性条件
    Predicate agentConnected = new Predicate() {
        @Override
        public boolean evaluate() {
            return connectedToServer;
        }
    };

    //blocker对象
    private Blocker blocker = new ConditionVarBlocker(false);

    //上报报警信息给报警服务
    public void sendAlarm(AlarmInfo alarmInfo) throws Exception {
        //构建guardedAction(要执行的方法)
        GuardedAction<Void> guardedAction = new GuardedAction<Void>(agentConnected) {
            @Override
            public Void call() throws Exception {
                //执行目标函数
                doSendAlarm(alarmInfo);
                return null;
            }
        };
        //通过blocker执行目标（判断是否连接成功，如果连接成功就调用目标方法doSendAlarm发送报警通知）
        blocker.callWithGuard(guardedAction);

    }

    //发送报警信息给报警服务器
    private void doSendAlarm(AlarmInfo alarmInfo) {
        //建立socket连接
        System.out.println("执行发送报警方法start send alarm " + alarmInfo);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //虚拟上报
        System.out.println("end send alarm");
    }


    //init: 初始化报警服务，和报警服务器建立连接，并定时发送心跳信息
    //sendAlarm：发送报警信息给服务器
    //onConnected: 和报警中心建立连接
    //reConnected: 重新和报警中心建立连接
    //onDisconnected: 断开和报警中心的连接
    public void init() {
        //出发连接
        Thread connectingThread = new Thread(new ConnectingTask());
        connectingThread.start();
        //定时任务
        ScheduledThreadPoolExecutor heardThreadPoolExecutor = new ScheduledThreadPoolExecutor(5, new ThreadFactory() {
            private AtomicInteger index = new AtomicInteger();

            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread();
                thread.setName("heard-thread-" + index.incrementAndGet());
                thread.setDaemon(true);
                System.out.println("heard-thread-" + index);
                return thread;
            }
        });
        //5000表示首次执行任务的延迟时间，2000表示每次执行任务的间隔时间，TimeUnit.MILLISECONDS执行的时间间隔数值单位
        //每5s执行一次
        heardThreadPoolExecutor.scheduleAtFixedRate(new HeartbeatTask(), 5000, 2000, TimeUnit.MILLISECONDS);
    }


    //确定报警器和服务建立连接
    private void onConnected() {
        try {
            System.out.println("连接成功后将标识位connectedServer 修改成true，同时唤醒其他的等待线程");
            blocker.signalAfter(() -> {
                System.out.println("update connectedServer = true ");
                connectedToServer = true;
                return Boolean.TRUE;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //重新连接
    private void reconnected() {
        //重新连接一次
        ConnectingTask connectingTask = new ConnectingTask();
        //直接通过心跳线程执行一次重连，
        connectingTask.run();
    }

    //断开连接
    private void onDisconnected() {
        connectedToServer = false;
    }

    /*
     * @description 测试连接是否正常
     * @date 2021/7/27 0027
     * @return boolean
     */
    private boolean tesConnection() {
        //通过socket给报警服务器发送一次连接
        //模拟发送一次
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test connection normal");
        return true;
    }

    //与报警服务建立连接
    class ConnectingTask implements Runnable {

        @Override
        public void run() {
            //与服务器连接
            System.out.println("与服务连接执行");
            try {
                //休息10s
                System.out.println("睡十秒假装连接");
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //连接建立完成1
            System.out.println("alarm connected 假装连接成功");

            onConnected();
        }


    }

    class HeartbeatTask implements Runnable {

        @Override
        public void run() {
            if (!tesConnection()) {
                //连接断开
                onDisconnected();
                //重新连接
                reconnected();
            }
        }


    }


}
