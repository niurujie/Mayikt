package com.mayikt.v2.entity;

/**
 * @author: create by wangmh
 * @name: UserEntity.java
 * @description:
 * @date:2019/6/23
 **/
public class UserEntity {

    private Integer userId;
    private String userName;

    public UserEntity(Integer userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
