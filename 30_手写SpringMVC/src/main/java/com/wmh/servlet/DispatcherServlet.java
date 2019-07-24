package com.wmh.servlet;

import com.wmh.handler.RequestMappingInfoHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: create by wangmh
 * @projectName: Mayikt
 * @packageName: com.wmh.servlet
 * @description:
 * @date: 2019/7/24
 **/
public class DispatcherServlet extends FrameworkServlet {
    RequestMappingInfoHandlerMapping requestMappingInfoHandlerMapping;

    public DispatcherServlet() {
        requestMappingInfoHandlerMapping = new RequestMappingInfoHandlerMapping();
    }

    @Override
    protected void onRefresh() {
        initStrategies();
    }

    private void initStrategies() {
        initHandlerMappings();
    }

    private void initHandlerMappings() {
        System.out.println("<<<<初始化initHandlerMappings>>>>");
        requestMappingInfoHandlerMapping.registryMapping();
    }

    @Override
    protected void doService(HttpServletRequest req, HttpServletResponse resp) {
    }
}
