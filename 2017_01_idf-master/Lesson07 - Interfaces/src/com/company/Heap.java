package com.company;

import java.security.InvalidParameterException;

/**
 * Created by eladlavi on 15/02/2017.
 */
public abstract class Heap {
    private int[] arr;
    private int size;
    private boolean isMaxHeap;
    //if it isn't a max heap then it is a min heap

    public Heap(boolean isMaxHeap){
        arr = new int[10];
        size = 0;
        this.isMaxHeap = isMaxHeap;
    }

    public Heap(int[] arr, boolean isMaxHeap){
        this.isMaxHeap = isMaxHeap;
        this.arr = arr;
        size = arr.length;
        for (int i = size/2; i >= 0; i--) {
            heapify(i);
        }

    }

    private boolean compare(int x, int y){
        if(isMaxHeap)
            return x > y;
        else
            return x < y;
    }

    private void heapify(int i){
        int largest = i;
        int l = leftChild(i);
        int r = rightChild(i);
        if(l<size && compare(arr[l],arr[largest]))
            largest = l;
        if(r<size && compare(arr[r], arr[largest]))
            largest = r;
        if(largest != i){
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            heapify(largest);
        }
    }

    private void bubbleUp(int i){
        int p;
        while(i != 0 && compare(arr[i], arr[(p=parent(i))])){
            int temp = arr[i];
            arr[i] = arr[p];
            arr[p] = temp;
            i = p;
        }
    }


    private int leftChild(int i){
        return 2*i + 1;
    }

    private int rightChild(int i){
        return 2*i + 2;
    }

    private int parent(int i){
        return (i-1)/2;
    }


    public void increase(int i, int addition){
        if(addition < 0 || i >= size || i < 0)
            throw new InvalidParameterException();
        arr[i] += addition;
        bubbleUp(i);
    }


    public void delete(int i){
        increase(i, isMaxHeap ? Integer.MAX_VALUE - arr[i] : Integer.MIN_VALUE - arr[i]);
        extractTop();
    }


    public void insert(int x){
        if(size == arr.length){
            int[] temp = new int[size * 2];
            for (int i = 0; i < size; i++) {
                temp[i] = arr[i];
            }
            arr = temp;
        }
        int i = size;
        size++;
        arr[i] = x;
        bubbleUp(i);
    }

    public int getTop(){
        if(size == 0)
            throw new IndexOutOfBoundsException();

        int result = arr[0];
        return result;
    }


    public int extractTop(){
        if(size == 0)
            throw new IndexOutOfBoundsException();

        if(size == 1){
            size--;
            return arr[0];
        }
        int max = arr[0];
        arr[0] = arr[size-1];
        size--;
        heapify(0);
        return max;
    }

    public int getSize(){
        return size;
    }
}
