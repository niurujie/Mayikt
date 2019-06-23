package com.mayikt.v1;

import com.mayikt.v1.entity.UserEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: create by wangmh
 * @name: Main.java
 * @description:使用xml形式形式加载IOC
 * @date:2019/6/23
 **/
public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserEntity userEntity = applicationContext.getBean("userEntity", UserEntity.class);
        System.out.println(userEntity.toString());
    }
}
