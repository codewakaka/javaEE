package com.xgh.gateway.day02.bind;

import com.xgh.gateway.day02.session.Configuration;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.config.utils.ReferenceConfigCache;
import org.apache.dubbo.rpc.service.GenericService;

import java.util.HashMap;
import java.util.Map;

/**
 * 泛化调用注册器
 */
public class GenericReferenceRegistry {

    private final Configuration configuration;


    public GenericReferenceRegistry(Configuration configuration) {
        this.configuration = configuration;
    }

    private final Map<String,GenericReferenceProxyFactory> knownGenericReferences = new HashMap<>();

    public IGenericReference getGenericReference(String methodName){
        GenericReferenceProxyFactory genericReferenceProxyFactory = knownGenericReferences.get(methodName);
        if(genericReferenceProxyFactory == null){
            throw new RuntimeException("Type "+methodName +"is not know to the GenericReferenceRegistry.");
        }
        return genericReferenceProxyFactory.newInstance(methodName);
    }


    public void addGenericReference(String application,String interfaceName,String methodName){
        //获取基础服务
        ApplicationConfig applicationConfig = configuration.getApplicationConfig(application);
        RegistryConfig registryConfig = configuration.getRegistryConfig(application);
        ReferenceConfig<GenericService> referenceConfig = configuration.getReferenceConfig(interfaceName);
        //构建dubbo服务
        DubboBootstrap bootstrap = DubboBootstrap.getInstance();
        bootstrap.application(applicationConfig).registry(registryConfig).reference(referenceConfig).start();
        //调用泛化服务
        ReferenceConfigCache cache = ReferenceConfigCache.getCache();
        GenericService genericService = cache.get(referenceConfig);
        //创建并保存到工厂
        knownGenericReferences.put(methodName,new GenericReferenceProxyFactory(genericService));
    }

}
