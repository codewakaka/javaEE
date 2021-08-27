package com.xgh.test.spring.step04.beans.factory.support;

import com.xgh.test.spring.step04.beans.BeansException;
import com.xgh.test.spring.step04.beans.factory.config.BeanDefinition;

/**
 * com.xgh.test.spring.step02.support.BeanDefinitionRegistry
 *
 * @author xgh <br/>
 * @description
 * @date 2021年08月17日
 */
public interface BeanDefinitionRegistry {

    /**
     * @description 向注册表中注册
     * @date 2021/8/27 0027
     * @param beanName
     * @param beanDefinition
     */
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);


    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * @description 判断是否包含指定的beanDefinition
     * @date 2021/8/27 0027
     * @param beanName
     * @return boolean
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * @description 返回注册表所有beanName
     * @date 2021/8/27 0027
     * @return java.lang.String[]
     */
    String[]  getBeanDefinitionNames();

}
