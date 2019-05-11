package com.mayi.Handler;

/**
 * Created by IDEA
 * User:wang7
 * Date:2019/5/9
 * Time:22:33
 */
public abstract class GatewayHandler {

    protected GatewayHandler gatewayHandler;

    public abstract void service();

    public void nextService() {
        if (gatewayHandler != null) {
            gatewayHandler.service();
        }
    }

    public void setGatewayHandler(GatewayHandler gatewayHandler) {
        this.gatewayHandler = gatewayHandler;
    }
}
