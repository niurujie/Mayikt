package com.mayikt.ext;

import com.mayikt.inter.service.OrderService;
import com.mayikt.inter.service.impl.OrderServiceImpl;

/**
 * @author: Wangmh
 * @date: 2019/5/31 接口继承方式实现
 * @time: 17:18
 */
public class OrderServiceProxy extends OrderServiceImpl {
    public void order() {
        System.out.println("日志收集开始..");
        super.order();
        System.out.println("日志收集结束..");
    }

    public static void main(String[] args) {
        OrderService orderService = new OrderServiceProxy();
        orderService.order();
    }
}
