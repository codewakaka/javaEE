package com.xgh.test.spring;

import com.xgh.test.spring.service.UserService;
import org.junit.Test;

/**
 * com.xgh.test.spring.ApiTests
 *
 * @author xgh <br/>
 * @description
 * @date 2021年08月12日
 */
public class ApiTests {

    @Test
    public void test_factory() {
        BeanFactory beanFactory = new BeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
        beanFactory.registerBeanDefinitionMap("userService", beanDefinition);

        UserService userService =(UserService) beanFactory.getBean("userService");
        userService.getUserInfo();
    }
}
