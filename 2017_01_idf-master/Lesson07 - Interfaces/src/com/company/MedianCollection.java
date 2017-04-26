package com.company;

/**
 * Created by eladlavi on 15/02/2017.
 */
public class MedianCollection {

    private MinHeap minHeap;
    private MaxHeap maxHeap;

    public MedianCollection(){
        minHeap = new MinHeap();
        maxHeap = new MaxHeap();
    }

    public int getMedian(){
        if(minHeap.getSize() == 0)
            throw new IndexOutOfBoundsException("collection is empty, how the hack can it has a median?");
        return maxHeap.getMax();
    }

    public void insert(int num){
        maxHeap.insert(num);

        if(maxHeap.getMax() > minHeap.getMin()){
            minHeap.insert(maxHeap.extractMax());
            maxHeap.insert(minHeap.extractMin());
        }
        if(maxHeap.getSize() - minHeap.getSize() == 2)
            minHeap.insert(maxHeap.extractMax());

    }

}
