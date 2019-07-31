package com.wmh.servlet.web.impl;

import com.wmh.servlet.DispatcherServlet;
import com.wmh.servlet.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * @author: create by wangmh
 * @projectName: Mayikt
 * @packageName: com.wmh.servlet.web.impl
 * @description:
 * @date: 2019/7/25
 **/
public class AbstractDispatcherServletInitializer extends WebApplicationInitializer {
    public void onStartup(ServletContext ctx)throws ServletException{
        ServletRegistration.Dynamic dynamic=ctx.addServlet("dispatcherServlet",new DispatcherServlet());
        dynamic.addMapping("/");//拦截所有请求
    }
}
