package com.wmh.handler;

import com.wmh.annotation.ComponentScan;
import com.wmh.config.SpringMvcConfig;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

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
        String springmvcPackage=declaredAnnotation.value();
        if (StringUtils.isEmpty(springmvcPackage)){
            return;
        }
        //2.使用Java反射机制获取，类上有加上controller注解

        //3.遍历每个类，查找类中的方法是否有加上RequestMapping注解



    }
}
