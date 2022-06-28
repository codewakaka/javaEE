package com.xgh.test.spring.test05;

import com.xgh.test.spring.step04.beans.BeansException;
import com.xgh.test.spring.step04.beans.PropertyValue;
import com.xgh.test.spring.step04.beans.PropertyValues;
import com.xgh.test.spring.step04.beans.factory.ConfigurableListableBeanFactory;
import com.xgh.test.spring.step04.beans.factory.config.BeanDefinition;
import com.xgh.test.spring.step04.beans.factory.config.BeanFactoryPostProcessor;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
        @Override
        public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
                throws BeansException {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService"
            );
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            propertyValues.addPropertyValue(new PropertyValue("company", "改为：字节跳动 "));
        }

}
