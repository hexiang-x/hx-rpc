package com.hexiang.hxrpc.serializer;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SerializerFactory {
    private static Map<String, Serializer> map = new HashMap<String, Serializer>(){
        {
            put(SerializerEnum.Jdk.name(), new JdkSerializer());
            put(SerializerEnum.Json.name(), new JsonSerializer());
            put(SerializerEnum.Hessian.name(), new HessianSerializer());
            put(SerializerEnum.Kryo.name(), new KryoSerializer());
        }
    };

    private static final String DEFAULT_SERIALIZER_KEY = SerializerEnum.Jdk.name();

    public static Serializer getInstance(String serializerKey){
        Serializer serializer = map.get(serializerKey);
        if(Objects.isNull(serializer))
            serializer = map.get(DEFAULT_SERIALIZER_KEY);
        return serializer;
    }

}
