package com.xgh.test.spring.step02.support;

import com.xgh.test.spring.step02.BeansException;
import com.xgh.test.spring.step02.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * com.xgh.test.spring.step02.support.DefaultListableBeanFactory
 *
 * @author xgh <br/>
 * @description 核心实现
 * @date 2021年08月17日
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry{

    private Map<String,BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    protected BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if(null == beanDefinition){
            throw new BeansException("No bean named '"+ beanName+"' is definde");
        }
        return beanDefinition;
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName,beanDefinition);
    }
}
