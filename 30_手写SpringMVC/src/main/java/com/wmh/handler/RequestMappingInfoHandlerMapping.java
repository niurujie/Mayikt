package com.wmh.handler;

import com.wmh.annotation.ComponentScan;
import com.wmh.annotation.Controller;
import com.wmh.annotation.RequestMapping;
import com.wmh.config.SpringMvcConfig;
import com.wmh.util.ReflexUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author: create by wangmh
 * @projectName: Mayikt
 * @packageName: com.wmh.handler
 * @description:
 * @date: 2019/7/24
 **/
public class RequestMappingInfoHandlerMapping {
    private final Map<String, HandlerMethod> registryMapping = new HashMap<String, HandlerMethod>();

    public void registryMapping() {
        //1.获取SpringMVConfig的ComponentScan扫包范围
        ComponentScan declaredAnnotation = SpringMvcConfig.class.getDeclaredAnnotation(ComponentScan.class);
        if (declaredAnnotation == null) {
            return;
        }
        //2.获取ComponentScan中value
        String springmvcPackage = declaredAnnotation.value();
        if (StringUtils.isEmpty(springmvcPackage)) {
            return;
        }
        //3.使用Java反射机制获取扫包范围中的类
        Set<Class<?>> classes = ReflexUtils.getClasses(springmvcPackage);
        //4.遍历每个类，查找类中的方法是否有加上Controller注解
        classes.forEach(item -> {
            Controller controller = item.getDeclaredAnnotation(Controller.class);
            if (controller == null) {
                return;
            }
            //5.获取类中的所有方法
            Method[] declaredMethods = item.getDeclaredMethods();
            for (Method method : declaredMethods) {
                //6.遍历方法，查找方法上是否加上RequestMapping注解
                RequestMapping requestMapping = method.getDeclaredAnnotation(RequestMapping.class);
                if (requestMapping != null) {
                    //7.获取requestMapping中的value
                    String url = requestMapping.value();
                    //8.添加到registryMapping中
                    registryMapping.put(url, new HandlerMethod(newInstance(item), method));
                }
            }
        });
    }

    public HandlerMethod getHandler(String url) {
        return registryMapping.get(url);
    }

    private Object newInstance(Class classInfo) {
        try {
            Object value = classInfo.newInstance();
            return value;
        } catch (Exception e) {
            return null;
        }
    }
}
