package com.xgh.test.spring.step04.core.io;

import cn.hutool.core.lang.Assert;
import com.xgh.test.spring.step04.util.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * com.xgh.test.spring.step04.core.io.ClassPathResource
 *
 * @author xgh <br/>
 * @description classPath的资源加载器
 * @date 2021年08月27日
 */
public class ClassPathResource implements Resource{

    private final String path;

    private ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path,null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        Assert.notNull(path,"path mast not be null");
        this.path = path;
        this.classLoader = (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is = classLoader.getResourceAsStream(path);
        if(is == null){
            throw  new FileNotFoundException(this.path + " cannot be opened because ist does not exist");
        }
        return is;
    }
}
