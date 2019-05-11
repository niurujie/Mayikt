package com.mayi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by IDEA
 * User:wang7
 * Date:2019/5/8
 * Time:22:30
 */
@SpringBootApplication
@MapperScan("com.mayi.mapper")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
