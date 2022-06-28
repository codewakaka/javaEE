package com.xgh.test.spring.step04.context.support;

import com.xgh.test.spring.step04.beans.BeansException;
import com.xgh.test.spring.step04.beans.factory.ConfigurableListableBeanFactory;
import com.xgh.test.spring.step04.beans.factory.config.BeanFactoryPostProcessor;
import com.xgh.test.spring.step04.beans.factory.config.BeanPostProcessor;
import com.xgh.test.spring.step04.context.ConfigurableApplicationContext;
import com.xgh.test.spring.step04.core.io.DefaultResourceLoader;

import java.util.Map;


/**
 * 应用上下文抽象类实现
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {


    @Override
    public void refresh() throws BeansException {
        //1创建beanFactory，加载BeanDefinition
        refreshFactory();

        //2.获取beanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        //3在bean实例化之前执行beanFactoryPostProcess
        invokeBeanFactoryPostProcessors(beanFactory);
        //4.beanPostProcessor需要提前雨其他bean对象实例化之前注册操作
        registerBeanPostProcessors(beanFactory);
        //5提前实例化单例bean对象
        beanFactory.preInstantiateSingletons();


    }
    protected abstract ConfigurableListableBeanFactory getBeanFactory() throws BeansException;


    protected abstract void refreshFactory() ;

    private   void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory){
       Map<String, BeanPostProcessor> beanPostProcessorMap =   beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
       //... getBean getBeansOfType getBeanDefinitionNames;
    }

    @Override
    public Object getBean(String beanName) throws BeansException {
        return getBeanFactory().getBean(beanName);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> requiredType) {
        return getBeanFactory().getBean(beanName,requiredType);
    }

    @Override
    public Object getBean(String beanName, Object... args) throws BeansException {
        return getBeanFactory().getBean(beanName,args);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    private  void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory){
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap =  beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    public void registerShutdownHook(){
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    public void close(){
        getBeanFactory().destroySingletons();
    }






}
