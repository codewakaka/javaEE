package com.xgh.test.thread.week03;

import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * com.xgh.test.thread.week03.AlarmSendingThread
 *
 * @author xgh <br/>
 * @description 上报机器报警信息的工作线程
 * @date 2021年08月02日
 */
public class AlarmSendingThread extends AbstractTerminationThread {

    //机器告警队列
    private final BlockingQueue<AlarmInfo> alarmQueue;

    //已经提交的机器告警信息
    private final ConcurrentHashMap<String, AtomicInteger> submittedAlarmRegistry;

    public AlarmSendingThread() {
        this.alarmQueue = new ArrayBlockingQueue<>(100);
        this.submittedAlarmRegistry = new ConcurrentHashMap<>();
    }

    public int sendAlarm(AlarmInfo alarmInfo) {
        if (terminationToken.isToShutdown()) {
            //已经终止
            System.err.println("已经终止了：" + alarmInfo);
            return -1;
        }
        try {
            //放入机器的告警队列中
            AtomicInteger prevSubmittedCounter;
            prevSubmittedCounter = submittedAlarmRegistry.putIfAbsent(alarmInfo.getUniqueId(), new AtomicInteger());
            if (prevSubmittedCounter == null) {
                //代表之前该类型的机器告警为空 ；未完成任务+1
                terminationToken.reservations.incrementAndGet();
                alarmQueue.put(alarmInfo);
            } else {
                //当前的故障还没有回复，不需要重复上报机器高中，只是增加机器告警的次数
                return prevSubmittedCounter.incrementAndGet();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }


    @Override
    protected void doRun() throws InterruptedException {
        AlarmInfo alarmInfo;

        alarmInfo = alarmQueue.take();
        System.out.println("告警线程队列中拉取到告警信息："+alarmInfo);
        //机器告警数量-1
        terminationToken.reservations.decrementAndGet();
        //发送机器告警信息到智慧监控系统中
        try {
            doSendAlarm();
            System.out.println("机器告警信息上报完成");
        }catch (Exception e){
            e.printStackTrace();
        }
        //如果当前是回复警告，则需要清空当前告警的统计信息，重置为空
        if(Objects.equals(alarmInfo.getAlarmType(),AlarmType.RESUME)){
            System.out.println("机器告警："+alarmInfo +"已恢复，清空告警次数");
            submittedAlarmRegistry.remove(alarmInfo.getUniqueByAlarmType(AlarmType.FAULT));
            submittedAlarmRegistry.remove(alarmInfo.getUniqueByAlarmType(AlarmType.RESUME));
        }
    }



    private void doSendAlarm() {
        try {
            System.out.println("假装上报到监控系统");
            Thread.sleep(50);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}
