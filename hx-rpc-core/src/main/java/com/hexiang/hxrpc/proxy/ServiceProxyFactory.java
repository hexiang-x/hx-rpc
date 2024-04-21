package com.hexiang.hxrpc.proxy;

import java.lang.reflect.Proxy;

/**
 * 服务代理工厂类
 */
public class ServiceProxyFactory {

    public static <T> T getProxy(Class<T> ServiceClass){
        return (T)Proxy.newProxyInstance(
                ServiceClass.getClassLoader(),
                new Class[]{ServiceClass},
                new ServiceProxy()
        );
    }
}
