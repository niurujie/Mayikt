package com.mayi.comtroller;

import com.mayi.Handler.GatewayHandler;
import com.mayi.service.GatewayHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IDEA
 * User:wang7
 * Date:2019/5/9
 * Time:23:24
 */
@RestController
public class HandlerController {

    @Autowired
    private GatewayHandlerService gatewayHandlerService;

    @RequestMapping("/client")
    public String client(){
        GatewayHandler gatewayHandler=gatewayHandlerService.getFirstGateWayHandler();
        gatewayHandler.service();
        return "success";
    }
}
