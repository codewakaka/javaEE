package com.xgh.test.spring.step04.beans.factory;


/**
 * 实现此接口的bean对象，会在beanFactory设置属性后做出相应的处理，儒执行自定义初始化，或者仅仅校验是否设置了所有强制属性
 */
public interface InitializingBean {

    /**
     * 处理了属性填充后调用
     * @throws Exception
     */
    void afterPropertiesSet() throws Exception;
}
