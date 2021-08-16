package com.xgh.test.spring.support;

import com.xgh.test.spring.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * com.xgh.test.spring.support.DefaultSingletonBeanRegistry
 *
 * @author xgh <br/>
 * @description
 * @date 2021年08月16日
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String,Object> singletonObjects = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    protected void  addSingleBean(String beanName,Object singletonObject){
        singletonObjects.put(beanName,singletonObject);
    }
}
