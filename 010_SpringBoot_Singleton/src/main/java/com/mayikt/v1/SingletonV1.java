package com.mayikt.v1;

/**
 * @author: create by wangmh
 * @name: SingletonV1.java  饿汉模式
 * @description: 优点：先天性线程是安全的，当类初始化时，就创建对象   缺点：如果饿汉模式使用过多，可能会影响项目的启动效率
 * @date:2019/5/30
 **/
public class SingletonV1 {
    private static SingletonV1 singletonV1 = new SingletonV1();

    /**
     * 将构造器私有化 禁止初始化
     */
    private SingletonV1() {
    }

    public static SingletonV1 getInstance() {
        return singletonV1;
    }

    public static void main(String[] args) {
        SingletonV1 singletonV1=SingletonV1.getInstance();
        SingletonV1 singletonV2=SingletonV1.getInstance();
        System.out.println(singletonV1==singletonV2);
    }
}
