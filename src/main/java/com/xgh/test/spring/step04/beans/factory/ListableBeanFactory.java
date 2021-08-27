package com.xgh.test.spring.step04.beans.factory;

import com.xgh.test.spring.step04.beans.BeansException;

import java.util.Map;

/**
 * com.xgh.test.spring.step04.beans.factory.ListableBeanFactory
 *
 * @author xgh <br/>
 * @description
 * @date 2021年08月27日
 */
public interface ListableBeanFactory extends BeanFactory {



        /*
         * @description 按类型返回实例
         * @date 2021/8/27 0027
         * @param type
         * @return java.util.Map<java.lang.String,T>
         */
        <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;


        /*
         * @description 返回所以bean的name
         * @date 2021/8/27 0027
         * @return java.lang.String[]
         */
        String[] getBeanDefinitionNames();

}
