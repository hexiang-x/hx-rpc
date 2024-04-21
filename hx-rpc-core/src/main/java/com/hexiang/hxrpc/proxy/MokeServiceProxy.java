package com.hexiang.hxrpc.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
public class MokeServiceProxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?> methodReturnClass = method.getReturnType();
        log.info("moke invoke {}", method.getName());
        return getDefaultObject(methodReturnClass);
    }

    private Object getDefaultObject(Class<?> klass){
        if(klass.isPrimitive()){
            if(klass == boolean.class){
                return false;
            }else if(klass == short.class){
                return (short)0;
            }else if(klass == int.class){
                return 0;
            }else if(klass == long.class){
                return 0L;
            }
        }
        return null;
    }
}
