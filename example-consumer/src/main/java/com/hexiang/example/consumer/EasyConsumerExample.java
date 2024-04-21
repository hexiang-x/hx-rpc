package com.hexiang.example.consumer;

import com.hexiang.example.common.model.User;
import com.hexiang.example.common.service.UserService;
import com.hexiang.hxrpc.RpcApplication;
import com.hexiang.hxrpc.config.RpcConfig;
import com.hexiang.hxrpc.proxy.ServiceProxyFactory;

public class EasyConsumerExample {
    public static void main(String[] args) {
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("hexiang");
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        System.out.println(rpcConfig.toString());

        User user1 = userService.getUser(user);
        if(user1 != null){
            System.out.println(user1.getName());
        }
        System.out.println(user1);
        System.out.println(user);
    }
}
