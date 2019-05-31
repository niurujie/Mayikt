package com.mayikt.service.impl;

import com.mayikt.service.AbstractSubject;
import com.mayikt.service.ObServer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Wangmh
 * @date: 2019/5/31 具体主题
 * @time: 16:37
 */
public class WeChatSubject implements AbstractSubject {
    /**
     * 存储所有Observer
     */
    private List<ObServer> list = new ArrayList<ObServer>();

    /**
     * 更新的内容
     */
    private String message;

    public void addObServer(ObServer obServer) {
        list.add(obServer);
    }

    public void rmoveObServer(ObServer obServer) {
        list.remove(obServer);
    }

    public void notifyObServerAll(String message) {
        for (int i=0;i<list.size();i++){
            ObServer obServer=list.get(i);
            obServer.update(message);
        }
    }

    public void setNtifyMessage(String message) {
        this.message=message;
        System.out.println("微信公众号设置message:" + message);
        this.notifyObServerAll(message);
    }
}
