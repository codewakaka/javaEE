package com.xgh.test.spring.step03;


/**
 * com.xgh.test.spring.step02.BeanFactory
 *
 * @author xgh <br/>
 * @description
 * @date 2021年08月12日
 */
public interface BeanFactory {

    public Object getBean(String beanName) throws BeansException;

    Object getBean(String beanName,Object... args )throws BeansException;

}
