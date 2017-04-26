package com.company;

/**
 * Created by eladlavi on 15/02/2017.
 */
public class MaxHeap extends Heap {
    public MaxHeap() {
        super(true);
    }

    public MaxHeap(int[] arr){
        super(arr, true);
    }

    public int getMax(){
        return getTop();
    }

    public int extractMax(){
        return extractTop();
    }
}
