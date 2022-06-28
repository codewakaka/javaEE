package com.xgh.test.spring.step04.context.support;

import com.xgh.test.spring.step04.beans.BeansException;
import com.xgh.test.spring.step04.beans.factory.ConfigurableListableBeanFactory;
import com.xgh.test.spring.step04.beans.factory.support.DefaultListableBeanFactory;

/**
 * 获取bean工厂和加载资源
 */
public abstract class AbstractRefreshableApplication extends AbstractApplicationContext {

    private DefaultListableBeanFactory beanFactory;


    @Override
    protected void refreshFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = creatBeanFactory();
        //加载spring.xml配置文件
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    private DefaultListableBeanFactory creatBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() throws BeansException {
        return beanFactory;
    }
}
