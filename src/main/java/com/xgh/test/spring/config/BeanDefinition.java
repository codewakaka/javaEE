package com.xgh.test.spring.config;

/**
 * com.xgh.test.spring.config.BeanDefinition
 *
 * @author xgh <br/>
 * @description
 * @date 2021年08月16日
 */
public class BeanDefinition {

    private Class beanClass;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}
