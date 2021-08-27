package com.xgh.test.spring.step04.beans.factory.config;

import com.xgh.test.spring.step04.beans.factory.HierarchicalBeanFactory;

/**
 * com.xgh.test.spring.step04.beans.factory.config.ConfigurableBeanFactory
 *
 * @author xgh <br/>
 * @description
 * @date 2021年08月27日
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory,SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE= "prototype";
}
