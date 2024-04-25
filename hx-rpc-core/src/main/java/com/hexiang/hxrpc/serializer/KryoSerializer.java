package com.hexiang.hxrpc.serializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class KryoSerializer implements Serializer {
    private static final ThreadLocal<Kryo> KRYO_THREAD_LOCAL = ThreadLocal.withInitial(() ->{
       Kryo kryo = new Kryo();
       //设置动态序列化和反序列化，不提前注册所有类
       kryo.setRegistrationRequired(false);
       return kryo;
    });

    @Override
    public <T> byte[] serializer(T object) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        Output output = new Output(byteArrayOutputStream);
        KRYO_THREAD_LOCAL.get().writeObject(output, object);
        output.close();

        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public <T> T deserializer(byte[] bytes, Class<T> type) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        Input input = new Input(byteArrayInputStream);
        T obj = KRYO_THREAD_LOCAL.get().readObject(input, type);
        input.close();
        return obj;
    }
}
