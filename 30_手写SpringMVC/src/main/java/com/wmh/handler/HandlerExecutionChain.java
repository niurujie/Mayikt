package com.wmh.handler;

import com.wmh.view.ModelAndView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HandlerExecutionChain {
    HandlerMethod handlerMethod;
    // 拦截器

    public HandlerExecutionChain(HandlerMethod handlerMethod) {
        this.handlerMethod = handlerMethod;
    }

    public ModelAndView handler() throws InvocationTargetException, IllegalAccessException {
        // 1. 使用java的反射机制执行我们请求方法
        Method method = handlerMethod.getMethod();
        Object bean = handlerMethod.getBean();
        // 2.执行我们的请求的方法
        Object viewName = method.invoke(bean, null);
        ModelAndView modelAndView = new ModelAndView((String) viewName);
        System.out.println(modelAndView);
        return modelAndView;
    }
}