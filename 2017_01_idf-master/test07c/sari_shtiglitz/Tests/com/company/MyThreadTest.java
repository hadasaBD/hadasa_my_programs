package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by hackeru on 4/19/2017.
 */
class MyThreadTest {


    @Test
    void MyRun1() {

        String word = "to be so";
        byte[] arr = word.getBytes();
        int key = 4;
        MyThread.Listener listener = new MyThread.Listener() {
            @Override
            public void found(int key, String text) {
                if (key != 4)
                    Assertions.fail("the key not valid");
                if (text != word)
                    Assertions.fail("the text incorrect");
            }
        };
        Set<String> listOfWords2 = new HashSet<>();
        listOfWords2.add("so");
        listOfWords2.add("to");
        listOfWords2.add("banana");

        for (int i = 0; i < arr.length; i++) {
            arr[i] += key;
        }
        MyThread myThread1 = new MyThread(arr, listOfWords2, 0, 128, listener);
        MyThread myThread2 = new MyThread(arr, listOfWords2, 129, 256, listener);


        myThread1.run();
        myThread2.run();


    }

    @Test
    void MyRun2() {
        int key = 0;
        for (int j = 0; j < 256; j++) {
            key = j;
            temp(key);

        }
    }


    void temp(int key1) {

        String word = "to be so";
        byte[] arr = word.getBytes();
        MyThread.Listener listener = new MyThread.Listener() {
            @Override
            public void found(int key2, String text) {
                if (key2 != key1)
                    Assertions.fail("the key not valid");
            }
        };
        Set<String> listOfWords2 = new HashSet<>();
        listOfWords2.add("so");
        listOfWords2.add("to");
        listOfWords2.add("be");

        for (int i = 0; i < arr.length; i++) {
            arr[i] += key1;
        }
        MyThread myThread1 = new MyThread(arr, listOfWords2, 0, 128, listener);
        MyThread myThread2 = new MyThread(arr, listOfWords2, 129, 256, listener);

        myThread1.run();
        myThread2.run();
        try {
            myThread1.join();
            myThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}






