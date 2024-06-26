package com.hexiang.example.provider;

import com.hexiang.example.common.service.UserService;
import com.hexiang.hxrpc.registry.LocalRegistry;
import com.hexiang.hxrpc.server.HttpServer;
import com.hexiang.hxrpc.server.VertxHttpServer;

/**
 * 简易服务提供者实例
 */
public class EasyProviderExample {
    public static void main(String[] args) {
        LocalRegistry.registry(UserService.class.getName(), UserServiceImpl.class);


        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(8080);
    }
}
