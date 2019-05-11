package com.mayi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by IDEA
 * User:wang7
 * Date:2019/5/9
 * Time:23:27
 */
@SpringBootApplication
@MapperScan("com.mayi.mapper")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
