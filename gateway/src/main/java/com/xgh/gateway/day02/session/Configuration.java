package com.xgh.gateway.day02.session;


import com.xgh.gateway.day02.bind.GenericReferenceRegistry;
import com.xgh.gateway.day02.bind.IGenericReference;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;

import java.util.HashMap;
import java.util.Map;

/**
 * 会话生命周期配置类
 */
public class Configuration {

    private final GenericReferenceRegistry registry = new GenericReferenceRegistry(this);

    //rpc 应用服务配置项api-gateway-test
    private final Map<String, ApplicationConfig> applicationConfigMap = new HashMap<>();

    /**
     *  注册中心配置项 zookeeper:127.0.0.1:2181
     */
    private final Map<String, RegistryConfig>  registryConfigMap = new HashMap<>();

    /**
     * rpc 泛化服务配置项目 cn.gateway.rpc.IActivityBooth
     */
    private final Map<String, ReferenceConfig<GenericService>> referenceConfigMap = new HashMap<>();


    public Configuration(){
        ApplicationConfig application = new ApplicationConfig();
        application.setName("api-gateway-test");
        application.setQosEnable(false);

        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("zookeeper://127.0.0.1:2181");
        registryConfig.setRegister(false);

        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        reference.setInterface("");
        reference.setVersion("1.0.1");
        reference.setGeneric("true");

        applicationConfigMap.put("api-gateway-test",application);
        registryConfigMap.put("api-gateway-test",registryConfig);
        referenceConfigMap.put("",reference);
    }

    public ApplicationConfig getApplicationConfig(String applicationName){
        return applicationConfigMap.get(applicationName);
    }

    public RegistryConfig getRegistryConfig(String applicationName){
        return registryConfigMap.get(applicationName);
    }

    public ReferenceConfig<GenericService> getReferenceConfig(String interfaceName){
        return referenceConfigMap.get(interfaceName);
    }


    public void addGenericReference(String application,String interfaceName,String methodName){
        registry.addGenericReference(application,interfaceName,methodName);
    }

    public IGenericReference getGenericReference(String methodName){
        return registry.getGenericReference(methodName);
    }

}
