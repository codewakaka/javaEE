package com.xgh.test.spring.step04.beans;

/**
 * com.xgh.test.spring.step03.PropertyValue
 *
 * @author xgh <br/>
 * @description 属性
 * @date 2021年08月23日
 */
public class PropertyValue {

    private String name;

    private Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
