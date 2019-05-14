package com.mayi.utils;

import com.mayi.service.BasicComponentGateway;
import com.mayi.GatewayComponent;
import com.mayi.service.impl.LimitDecorator;
import com.mayi.service.impl.LogDecorator;

/**
 * @author: create by wangmh
 * @name: FactoryGateway.java
 * @description:
 * @date:2019/5/14
 **/
public class FactoryGateway {

    public static GatewayComponent getGateway(){
        return new LimitDecorator(new LogDecorator(new BasicComponentGateway()));
    }

    public static void main(String[] args) {
        LimitDecorator limitDecorator=new LimitDecorator(new LogDecorator(new BasicComponentGateway()));
        limitDecorator.service();
    }
}
