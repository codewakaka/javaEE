package com.xgh.test.spring.step04.beans.factory;

import com.xgh.test.spring.step04.beans.BeansException;

/**
 * com.xgh.test.spring.step04.beans.factory.BeanFactory
 *
 * @author xgh <br/>
 * @description
 * @date 2021年08月27日
 */
public interface BeanFactory {


        public Object getBean(String beanName) throws BeansException;

        <T> T getBean(String beanName,Class<T> requiredType);

        Object getBean(String beanName,Object... args )throws BeansException;



}
