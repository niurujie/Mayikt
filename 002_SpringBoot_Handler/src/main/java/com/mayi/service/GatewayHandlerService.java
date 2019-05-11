package com.mayi.service;

import com.mayi.Handler.GatewayHandler;
import com.mayi.mapper.HandlerMapper;
import com.mayi.mapper.entity.GatewayHandlerEntity;
import com.mayi.utils.SpringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;

/**
 * Created by IDEA
 * User:wang7
 * Date:2019/5/9
 * Time:23:08
 */
@Service
public class GatewayHandlerService {

    @Autowired
    private HandlerMapper handlerMapper;

    private GatewayHandler firstGatewayHandler;



    public GatewayHandler getFirstGateWayHandler() {
        if (firstGatewayHandler != null) {
            return firstGatewayHandler;
        }
        // 1.获取第一个GatewayHandler信息
        GatewayHandlerEntity gatewayHandlerEntity = handlerMapper.getFirstGatewayHandler();
        if (gatewayHandlerEntity == null) {
            return null;
        }
        // 2.获取第一个firstGatewayHandler spring容器中的id
        String beanId = gatewayHandlerEntity.getHandlerId();
        // 3.从spring容器中获取对应的对象 firstGatewayHandler
        GatewayHandler firstGatewayHandler = SpringUtils.getBean(beanId, GatewayHandler.class);
        // 4.使用white循环 设置下一个节点 同时定义循环遍历临时对象
        GatewayHandler tempGatewayHandler = firstGatewayHandler;
        // 5.获取下一个节点
        String nextBeanId = gatewayHandlerEntity.getNextHandlerId();
        while (!StringUtils.isEmpty(nextBeanId)) {
            GatewayHandlerEntity nextGatewayHandlerEntity = handlerMapper.getByHandler(nextBeanId);
            if (nextGatewayHandlerEntity == null) {
                break;
            }
            //6.从springboot容器获取下一个handler 对象
            GatewayHandler nextgatewayHandler = SpringUtils.getBean(nextGatewayHandlerEntity.getHandlerId(), GatewayHandler.class);
            // 7.设置当前handler下一个handler对象
            tempGatewayHandler.setGatewayHandler(nextgatewayHandler);
            tempGatewayHandler=nextgatewayHandler;
        }
        this.firstGatewayHandler=firstGatewayHandler;
        return firstGatewayHandler;
    }
}
