package com.mayikt.entity;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: UserEntity 对应数据库的user表..
 * @description: 每特教育独创第五期互联网架构课程
 * @date 2019/6/420:49
 */
public class UserEntity {
    private Long id;
    private String name;
    private Integer age;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Long getId() {
        return id;
    }
}
