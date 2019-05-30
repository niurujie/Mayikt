package com.mayikt.v4;

/**
 * @author: create by wangmh
 * @name: SingletonV1.java  静态内部内形式  基于饿汉模式
 * @description:
 * @date:2019/5/30
 **/
public class SingletonV4 {

    public SingletonV4() {
        System.out.println("对象初始化.....");
    }

    public static SingletonV4 getInstance(){
        return SingletonUtil.singletonV4;
    }
    public static class SingletonUtil{
        private static SingletonV4 singletonV4=new SingletonV4();
    }

    public static void main(String[] args) {
        SingletonV4 singletonV4=SingletonV4.getInstance();
        SingletonV4 singletonV5=SingletonV4.getInstance();
        System.out.println(singletonV4==singletonV5);
    }
}
