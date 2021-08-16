package com.xgh.test.spring.support;

import com.xgh.test.spring.BeanFactory;
import com.xgh.test.spring.config.BeanDefinition;
import org.springframework.beans.BeansException;

/**
 * com.xgh.test.spring.support.AbstractBeanFactory
 *
 * @author xgh <br/>
 * @description
 * @date 2021年08月16日
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String beanName) throws BeansException {
        Object singleton = getSingleton(beanName);
        if(null != singleton){
            return singleton;
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);

        return creatBean(beanName,beanDefinition);
    }

    protected abstract Object creatBean(String beanName, BeanDefinition beanDefinition) throws BeansException;

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;
}
