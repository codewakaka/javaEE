package com.xgh.test.spring.step04.context;

import com.xgh.test.spring.step04.beans.BeansException;
import com.xgh.test.spring.step04.beans.factory.ListableBeanFactory;

public interface ConfigurableApplicationContext extends ListableBeanFactory {


    /**
     *  刷新容器
     * @throws BeansException
     */
    void refresh() throws BeansException;

    void registerShutdownHook();

    void close();
}
