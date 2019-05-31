package com.mayikt.service;

/**
 * @author: Wangmh
 * @date: 2019/5/31  抽象观察者
 * @time: 16:30
 */
public interface ObServer {

    /**
     * 更新消息内容
     * @param message
     */
    public void update(String message);
}
