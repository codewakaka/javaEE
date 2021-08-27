package com.xgh.test.spring.step04.core.io;

/**
 * com.xgh.test.spring.step04.core.io.ResourceLoader
 *
 * @author xgh <br/>
 * @description 资源加载器
 * @date 2021年08月27日
 */
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX= "classpath:";

    Resource getResource(String location);
}
