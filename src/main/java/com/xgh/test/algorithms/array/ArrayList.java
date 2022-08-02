package com.xgh.test.algorithms.array;

import java.util.Arrays;

public class ArrayList<E> implements List<E> {


    /**
     * 默认初始化空间
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 空元素
     */
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    /**
     * 元素数组缓冲区
     */
    transient Object[] elementData;

    /**
     * 元素个数
     */
    private int size;


    @Override
    public boolean add(E e) {
        //确保内部容量
        int minCapacity = size + 1;
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        //判断扩容
        if (minCapacity - elementData.length > 0) {
            int oldCapacity = elementData.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            if(newCapacity -minCapacity < 0){
                newCapacity = minCapacity;
            }
            elementData = Arrays.copyOf(elementData,newCapacity);
        }
        //添加原始
        elementData[size++] = e;
        return true;
    }

    @Override
    public E remove(int index) {
        E oldElement =(E) elementData[index];
        int numMoved = size - index - 1;
        if(numMoved > 0){
            System.arraycopy(elementData,index+1,elementData,index,numMoved);
        }
        elementData[--size] = null;
        return oldElement;
    }

    @Override
    public E get(int index) {
        return (E) elementData[index];
    }
}
