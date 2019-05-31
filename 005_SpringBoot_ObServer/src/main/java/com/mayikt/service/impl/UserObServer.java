package com.mayikt.service.impl;

import com.mayikt.service.ObServer;

/**
 * @author: Wangmh
 * @date: 2019/5/31  具体观察者
 * @time: 16:43
 */
public class UserObServer implements ObServer {

    /**
     * 订阅者用户名称
     */
    private String name;

    /**
     * 发送内容
     */
    private String message;

    public UserObServer(String name) {
        this.name = name;
    }

    public void update(String message) {
        this.message = message;
        read();
    }

    public void read() {
        System.out.println(name + ",老师收到推送消息:" + message);
    }
}
