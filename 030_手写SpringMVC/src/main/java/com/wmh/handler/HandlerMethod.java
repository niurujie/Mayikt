package com.wmh.handler;

import java.lang.reflect.Method;

public class HandlerMethod {

    // 请求方法对应的bean对象
    private Object bean;
    private Method method;


    public HandlerMethod(Object bean, Method method) {
        this.bean = bean;
        this.method = method;
    }

    public Object getBean() {
        return bean;
    }

    public Method getMethod() {
        return method;
    }
}
