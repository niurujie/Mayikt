package com.mayi.Handler.impl;

import com.mayi.Handler.GatewayHandler;
import org.springframework.stereotype.Component;

/**
 * Created by IDEA
 * User:wang7
 * Date:2019/5/9
 * Time:22:36
 */
@Component
public class CurrentLimitHandler extends GatewayHandler {
    public void service() {
        System.out.println("第一关网关限流判断....");
        nextService();
    }
}
