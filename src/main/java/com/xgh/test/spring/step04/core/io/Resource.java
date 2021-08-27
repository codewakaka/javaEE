package com.xgh.test.spring.step04.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * com.xgh.test.spring.step04.core.io.Resource
 *
 * @author xgh <br/>
 * @description 资源加载接口的定义和实现
 * @date 2021年08月27日
 */
public interface Resource {

    InputStream getInputStream() throws IOException;
}
