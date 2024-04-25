package com.hexiang.hxrpc.config;

import com.hexiang.hxrpc.serializer.SerializerEnum;
import lombok.Data;

/**
 * Rpc配置类
 */
@Data
public class RpcConfig {
    /**
     * 名称
     */
    private String name = "hx-rpc";
    /**
     * 版本号
     */
    private String version = "1.0";
    /**
     * 域名
     */
    private String serverHost = "localhost";
    /**
     * 端口号
     */
    private Integer serverPort = 8080;
    /**
     * 是否启用moke
     */
    private Boolean moke = false;
    /**
     * 序列化器
     */
    private String serializerKey = "Jdk";
}
