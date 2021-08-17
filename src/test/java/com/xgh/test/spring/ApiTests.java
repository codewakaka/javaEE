package com.xgh.test.spring;

import com.xgh.test.spring.service.UserService;
import com.xgh.test.spring.step02.BeanFactory;
import com.xgh.test.spring.step02.config.BeanDefinition;
import com.xgh.test.spring.step02.support.DefaultListableBeanFactory;
import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

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
       //初始化 beanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //注册bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService",beanDefinition);
        //第一次获取
        UserService userService = (UserService)beanFactory.getBean("userService","张三");
        userService.getUserInfo();

        //第二次
        UserService userService1 = (UserService)beanFactory.getBean("userService");
        userService1.getUserInfo();
    }

    @SneakyThrows
    @Test
    public void test_newInstant(){
        Class<UserService> userServiceClass = UserService.class;
        UserService userService = userServiceClass.newInstance();
        System.out.println(userService);
    }

    @Test
    public void test_constructor() throws Exception {
        Class<UserService> userServiceClass = UserService.class;
        Constructor<UserService> declaredConstructor = userServiceClass.getDeclaredConstructor(String.class);
        UserService userService = declaredConstructor.newInstance("张三");
        System.out.println(userService);
    }

    @Test
    public void test_constructors() throws Exception {
        Class<UserService> userServiceClass = UserService.class;
        Constructor<?>[] declaredConstructors = userServiceClass.getDeclaredConstructors();
        Constructor<?> constructor = null;
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            if(declaredConstructor.getParameterTypes().length == 1){
                constructor = declaredConstructor;
            }
        }
        Constructor<UserService> declaredConstructor = userServiceClass.getDeclaredConstructor(constructor.getParameterTypes());
        UserService userService = declaredConstructor.newInstance("李四");
        System.out.println(userService);
    }

    @Test
    public void test_cglib(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserService.class);
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        Object o = enhancer.create(new Class[]{String.class}, new Object[]{"王五"});
        System.out.println(o);
    }
}
