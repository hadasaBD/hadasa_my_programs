package com.company;

/**
 * Created by eladlavi on 20/02/2017.
 */
public class MinHeap {
    Comparable[] arr;
    int size;

    public MinHeap(){
        arr = new Comparable[10];
        size = 0;
    }

    public MinHeap(Comparable[] arr){
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
        if(l<size && arr[l].compareTo(arr[smallest]) < 0)
            smallest = l;
        if(r<size && arr[r].compareTo(arr[smallest]) < 0)
            smallest = r;
        if(smallest != i){
            Comparable temp = arr[i];
            arr[i] = arr[smallest];
            arr[smallest] = temp;
            heapify(smallest);
        }
    }

    private void bubbleUp(int i){
        int p;
        while(i != 0 && arr[(p=parent(i))].compareTo(arr[i]) > 0){
            Comparable temp = arr[i];
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


    public void insert(Comparable x){
        if(size == arr.length){
            Comparable[] temp = new Comparable[size * 2];
            for (int i = 0; i < size; i++) {
                temp[i] = arr[i];
            }
            arr = temp;
        }
        //arr[size++] = x;
        int i = size;
        size++;
        arr[i] = x;
        bubbleUp(i);
    }

    public Comparable getMin(){
        if(size == 0)
            throw new IndexOutOfBoundsException();
        Comparable result = arr[0];
        return result;
    }

    public Comparable extractMin(){
        if(size == 0)
            throw new IndexOutOfBoundsException();
        if(size == 1){
            size--;
            return arr[0];
        }
        Comparable min = arr[0];
        arr[0] = arr[size-1];
        size--;
        heapify(0);
        return min;
    }

    public int getSize(){
        return size;
    }


    public static boolean isValidMinHeap(Comparable[] arr, int i, int size){
        int left = 2*i + 1;
        int right = 2*i + 2;
        if(left > size)
            return true;
        else {
            if(arr[i].compareTo(arr[left]) > 0)
                return false;
        }
        if(right <= size){
            if(arr[i].compareTo(arr[right]) > 0)
                return false;
        }

        return isValidMinHeap(arr, left, size) &&
                isValidMinHeap(arr, right, size);
    }
}
