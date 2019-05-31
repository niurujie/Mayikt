package com.mayi.Handler.impl;

import com.mayi.Handler.GatewayHandler;
import org.springframework.stereotype.Component;

/**
 * Created by IDEA
 * User:wang7
 * Date:2019/5/9
 * Time:22:37
 */
@Component
public class ConversationHandler extends GatewayHandler {
    public void service() {
        System.out.println("第三关用户会话拦截判断....");
        nextService();
    }
}
