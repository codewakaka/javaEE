package com.xgh.test.spring.step04.beans.factory.config;

import com.xgh.test.spring.step04.beans.BeansException;

public interface BeanPostProcessor {

    /**
     *  在bean对象执行初始化方法之前，执行此方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;


    /**
     *  在bean对象执行初始化完成之后执行此方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessAfterInitialization(Object bean,String beanName) throws  BeansException;
}
