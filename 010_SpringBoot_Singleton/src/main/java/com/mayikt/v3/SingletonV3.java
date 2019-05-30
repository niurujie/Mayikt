package com.mayikt.v3;

/**
 * @author: create by wangmh
 * @name: SingletonV1.java  双重检验锁(DCL)  基于懒汉模式
 * @description:
 * @date:2019/5/30
 **/
public class SingletonV3 {
    /**
     * volatile 静止重排序 提高可见性
     */
    private volatile static SingletonV3 singletonV3;

    /**
     * 将构造器私有化 禁止初始化
     */
    private SingletonV3() {
    }

    public static SingletonV3 getInstance() {
        if (singletonV3 == null) {//第一次判断如果没有创建对象，就开始上锁
            synchronized (SingletonV3.class) {
                if (singletonV3 == null) {//当用户拿到锁的时候，判断初始化
                    try {
                        Thread.sleep(3000);
                    } catch (Exception e) {
                    }
                    singletonV3 = new SingletonV3();
                }
            }
        }
        return singletonV3;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                public void run() {
                    SingletonV3 singletonV3 = SingletonV3.getInstance();
                    System.out.println("Thread.currentThread().getName() + \",\" + instance1");
                }
            }).start();
        }
    }
}
