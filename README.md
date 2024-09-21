---
title: 知然AI开发文档
date: 2024-08-21 12:09:08
tags:开发后端辅助文档

---

###### date: 2024-08-21 12:09:08
######基于Springboot + SpringCloud + nacos + redis + mysql开发

<!-- more -->

# 账户服务

1. 信息
2. 对密码使用加盐
   - 使用"SHA1PRNG"生成随机盐值,使用"SHA-256"处理密码

# AI服务

1. 对接ai接口

# 网关服务

## 配置网络进出限制和请求白名单

# 常用服务

## 提供常用的工具类

# 模型服务

## 提供PO持久化模型和VO数据模型

# 存储服务

## 提供数据库连接操作

- 账户存储

  - 数据库表(账户表)

    ```my
    create table z_account
    (
        id            varchar(64)  not null comment '主键'
            primary key,
        username      varchar(64)  not null comment '用户名称',
        useralias     varchar(32)  not null comment '用户真实姓名',
        password      varchar(128) not null comment '用户密码',
        password_salt varchar(32)  null comment '密码盐值',
        phone         varchar(32)  not null comment '用户电话号码',
        CREATE_TIME   datetime     null comment '创建时间',
        CREATE_BY     varchar(64)  null comment '创建人ID',
        UPDATE_TIME   datetime     null comment '更新时间',
        UPDATE_BY     varchar(64)  null comment '更新人ID',
        REVISION      int          null comment '乐观锁',
        status        tinyint      null comment '账户状态',
        avatar        varchar(200) null comment '用户头像地址'
    );
    
    
    ```

  - 账户表(原始密码表)

    ```java
    create table z_account_original
    (
        id            varchar(32) not null comment '主键',
        username      varchar(64) null comment '用户名',
        password      varchar(64) null comment '密码',
        password_salt varchar(32) null comment '密码盐'
    );
    
    
    ```

    



