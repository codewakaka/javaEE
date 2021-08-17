package com.xgh.test.spring.step02.config;

/**
 * com.xgh.test.spring.step02.config.BeanDefinition
 *
 * @author xgh <br/>
 * @description
 * @date 2021年08月17日
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
