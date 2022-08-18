package com.xgh.gateway.day02.bind;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.dubbo.rpc.service.GenericService;

import java.lang.reflect.Method;

/**
 * 泛化调用静态代理：方便做一些拦截处理，给http对应rpc调用，做一层代理控制
 * 每调用到一个http对应的网关方法，就会代理的方式调用到rpc对应的泛化方法上
 */
public class GenericReferenceProxy implements MethodInterceptor {

    /**
     * rpc泛化调用服务
     */
    private final GenericService genericService;

    /**
     * rpc 泛化调用方法
     */
    private final String methodName;

    public GenericReferenceProxy(GenericService genericService, String methodName) {
        this.genericService = genericService;
        this.methodName = methodName;
    }


    /**
     *
     * 做一层代理控制，后续不止是可以使用 Dubbo 泛化调用，也可以是其他服务的泛化调用
     * 泛化调用文档：https://dubbo.apache.org/zh/docsv2.7/user/examples/generic-reference/
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        Class<?>[] parameterTypes = method.getParameterTypes();
        String[] parameters = new String[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            parameters[i] = parameterTypes[i].getName();
        }
        return genericService.$invoke(methodName,parameters,args);
    }
}
