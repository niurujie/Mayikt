package com.mayi;

import com.mayi.GatewayComponent;

/**
 * @author: create by wangmh
 * @name: AbstractDecorator.java  抽象装饰角色
 * @description:
 * @date:2019/5/14
 **/
public abstract class AbstractDecorator extends GatewayComponent {
    public GatewayComponent gatewayComponent;

    public AbstractDecorator(){}
    public AbstractDecorator(GatewayComponent gatewayComponent){
        this.gatewayComponent=gatewayComponent;
    }
    @Override
    public void service() {
        gatewayComponent.service();
    }
}
