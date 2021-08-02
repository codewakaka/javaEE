package com.xgh.test.thread.week03;

/**
 * com.xgh.test.thread.week03.AlarmManager
 *
 * @author xgh <br/>
 * @description 自动对机器进行监控，机器的报警信息会上报到监控系统中
 * @date 2021年08月02日
 */
public class AlarmManager {
    //是否关闭
    private volatile  boolean shutdownRequested = false;

    private static final AlarmManager instance = new AlarmManager();

    //上报机器的报警信息工作现场
    private final AlarmSendingThread alarmSendingThread;

    private AlarmManager(){
        System.out.println("创建机器上报告警信息的后台线程");
        alarmSendingThread = new AlarmSendingThread();
    }

    public static AlarmManager getInstance(){
        return instance;
    }


    public void init(){
        alarmSendingThread.start();
    }

    public int sendAlarm(AlarmType alarmType,String id,String extraInfo){
        AlarmInfo alarmInfo = new AlarmInfo(alarmType,id,extraInfo);
        //重复提交次数
        int duplicateSubmissionCount = 0;
        try {
            duplicateSubmissionCount = alarmSendingThread.sendAlarm(alarmInfo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return duplicateSubmissionCount;
    }
    //关闭机器上报功能
    public synchronized   void shutdown(){
        if(shutdownRequested){
            throw new IllegalStateException("停止请求已经发送");
        }
        //关闭后台警告线程
        alarmSendingThread.terminate();
        shutdownRequested = true;
    }




}
