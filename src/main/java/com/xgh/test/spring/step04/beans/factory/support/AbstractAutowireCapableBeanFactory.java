package com.xgh.test.spring.step04.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.xgh.test.spring.step04.beans.BeansException;
import com.xgh.test.spring.step04.beans.PropertyValue;
import com.xgh.test.spring.step04.beans.PropertyValues;
import com.xgh.test.spring.step04.beans.factory.DisposableBean;
import com.xgh.test.spring.step04.beans.factory.InitializingBean;
import com.xgh.test.spring.step04.beans.factory.config.AutowireCapableBeanFactory;
import com.xgh.test.spring.step04.beans.factory.config.BeanDefinition;
import com.xgh.test.spring.step04.beans.factory.config.BeanPostProcessor;
import com.xgh.test.spring.step04.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * com.xgh.test.spring.step02.support.AbstractAutowireCapableBeanFactory
 *
 * @author xgh <br/>
 * @description 实例化bean类
 * @date 2021年08月17日
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();
    
    @Override
    protected Object creatBean(String beanName, BeanDefinition beanDefinition, Object... args) throws BeansException {
        Object bean = null;
        try {
            bean = creatBeanInstance(beanDefinition,beanName,args);
            //给bean填充属性
            applyPropertyValues(beanName,bean,beanDefinition);

            //执行bean的初始化方法和beanPostProcessor的前置后置方法
            bean = initializeBean(beanName,bean,beanDefinition);

        }catch (Exception e){
            throw new BeansException("instantiation of bean failed",e);
        }
        //注册实现了disposableBean接口的bean
        registerDisposableBeanIfNecessary(beanName,bean,beanDefinition);
        addSingleton(beanName,bean);
        return bean;
    }

    protected  void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition){
        if(bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())){
            registerDisposableBean(beanName,new DisposableBeanAdapter(bean,beanName,beanDefinition));
        }
    }


    protected  Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition){
        //执行beanPostProcessor before处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean,beanName);
        try {
            //待完成内容invokeInitMethods
            invokeInitMethods(beanName,wrappedBean,beanDefinition);
        }catch (Exception e){
            throw new BeansException("Invocation of init method of bean[" + beanName + "] failed", e);
        }

        //执行beanPostProcessor after
        wrappedBean = applyBeanPostProcessorAfterInitialization(bean,beanName);
        return wrappedBean;
    }

    private Object applyBeanPostProcessorAfterInitialization(Object existingBean, String beanName) {
        Object result =existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current =  processor.postProcessAfterInitialization(result,beanName);
            if(null == current){
                return result;
            }
            result = current;
        }
        return result;
    }

    private void invokeInitMethods(String beanName, Object bean, BeanDefinition beanDefinition) throws Exception {
        //1实现接口initializingBean
        if(bean instanceof InitializingBean){
            ((InitializingBean) bean).afterPropertiesSet();
        }
        //注解配置init-method(判断是否为了避免二次执行)
       String initMethodName =  beanDefinition.getInitMethodName();
        if(StrUtil.isNotEmpty(initMethodName)){
            Method initMethod = beanDefinition.getBeanClass().getMethod(initMethodName);
            if(null == initMethod){
                throw new BeansException("Could not find an init method named '" + initMethodName + "' on bean with name '" + beanName + "'");
            }
            initMethod.invoke(bean);
        }

    }

    public   Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName){
        Object result =existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current =  processor.postProcessBeforeInitialization(result,beanName);
            if(null == current){
                return result;
            }
            result = current;
        }
        return result;
    };

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
