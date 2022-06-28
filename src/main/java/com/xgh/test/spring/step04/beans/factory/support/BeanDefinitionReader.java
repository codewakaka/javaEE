package com.xgh.test.spring.step04.beans.factory.support;

import com.xgh.test.spring.step04.beans.BeansException;
import com.xgh.test.spring.step04.core.io.Resource;
import com.xgh.test.spring.step04.core.io.ResourceLoader;

/**
 * com.xgh.test.spring.step04.beans.factory.support.BeanDefinitionReader
 *
 * @author xgh <br/>
 * @description bean定义读取接口
 * @date 2021年08月27日
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource)throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

    void loadBeanDefinitions(String... locations)throws BeansException;

}
