package com.hexiang.example.provider;

import com.hexiang.example.common.service.UserService;
import com.hexiang.hxrpc.RpcApplication;
import com.hexiang.hxrpc.config.RpcConfig;
import com.hexiang.hxrpc.registry.LocalRegistry;
import com.hexiang.hxrpc.server.HttpServer;
import com.hexiang.hxrpc.server.VertxHttpServer;

public class ProviderExample {
    public static void main(String[] args) {
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();

        LocalRegistry.registry(UserService.class.getName(), UserServiceImpl.class);
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(rpcConfig.getServerPort());

    }
}
