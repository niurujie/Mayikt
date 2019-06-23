package com.mayikt.v2.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * @author: create by wangmh
 * @name: UserService.java
 * @description:
 * @date:2019/6/23
 **/
@Service
@Lazy(true)//True 表示为懒加载 false表示为在IOC容器加载的时候被创建。默认true
public class UserService {
    public UserService() {
        System.out.println("UserService无参数构造被加载...");
    }
}
