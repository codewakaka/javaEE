package com.xgh.test.spring.step04.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * com.xgh.test.spring.step03.PropertyValues
 *
 * @author xgh <br/>
 * @description
 * @date 2021年08月23日
 */
public class PropertyValues {

    private final List<PropertyValue> propertyValues = new ArrayList<>();


    public void addPropertyValue(PropertyValue propertyValue){
        this.propertyValues.add(propertyValue);
    }


    public PropertyValue[] getPropertyValues(){
        return this.propertyValues.toArray(new PropertyValue[0]);
    }


    public PropertyValue getPropertyValue(String name){
        for (PropertyValue propertyValue : propertyValues) {
            if(propertyValue.getName().equals(name)){
                return propertyValue;
            }
        }
        return null;
    }
}
