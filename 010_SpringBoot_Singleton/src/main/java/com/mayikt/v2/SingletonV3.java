package com.mayikt.v2;

/**
 * @author: create by wangmh
 * @name: SingletonV1.java  懒汉模式
 * @description: 线程安全
 * @date:2019/5/30
 **/
public class SingletonV3 {
    private static SingletonV3 singletonV2;

    /**
     * 将构造器私有化 禁止初始化
     */
    private SingletonV3() {
    }

    public synchronized static SingletonV3 getInstance() {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
        if (singletonV2 == null) {
            System.out.println("创建实例SingletonV3");
            singletonV2 = new SingletonV3();
        }
        System.out.println("获取SingletonV3实例");
        return singletonV2;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                public void run() {
                    SingletonV3 instance1 = SingletonV3.getInstance();
                    System.out.println(Thread.currentThread().getName() + "," + instance1);
                }
            }).start();
        }

    }
}
