package com.xgh.test.spring.step04.core.io;

import cn.hutool.core.lang.Assert;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * com.xgh.test.spring.step04.core.io.DefaultResourceLoader
 *
 * @author xgh <br/>
 * @description 获取资源
 * @date 2021年08月27日
 */
public class DefaultResourceLoader implements ResourceLoader{



    @Override
    public Resource getResource(String location) {
        Assert.notNull(location,"location mast not  be null");
        if(location.startsWith(CLASSPATH_URL_PREFIX)){
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        }
        else
        {
            try {
                URL url = new URL(location);
                return new UrlResource(url);
            }catch (MalformedURLException e){
                return new FileSystemResource(location);
            }
        }
    }
}
