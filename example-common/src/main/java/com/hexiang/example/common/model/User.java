package com.hexiang.example.common.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户实体类
 * 实现Serializable接口，方便后续序列化和反序列化
 */
@Data
public class User implements Serializable {
    /**
     * 用户名
     */
    String name;

}
