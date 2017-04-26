package com.company;

import java.security.InvalidParameterException;

/**
 * Created by eladlavi on 06/03/2017.
 */
public class MaxHeap {
    int[] arr;
    int size;

    public MaxHeap(){
        arr = new int[10];
        size = 0;
    }

    public MaxHeap(int[] arr){
        this.arr = arr;
        size = arr.length;
        for (int i = size/2; i >= 0; i--) {
            heapify(i);
        }
    }

    private void heapify(int i){
        int smallest = i;
        int l = leftChild(i);
        int r = rightChild(i);
        if(l<size && arr[l]<arr[smallest])
            smallest = l;
        if(r<size && arr[r]<arr[smallest])
            smallest = r;
        if(smallest != i){
            int temp = arr[i];
            arr[i] = arr[smallest];
            arr[smallest] = temp;
            heapify(smallest);
        }
    }

    private void bubbleUp(int i){
        int p;
        while(i != 0 && arr[(p=parent(i))]<arr[i]){
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


    public void insert(int x){
        if(size == arr.length){
            /*int[] temp = new int[size * 2];
            for (int i = 0; i < size; i++) {
                temp[i] = arr[i];
            }
            arr = temp;*/
            throw new IndexOutOfBoundsException("no room");
        }
        //arr[size++] = x;
        int i = size;
        size++;
        arr[i] = x;
        bubbleUp(i);
    }

    public int getMax(){
        if(size == 0)
            throw new IndexOutOfBoundsException();
        int result = arr[0];
        return result;
    }

    public int extractMax(){
        if(size == 0)
            throw new IndexOutOfBoundsException();
        if(size == 1){
            size--;
            return arr[0];
        }
        int min = arr[0];
        arr[0] = arr[size-1];
        size--;
        heapify(0);
        return min;
    }

    public int[] getArr() {
        return arr;
    }

    public int getSize(){
        return size;
    }
}
