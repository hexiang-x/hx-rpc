package com.hexiang.hxrpc;

import com.hexiang.hxrpc.config.RpcConfig;
import com.hexiang.hxrpc.constant.RpcConstant;
import com.hexiang.hxrpc.utils.ConfigUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class RpcApplication {
    private static volatile RpcConfig rpcConfig;

    private static void init(RpcConfig newRpcConfig){
        rpcConfig = newRpcConfig;
        log.info("rpc init, config={}", newRpcConfig.toString());
    }

    private static void init(){
        RpcConfig newRpcConfig;
        try{
            newRpcConfig = ConfigUtils.loadConfig(RpcConfig.class, RpcConstant.DEFAULT_CONFIG_PREFIX);
        } catch (Exception e) {
            newRpcConfig = new RpcConfig();
        }
        rpcConfig = newRpcConfig;
    }

    public static RpcConfig getRpcConfig(){
        if(Objects.isNull(rpcConfig)){
            synchronized (RpcApplication.class){
                if(Objects.isNull(rpcConfig)){
                    init();
                }
            }
        }
        return rpcConfig;
    }
}
