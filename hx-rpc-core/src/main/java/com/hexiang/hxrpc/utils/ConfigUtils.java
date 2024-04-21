package com.hexiang.hxrpc.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;

/**
 * 配置工具类
 * 返回配置实体对象
 */
public class ConfigUtils {
    public static <T> T loadConfig(Class<T> klass, String prefix){
        return loadConfig(klass, prefix, "");
    }

    public static <T> T loadConfig(Class<T> klass, String prefix, String enviroment){
        StringBuilder configFileConfig = new StringBuilder("application");
        if(StrUtil.isNotBlank(enviroment)){
            configFileConfig.append('-').append(enviroment);
        }
        configFileConfig.append(".properties");
        Props props = new Props(configFileConfig.toString());
        return props.toBean(klass, prefix);
    }
}
