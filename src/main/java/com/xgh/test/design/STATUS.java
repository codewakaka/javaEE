package com.xgh.test.design;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
public enum STATUS {
    //排序字段
    TO_BE_EXECUTD(0," order_time desc"),
    EXECUTED(1," write_off_time DESC"),
    EXPIRED(2," update_time DESC"),
    FAILED(2," update_time DESC"),

    ;
    private int value;
    private String name;


}
