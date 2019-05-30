package com.mayikt.v6;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: create by wangmh
 * @name: SingletonV1.java  使用容器管理
 * @description: 将多种单例类统一管理，在使用时根据key获取对象对应类型的对象。
 *               这种方式使得我们可以管理多种类型的单例，并且在使用时可以通过统一的接口进行获取操作，
 *               降低了用户的使用成本，也对用户隐藏了具体实现，降低了耦合度。
 * @date:2019/5/30
 **/
public class SingletonV5 {
    private static Map<String, Object> map = new HashMap<String, Object>();

    public static void registerService(String key, Object instance) {
        if (!map.containsKey(key)) {
            map.put(key, instance);
        }
    }

    public static Object getService(String key) {
        return map.get(key);
    }

    public static void main(String[] args) {
    }
}
