package com.xgh.test.spring.step04.beans.factory.support;

import com.xgh.test.spring.step04.beans.BeansException;
import com.xgh.test.spring.step04.beans.factory.BeanFactory;
import com.xgh.test.spring.step04.beans.factory.config.BeanDefinition;

/**
 * com.xgh.test.spring.step02.support.AbstractBeanFactory
 *
 * @author xgh <br/>
 * @description 抽象类定义模板方法
 * @date 2021年08月17日
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String beanName) throws BeansException {
       return doGetBean(beanName,null);
    }

    @Override
    public Object getBean(String beanName, Object... args) throws BeansException {
        return doGetBean(beanName,args);
    }

    protected  <T> T doGetBean(String beanName, Object[] args){
        Object bean = getSingleton(beanName);
        if(bean != null){
            return (T) bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return (T) creatBean(beanName,beanDefinition,args);

    }
    @Override
    public <T> T getBean(String beanName, Class<T> requiredType) {
        return (T)getBean(beanName);
    }

    protected abstract Object creatBean(String beanName, BeanDefinition beanDefinition, Object... args) throws BeansException;

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

}
