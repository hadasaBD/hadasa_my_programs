package com.company;

/**
 * Created by eladlavi on 07/03/2017.
 */
public class FindMaxThread extends Thread {

    private int[] arr;
    private int from, to;
    private MaxFoundListener listener;


    public FindMaxThread(int[] arr, int from, int to, MaxFoundListener listener) {
        this.arr = arr;
        this.from = from;
        this.to = to;
        this.listener = listener;
    }

    @Override
    public void run() {
        int max = Integer.MIN_VALUE;
        for (int i = from; i < to; i++) {
            if(arr[i] > max)
                max = arr[i];
        }
        if(listener != null)
            listener.maxFound(max);
    }

    interface MaxFoundListener{
        void maxFound(int max);
    }
}
