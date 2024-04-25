package com.hexiang.hxrpc.proxy;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.hexiang.hxrpc.RpcApplication;
import com.hexiang.hxrpc.model.RpcRequest;
import com.hexiang.hxrpc.model.RpcResponse;
import com.hexiang.hxrpc.serializer.JdkSerializer;
import com.hexiang.hxrpc.serializer.Serializer;
import com.hexiang.hxrpc.serializer.SerializerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 服务代理类
 */
public class ServiceProxy implements InvocationHandler {
    /**
     * 调用代理
     *
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        final Serializer serializer = SerializerFactory.getInstance(RpcApplication.getRpcConfig().getSerializerKey());
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .args(args)
                .parameterTypes(method.getParameterTypes())
                .build();
        try{
            byte[] bytes = serializer.serializer(rpcRequest);
            try(HttpResponse httpResponse = HttpRequest.post("http://localhost:8080").body(bytes).execute()){
                RpcResponse rpcResponse = serializer.deserializer(httpResponse.bodyBytes(), RpcResponse.class);
                return rpcResponse.getData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
