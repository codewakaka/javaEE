package com.xgh.test.spring.step04.context.support;

import com.xgh.test.spring.step04.beans.factory.support.DefaultListableBeanFactory;
import com.xgh.test.spring.step04.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 上下文对配置信息的加载
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplication {


    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);

        String[] configLocations = getConfigLocations();
        if( null != configLocations){
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();


}
