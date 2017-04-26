package com.company;

/**
 * Created by eladlavi on 22/02/2017.
 */
public class Queue {
    int[] arr;
    int front, rear, size;

    public Queue() {
        arr = new int[5];
        front = 0;
        rear = arr.length-1;
        size = 0;
    }


    public int size(){
        if(rear > front)
            return rear - front + 1;
        else
            return arr.length - front + rear + 1;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public boolean isFull(){
        return size == arr.length;//front == rear
    }

    public void insert(int x){
        if(isFull())
            throw new IndexOutOfBoundsException();
        rear = (rear + 1) % arr.length;
        arr[rear] = x;
        size++;
    }

    public int pop(){
        if(isEmpty())
            throw new IndexOutOfBoundsException();
        int x = arr[front];
        front = (front + 1)%arr.length;
        size--;
        return x;
    }

    public int front(){
        if(isEmpty())
            throw new IndexOutOfBoundsException();
        return arr[front];
    }

    public int rear(){
        if(isEmpty())
            throw new IndexOutOfBoundsException();
        return arr[rear];
    }


}
