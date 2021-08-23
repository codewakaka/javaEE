package com.xgh.test.spring.step03.support;

import com.xgh.test.spring.step03.config.BeanDefinition;

/**
 * com.xgh.test.spring.step02.support.BeanDefinitionRegistry
 *
 * @author xgh <br/>
 * @description
 * @date 2021年08月17日
 */
public interface BeanDefinitionRegistry {

    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

}
