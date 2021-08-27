package com.xgh.test.spring.step04.beans.factory;

import com.xgh.test.spring.step04.beans.BeansException;
import com.xgh.test.spring.step04.beans.factory.config.AutowireCapableBeanFactory;
import com.xgh.test.spring.step04.beans.factory.config.BeanDefinition;
import com.xgh.test.spring.step04.beans.factory.config.ConfigurableBeanFactory;

/**
 * com.xgh.test.spring.step04.beans.factory.ConfigurableListableBeanFactory
 *
 * @author xgh <br/>
 * @description
 * @date 2021年08月27日
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName)throws BeansException;
}
