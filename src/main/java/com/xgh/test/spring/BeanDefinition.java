package com.xgh.test.spring;

/**
 * com.xgh.test.spring.BeanDefinition
 *
 * @author xgh <br/>
 * @description
 * @date 2021年08月12日
 */
public class BeanDefinition {

    private Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }


}
