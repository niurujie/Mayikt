package com.wmh;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: create by wangmh
 * @projectName: Mayikt
 * @packageName: com.wmh
 * @description:
 * @date: 2019/8/7
 **/
public class Main {
    public static void main(String[] args) {
        MyList<String> myList=new MyArrayList<String>();
        myList.add("第一个元素");
        myList.add("第二个元素");
        myList.add("第三个元素");
        myList.add("第四个元素");
        for (int i=0;i<myList.size();i++){
            System.out.println("第"+(i+1)+"个元素："+myList.get(i));
        }
        System.out.println("---------测试删除元素---------");
        myList.remove(1);
        for (int i=0;i<myList.size();i++){
            System.out.println("第"+(i+1)+"个元素："+myList.get(i));
        }
    }
}
