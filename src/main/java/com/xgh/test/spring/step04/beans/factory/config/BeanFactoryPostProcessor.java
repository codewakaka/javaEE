package com.xgh.test.spring.step04.beans.factory.config;


import com.xgh.test.spring.step04.beans.BeansException;
import com.xgh.test.spring.step04.beans.factory.ConfigurableListableBeanFactory;

public interface BeanFactoryPostProcessor {


    /**
     * 在所有的beanDefinition加载完成后，实例化bean对象之前，提供修改BeanDefinition属性的机制
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
