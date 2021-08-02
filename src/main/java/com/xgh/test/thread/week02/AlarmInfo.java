package com.xgh.test.thread.week02;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * com.xgh.test.thread.week2.AlarmInfo
 *
 * @author xgh <br/>
 * @description
 * @date 2021年07月27日
 */
@Data
@AllArgsConstructor
public class AlarmInfo {


    //楼号
    private Integer no;

    //几单元
    private Integer unit;

    //几零几
    private  String roomNumber;


    //报警类型
    private Integer alarmType;




}
