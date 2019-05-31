package com.mayikt.inter.proxy;

import com.mayikt.inter.service.OrderService;

/**
 * @author: Wangmh
 * @date: 2019/5/31
 * @time: 17:14
 */
public class OrderServiceProxy implements OrderService {

    /**
     * 代理对象
     */
    private OrderService proxiedOrderService;

    public OrderServiceProxy(OrderService orderService) {
        this.proxiedOrderService = orderService;
    }

    public void order() {
        System.out.println("日志收集开始..");
        proxiedOrderService.order();
        System.out.println("日志收集结束..");
    }
}
