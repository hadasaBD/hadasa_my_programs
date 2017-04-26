package com.company;

/**
 * Created by eladlavi on 21/02/2017.
 */
public class ArrayStack<T> implements Stack<T> {

    private T[] arr;
    private int size;

    @Override
    public void push(T element) {
        if(arr.length == size){
            T[] temp = (T[]) new Object[arr.length*2];
            for (int i = 0; i < size; i++) {
                temp[i] = arr[i];
            }
            this.arr = temp;
        }
        arr[size++] = element;
    }

    @Override
    public T pop() {
        if(isEmpty())
            throw new IndexOutOfBoundsException("stack is empty");
        return arr[size--];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
