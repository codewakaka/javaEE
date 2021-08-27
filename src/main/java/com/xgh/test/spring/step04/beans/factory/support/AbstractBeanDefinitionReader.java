package com.xgh.test.spring.step04.beans.factory.support;

import com.xgh.test.spring.step04.core.io.DefaultResourceLoader;
import com.xgh.test.spring.step04.core.io.ResourceLoader;

/**
 * com.xgh.test.spring.step04.beans.factory.support.AbstractBeanDefinitionReader
 *
 * @author xgh <br/>
 * @description
 * @date 2021年08月27日
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{

    private final BeanDefinitionRegistry registry;

    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
       this(registry,new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
