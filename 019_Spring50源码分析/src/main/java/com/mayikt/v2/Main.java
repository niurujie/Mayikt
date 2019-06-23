package com.mayikt.v2;

import com.mayikt.v1.entity.UserEntity;
import com.mayikt.v2.config.SpringConfig;
import com.mayikt.v2.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author: create by wangmh
 * @name: Main.java
 * @description:使用注解形式形式加载IOC
 * @date:2019/6/23
 **/
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserEntity userEntity = annotationConfigApplicationContext.getBean("userEntity", UserEntity.class);
        UserService userService = annotationConfigApplicationContext.getBean("userService", UserService.class);
        UserService userService1 = annotationConfigApplicationContext.getBean("userService", UserService.class);
        System.out.println(userService == userService1);
        System.out.println(userEntity.toString());
        System.out.println(userService.toString());

        //获取Spring中注入beanName
        String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
        for (int i = 0; i < beanDefinitionNames.length; i++) {
            System.out.println(beanDefinitionNames[i]);
        }
    }
}
