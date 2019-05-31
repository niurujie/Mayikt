package com.mayi.factory;

import com.mayi.Handler.GatewayHandler;
import com.mayi.Handler.impl.BlacklistHandler;
import com.mayi.Handler.impl.ConversationHandler;
import com.mayi.Handler.impl.CurrentLimitHandler;

/**
 * Created by IDEA
 * User:wang7
 * Date:2019/5/9
 * Time:22:59
 */
public class FactoryHandler {

    public static GatewayHandler getGateWayHandler() {
        GatewayHandler gatewayHandler1 = new BlacklistHandler();
        GatewayHandler gatewayHandler2 = new ConversationHandler();
        gatewayHandler1.setGatewayHandler(gatewayHandler2);
        GatewayHandler gatewayHandler3 = new CurrentLimitHandler();
        gatewayHandler2.setGatewayHandler(gatewayHandler3);
        return gatewayHandler1;
    }
}
