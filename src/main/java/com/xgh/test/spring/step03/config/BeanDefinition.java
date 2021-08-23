package com.xgh.test.spring.step03.config;

import com.xgh.test.spring.step03.PropertyValues;

/**
 * com.xgh.test.spring.step02.config.BeanDefinition
 *
 * @author xgh <br/>
 * @description
 * @date 2021年08月17日
 */
public class BeanDefinition {

    private Class beanClass;

    private PropertyValues propertyValues;


    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}
