package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        int[] arr = new int[300000000];
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt();
        }

        int realMax = Integer.MIN_VALUE;
        long before = System.nanoTime();
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] > realMax)
                realMax = arr[i];
        }
        long after = System.nanoTime();
        long elapsed = after - before;
        System.out.println("elapsed: " + elapsed);
        System.out.println(realMax);



        FindMaxThread.MaxFoundListener listener = new FindMaxThread.MaxFoundListener(){

            boolean firstResponse = true;
            int maxFromFirstResponse;

            @Override
            public void maxFound(int max) {
                if(firstResponse){
                    firstResponse = false;
                    maxFromFirstResponse = max;
                }else{
                    /*
                    if(max > maxFromFirstResponse)
                        System.out.println("max is : " + max);
                    else
                        System.out.println("max is : " + maxFromFirstResponse);
                    */
                }
            }
        };
        int middle = (arr.length-1)/2;
        FindMaxThread findMaxThread1 = new FindMaxThread(arr, 0, middle, listener);
        FindMaxThread findMaxThread2 = new FindMaxThread(arr, middle, arr.length, listener);
        before = System.nanoTime();
        findMaxThread1.start();
        findMaxThread2.start();

        try {
            findMaxThread1.join();
            findMaxThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        after = System.nanoTime();
        elapsed = after - before;
        System.out.println("threads elapsed: " + elapsed);
    }
}
