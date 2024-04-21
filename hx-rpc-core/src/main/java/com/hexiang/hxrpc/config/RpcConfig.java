package com.hexiang.hxrpc.config;

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
}
