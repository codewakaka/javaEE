package com.xgh.test.spring.step02.support;

import com.xgh.test.spring.step02.BeansException;
import com.xgh.test.spring.step02.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * com.xgh.test.spring.step02.support.AbstractAutowireCapableBeanFactory
 *
 * @author xgh <br/>
 * @description 实例化bean类
 * @date 2021年08月17日
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object creatBean(String beanName, BeanDefinition beanDefinition, Object... args) throws BeansException {
        Object bean = null;
        try {
            //todo 有参实现
           // bean =  beanDefinition.getBeanClass().newInstance();
            bean =  creatBeanInstance(beanDefinition,beanName,args);

        } catch (Exception e) {
            throw new BeansException("Instantiation of bean fail",e);
        }
        addSingleton(beanName,bean);
        return bean;
    }

    protected  Object creatBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args){
        Constructor constructorToUser = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor<?> ctor : declaredConstructors) {
            if(null != args && ctor.getParameterTypes().length == args.length){
                constructorToUser = ctor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition,beanName,constructorToUser,args);
    };

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
