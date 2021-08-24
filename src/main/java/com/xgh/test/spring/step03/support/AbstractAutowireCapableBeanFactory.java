package com.xgh.test.spring.step03.support;

import cn.hutool.core.bean.BeanUtil;
import com.xgh.test.spring.step03.BeansException;
import com.xgh.test.spring.step03.PropertyValue;
import com.xgh.test.spring.step03.PropertyValues;
import com.xgh.test.spring.step03.config.BeanDefinition;
import com.xgh.test.spring.step03.config.BeanReference;

import java.lang.reflect.Constructor;

/**
 * com.xgh.test.spring.step02.support.AbstractAutowireCapableBeanFactory
 *
 * @author xgh <br/>
 * @description 实例化bean类
 * @date 2021年08月17日
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object creatBean(String beanName, BeanDefinition beanDefinition, Object... args) throws BeansException {
        Object bean = null;
        try {

            bean =  creatBeanInstance(beanDefinition,beanName,args);
            //设置参数
            applyPropertyValues(beanName,bean,beanDefinition);
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

    protected  void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition){
       try {
           PropertyValues propertyValues = beanDefinition.getPropertyValues();
           for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
               String name = propertyValue.getName();
               Object value = propertyValue.getValue();
               if(value instanceof BeanReference){
                   //a依赖b
                   BeanReference beanReference =  (BeanReference)value;
                   value = getBean(beanReference.getBeanName());
               }
               BeanUtil.setFieldValue(bean,name,value);
           }
       }catch (Exception e){
           throw new BeansException("Error setting property values" + beanName);
       }


    };
    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
