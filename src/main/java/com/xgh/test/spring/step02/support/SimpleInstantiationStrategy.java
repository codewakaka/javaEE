package com.xgh.test.spring.step02.support;

import com.xgh.test.spring.step02.BeansException;
import com.xgh.test.spring.step02.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * com.xgh.test.spring.step02.support.SimpleInstantiationStrategy
 *
 * @author xgh <br/>
 * @description jdk
 * @date 2021年08月17日
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {


    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) {
        Class clazz = beanDefinition.getBeanClass();
        try {
            if (null != ctor) {
                return clazz.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
            }else {
                return clazz.getDeclaredConstructor().newInstance();
            }
        }catch(InstantiationException |IllegalAccessException|InvocationTargetException | NoSuchMethodException e) {
            throw new BeansException("Failed to instantiate["+clazz.getName()+"]",e);
        }
    }


}
