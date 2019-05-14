package com.mayi.service;

import com.mayi.GatewayComponent;

/**
 * @author: create by wangmh
 * @name: BasicComponentGateway.java  被装饰角色
 * @description:
 * @date:2019/5/14
 **/
public class BasicComponentGateway extends GatewayComponent {
    @Override
    public void service() {
        System.out.println("第一步>>>网关基本操作获取参数信息....");
    }
}
