package com.wmh;

import java.util.Collection;

/**
 * @author: create by wangmh
 * @projectName: Mayikt  手写常用api
 * @packageName: com.wmh
 * @description:
 * @date: 2019/8/7
 **/
public interface MyList<E> {
    int size();
    public E remove(int index);
    boolean add(E e);
    E get(int index);
}
