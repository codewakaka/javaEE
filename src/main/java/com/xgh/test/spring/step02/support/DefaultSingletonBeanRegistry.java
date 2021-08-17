package com.xgh.test.spring.step02.support;

import com.xgh.test.spring.step02.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * com.xgh.test.spring.step02.support.DefaultSingletinBeanRegistry
 *
 * @author xgh <br/>
 * @description 单例实例的获取接口
 * @date 2021年08月17日
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String,Object> singletonObjects = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    protected void addSingleton(String beanName,Object singletonObject){
        singletonObjects.put(beanName,singletonObject);
    }
}
