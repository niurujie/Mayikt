package com.wmh;

import java.util.Arrays;

/**
 * @author: create by wangmh
 * @projectName: Mayikt
 * @packageName: com.wmh
 * @description:
 * @date: 2019/8/7
 **/
public class MyArrayList<E> implements MyList<E> {

    //ArrayList底层用于存储元素的，transient不可序列化
    transient Object[] elementData;
    //默认集合中数组元素为空对象
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    //数组长度大小
    private int size;

    //默认容量大小
    private static final int DEFAULT_CAPACITY = 10;

    protected transient int modCount = 0;

    //最大数组长度 Integer.MAX_VALUE - 8   2的31次方-1-8
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    public MyArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    /**
     * 获取数组长度大小
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 根据下标删除元素
     *
     * @param index
     * @return
     */
    public E remove(int index) {
        //判断是否越界
        rangeCheck(index);
        modCount++;
        //获取到当前的下标的元素
        E oldValue = elementData(index);
        // 获取一共要移动的次数 即删除某一元素，后面的元素依次向前移动
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index + 1, elementData, index,
                    numMoved);
        //给最后一个元素赋值null，即删除
        elementData[--size] = null;
        return oldValue;
    }

    /**
     * 添加元素
     *
     * @param e
     * @return
     */
    public boolean add(E e) {
        //进行初始化容量操作，以及对数组进行扩容
        ensureCapacityInternal(size + 1);
        //添加新的元素
        elementData[size++] = e;
        return true;
    }

    private void ensureCapacityInternal(int minCapacity) {
        //判断数组的容量是否为空
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            //如果为空的话，DEFAULT_CAPACITY（10）和minCapacity（size+1）比较，取最大值
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        ensureExplicitCapacity(minCapacity);
    }

    private void ensureExplicitCapacity(int minCapacity) {
        modCount++;
        //若minCapacity-数组容量>0的话，说明需要扩容了
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
    }

    private void grow(int minCapacity) {
        //获取到原来数组长度
        int oldCapacity = elementData.length;
        //得到新的数组长度=旧的数组长度+旧的数组长度/2   即旧数组长度的150%
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        //如果新的数组长度-最小容量长度<0的话，新的数组长度就等于最小容量长度
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        //如果新的数组长度-(Integer.MAX_VALUE-8)>0的话  调用hugeCapacity方法 获取新容量大小    Integer.MAX_VALUE-8  2的31次方-1-8
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        // 使用Arrays.copyOf()给我们的数据进行扩容elementData 将新的数组长度赋值给你elementData的容量
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        //判断最小容量如果大于Integer.MAX_VALUE-8的话 返回Integer.MAX_VALUE 反之返回Integer.MAX_VALUE-8
        //说明数组长度最大能容量Integer.MAX_VALUE个元素
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }

    /**
     * 根据下标获取元素
     *
     * @param index
     * @return
     */
    public E get(int index) {
        //判断数组下标是否越界
        rangeCheck(index);
        //获取元素
        return elementData(index);
    }

    /**
     * 判断数组下标是否越界
     *
     * @param index
     */
    private void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException("越界！！！" + index);
    }

    /**
     * 获取元素
     *
     * @param index
     * @return
     */
    E elementData(int index) {
        return (E) elementData[index];
    }
}
