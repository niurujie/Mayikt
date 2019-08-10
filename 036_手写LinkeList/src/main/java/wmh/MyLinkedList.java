package wmh;

import java.util.LinkedList;

/**
 * @author: create by wangmh
 * @name: MyLinkedList.java
 * @description:
 * @date:2019/8/10
 **/
public class MyLinkedList<E> implements MyList<E> {

    transient int size = 0;

    transient MyLinkedList.Node<E> first;

    transient MyLinkedList.Node<E> last;

    public int size() {
        return size;
    }

    /***
     * 根据index删除元素
     * @param index
     * @return
     */
    public E remove(int index) {
        //判断下标是否越界
        checkElementIndex(index);
        return unlink(node(index));
    }

    E unlink(MyLinkedList.Node<E> x) {
        //获取当前节点的值
        final E element = x.item;
        //获取下一个节点
        final MyLinkedList.Node<E> next = x.next;
        //获取下一个节点
        final MyLinkedList.Node<E> prev = x.prev;
        //判断上一个节点是否为null
        if (prev == null) {
            //说明当前删除的节点是第一个元素，将下一个节点设为第一个节点
            first = next;
        } else {
            //不为null的话，将当前节点的上一个节点关联的下一个节点信息设置为当前节点的下一个节点
            prev.next = next;
            //（删除）将当前节点的prev回收
            x.prev = null;
        }

        //判断当前节点的下一个节点是否为空
        if (next == null) {
            //说明删除的节点是最后一个节点，将当前节点的上一个节点设置为最后一个节点
            last = prev;
        } else {
            //若下一个节点不为空，则将下一个节点关联的上一个节点信息设置为当前节点的上一个节点
            next.prev = prev;
            //（删除）回收当前节点的next
            x.next = null;
        }
        //（删除）回收当前节点值
        x.item = null;
        size--;
        return element;
    }

    /***
     * 添加方法
     * @param e
     */
    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    void linkLast(E e) {
        //获取当前节点的最后一个元素
        final MyLinkedList.Node<E> l = last;
        //设置新增的node节点元素
        final MyLinkedList.Node<E> newNode = new MyLinkedList.Node<>(l, e, null);
        //将当前节点设置为最后一个节点
        last = newNode;
        //判断最后一个节点是否为空
        if (l == null)
            //说明当前链表是第一次添加元素
            first = newNode;
        else
            //当前下一个节点为 新增的节点
            l.next = newNode;
        size++;
    }

    private static class Node<E> {
        //一个节点包含中包含了三个信息：关联的上一个节点信息（prev）、当前节点值（item）、关联的下一个节点信息（next）  即双向链表
        E item;
        MyLinkedList.Node<E> next;
        MyLinkedList.Node<E> prev;

        Node(MyLinkedList.Node<E> prev, E element, MyLinkedList.Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }


    /**
     * 根据index获取元素
     *
     * @param index
     * @return
     */
    public E get(int index) {
        checkElementIndex(index);
        return (E) node(index).item;
    }

    private void checkElementIndex(int index) {
        //判断元素是否越界
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException("你获取的下标元素已经越界！index=" + index);
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    MyLinkedList.Node<E> node(int index) {
        //如果当前元素小于size/2  即折半查找
        if (index < (size >> 1)) {
            //获取第一个节点
            MyLinkedList.Node<E> x = first;
            //从0开始查找，当前下标位置
            for (int i = 0; i < index; i++)
                //获取该元素
                x = x.next;
            return x;
        } else {
            //获取最后一个元素
            MyLinkedList.Node<E> x = last;
            //从最后一个元素开始查找当前位置的元素
            for (int i = size - 1; i > index; i--)
                //获取该元素
                x = x.prev;
            return x;
        }
    }


}

