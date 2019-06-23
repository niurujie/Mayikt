package com.mayikt.v2.config;

import com.mayikt.v1.entity.UserEntity;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * @author: create by wangmh
 * @name: SpringConfig.java
 * @description:
 * @date:2019/6/23
 **/
@Configuration  //@Configuration 等同于配置的spring配置文件
//@ComponentScan(value = "com.mayikt.v2.service", excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class)}, useDefaultFilters = false)
@ComponentScan("com.mayikt.v2.service")
public class SpringConfig {

    @Bean
    public UserEntity userEntity() {
        return new UserEntity(11, "王明辉");
    }
}
