package com.xgh.test.thread.week03;

/**
 * com.xgh.test.thread.week03.AlarmType
 *
 * @author xgh <br/>
 * @description
 * @date 2021年08月02日
 */
public enum  AlarmType {

    /**
     * 故障
     */
    FAULT(1, "故障"),

    /**
     * 恢复告警
     */
    RESUME(2, "刷新");

    private Integer alarmType;

    private String desc;

    AlarmType(Integer alarmType, String desc) {
        this.alarmType = alarmType;
        this.desc = desc;
    }

    public Integer getAlarmType() {
        return alarmType;
    }

    public String getDesc() {
        return desc;
    }
}
