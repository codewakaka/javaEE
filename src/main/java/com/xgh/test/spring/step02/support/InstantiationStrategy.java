package com.xgh.test.spring.step02.support;

import com.xgh.test.spring.step02.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * com.xgh.test.spring.step02.support.InstantionStrategy
 *
 * @author xgh <br/>
 * @description 实例化策略
 * @date 2021年08月17日
 */
public interface InstantiationStrategy {

    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor,Object[] args);
}
