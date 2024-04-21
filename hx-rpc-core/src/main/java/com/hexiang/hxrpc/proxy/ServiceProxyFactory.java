package com.hexiang.hxrpc.proxy;

import com.hexiang.hxrpc.RpcApplication;
import com.hexiang.hxrpc.config.RpcConfig;

import java.lang.reflect.Proxy;

/**
 * 服务代理工厂类
 */
public class ServiceProxyFactory {

    public static <T> T getProxy(Class<T> ServiceClass){
        if(!RpcApplication.getRpcConfig().getMoke()){
            return getTrueProxy(ServiceClass);
        }else{
            return getMokeProxy(ServiceClass);
        }
    }

    public static <T> T getTrueProxy(Class<T> ServiceClass){
        return (T)Proxy.newProxyInstance(
                ServiceClass.getClassLoader(),
                new Class[]{ServiceClass},
                new ServiceProxy()
        );
    }

    public static <T> T getMokeProxy(Class<T> ServiceProxy){
        return (T) Proxy.newProxyInstance(
                ServiceProxy.getClassLoader(),
                new Class[]{ServiceProxy},
                new MokeServiceProxy()
        );
    }
}
