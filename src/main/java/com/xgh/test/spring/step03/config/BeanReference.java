package com.xgh.test.spring.step03.config;

/**
 * com.xgh.test.spring.step03.config.BeanReference
 *
 * @author xgh <br/>
 * @description
 * @date 2021年08月23日
 */
public class BeanReference {

    private String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
