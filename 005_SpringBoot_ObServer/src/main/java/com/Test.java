package com;

import com.mayikt.service.AbstractSubject;
import com.mayikt.service.impl.UserObServer;
import com.mayikt.service.impl.WeChatSubject;

/**
 * @author: Wangmh
 * @date: 2019/5/31
 * @time: 16:48
 */
public class Test {
    public static void main(String[] args) {
        // 1.注册主题
        AbstractSubject weChatSubject = new WeChatSubject();
        // 2.添加观察者 订阅主题
        weChatSubject.addObServer(new UserObServer("小薇"));
        weChatSubject.addObServer(new UserObServer("小敏"));
        // 3.设置发送消息
        weChatSubject.setNtifyMessage("xx教育第五期平均就业薪资破3万+");

    }
}
