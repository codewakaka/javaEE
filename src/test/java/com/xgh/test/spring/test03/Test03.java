package com.xgh.test.spring.test03;

import com.xgh.test.spring.step03.PropertyValue;
import com.xgh.test.spring.step03.PropertyValues;
import com.xgh.test.spring.step03.config.BeanDefinition;
import com.xgh.test.spring.step03.config.BeanReference;
import com.xgh.test.spring.step03.support.DefaultListableBeanFactory;
import org.junit.Test;

/**
 * com.xgh.test.spring.test03.Test03
 *
 * @author xgh <br/>
 * @description
 * @date 2021年08月24日
 */
public class Test03 {


    @Test
    public void test_BeanFactory(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //注册userDao
         beanFactory.registerBeanDefinition("userDao",new BeanDefinition(UserDao.class));

         //userService
        PropertyValues propertyValues = new PropertyValues();

        propertyValues.addPropertyValue(new PropertyValue("uId","1001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));


        //userService注入
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService",beanDefinition);
        UserService userService = (UserService)beanFactory.getBean("userService");
        userService.queryUserInfo();
    }


}
