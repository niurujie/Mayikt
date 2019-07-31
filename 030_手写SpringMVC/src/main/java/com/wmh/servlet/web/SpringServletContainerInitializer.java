package com.wmh.servlet.web;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author: create by wangmh
 * @projectName: Mayikt
 * @packageName: com.wmh.servlet.web
 * @description:
 * @date: 2019/7/25
 **/
@HandlesTypes(WebApplicationInitializer.class)
public class SpringServletContainerInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        for (Class<?> classInfo : set) {
            try {
                //使用Java反射技术执行onStartup方法
                Object o = classInfo.newInstance();
                Method onStartup = classInfo.getMethod("onStartup", ServletContext.class);
                onStartup.invoke(o, servletContext);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
