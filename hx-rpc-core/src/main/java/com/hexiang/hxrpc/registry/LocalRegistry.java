package com.hexiang.hxrpc.registry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 本地注册中心
 */
public class LocalRegistry {
    /**
     * 本地注册缓存
     */
    private static final Map<String, Class<?>> localregistry = new ConcurrentHashMap<>();

    /**
     * 注册
     * @param name
     * @param klass
     */
    public static void registry(String name, Class<?> klass){
        localregistry.put(name, klass);
    }
    /**
     * 获取
     */
    public static Class<?> get(String serviceName){
        return localregistry.get(serviceName);
    }
    /**
     * 删除服务
     */
    public static void remove(String serviceName){
        localregistry.remove(serviceName);
    }

}
