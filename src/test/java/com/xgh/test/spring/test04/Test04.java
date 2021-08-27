package com.xgh.test.spring.test04;

import cn.hutool.core.io.IoUtil;
import com.xgh.test.spring.step04.beans.factory.support.DefaultListableBeanFactory;
import com.xgh.test.spring.step04.beans.factory.xml.XmlBeanDefinitionReader;
import com.xgh.test.spring.step04.core.io.DefaultResourceLoader;
import com.xgh.test.spring.step04.core.io.Resource;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * com.xgh.test.spring.test04.Test04
 *
 * @author xgh <br/>
 * @description
 * @date 2021年08月27日
 */
public class Test04 {

    private DefaultResourceLoader resourceLoader;

    @Before
    public void  init(){
        resourceLoader = new DefaultResourceLoader();
    }

    @Test
    public void test_classpath() throws IOException{
        Resource resource = resourceLoader.getResource("classpath:important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_file()throws IOException{
        Resource resource = resourceLoader.getResource("src/test/resources/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_xml(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");
        UserService userService = beanFactory.getBean("userService", UserService.class);
        System.out.println(userService.queryUserInfo());
    }

}
