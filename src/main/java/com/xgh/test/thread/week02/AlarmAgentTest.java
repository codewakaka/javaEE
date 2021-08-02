package com.xgh.test.thread.week02;

/**
 * com.xgh.test.thread.week2.AlarmAgentTest
 *
 * @author xgh <br/>
 * @description
 * @date 2021年07月27日
 */
public class AlarmAgentTest {

    public static void main(String[] args) {
        AlarmAgent alarmAgent = new AlarmAgent();
        alarmAgent.init();

        AlarmInfo alarmInfo = new AlarmInfo(6, 2, "1101", 1);
        try {
            alarmAgent.sendAlarm(alarmInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
