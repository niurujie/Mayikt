package com.mayikt.v5;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author: create by wangmh
 * @name: SingletonV1.java  枚举形式
 * @description: 枚举能够先天性 防止反射和序列化破解单例
 * @date:2019/5/30
 **/
public enum SingletonV5 {
    INSTANCE;

    public void add(){
        System.out.println("add方法.....");
    }
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        SingletonV5 singletonV5=SingletonV5.INSTANCE;
        SingletonV5 singletonV6=SingletonV5.INSTANCE;
        singletonV5.add();
        singletonV6.add();
        System.out.println(singletonV5==singletonV6);

        /**
         * 反射测试
         */
        Constructor<SingletonV5> declaredConstructor = SingletonV5.class.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        SingletonV5 v3 = declaredConstructor.newInstance();
        System.out.println(v3==singletonV5);
    }
}
