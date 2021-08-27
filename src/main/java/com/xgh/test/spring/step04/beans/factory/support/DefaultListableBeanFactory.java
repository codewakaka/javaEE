package com.xgh.test.spring.step04.beans.factory.support;

import com.xgh.test.spring.step04.beans.BeansException;
import com.xgh.test.spring.step04.beans.factory.ConfigurableListableBeanFactory;
import com.xgh.test.spring.step04.beans.factory.config.BeanDefinition;
import com.xgh.test.spring.step04.beans.factory.config.ConfigurableBeanFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * com.xgh.test.spring.step02.support.DefaultListableBeanFactory
 *
 * @author xgh <br/>
 * @description 核心实现
 * @date 2021年08月17日
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();


    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName,beanDefinition);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        Map<String, T> result = new HashMap<>();
        beanDefinitionMap.forEach((beanName,beanDefinition)->{
            Class beanClass = beanDefinition.getBeanClass();
            if(type.isAssignableFrom(beanClass)){
                result.put(beanName,(T) getBean(beanName));
            }
        });
        return result;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[0]);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if(null == beanDefinition){
            throw new BeansException("No bean named '"+ beanName+"' is definded");
        }
        return beanDefinition;
    }


}
