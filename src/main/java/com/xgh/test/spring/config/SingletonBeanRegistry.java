package com.xgh.test.spring.config;

/**
 * com.xgh.test.spring.config.SingletonBeanRegistry
 *
 * @author xgh <br/>
 * @description
 * @date 2021年08月16日
 */

public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

}
