package com.mayikt.v2;

/**
 * @author: create by wangmh
 * @name: SingletonV1.java  懒汉模式
 * @description:  线程不安全
 * @date:2019/5/30
 **/
public class SingletonV2 {
    private static SingletonV2 singletonV2;

    /**
     * 将构造器私有化 禁止初始化
     */
    private SingletonV2() {
    }

    public static SingletonV2 getInstance() {
        if (singletonV2==null){
            singletonV2=new SingletonV2();
        }
        return singletonV2;
    }

    public static void main(String[] args) {
        SingletonV2 singletonV1= SingletonV2.getInstance();
        SingletonV2 singletonV2= SingletonV2.getInstance();
        System.out.println(singletonV1==singletonV2);
    }
}
