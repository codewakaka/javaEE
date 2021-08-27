package com.xgh.test.spring.step04.beans.factory.config;

/**
 * com.xgh.test.spring.step02.config.SingletonBeanRegistry
 *
 * @author xgh <br/>
 * @description 单例bean注册
 * @date 2021年08月17日
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);
}
