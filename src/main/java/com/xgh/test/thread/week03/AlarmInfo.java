package com.xgh.test.thread.week03;

import lombok.Data;

/**
 * com.xgh.test.thread.week03.AlarmInfo
 *
 * @author xgh <br/>
 * @description 机器告警信息
 * @date 2021年08月02日
 */
@Data
public class AlarmInfo {

    //机器告警类型
    private AlarmType alarmType;

    //机器告警编号
    private String  id;

    //机器告警参数
    private String extraInfo;


    public AlarmInfo(AlarmType alarmType, String id, String extraInfo) {
        this.alarmType = alarmType;
        this.id = id;
        this.extraInfo = extraInfo;
    }

    //获取机器告警信息的唯一id
    public String getUniqueId(){
        return this.alarmType.getDesc() +":"+this.id +":"+this.extraInfo;
    }

    public String getUniqueByAlarmType(AlarmType alarmType){
        return alarmType.getDesc() +":"+this.id +":"+this.extraInfo;
    }





}
