package com.wmh.annotation;

import java.lang.annotation.*;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: RequestMapping
 * @description: 每特教育独创第五期互联网架构课程
 * @date 2019/7/2315:09
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMapping {
    String value() default "";
}
