package com.hexiang.hxrpc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RpcRequest implements Serializable {
    /**
     * 服务名
     */
    private String serviceName;

    /**
     * 服务方法名
     */
    private String methodName;

    /**
     * 传参类型数组
     */
    private Class<?>[] parameterTypes;

    /**
     * 传参列表
     */
    private Object[] args;
}
