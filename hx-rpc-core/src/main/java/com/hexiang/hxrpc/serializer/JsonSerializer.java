package com.hexiang.hxrpc.serializer;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexiang.hxrpc.model.RpcRequest;
import com.hexiang.hxrpc.model.RpcResponse;

import java.io.IOException;

public class JsonSerializer implements  Serializer{
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    @Override
    public <T> byte[] serializer(T object) throws IOException {
        return OBJECT_MAPPER.writeValueAsBytes(object);
    }

    @Override
    public <T> T deserializer(byte[] bytes, Class<T> type) throws IOException {
        T obj = OBJECT_MAPPER.readValue(bytes, type);
        if(obj instanceof RpcRequest){
            return handleRequest((RpcRequest) obj, type);
        }else if(obj instanceof RpcResponse){
            return handleResponse((RpcResponse) obj, type);
        }

        return null;
    }

    private <T> T handleRequest(RpcRequest rpcRequest, Class<T> klass) throws IOException {
        Class<?>[] classes = rpcRequest.getParameterTypes();
        Object[] objects = rpcRequest.getArgs();

        for(int i = 0; i < objects.length; i ++){
            if(!classes[i].isAssignableFrom(objects[i].getClass())){
                byte[] bytes = OBJECT_MAPPER.writeValueAsBytes(objects[i]);
                objects[i] = OBJECT_MAPPER.readValue(bytes, classes[i]);
            }
        }
        return klass.cast(rpcRequest);
    }
    private <T> T handleResponse(RpcResponse rpcResponse, Class<T> klass) throws IOException {
        byte[] bytes = OBJECT_MAPPER.writeValueAsBytes(rpcResponse.getData());
        rpcResponse.setData(OBJECT_MAPPER.readValue(bytes, rpcResponse.getKlass()));
        return klass.cast(rpcResponse);
    }
}
