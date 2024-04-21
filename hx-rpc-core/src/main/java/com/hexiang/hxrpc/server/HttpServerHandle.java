package com.hexiang.hxrpc.server;

import com.hexiang.hxrpc.model.RpcRequest;
import com.hexiang.hxrpc.model.RpcResponse;
import com.hexiang.hxrpc.registry.LocalRegistry;
import com.hexiang.hxrpc.serializer.JdkSerializer;
import com.hexiang.hxrpc.serializer.Serializer;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;

import java.io.IOException;
import java.lang.reflect.Method;

public class HttpServerHandle implements Handler<HttpServerRequest> {
    /**
     * 自定义rpc请求处理
     * 1. 反序列化request
     * 2. 构建response
     * 3. 反射调用方法，得到返回值
     * 4. 调用vertx的response方法
     * @param httpServerRequest
     */
    @Override
    public void handle(HttpServerRequest httpServerRequest) {
        //指定反序列化器
        final Serializer serializer = new JdkSerializer();

        //记录日志
        System.out.println("Receive Request:" + httpServerRequest.method() + " " + httpServerRequest.uri());

        //自定义异步处理请求
        httpServerRequest.bodyHandler(body ->{
            byte[] bytes = body.getBytes();
            RpcRequest rpcRequest = null;
            //request 反序列化
            try{
                rpcRequest = (RpcRequest) serializer.deserializer(bytes, RpcRequest.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //构建rpcresponse
            RpcResponse rpcResponse = new RpcResponse();
            //rpcrequest为空
            if(rpcRequest == null){
                rpcResponse.setMessage("request is null");
                doResponse(httpServerRequest, rpcResponse, serializer);
                return;
            }
            //反射调用方法，设置rpcresponse
            try{
                Class<?> implClass = LocalRegistry.get(rpcRequest.getServiceName());
                Method method = implClass.getMethod(rpcRequest.getMethodName(), rpcRequest.getParameterTypes());
                Object result = method.invoke(implClass.newInstance(), rpcRequest.getArgs());
                rpcResponse.setData(result);
                rpcResponse.setKlass(method.getReturnType());
                rpcResponse.setMessage("OK");
                rpcResponse.setException(null);
                doResponse(httpServerRequest, rpcResponse, serializer);
            } catch (Exception e) {
                e.printStackTrace();
                rpcResponse.setMessage(e.getMessage());
                rpcResponse.setException(e);
                doResponse(httpServerRequest, rpcResponse, serializer);
            }
        });
    }

    private void doResponse(HttpServerRequest request, RpcResponse response, Serializer serializer){
        HttpServerResponse httpServerResponse = request.response().putHeader("content-type", "application/json");
        try{
            byte[] serializer1 = serializer.serializer(response);
            httpServerResponse.end(Buffer.buffer(serializer1));
        } catch (IOException e) {
            e.printStackTrace();
            httpServerResponse.end(Buffer.buffer());
        }
    }
}
