package com.xgh.test.spring.support;

import com.xgh.test.spring.config.BeanDefinition;
import org.springframework.beans.BeansException;

/**
 * com.xgh.test.spring.support.AbstractAutowireCapableBeanFactory
 *
 * @author xgh <br/>
 * @description 实例化bean
 * @date 2021年08月16日
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    @Override
    protected Object creatBean(String beanName, BeanDefinition beanDefinition) throws BeansException {

        Object bean = null;

            try {
                bean = beanDefinition.getBeanClass().newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw  new BeansException("install bean fail" + e);
            }

        addSingleBean(beanName,bean);
        return bean;
    }
}
