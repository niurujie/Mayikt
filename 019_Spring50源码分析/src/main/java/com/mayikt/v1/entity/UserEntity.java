package com.mayikt.v1.entity;

/**
 * @author: create by wangmh
 * @name: UserEntity.java
 * @description:
 * @date:2019/6/23
 **/
public class UserEntity {
    private int userId;
    private String userName;

    public UserEntity(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public UserEntity() {
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }
}
