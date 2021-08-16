package com.xgh.test.spring;

import org.springframework.beans.BeansException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * com.xgh.test.spring.BeanFactory
 *
 * @author xgh <br/>
 * @description
 * @date 2021年08月12日
 */
public interface BeanFactory {

    public Object getBean(String beanName) throws BeansException;

}
